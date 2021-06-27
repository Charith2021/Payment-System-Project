package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Student;
import service.StudentService;

import java.io.IOException;
import java.time.LocalDate;

public class AddNewStudentFormController {

    public AnchorPane contextOfAddNewStudent;
    public Label txtUserName;
    public JFXTextField txtNic;
    public JFXTextField txtFullName;
    public JFXTextField txtAddress;
    public JFXTextField txtDob;
    public JFXTextField txtContact;
    public JFXTextField txtMail;

    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {

        Stage homeStage = (Stage) contextOfAddNewStudent.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormRegular.fxml"));
        Scene homeScene=new Scene(root);
        homeStage.setScene(homeScene);
        homeStage.setTitle("Home");
    }


    public void btnAddStudent_OnAction(ActionEvent actionEvent) {

        Student student=new Student(txtNic.getText(),
                txtFullName.getText(),
                txtAddress.getText(),
                LocalDate.parse(txtDob.getText()),
                txtContact.getText(),
                txtMail.getText());

        StudentService.saveStudent(student);
        new Alert(Alert.AlertType.NONE,"Student has been saved successfully", ButtonType.OK).show();
    }
}
