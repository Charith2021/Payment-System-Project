package controller;

import com.jfoenix.controls.JFXButton;
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
    public Label lblOutput;
    public JFXTextField txtNic;
    public JFXTextField txtFullName;
    public JFXTextField txtAddress;
    public JFXTextField txtDob;
    public JFXTextField txtContact;
    public JFXTextField txtMail;
    public JFXButton btnHomeRegular;
    public JFXButton btnHomeAdmin;

    public void initialize() {

    }

    public void hide() {
        if(lblOutput.getText().equals("Admin")){
            btnHomeRegular.setVisible(false);
        }
        if (lblOutput.getText().equals("Regular User")){
            btnHomeAdmin.setVisible(false);
        }
    }


        public void setLabelText (String text){
            lblOutput.setText(text);
        }

        public void btnHomeRegular_OnAction (ActionEvent actionEvent) throws IOException {

            Stage homeStage = (Stage) contextOfAddNewStudent.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormRegular.fxml"));
            Scene homeScene = new Scene(root);
            homeStage.setScene(homeScene);
            homeStage.setTitle("Regular Home");
        }

        public void btnHomeAdmin_OnAction (ActionEvent actionEvent) throws IOException {
            Stage homeStage = (Stage) contextOfAddNewStudent.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormAdmin.fxml"));
            Scene homeScene = new Scene(root);
            homeStage.setScene(homeScene);
            homeStage.setTitle("Admin Home");
        }


        public void btnAddStudent_OnAction (ActionEvent actionEvent){

            Student student = new Student(txtNic.getText(),
                    txtFullName.getText(),
                    txtAddress.getText(),
                    LocalDate.parse(txtDob.getText()),
                    txtContact.getText(),
                    txtMail.getText());

            StudentService.saveStudent(student);
            new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();
        }
    }

