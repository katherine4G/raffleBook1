package controller;

import javafx.scene.input.KeyEvent;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import Classes.Raffle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MatrixState;

import model.RaffleModel;

/**
 * FXML Controller class
 *
 * @author kathe
 */
public class CreateRaffleController implements Initializable {

    @FXML
    private StackPane stackPane2;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private Button btn_back;
    @FXML
    protected TextField nameRaffle;
    @FXML
    protected DatePicker dateRaffle;
    @FXML
    protected TextField priceOfNumber;
    @FXML
    protected TextField amountNumbers;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_counterfoil;

    /**
     * Initializes the controller class.
     */
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        priceOfNumber.addEventFilter(KeyEvent.KEY_TYPED, event -> {
        if (!isNumber(event)) {
            event.consume(); // Consumir el evento para evitar que se escriban letras
        }
    });
    
    amountNumbers.addEventFilter(KeyEvent.KEY_TYPED, event -> {
        if (!isNumber(event)) {
            event.consume();
        }
    });
    }    

    private boolean isNumber(KeyEvent event) {
        String character = event.getCharacter();
        return character.matches("[0-9]"); // Verificar si el carácter es un número
    }
    
    public int getAmountNumbers() {
        return Integer.parseInt(amountNumbers.getText());
    }
    @FXML
    private void toPrincipalScreen(ActionEvent event) {
        Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXMLprincipal.fxml"));
                Scene scene = btn_back.getScene();
                root.translateYProperty().set(scene.getHeight());
                stackPane2.getChildren().add(root);
                
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    stackPane2.getChildren().remove(anchorPane2);
                });
                timeline.play();
                
            } catch (IOException ex) {
                Logger.getLogger(CreateRaffleController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public TextField getNameRaffle() {
        return nameRaffle;
    }

    public void setNameRaffle(TextField nameRaffle) {
        this.nameRaffle = nameRaffle;
    }

    public TextField getPriceOfNumber() {
        return priceOfNumber;
    }

    public void setPriceOfNumber(TextField priceOfNumber) {
        this.priceOfNumber = priceOfNumber;
    }

    public void setAmountNumbers(TextField amountNumbers) {
        this.amountNumbers = amountNumbers;
    }

    public DatePicker getDateRaffle() {
        return dateRaffle;
    }

    public void setDateRaffle(DatePicker dateRaffle) {
        this.dateRaffle = dateRaffle;
    }

    @FXML
     void obtainName(ActionEvent event) {
        String name = nameRaffle.getText();
        System.out.println("Nuevo nombre de rifa: " + name);
   
        MatrixState.setRaffleName(name);
       // getNameRaffle();
    }
    @FXML
    private void obtainDateRaffle(ActionEvent event) {
       // getDateRaffle();
    }

    @FXML
    private void obtain_priceOfNumber(ActionEvent event) {
       // getPriceOfNumber();
    }

    @FXML
    private void obtain_amountNumbers(ActionEvent event) {
    
    int amount = getAmountNumbers(); // Obtener el valor del TextField amountNumbers
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MatrixNumbers.fxml"));
    Parent root;
    try {
        root = loader.load();
        MatrixNumbersController matrixController = loader.getController();
        matrixController.setRaffleNameAndNumbers(nameRaffle.getText(), amount);
        
        stackPane2.getChildren().add(root); 
        matrixController.setCreateRaffleController(this);
    } catch (IOException ex) {}
    }

    @FXML
    private void cancel_BackToPrincipal(ActionEvent event) {
         Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXMLprincipal.fxml"));
                Scene scene = btn_cancel.getScene();
                root.translateYProperty().set(scene.getHeight());
                stackPane2.getChildren().add(root);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    stackPane2.getChildren().remove(anchorPane2);
                });
                timeline.play();
            } catch (IOException ex) {
                Logger.getLogger(CreateRaffleController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void AddCounterfoil(ActionEvent event) {
        
    String name = nameRaffle.getText();
    int price = Integer.parseInt(priceOfNumber.getText());
    int amount = Integer.parseInt(amountNumbers.getText());
    
    if (name.isEmpty() || dateRaffle.getValue() == null || priceOfNumber.getText().isEmpty() || amountNumbers.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, complete todos los campos.");
        alert.showAndWait();
    } 
    else {
        Raffle newRaffle = new Raffle(name, price, amount);

        RaffleModel.getInstance().addRaffle(newRaffle);

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/CounterfoilR.fxml"));
            Scene scene = btn_counterfoil.getScene(); //
            root.translateYProperty().set(scene.getHeight());
            stackPane2.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.001), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                stackPane2.getChildren().remove(anchorPane2);
            });
            timeline.play();
        } catch (IOException ex) {
            Logger.getLogger(CreateRaffleController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
   }   
    
}
