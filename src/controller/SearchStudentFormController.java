package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Student;
import model.StudentTM;
import service.StudentService;
import service.StudentServiceRedisImpl;

import java.io.IOException;
import java.util.List;

public class SearchStudentFormController {
    public AnchorPane contextOfSearchStudent;
    public TableView<StudentTM> tblStudents;
    public JFXTextField txtQuery;
    public Label lblUserName;
    public JFXButton btnAdminHome;
    public JFXButton btnRegularHome;
    private StudentServiceRedisImpl studentServiceRedis = new StudentServiceRedisImpl();

    public void initialize(){
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllStudents(newValue));
        loadAllStudents("");
    }

    private void loadAllStudents(String query) {
        tblStudents.getItems().clear();

        for (Student student : studentServiceRedis.findStudents(query)) {
            tblStudents.getItems().add(new StudentTM(student.getNic(), student.getFullName(), student.getAddress()));
        }
    }

    public void setLabelText (String text){
        lblUserName.setText(text);
    }

    public void hide() {
        if(lblUserName.getText().equals("Admin")){
            btnRegularHome.setVisible(false);
        }
        if (lblUserName.getText().equals("Regular User")){
            btnAdminHome.setVisible(false);
        }
    }


    public void btnHomeAdmin_OnAction(ActionEvent actionEvent) throws IOException {
        homeNavigate("Home","/view/MenuFormAdmin.fxml");
    }

    public void btnRegularHome_OnAction(ActionEvent actionEvent) throws IOException {
        homeNavigate("Home","/view/MenuFormRegular.fxml");
    }

    public  void homeNavigate(String title,String url) throws IOException{
        Stage homeStage = (Stage) contextOfSearchStudent.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource(url));
        Scene homeScene=new Scene(root);
        homeStage.setScene(homeScene);
        homeStage.setTitle("Home");
    }
}
