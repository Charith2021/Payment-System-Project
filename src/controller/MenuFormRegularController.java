package controller;

import com.jfoenix.controls.JFXRippler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MenuFormRegularController {
    public AnchorPane pneAddNewStudent;
    public AnchorPane pneSearchStudents;
    public JFXRippler rprAddNewStudent;
    public JFXRippler rprSearchStudents;
    public JFXRippler rprPayment;
    public AnchorPane pnePayment;


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

    public void pneAddNewStudent_OnKeyReleased(KeyEvent keyEvent) {

    }

    public void pneSearchStudents_OnKeyReleased(KeyEvent keyEvent) {

    }

    public void pnePayment_OnKeyReleased(KeyEvent keyEvent) {

    }
}
