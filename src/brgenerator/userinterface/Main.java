package brgenerator.userinterface;

import brgenerator.model.BusinessRule;
import brgenerator.persistency.BusinessRuleDAO;
import brgenerator.persistency.BusinessRuleDAOImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Main extends Application {
    BusinessRuleDAO businessRuleDAO = new BusinessRuleDAOImpl();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("defineRule.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 773, 496));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
