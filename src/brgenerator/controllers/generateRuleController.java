package brgenerator.controllers;

import brgenerator.generate.AttributeRangeGenerator;
import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;
import brgenerator.model.Table;
import brgenerator.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class generateRuleController implements Initializable {
    public Text idLabel;
    public Text nameLabel;
    public Text ruleTypeLabel;
    public Text operatorLabel;
    public Text minvalLabel;
    public Text targetTableLabel;
    public Text targetColumnLabel;
    public Text maxvalLabel;
    public Button generateRule;
    public Text litvalLabel;


    public void generateRule(ActionEvent actionEvent) throws SQLException {
        BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
        TableService tableService = ServiceProvider.getTableService();
        AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();
        ColumnService columnService = ServiceProvider.getColumnService();
        TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();

        // Step 1
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        BusinessRule br = (BusinessRule) stage.getUserData();
        // Step 3

        BusinessRule businessRule = businessRuleService.findById(br.getId());
        AttributeRange ar = attributeRangeService.findById(businessRule.getBrtid());
        Column c = columnService.findById(businessRule.getTcid());
        Table t = tableService.findById(c.getTableid());

        idLabel.setText(String.valueOf(br.getId()));
        ruleTypeLabel.setText(br.getType());
        operatorLabel.setText(br.getOperator());
        nameLabel.setText(br.getName());

        minvalLabel.setText(String.valueOf(ar.getMinvalue()));
        maxvalLabel.setText(String.valueOf(ar.getMaxvalue()));

        targetColumnLabel.setText(c.getName());
        targetTableLabel.setText(t.getName());

        AttributeRangeGenerator atrg = new AttributeRangeGenerator();
        String name = nameLabel.getText();
        String tc = targetColumnLabel.getText();
        String tt = targetTableLabel.getText();
        String operator = operatorLabel.getText();
        int minVal = Integer.parseInt(minvalLabel.getText());
        int maxVal = Integer.parseInt(maxvalLabel.getText());

        String script = atrg.generateRule(name,tc,tt,operator,minVal,maxVal);
        targetDBSerivce.executeRule(script);

    }
    public void GenerateRule(String name, String tc, String tt, String operator, int minVal, int maxVal){
        //Ophalen PSQL template via toString methode in PSQL class
        //Dan kwestie van variabelen erin gooien
        //Dan de TargetDatabaseDao oproepen en uitvoeren die hap




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
