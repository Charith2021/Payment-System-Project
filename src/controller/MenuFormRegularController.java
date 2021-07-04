package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuFormRegularController {
    public AnchorPane pneAddNewStudent;
    public AnchorPane pneSearchStudents;
    public JFXRippler rprAddNewStudent;
    public JFXRippler rprSearchStudents;
    public JFXRippler rprPayment;
    public AnchorPane pnePayment;
    public AnchorPane contextOfMenuFormRegular;
    public Label lblUser;


    public  void  initialize(){
        rprAddNewStudent.setControl(pneAddNewStudent);
        rprSearchStudents.setControl(pneSearchStudents);
        rprPayment.setControl(pnePayment);
        pneAddNewStudent.setFocusTraversable(true);
        pneSearchStudents.setFocusTraversable(true);
        pnePayment.setFocusTraversable(true);
    }


    public void rprAddNewStudent_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER||keyEvent.getCode()==KeyCode.SPACE){
            rprAddNewStudent.createManualRipple().run();
        }
    }

    public void rprSearchStudents_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()==KeyCode.ENTER||keyEvent.getCode()==KeyCode.SPACE){
            rprSearchStudents.createManualRipple().run();
        }
    }

    public void rprPayment_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()==KeyCode.ENTER||keyEvent.getCode()==KeyCode.ENTER){
            rprPayment.createManualRipple().run();
        }
    }


    public void pneAddNewStudent_OnKeyReleased(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()==KeyCode.ENTER || keyEvent.getCode()==KeyCode.SPACE){
            navigate("Add New Student", "/view/AddNewStudentForm.fxml");
        }
    }

    public void pneAddNewStudent_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        navigate("Add New Student", "/view/AddNewStudentForm.fxml");
    }

    public void pneSearchStudents_OnKeyReleased(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()==KeyCode.ENTER || keyEvent.getCode()==KeyCode.SPACE){
            navigate("Search Student", "/view/SearchStudentForm.fxml");
        }
    }

    public void pneSearchStudents_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        navigate("Search Student", "/view/SearchStudentForm.fxml");
    }


    public void pnePayment_OnKeyReleased(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()==KeyCode.ENTER || keyEvent.getCode()==KeyCode.SPACE){
            navigate("Add Payment", "/view/PaymentForm.fxml");
        }
    }

    public void pnePayment_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        navigate("Add Payment", "/view/PaymentForm.fxml");
    }

    public  void navigate(String title,String url) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(url));
            Parent root = loader.load();

            //The following both lines are the only addition we need to pass the arguments
            if(title.equals("Add New Student")) {
                AddNewStudentFormController controller2 = loader.getController();
                controller2.setLabelText(lblUser.getText());
                controller2.hide();
            }
            if(title.equals("Search Student")){
                SearchStudentFormController controller3 = loader.getController();
                controller3.setLabelText(lblUser.getText());
                controller3.hide();
            }if(title.equals("Add Payment")){
                PaymentFormController controller4 = loader.getController();
                controller4.setLabelText(lblUser.getText());
                controller4.hide();
            }

            Stage stage = (Stage) contextOfMenuFormRegular.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bynChangeLogin_OnAction(ActionEvent actionEvent) throws IOException {
        Stage loginStage = (Stage) contextOfMenuFormRegular.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        Scene loginScene=new Scene(root);
        loginStage.setScene(loginScene);
        loginStage.show();
    }
}
