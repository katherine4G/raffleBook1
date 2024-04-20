/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.google.zxing.WriterException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;
import model.MatrixState;

/**
 * FXML Controller class
 *
 * @author kathe
 */
public class MatrixNumbersController implements Initializable {

    @FXML
    private StackPane StackPane4;
    @FXML
    private AnchorPane AnchorPane4;
    @FXML
    private GridPane gridPane;
    @FXML
    private MenuButton buttonsView;
    @FXML
    private Label nameRaffle;
    @FXML
    private Button btn_back;
    @FXML
    private ScrollPane ScrollPane4;
    @FXML
    private Button btn_generateWinner;
        
    /**
     * Initializes the controller class.
     */

    private String raffleName;
    private int totalNumbers;
    
    private Button originalButton; 
      
    private String buyersName;
    private String buyersNumber;
    private int buttonState;
    

    @FXML
    private Label lblWinnerNumber;
    private CreateRaffleController createRaffleController;
    private boolean winnerGenerated = false;
    
    @Override
    
    
   
    public void initialize(URL url, ResourceBundle rb) {
        nameRaffle.setText(raffleName); // Establecer el nombre de la rifa en el Label correspondiente
        fillGridPane(totalNumbers); // Llenar el GridPane con la cantidad de botones adecuada
            Integer winnerNumber = MatrixState.getWinnerNumber();
        if (winnerNumber != null) {
            lblWinnerNumber.setText(Integer.toString(winnerNumber));
        }
 
    }  
    public void setOriginalButton(Button button) {
        this.originalButton = button;
    }

    
     public void setRaffleNameAndNumbers(String raffleName, int totalNumbers) {
        this.raffleName = raffleName;
        this.totalNumbers = totalNumbers;
    }  
    
    public void setCreateRaffleController(CreateRaffleController createRaffleController) {
        this.createRaffleController = createRaffleController;
    }

    private void obtainNameFromCreateRaffle() {
        if (createRaffleController != null) {
            createRaffleController.obtainName(null); 
        }
    }
    
    @FXML
    private void buttons_Views(ActionEvent event) {
    }
    public void createButtons(int amount) {
         gridPane.getChildren().clear();
    // Crea los botones en el GridPane con la cantidad deseada
    for (int i = 0; i < amount; i++) {
        Button button = new Button(String.valueOf(i + 1));
        gridPane.add(button, i % 4, i / 4);
        
        // Establecer el botón original y el controlador en el botón
        button.setOnAction(event -> {
            originalButton = button;
            setOriginalButton(button);
        });
    }
    }

    void fillGridPane(int totalNumeros) {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        gridPane.setHgap(10); 
        gridPane.setVgap(10); 
        gridPane.setPadding(new Insets(10));

        for (int i = 0; i < 4; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(100 / 4); 
            gridPane.getColumnConstraints().add(colConstraints);
        }

        int numRows = (totalNumeros + 3) / 4;

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100 / numRows);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        int count = 0;
        for (int i = 0; i < totalNumeros; i++) {
            Button button = new Button(String.valueOf(count));
            count++;
            button.setPrefSize(60, 60);
            button.setMaxSize(60, 60);
            button.setMinSize(60, 60);
            button.setOnAction(this::touchNumber);
            button.setOnAction(this::obtainNumber);

            int columna = i % 4; 
            int fila = i / 4;
            gridPane.add(button, columna, fila); // Agregar el botón al GridPane
      
        }

        gridPane.requestLayout();
    }
  
    
    @FXML
    private void btnBackToCounterFoil(ActionEvent event) {
          Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/CounterfoilR.fxml"));//new
                Scene scene = btn_back.getScene();
                root.translateYProperty().set(scene.getHeight());
                StackPane4.getChildren().add(root);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    StackPane4.getChildren().remove(AnchorPane4);
                });
                timeline.play();
            } catch (IOException ex) {
                Logger.getLogger(MatrixNumbersController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void touchNumber(ActionEvent event) {
        Button button = (Button) event.getSource(); // Obtener el botón que disparó el evento
        originalButton = button; // Almacenar la referencia al botón original
        button.setText(button.getText());
        button.setStyle("-fx-background-color: white"); 
    }

    private void obtainNumber(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BuyerNumber.fxml"));
            Parent root = loader.load();
            BuyerNumberController controller = loader.getController();

            controller.setOriginalButton(originalButton);

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Obtener la ventana principal desde el evento
            dialogStage.setScene(new Scene(root));
       
            Button button = (Button) event.getSource();
            String numeroSeleccionado = button.getText();

            controller.setTouchedNumber(numeroSeleccionado);
            ////////////
            controller.setBuyersName(buyersName);
            controller.setBuyersNumber(buyersNumber);
    
            dialogStage.showAndWait();
        } catch (IOException e) { }
   }

    @FXML
    private void generate_Winner(ActionEvent event) {
        if (!winnerGenerated) {
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            Alert winnerAlert = new Alert(AlertType.NONE);
            winnerAlert.setTitle("Número Ganador");
            winnerAlert.setHeaderText(null);
            winnerAlert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE); 
            winnerAlert.setContentText("\n\tEl número\n  ganador es:\n\t\t" + randomNumber);
            winnerAlert.getDialogPane().setPrefSize(150, 150);

            MatrixState.setWinnerNumber(randomNumber);
            lblWinnerNumber.setText(Integer.toString(randomNumber));
            
            winnerAlert.showAndWait();
            // Deshabilitar el botón para que no se pueda presionar de nuevo
            btn_generateWinner.setDisable(true);
            winnerGenerated = true;
        }
    }
}
