
package controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;

import Classes.QRImageConverter;
import Classes.QR_Code;
import Classes.userState;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.paint.Color.color;
/**
 * FXML Controller class
 *
 * @author kathe
 */
public class BuyerNumberController implements Initializable {

    @FXML
    private StackPane StackPane5;
    @FXML
    private AnchorPane AnchorPane5;
    @FXML
    private Label numberSelected;
    @FXML
    private TextField btn_obtainName;
    @FXML
    private TextField btn_obtainNumer;
    @FXML
    private CheckBox BtnCheck_reserved;
    @FXML
    private CheckBox BtnCheck_available;
    @FXML
    private CheckBox BtnCheck_paid;
    @FXML
    private Button btn_addBuyer;
    @FXML
    private Button QRcode;
    
    
    private Button originalButton;
    
    /**
     * Initializes the controller class.
     */
  
    private String touchNumb ;
    private String buyersName;
    private String buyersNumber;
    
    private int buttonState = -1;
    private MatrixNumbersController matrixNumbersController; 
    
    private userState currentUserState;
    private final Map<String,List<String>> usersByNum = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i;
        currentUserState = new userState(0,"","",-1);
         for(i=0 ; i<100;i++ ){
             String number = String.valueOf(i);
             usersByNum.put(number,new LinkedList<>());
         }
        BtnCheck_available.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                BtnCheck_reserved.setSelected(false);
                BtnCheck_paid.setSelected(false);
            }
        });

        BtnCheck_reserved.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                BtnCheck_available.setSelected(false);
                BtnCheck_paid.setSelected(false);
            }
        });

        BtnCheck_paid.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                BtnCheck_available.setSelected(false);
                BtnCheck_reserved.setSelected(false);
            }
        });
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MatrixNumbers.fxml"));
        try {
            Parent root = loader.load();
            matrixNumbersController = loader.getController();
        }
        catch (IOException e) {
        }
        
    }   
    void setTouchedNumber(String numeroSeleccionado) {
        this.touchNumb = numeroSeleccionado;
        updateLabel();
    }   
    public void setBuyersName(String name) {
        this.buyersName = name;
        btn_obtainName.setText(buyersName);
        updateLabel();
    }

    public void setBuyersNumber(String number) {
        this.buyersNumber = number;
        btn_obtainNumer.setText(buyersNumber);
        updateLabel();

    }
    
    private void updateLabel() {
        
        numberSelected.setText(touchNumb);
        btn_obtainName.setText(buyersName);
        btn_obtainNumer.setText(buyersNumber);
    
    }
    @FXML
    private void obtainName(ActionEvent event) {
    }

    @FXML
    private void obtainNumer(ActionEvent event) {

    }
    public void setOriginalButton(Button originalButton) {
        this.originalButton = originalButton;
    }
    @FXML
    private void check_isPaid(ActionEvent event) {
       changeButtonColor(BtnCheck_paid.isSelected(), Color.GREEN);
        usersToNumber(btn_obtainName.getText());
    
    }
    @FXML
    private void check_isReserved(ActionEvent event) {
        changeButtonColor(BtnCheck_reserved.isSelected(), Color.RED);
        usersToNumber(btn_obtainName.getText());
  
    }

    @FXML
    private void check_isAvailable(ActionEvent event) {
        changeButtonColor(BtnCheck_available.isSelected(), Color.WHITE);
        usersToNumber(btn_obtainName.getText());

    }

    private void usersToNumber(String userName) {
       // String number = null;
        for(String number: usersByNum.keySet()){
            List<String> userList =usersByNum.get(number);
            userList.add(userName) ;
            usersByNum.put(number,userList);
         }
    }


    @FXML
    private void AddBuyerToNumber(ActionEvent event) throws IOException {
        
        
         // Obtener el nombre y número del comprador
        touchNumb = numberSelected.getText();
        buyersName = btn_obtainName.getText();
        buyersNumber = btn_obtainNumer.getText();
        
        if (BtnCheck_paid.isSelected()) {
            changeButtonColor(true, Color.GREEN);
            currentUserState.setStatus(2);
        }
        else if (BtnCheck_reserved.isSelected()) {
                changeButtonColor(true, Color.RED);
                currentUserState.setStatus(1);
            } 
            else if (BtnCheck_available.isSelected()) {
                changeButtonColor(true, Color.WHITE);
                currentUserState.setStatus(0);
            }
        currentUserState.setNumberR(Integer.parseInt(touchNumb));
        currentUserState.setNameUser(buyersName);
        currentUserState.setNumberUser(buyersNumber);
        
        Stage stage = (Stage) btn_addBuyer.getScene().getWindow();
        stage.close();
   
    }

    @FXML
    private void generateQR_code(ActionEvent event) throws WriterException {
        
    touchNumb = numberSelected.getText();
    String buyersName1 = btn_obtainName.getText();
    String buyersNumber1 = btn_obtainNumer.getText();

    String content = "No." + touchNumb + "\nNombre: " + buyersName1 + "\nTelefono: " + buyersNumber1;

    int width = 200;
    int height = 200;

    try { 
        
        BitMatrix bitMatrix = QR_Code.generateQRCode(content, width, height);
       
        Image qrImage = QRImageConverter.convertBitMatrixToImage(bitMatrix); // Convertir el BitMatrix en una imagen compatible con JavaFX
        
        ImageView imageView = new ImageView(qrImage);// Mostrar la imagen del código QR en un ImageView
        
        Alert qrDialog = new Alert(AlertType.NONE);
        qrDialog.setTitle("Código QR No. "+ touchNumb);
        qrDialog.getDialogPane().setContent(imageView);
        qrDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE); 
        qrDialog.showAndWait();
    } catch (WriterException e) {
           e.printStackTrace();
        }
    }    

    private void changeButtonColor(boolean selected, Color color) {
         if (selected && originalButton != null) {
        originalButton.setStyle("-fx-background-color: " + color.toString().replace("0x", "#") + ";");
        } else if (originalButton != null) {
            originalButton.setStyle(""); // Restablecer el color del botón
        }
     }


}
