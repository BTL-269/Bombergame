package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class  BombermanGame extends Application {

    public static Stage stages = new Stage();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInterface.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stages.setScene(scene);
        stages.show();
    }
}
