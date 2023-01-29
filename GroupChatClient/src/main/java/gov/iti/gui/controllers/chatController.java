package gov.iti.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import gov.iti.gui.Client;
import gov.iti.gui.Main;

import gov.iti.gui.DB.FakeDB;
import gov.iti.gui.models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class chatController implements Initializable {
    @FXML
    private Button btnSend;
    @FXML
    private VBox vbMessagesContainer;
    @FXML
    private Label userName;
    @FXML
    private Circle userImage;
    @FXML
    private TextField tfSendMessage;
    @FXML
    private HBox messageSent;
    @FXML
    private ScrollPane scrollPane;
    Person user;

    String message;
    String friendName;
    String friendImageUrl;
    Client client = new Client(this);
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

    @FXML
    void onBack() {
        try {
            Main.loginScene();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        client.sendMessage("bye");

    }

    @FXML
    void sendMessage() {
        if (!tfSendMessage.getText().isBlank()) {
            vbMessagesContainer.getChildren()
                    .add(createSentMesseage(tfSendMessage.getText(), user.getImageUrl(), "right"));
            scrollPane.setVvalue(scrollPane.getVmax());
            
            client.sendMessage(tfSendMessage.getText());
            tfSendMessage.clear();

        }

    }
    @FXML
    public void acceptGroupMessage(String message) {
        if (!message.isBlank()) {
            vbMessagesContainer.getChildren()
                    .add(createSentMesseage(message, user.getImageUrl(), "left"));
            scrollPane.setVvalue(scrollPane.getVmax());
        }

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
        user = FakeDB.getUser();
        String name = user.getName();
        String imageUrl = user.getImageUrl();
        userName.setText(name);
        userImage.setFill(new ImagePattern(new Image(imageUrl)));
    }

    public HBox createSentMesseage(String messageText, String imageUrl, String alignment) {
        Label messageLabel = new Label(messageText);
        messageLabel.setTextAlignment(TextAlignment.RIGHT);


        Circle imageContainer = new Circle(18);
       

        Text date =new Text(simpleDateFormat.format(new Date()));


        HBox hBox = new HBox();
        VBox vBox = new VBox();


        hBox.getStyleClass().add("message-container");
        if (alignment == "right") {
            messageLabel.getStyleClass().add("messageRight");
            vBox.getChildren().addAll(messageLabel);
            Image image = new Image(imageUrl);
            imageContainer.setFill(new ImagePattern(image));
            hBox.getChildren().addAll(new VBox(vBox,date), imageContainer);
            hBox.setAlignment(Pos.TOP_RIGHT);
            vBox.setAlignment(Pos.TOP_RIGHT);
            vBox.getStyleClass().add("messageRight");


        } else if (alignment == "left") {
            messageLabel.getStyleClass().add("messageLeft");
            vBox.getChildren().addAll(new Text(friendName),messageLabel);
            Image image = new Image(friendImageUrl);
            imageContainer.setFill(new ImagePattern(image));
            hBox.getChildren().addAll(imageContainer, new VBox(vBox,date));
            hBox.setAlignment(Pos.TOP_LEFT);
            vBox.setAlignment(Pos.TOP_LEFT);
            vBox.getStyleClass().add("messageLeft");

        }
        hBox.setSpacing(5);
        //Insets(double top, double right, double bottom, double left)
        vBox.setPadding(new Insets(0,5,0,10));

        return hBox;

    }
    public void setFriendName(String friendName){
        this.friendName = friendName;
    }
    public void setFriendImage(String friendImageUrl){
        this.friendImageUrl = friendImageUrl;
    }
    public void welcomeMessage(String welcomeMessage){
        Text middleText = new Text(welcomeMessage);
        Separator separator = new Separator();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().addAll(middleText);
        vbMessagesContainer.getChildren()
                    .add(hBox);
    }

}
