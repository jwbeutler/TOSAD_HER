package brgenerator.controllers;

import brgenerator.generate.AttributeCompareGenerator;
import brgenerator.generate.AttributeRangeGenerator;
import brgenerator.model.*;
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
        AttributeCompareService attributeCompareService = ServiceProvider.getAttributeCompareService();
        ColumnService columnService = ServiceProvider.getColumnService();
        TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();

        // Step 1
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        BusinessRule br = (BusinessRule) stage.getUserData();
        // Step 3

        BusinessRule businessRule = businessRuleService.findById(br.getId());

        //Invullen Standaard Table en Column waarden
        Column c = columnService.findById(businessRule.getTcid());
        Table t = tableService.findById(c.getTableid());
        idLabel.setText(String.valueOf(br.getId()));
        ruleTypeLabel.setText(br.getType());
        operatorLabel.setText(br.getOperator());
        nameLabel.setText(br.getName());
        targetColumnLabel.setText(c.getName());
        targetTableLabel.setText(t.getName());


        //Specifiek voor AttributeRange
//        AttributeRange ar = attributeRangeService.findById(businessRule.getBrtid());
//        minvalLabel.setText(String.valueOf(ar.getMinvalue()));
//        maxvalLabel.setText(String.valueOf(ar.getMaxvalue()));
//        AttributeRangeGenerator atrg = new AttributeRangeGenerator();
//        String name = nameLabel.getText();
//        String tc = targetColumnLabel.getText();
//        String tt = targetTableLabel.getText();
//        String operator = operatorLabel.getText();
//        int minVal = Integer.parseInt(minvalLabel.getText());
//        int maxVal = Integer.parseInt(maxvalLabel.getText());
//        String script = atrg.generateRule(name,tc,tt,operator,minVal,maxVal);

        //Specifiek voor AttributeCompare
        AttributeCompare ac = attributeCompareService.findById(businessRule.getBrtid());
        litvalLabel.setText(String.valueOf(ac.getLitvalue()));
        AttributeCompareGenerator acg = new AttributeCompareGenerator();
        String name = nameLabel.getText();
        String tc = targetColumnLabel.getText();
        String tt = targetTableLabel.getText();
        String operator = operatorLabel.getText();
        int litVal = Integer.parseInt(litvalLabel.getText());
        String script = acg.generateRule(name,tc,tt,operator,litVal);
        System.out.println(script);

        //Uitvoeren script
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
