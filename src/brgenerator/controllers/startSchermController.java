package brgenerator.controllers;

import brgenerator.model.BusinessRule;
import brgenerator.services.BusinessRuleService;
import brgenerator.services.ServiceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class startSchermController implements Initializable {
    public TableView<BusinessRule> ruleTable;
    public TableColumn ruleNameColumn;
    public TableColumn ruleTypeColumn;
    public TableColumn attributeColumn;
    public TableColumn operatorColumn;
    public TableColumn generateButtonColumn;
    public Button defineRuleButton;

    public BusinessRuleService businessRuleService = ServiceProvider.getBusinessRuleService();
    public Button generateRule;


    public void goToDefinePage(ActionEvent actionEvent) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("/brgenerator/userinterface/defineRule.fxml"));
        Stage currentStage = (Stage) defineRuleButton.getScene().getWindow();
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        currentStage.close();
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ruleNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ruleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("attribute"));
        operatorColumn.setCellValueFactory(new PropertyValueFactory<>("operator"));
        ruleTable.setItems(observableList);


    }
    ObservableList<BusinessRule> observableList = FXCollections.observableArrayList(
            businessRuleService.findStartSchermRules());

    public void generateRuleScreen(ActionEvent actionEvent) throws IOException {
        BusinessRule br = ruleTable.getSelectionModel().getSelectedItem();
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/brgenerator/userinterface/generateRule.fxml"));
            stage.setUserData(br);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

//        Parent part = FXMLLoader.load(getClass().getResource("/brgenerator/userinterface/generateRule.fxml"));
//        Stage currentStage = (Stage) generateRule.getScene().getWindow();
//        Stage stage = new Stage();
//        Scene scene = new Scene(part);
//        stage.setScene(scene);
//        currentStage.close();
//        stage.show();
    }
