package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuFormAdminController {
    public AnchorPane contextOfMenuFormAdmin;
    public JFXRippler rprAddNewStudent;
    public JFXRippler rprSearchStudents;
    public JFXRippler rprPayment;
    public AnchorPane pneSearchStudents;
    public AnchorPane pneAddNewStudent;
    public AnchorPane pnePayment;

    public void initialize(){
    rprAddNewStudent.setControl(pneAddNewStudent);
    rprSearchStudents.setControl(pneSearchStudents);
    rprPayment.setControl(pnePayment);
    pneAddNewStudent.setFocusTraversable(true);
    pneSearchStudents.setFocusTraversable(true);
    pnePayment.setFocusTraversable(true);
    }

    public void rprAddNewStudent_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER||keyEvent.getCode()==KeyCode.SPACE) {
            rprAddNewStudent.createManualRipple().run();
        }
    }

    public void rprSearchStudents_OnKeyPressed(KeyEvent keyEvent) {
            if(keyEvent.getCode()==KeyCode.ENTER||keyEvent.getCode()==KeyCode.SPACE) {
                rprSearchStudents.createManualRipple().run();
        }
    }

    public void rprPayment_OnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode()==KeyCode.ENTER||keyEvent.getCode()==KeyCode.ENTER){
            rprPayment.createManualRipple().run();
        }
    }


    public void pneAddNewStudent_OnKeyReleased(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()== KeyCode.ENTER || keyEvent.getCode()==KeyCode.SPACE){
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
            navigate("Edit Payment","/view/PaymentForm.fxml");
        }
    }

    public void pnePayment_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        navigate("Edit Payment","/view/PaymentForm.fxml");
    }

    public  void navigate(String title,String url) throws IOException {
        Stage stage = (Stage) contextOfMenuFormAdmin.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource(url));
        Scene addNewStudentScene=new Scene(root);
        stage.setScene(addNewStudentScene);
        stage.setTitle(title);
    }


}
