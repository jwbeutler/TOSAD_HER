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

    public void fillData(ActionEvent actionEvent){
        BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
        TableService tableService = ServiceProvider.getTableService();
        ColumnService columnService = ServiceProvider.getColumnService();

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        BusinessRule br = (BusinessRule) stage.getUserData();
        BusinessRule businessRule = businessRuleService.findById(br.getId());

        Column c = columnService.findById(businessRule.getTcid());
        Table t = tableService.findById(c.getTableid());
        idLabel.setText(String.valueOf(br.getId()));
        ruleTypeLabel.setText(br.getType());
        operatorLabel.setText(br.getOperator());
        nameLabel.setText(br.getName());
        targetColumnLabel.setText(c.getName());
        targetTableLabel.setText(t.getName());

    }

    public void generateACRule() throws SQLException {
        BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
        AttributeCompareService attributeCompareService = ServiceProvider.getAttributeCompareService();
        TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();

        int ruleid = Integer.parseInt(idLabel.getText());
        BusinessRule br = businessRuleService.findById(ruleid);

        AttributeCompare ac = attributeCompareService.findById(br.getBrtid());
        litvalLabel.setText(String.valueOf(ac.getLitvalue()));
        AttributeCompareGenerator acg = new AttributeCompareGenerator();
        String name = nameLabel.getText();
        String tc = targetColumnLabel.getText();
        String tt = targetTableLabel.getText();
        String operator = operatorLabel.getText();
        int litVal = Integer.parseInt(litvalLabel.getText());
        String script = acg.generateRule(name,tc,tt,operator,litVal);
        targetDBSerivce.executeRule(script);

    }
    public void generateARRule() throws SQLException{
        AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();
        BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
        TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();

        int ruleid = Integer.parseInt(idLabel.getText());
        BusinessRule br = businessRuleService.findById(ruleid);

        AttributeRange ar = attributeRangeService.findById(br.getBrtid());
        minvalLabel.setText(String.valueOf(ar.getMinvalue()));
        maxvalLabel.setText(String.valueOf(ar.getMaxvalue()));
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



    public void generateRule(ActionEvent actionEvent) throws SQLException {
        if(ruleTypeLabel.getText().equals("AttributeRange")){
            generateARRule();
            System.out.println("Attribute Range aangemaakt!");
        }else if(ruleTypeLabel.getText().equals("AttributeCompare")){
            generateACRule();
            System.out.println("Attribute Compare aangemaakt!");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
