package brgenerator.controllers;

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


    public void generateRule(ActionEvent actionEvent) {
        BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
        TableService tableService = ServiceProvider.getTableService();
        AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();
        ColumnService columnService = ServiceProvider.getColumnService();


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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
