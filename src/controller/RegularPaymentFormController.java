package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;


public class RegularPaymentFormController {


    public ChoiceBox cheCourse;
    public ChoiceBox cheID;
    ObservableList<String> olName=FXCollections.observableArrayList("Graduate Diploma in Software Engineering","CMJD","Direct Entry Program");
    ObservableList<String> olId=FXCollections.observableArrayList("GDSE","CMJD","DEP");

    public  void  initialize(){
        cheCourse.setItems(olName);
        cheID.setItems(olId);
        cheCourse.setValue("Graduate Diploma in Software Engineering");
        cheID.setValue("GDSE");
    }




}
