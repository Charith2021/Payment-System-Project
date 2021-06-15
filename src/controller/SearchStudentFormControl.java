package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SearchStudentFormControl {
    public AnchorPane contextOfSearchStudent;

    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Stage homeStage = (Stage) contextOfSearchStudent.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormRegular.fxml"));
        Scene homeScene=new Scene(root);
        homeStage.setScene(homeScene);
        homeStage.setTitle("Home");
    }
}
