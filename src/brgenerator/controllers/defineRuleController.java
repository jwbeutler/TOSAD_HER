package brgenerator.controllers;

import brgenerator.model.Column;
import brgenerator.model.Table;
import brgenerator.services.BusinessRuleService;
import brgenerator.services.ServiceProvider;
import brgenerator.services.TargetDBSerivce;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class defineRuleController implements Initializable {
    private TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();
    private BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
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
        for(String s : ruleTypesList){
            ruleType.getItems().add(s);
        }
        targetTables.valueProperty().addListener(((observableValue, o, t1) -> {
            if(t1 == null){
                targetTables.getItems().clear();
                targetTables.setDisable(true);
            }else {
                List<Column> columns = targetDBSerivce.findColumnsByTable(t1.toString());
                targetColumns.getItems().setAll(columns);
                targetColumns.setDisable(false);

            }
        }));

    }


    public void defineRule(ActionEvent actionEvent) {
    }
}
