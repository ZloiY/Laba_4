package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by ZloiY on 13.05.2016.
 */
public class App extends Application {

    public void start(Stage window) throws Exception{
        window.setTitle("Custom FileChooser");
        BorderPane mainLayout = new BorderPane();
        window.setScene(new Scene(mainLayout, 800, 600));
        window.show();
    }

    public static void run(String[] args){
        launch(args);
    }

}
