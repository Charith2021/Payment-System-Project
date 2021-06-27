package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class PaymentFormController {


    public ChoiceBox cheCourse;
    public ChoiceBox cheID;
    public JFXButton btnHome;
    public AnchorPane contextOfRegularPaymentForm;

    ObservableList<String> olName=FXCollections.observableArrayList("Graduate Diploma in Software Engineering","CMJD","Direct Entry Program");
    ObservableList<String> olId=FXCollections.observableArrayList("GDSE","CMJD","DEP");

    public  void  initialize(){
        cheCourse.setItems(olName);
        cheID.setItems(olId);
        cheCourse.setValue("Graduate Diploma in Software Engineering");
        cheID.setValue("GDSE");
    }


    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Stage homeStageRegular = (Stage) contextOfRegularPaymentForm.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormRegular.fxml"));
        Scene homeSceneRegular=new Scene(root);
        homeStageRegular.setScene(homeSceneRegular);
        homeStageRegular.setTitle("Home");
    }
}
