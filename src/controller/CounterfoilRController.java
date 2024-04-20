/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Classes.Raffle;
import model.RaffleModel;

/**
 * FXML Controller class
 *
 * @author kathe
 */
public class CounterfoilRController implements Initializable {

    @FXML
    private StackPane StackPane3;
    @FXML
    private AnchorPane anchorPane3;
    @FXML
    private Label lbl;
    @FXML
    private ScrollPane scroll_pane;
    @FXML
    private AnchorPane anchor_paneScroll;
    @FXML
    private ListView<Raffle> listRaffleView;

    @FXML
    private Button btn_editR;
    @FXML
    private Button btn_deleteR;
    @FXML
    private Button btn_back;
    
    private final ObservableList<Raffle> raffles = FXCollections.observableArrayList();

    public CounterfoilRController() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listRaffleView.setItems(raffles);//
        listRaffleView.setItems(RaffleModel.getInstance().getRaffles());
    }   
    
    public void addRaffle(Raffle newRaffle) {
        raffles.add(newRaffle);
    }
    
    @FXML
    private void btnEditRaffle(ActionEvent event) throws IOException {
         Raffle raffleSeleccionada = listRaffleView.getSelectionModel().getSelectedItem();
    
        // Verificar si se ha seleccionado una rifa
        if (raffleSeleccionada != null) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MatrixNumbers.fxml"));
                Parent root = loader.load();

                // Obtener el controlador del nuevo FXML
                MatrixNumbersController rifasController = loader.getController();

                // Llenar el GridPane con 100 botones
                rifasController.fillGridPane(100);

                Scene scene = btn_editR.getScene();
                root.translateYProperty().set(scene.getHeight());
                StackPane3.getChildren().add(root);

                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    StackPane3.getChildren().remove(anchorPane3);
                });
                timeline.play();

            } catch (IOException ex) {
                Logger.getLogger(CounterfoilRController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("ERROR");
             alert.setHeaderText(null);
             alert.setContentText("Por favor, seleccione una rifa para editar.");
             alert.showAndWait();
        }
//        Parent root;
//        try {
//           root = FXMLLoader.load(getClass().getResource("/FXML/MatrixNumbers.fxml"));//new
//           Scene scene = btn_editR.getScene();
//           root.translateYProperty().set(scene.getHeight());
//           StackPane3.getChildren().add(root);
//           Timeline timeline = new Timeline();
//           KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
//           KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
//           timeline.getKeyFrames().add(kf);
//           timeline.setOnFinished(t -> {
//               StackPane3.getChildren().remove(anchorPane3);
//           });
//           timeline.play();
//       } catch (IOException ex) {
//           Logger.getLogger(CounterfoilRController.class.getName()).log(Level.SEVERE, null, ex);
//       }
    }

    @FXML
    private void btnDeleteRaffle(ActionEvent event) {
        // Obtener la rifa seleccionada en el ListView
          Raffle raffleSeleccionada = listRaffleView.getSelectionModel().getSelectedItem();
    
        // Verificar si se ha seleccionado una rifa
        if (raffleSeleccionada != null) {
            // Eliminar la rifa seleccionada del modelo compartido
            RaffleModel.getInstance().getRaffles().remove(raffleSeleccionada);
        } 
        else {
                // error si no han seleccionado ninguna rifa
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, seleccione una rifa para eliminar.");
                alert.showAndWait();
             }
    }

    @FXML
    private void btnBackToPrincipal(ActionEvent event) {
        Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXMLprincipal.fxml"));
                Scene scene = btn_back.getScene();
                root.translateYProperty().set(scene.getHeight());
                StackPane3.getChildren().add(root);
                
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    StackPane3.getChildren().remove(anchorPane3);
                });
                timeline.play();
                
            } catch (IOException ex) {
                Logger.getLogger(CounterfoilRController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
