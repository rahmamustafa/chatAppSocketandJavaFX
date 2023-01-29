package gov.iti.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    Scene loginScene;
    static Scene chatScene;
    static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));

        Parent loginPane = loader.load();
        
        loginScene = new Scene(loginPane);
        // loginScene.getStylesheets().add(getClass().getResource("/style/login.css").toExternalForm());
        this.stage = stage;
        stage.setScene(loginScene);
        stage.setResizable(false);
        stage.setTitle("JESTS CHAT APP");
        stage.show();

    }
    public static void chatScene() throws IOException{
        FXMLLoader chatLoader = new FXMLLoader(Main.class.getResource("/view/chat.fxml"));
        Parent chatPane = chatLoader.load();
        chatScene =   new Scene(chatPane);
        stage.setScene(chatScene);
    }
    public static void loginScene() throws IOException{
        FXMLLoader chatLoader = new FXMLLoader(Main.class.getResource("/view/login.fxml"));
        Parent chatPane = chatLoader.load();
        chatScene =   new Scene(chatPane);
        stage.setScene(chatScene);
    }

    public static void main(String[] args) {
        launch();
    }

}