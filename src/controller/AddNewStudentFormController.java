package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Student;
import model.StudentTM;
import service.StudentService;
import service.StudentServiceRedisImpl;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import static util.ValidationUtil.*;




public class AddNewStudentFormController {
    private final StudentServiceRedisImpl studentServiceRedis = new StudentServiceRedisImpl();
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
    public JFXButton btnSave;

    public void initialize() {
        Platform.runLater(() -> {
            if (contextOfAddNewStudent.getUserData() != null) {
                StudentTM tm = (StudentTM) contextOfAddNewStudent.getUserData();
                Student student = null;
                try {
                    student = studentServiceRedis.findStudent(tm.getNic());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Something went wrong terribly.Please contact the DEPO", ButtonType.OK).show();
                }

                txtNic.setEditable(false);
                txtNic.setText(student.getNic());
                txtFullName.setText(student.getFullName());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContact());
                txtMail.setText(student.getEmail());
                txtDob.setText(student.getDateOfBirth().toString());

                btnSave.setText("UPDATE STUDENT");

            }
        });


        setMaxLength(txtContact, 11);

    }

    private void setMaxLength(TextField txt, int maxLength) {
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                txt.setText(txt.getText(0, maxLength));
            }
        });
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


        public void btnAddStudent_OnAction (ActionEvent actionEvent) throws DuplicateEntryException {

            try {

                if (!isValidated()) {
                    return;
                }

                Student student = new Student(txtNic.getText(),
                        txtFullName.getText(),
                        txtAddress.getText(),
                        LocalDate.parse(txtDob.getText()),
                        txtContact.getText(),
                        txtMail.getText());

                if (btnSave.getText().equals("Add Student")) {
                    studentServiceRedis.saveStudent(student);

                    txtNic.clear();
                    txtFullName.clear();
                    txtAddress.clear();
                    txtDob.clear();
                    txtContact.clear();
                    txtMail.clear();
                    txtNic.requestFocus();

                } else {
                    StudentTM tm = (StudentTM) contextOfAddNewStudent.getUserData();
                    tm.setFullName(txtFullName.getText());
                    tm.setAddress(txtAddress.getText());
                    studentServiceRedis.updateStudent(student);
                }
                new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();
            } catch (DuplicateEntryException e) {
                new Alert(Alert.AlertType.ERROR, "A student already exits with the same NIC", ButtonType.OK).show();
                txtNic.requestFocus();
            } catch (NotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something terribly went wrong, please contact DEPPO!", ButtonType.OK).show();
            }
        }

    private boolean isValidated() {

        String nic = txtNic.getText();
        String fullName = txtFullName.getText();
        String address = txtAddress.getText();
        String dob = txtDob.getText();
        String contact = txtContact.getText();
        String email = txtMail.getText();

        if (!((nic.length() == 10 && (nic.endsWith("V") || nic.endsWith("v")) && isInteger(nic.substring(0, 9)))
                || (nic.length() == 12 && isInteger(nic)))) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            txtNic.requestFocus();
            return false;
        } else if (!(isValid(fullName, true, false) && fullName.trim().length() >= 3)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name. Name should contain at least 3 letters and can contain only alphabetic letters and spaces").show();
            txtFullName.requestFocus();
            return false;
        } else if (!(address.trim().length() >= 4 && isValid(address, true, true, ':', '.', ',', '-', '/', '\\'))) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address. Address should be at least 4 digits and can contain only alphabetic letters, spaces and - , . / \\").show();
            txtAddress.requestFocus();
            return false;
        } else if (!isValidDate(dob)) {
            new Alert(Alert.AlertType.ERROR, "Invalid DOB").show();
            txtDob.requestFocus();
            return false;
        } else if (!(contact.length() == 11 && isInteger(contact.substring(0, 3)) && isInteger(contact.substring(4, 11)))) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number").show();
            txtContact.requestFocus();
            return false;
        } else if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email. Email can contain only letters, digits, periods and underscore.").show();
            txtMail.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    }

