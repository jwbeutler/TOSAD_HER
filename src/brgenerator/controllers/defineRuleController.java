package brgenerator.controllers;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;
import brgenerator.model.Table;
import brgenerator.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class defineRuleController implements Initializable {
    private TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();
    private BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
    private ColumnService columnService = ServiceProvider.getColumnService();
    private TableService tableService = ServiceProvider.getTableService();
    private AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();

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
        String arString = "AttributeRange";
        ruleType.getItems().add(arString);

        String opBetween = "Between";
        String opNotBetween = "NotBetwen";
        operator.getItems().add(opBetween);
        operator.getItems().add(opNotBetween);

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

    public void defineRule(ActionEvent actionEvent) {
        //CREATING THE TABLE FOR TOOL DATABASE
        Table t = tableService.create(targetTables.getValue().toString());
        String targetColumnValue = targetColumns.getValue().toString();
        int tableid = t.getId();

        //CREATING THE COLUMN CORRELATED TO THE CHOSEN TABLE
        Column c = columnService.createColumn(targetColumnValue,ruleType.getValue().toString(),tableid);

        //REATING NEW ATTRIBUTERANGE RULE
        String ruletext = ruleName.getText();
        String op = operator.getValue().toString();
        int mnval = Integer.parseInt(minValue.getText());
        int mxval = Integer.parseInt(maxValue.getText());
        AttributeRange ar = attributeRangeService.createAR(ruletext,op,mnval,mxval);

        //FINALIZE CREATING BUSINESS RULE
        int columnid = c.getId();
        int ruleid = ar.getId();
        BusinessRule br = businessRuleService.createARBusinessRule(ruletext,columnid,ruleid);
    }
}
