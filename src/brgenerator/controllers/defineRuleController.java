package brgenerator.controllers;

import brgenerator.model.*;
import brgenerator.services.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class defineRuleController implements Initializable {
    public TextField litValue;
    private TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();
    private BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
    private ColumnService columnService = ServiceProvider.getColumnService();
    private TableService tableService = ServiceProvider.getTableService();
    private AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();
    private AttributeCompareService attributeCompareService = ServiceProvider.getAttributeCompareService();
    public List<Table> tablesList = targetDBSerivce.findAll();
    public List<String> ruleTypesList = businessRuleService.getAllRuleTypes();


    public TextField ruleName;
    public TextField minValue;
    public TextField maxValue;
    public Button defineRule;
    public ComboBox targetColumns;
    public ComboBox targetTables;
    public ComboBox operator;
    public ComboBox ruleType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Table t : tablesList){
            targetTables.getItems().add(t.getName());
            }

        String acString = "AttributeCompare";
        String arString = "AttributeRange";
        ruleType.getItems().add(acString);
        ruleType.getItems().add(arString);


        //ATTRIBUTE RANGE OPERATORS
        //TODO: IF TYPE == ATTRIBUTERANGE OPERATORS.GETITEMS.ADD ETC.
        String opBetween = "Between";
        String opNotBetween = "NotBetween";

        //ATTRIBUTE COMPARE OPERATORS
        //TODO: IF TYPE == ATTRIBUTECOMPARE OPERATORS.GETITEMS.ADD ETC.
        String opGreaterThan = ">";
        String opSmallerThan = "<";
        String opEquals = "=";
        String opNotEquals = "!=";

        operator.getItems().add(opBetween);
        operator.getItems().add(opNotBetween);

        //AC
        operator.getItems().add(opGreaterThan);
        operator.getItems().add(opSmallerThan);
        operator.getItems().add(opEquals);
        operator.getItems().add(opNotEquals);

        targetTables.valueProperty().addListener(((observableValue, o, t1) -> {
            if(t1 == null){
                targetTables.getItems().clear();
                targetTables.setDisable(true);
            }else {
                List<Column> columns = targetDBSerivce.findColumnsByTable(t1.toString());
                for(Column c : columns){
                    targetColumns.getItems().add(c.getName());

                }
                targetColumns.setDisable(false);
            }
        }));
    }
    public void defineAR() throws SQLException {
        //CREATING THE TABLE FOR TOOL DATABASE
        Table t = tableService.create(targetTables.getValue().toString());
        String targetColumnValue = targetColumns.getValue().toString();
        int tableid = t.getId();
        //System.out.println(tableid);
        //CREATING THE COLUMN CORRELATED TO THE CHOSEN TABLE
        Column c = columnService.createColumn(targetColumnValue,ruleType.getValue().toString(),tableid);

        //CREATING NEW ATTRIBUTERANGE RULE FUNCTIE!!! DIE ATTRIBUTERANGE TERUGGEEFT
        String ruletext = ruleName.getText();
        String op = operator.getValue().toString();
        int mnval = Integer.parseInt(minValue.getText());
        int mxval = Integer.parseInt(maxValue.getText());
        AttributeRange ar = attributeRangeService.createAR(ruletext, op, mnval, mxval);
        //FINALIZE CREATING BUSINESS RULE
        int columnid = c.getId();
        int ruleid = ar.getId();
        //System.out.println(columnid);
        //System.out.println(ruleid);
        businessRuleService.createARBusinessRule(ruletext, columnid, ruleid);
    }
    public void defineAC() throws SQLException{
        //CREATING THE TABLE FOR TOOL DATABASE
        Table t = tableService.create(targetTables.getValue().toString());
        String targetColumnValue = targetColumns.getValue().toString();
        int tableid = t.getId();
        //System.out.println(tableid);
        //CREATING THE COLUMN CORRELATED TO THE CHOSEN TABLE
        Column c = columnService.createColumn(targetColumnValue,ruleType.getValue().toString(),tableid);

        //CREATING NEW ATTRIBUECOMPARE RULE FUNCTIE!!! DIE ATTRIBUTECOMPARE TERUGGEEFT
        String ruletext = ruleName.getText();
        String op = operator.getValue().toString();
        int litval = Integer.parseInt(litValue.getText());
        AttributeCompare ac = attributeCompareService.createAC(ruletext, op, litval);
        //FINALIZE CREATING BUSINESS RULE
        int columnid = c.getId();
        int ruleid = ac.getId();

        businessRuleService.createACBusinessRule(ruletext, columnid, ruleid);
    }

    public void defineRule(ActionEvent actionEvent) throws IOException, SQLException {
        String ruletext = ruleType.getValue().toString();
            if (ruletext == "AttributeRange") {
                defineAR();
            } else if (ruletext == "AttributeCompare") {
                defineAC();
            }






        Parent part = FXMLLoader.load(getClass().getResource("/brgenerator/userinterface/startScherm.fxml"));
        Stage currentStage = (Stage) defineRule.getScene().getWindow();
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        currentStage.close();
        stage.show();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Buinsess Rule aangemaakt", ButtonType.OK);
        alert.show();

        }

    }
