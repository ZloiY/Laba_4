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
        Button openBtn = new Button("open");
        openBtn.setOnAction(e ->{
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(window);
        });
        File[] fileList = File.listRoots();
        List<File> list = new ArrayList<>();
        Integer i = 0;
        for (File f:fileList){
            list.add(new File(f.getPath()));
            i++;
        }
        Node myComputerIocn = new ImageView(new Image(getClass().getResourceAsStream("mycomputerIcon.png")));
        Image diskIcon = new Image(getClass().getResourceAsStream("diskIcon.png"));
        TreeItem<String> rootNode = new TreeItem<>("My Computer",myComputerIocn);
        rootNode.setExpanded(true);
        for (Integer index =0; index < list.size(); index++){
            TreeItem<String> diskLeaf = new TreeItem<>(list.get(index).toString(), new ImageView(diskIcon));
            rootNode.getChildren().add(diskLeaf);
        }
        TreeView<String> treeView = new TreeView<>(rootNode);
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(treeView);
        mainLayout.setBottom(openBtn);
        window.setScene(new Scene(mainLayout, 800, 600));
        window.show();
    }

    public static void run(String[] args){
        launch(args);
    }

}
