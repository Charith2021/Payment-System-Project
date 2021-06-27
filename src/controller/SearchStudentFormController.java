package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Student;
import model.StudentTM;
import service.StudentService;

import java.io.IOException;
import java.util.List;

public class SearchStudentFormController {
    public AnchorPane contextOfSearchStudent;
    public TableView<StudentTM> tblStudents;
    public JFXTextField txtQuery;

    public void initialize(){
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllStudents(newValue));
        loadAllStudents(null);
    }

    private void loadAllStudents(String query) {
        tblStudents.getItems().clear();

        List<Student> searchResult;
        if(query == null || query.trim().isEmpty()){
            searchResult = StudentService.findAllStudents();
        }else{
            searchResult = StudentService.findStudents(query);
        }

        for(Student student : searchResult){
            tblStudents.getItems().add(new StudentTM(student.getNic(),student.getFullName(),student.getAddress()));
        }
    }

    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Stage homeStage = (Stage) contextOfSearchStudent.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MenuFormRegular.fxml"));
        Scene homeScene=new Scene(root);
        homeStage.setScene(homeScene);
        homeStage.setTitle("Home");
    }
}
