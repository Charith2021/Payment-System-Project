package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane contextOfMainForm;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        if(txtUserName.getText().trim().isEmpty() && txtPassword.getText().trim().isEmpty()){

            new  Alert(Alert.AlertType.ERROR,"Enter a valid user name and a password",ButtonType.OK).show();
        }


       else if(txtUserName.getText().equals("admin") && txtPassword.getText().equals("admin")){

            Stage stageAdmin = (Stage) contextOfMainForm.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormAdmin.fxml"));
            Scene adminScene=new Scene(root);
            stageAdmin.setScene(adminScene);

        }

        else if(txtUserName.getText().equals("regular") && txtPassword.getText().equals("123")){

            Stage stageRegular= (Stage) contextOfMainForm.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormRegular.fxml"));
            Scene regularScene=new Scene(root);
            stageRegular.setScene(regularScene);

        }

        else{
          new  Alert(Alert.AlertType.ERROR,"Wrong user name or password", ButtonType.OK).show();
        }
    }
}
