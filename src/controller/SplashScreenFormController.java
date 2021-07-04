package controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class SplashScreenFormController {

    public JFXProgressBar prb;
    private Timeline tl;

    public void initialize(){

        prb.setProgress(0);

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            if (prb.getProgress() <= 1) {
                prb.setProgress(prb.getProgress() + 0.01);
            }
        }));

        tl.setRate(3);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.playFromStart();

        prb.progressProperty().addListener((observable, oldValue, newValue) -> {
            try {

                if (newValue.intValue() == 1) {
                    tl.stop();
                    initializeUI();
                } else if (newValue.doubleValue() >= 0.95) {
                    spinUpRedisServerInstance();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load the app, please contact DEPPO!").showAndWait();
                System.exit(1);
            }
        });
    }

    private void initializeUI() throws IOException {
        Stage primaryStage = (Stage) prb.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        MainFormController ctrl = fxmlLoader.getController();

        mainScene.setUserData(ctrl);
        primaryStage.setTitle("Payment System Login");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }





  /*      try {
            spinUpRedisServerInstance();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load data contact DEPPO").show();
            return;
        }    */




    private void spinUpRedisServerInstance() throws Exception {
        String[] commands = {"redis-server", "redis.conf","--requirepass","123"};   //123 is the password

        Process redisServer = Runtime.getRuntime().exec(commands);
        int exitCode = redisServer.waitFor();

        if (exitCode != 0) {
            throw new Exception("Failed to spin up the redis server instance");
        }
    }
}
