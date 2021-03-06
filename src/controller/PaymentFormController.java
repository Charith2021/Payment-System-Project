package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Student;
import service.StudentService;
import service.StudentServiceRedisImpl;

import java.io.IOException;


public class PaymentFormController {


    public ChoiceBox cheCourse;
    public ChoiceBox cheID;
    public AnchorPane contextOfPaymentForm;
    public Label lblUserName;
    public JFXButton btnHomeAdmin;
    public JFXButton btnHomeRegular;
    public JFXTextField txtNic;
    public JFXTextField txtFullName;
    public JFXTextField txtFees;

    private StudentServiceRedisImpl studentServiceRedis = new StudentServiceRedisImpl();

    ObservableList<String> olName=FXCollections.observableArrayList("Graduate Diploma in Software Engineering","CMJD","Direct Entry Program");
    ObservableList<String> olId=FXCollections.observableArrayList("GDSE","CMJD","DEP");

    public  void  initialize(){
        cheCourse.setItems(olName);
        cheID.setItems(olId);
        cheCourse.setValue("Graduate Diploma in Software Engineering");
        cheID.setValue("GDSE");

        cheCourse.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(cheCourse.getValue() == "Graduate Diploma in Software Engineering"){
                cheID.setValue("GDSE");
               if(lblUserName.getText().equals("Admin")) {
                   txtFees.setText("Rs 200000");
               }
                if(lblUserName.getText().equals("Regular User")) {
                    txtFees.setText("Rs 200000");
                    txtFees.setEditable(false);
                }

            }

            if(cheCourse.getValue() == "CMJD"){
                cheID.setValue("CMJD");
                if(lblUserName.getText().equals("Admin")) {
                    txtFees.setText("Rs 50000");
                }
                if(lblUserName.getText().equals("Regular User")) {
                    txtFees.setText("Rs 50000");
                    txtFees.setEditable(false);
                }

            }

            if(cheCourse.getValue() == "Direct Entry Program"){
                cheID.setValue("DEP");
                if(lblUserName.getText().equals("Admin")) {
                    txtFees.setText("Rs 100000");
                }
                if(lblUserName.getText().equals("Regular User")) {
                    txtFees.setText("Rs 100000");
                    txtFees.setEditable(false);
                }
            }
        });

        txtNic.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                  String nic = txtNic.getText();
                if (txtNic.getLength() == 10 ){
                   txtNic.setText(nic);
                   txtNic.setEditable(false);
                    System.out.println("Working");

                }
            }
        });
    }


    public void setLabelText (String text){
        lblUserName.setText(text);
    }

    public void hide() {
        if(lblUserName.getText().equals("Admin")){
            btnHomeRegular.setVisible(false);
        }
        if (lblUserName.getText().equals("Regular User")){
            btnHomeAdmin.setVisible(false);
        }
    }



    public void btnHomeAdmin_OnAction(ActionEvent actionEvent) throws IOException {
        navigateHome("Home","/view/MenuFormAdmin.fxml");
    }

    public void btnHomeRegular_OnAction(ActionEvent actionEvent) throws IOException {
        navigateHome("Home","/view/MenuFormRegular.fxml");
    }

    public  void  navigateHome(String title,String url) throws IOException {
        Stage homeStageRegular = (Stage) contextOfPaymentForm.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource(url));
        Scene homeSceneRegular=new Scene(root);
        homeStageRegular.setScene(homeSceneRegular);
        homeStageRegular.setTitle(title);
    }

    public void btnPrint_OnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"Printing",ButtonType.OK).show();
    }
}
