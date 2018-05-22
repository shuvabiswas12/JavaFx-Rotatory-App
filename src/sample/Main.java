package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int COUNT_LIMIT = 120000;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/home.fxml"));
        primaryStage.setTitle("Rotatory");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("/sample/assests/pill.png"));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        for (int i = 0; i < COUNT_LIMIT; i++) {
            double progress = (100 * i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }
    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, MyPreloader.class, args);

    }



   /* public static void main(String[] args) {
        launch(args);
    }*/
}
