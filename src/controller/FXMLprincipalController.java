/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author kathe
 */
public class FXMLprincipalController implements Initializable {
    
    @FXML
    private StackPane stackPane; //cont padre
    @FXML
    private AnchorPane anchorPane; //raiz
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private Button btn_viewMyRaffles;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
         Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/createRaffle.fxml"));//new
                Scene scene = button.getScene();
                root.translateYProperty().set(scene.getHeight());
                stackPane.getChildren().add(root);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    stackPane.getChildren().remove(anchorPane);
                });
                timeline.play();
            } catch (IOException ex) {
                Logger.getLogger(FXMLprincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnViewMyRaffles(ActionEvent event) {
        Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/CounterfoilR.fxml"));//new
                Scene scene = btn_viewMyRaffles.getScene();
                root.translateYProperty().set(scene.getHeight());
                stackPane.getChildren().add(root);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    stackPane.getChildren().remove(anchorPane);
                });
                timeline.play();
            } catch (IOException ex) {
                Logger.getLogger(FXMLprincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    
}
