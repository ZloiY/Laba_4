package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ZloiY on 13.05.2016.
 */
public class App extends Application {

    public void start(Stage window) throws Exception{
        window.setTitle("Custom FileChooser");
        TreeClass tree = new TreeClass();
        FilePane folderView = new FilePane();
        tree.setComputerIcon(new Image(getClass().getResourceAsStream("mycomputerIcon.png")));
        tree.setHardDriveIcon(new Image(getClass().getResourceAsStream("diskIcon.png")));
        tree.setFlashDriveIcon(new Image(getClass().getResourceAsStream("flashDriveIcon.png")));
        tree.setDvdDriveIcon(new Image(getClass().getResourceAsStream("dvdDriveIcon.png")));
        tree.setFolderIcon(new Image(getClass().getResourceAsStream("folderIcon.png")));
        tree.setTree();
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(tree.getTreeView());
        tree.getTreeView().setOnMouseClicked(e ->{

        });
        window.setScene(new Scene(mainLayout, 800, 600));
        window.show();
    }

    public static void run(String[] args){
        launch(args);
    }

}
