package gov.iti.gui.controllers;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import gov.iti.gui.Main;
import gov.iti.gui.DB.FakeDB;
import gov.iti.gui.models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class loginController implements Initializable {
    @FXML
    private Button btnYalla;

    @FXML
    private ImageView imageUser;

    @FXML
    private TextField userName;
    @FXML
    private Label warningMessage;

    @FXML
    private void onYalla() {

        String name = userName.getText();
        String imageUrl = imageUser.getImage().getUrl();
        Person user = new Person(name, imageUrl);
        FakeDB.setUser(user);
        if (checkIsValid()) {

            try {
                Main.chatScene();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            warningMessage.setText("please enter valid name ");
            warningMessage.setVisible(true);
            warningMessage.setTextFill(Color.RED);
        }

    }

    @FXML
    private void onChooseImage(ActionEvent event) {
        Image image;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif",
                "*.jpeg");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            image = new Image(file.toURI().toString());
            imageUser.setImage(image);
        }
    }

    @FXML
    private void onImageClicked(MouseEvent event) {
        Image image;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif",
                "*.jpeg");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            image = new Image(file.toURI().toString());
            imageUser.setImage(image);
        }
    }

    @FXML
    private void onKeyTyped() {
        btnYalla.setDisable(userName.getText().isBlank());

    }

    @FXML
    private void onInputMethodTextChanged() {
        btnYalla.setDisable(userName.getText().isBlank());

    }

    private boolean checkIsValid() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(userName.getText());
        return (!matcher.matches());

    }

    @FXML
    void onMouseEntered(MouseEvent m) {
        ((Node) m.getSource()).getScene().setCursor(Cursor.HAND);
    }

    @FXML
    void onMouseExit(MouseEvent m) {
        ((Node) m.getSource()).getScene().setCursor(Cursor.DEFAULT);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        warningMessage.setVisible(false);
        btnYalla.setDisable(userName.getText().isBlank());

    }

}
