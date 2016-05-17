package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

    TreeClass tree;
    RadioButton[] rbs;
    FilePane folderView;
    BorderPane mainLayout;

    public void start(Stage window) throws Exception{
        window.setTitle("Custom FileChooser");
        tree = new TreeClass();
        folderView = new FilePane();
        rbs = new RadioButton[3];
        rbs[0] = new RadioButton("Folder view");
        rbs[0].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("folderViewIcon.png"))));
        rbs[1] = new RadioButton("List view");
        rbs[1].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("listIcon.png"))));
        rbs[2] = new RadioButton("Table view");
        rbs[2].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("tableIcon.png"))));
        ToggleGroup toggleGroup =new ToggleGroup();
        rbs[0].setToggleGroup(toggleGroup);
        rbs[1].setToggleGroup(toggleGroup);
        rbs[2].setToggleGroup(toggleGroup);
        rbs[0].setSelected(true);
        tree.setComputerIcon(new Image(getClass().getResourceAsStream("mycomputerIcon.png")));
        tree.setHardDriveIcon(new Image(getClass().getResourceAsStream("diskIcon.png")));
        tree.setFlashDriveIcon(new Image(getClass().getResourceAsStream("flashDriveIcon.png")));
        tree.setDvdDriveIcon(new Image(getClass().getResourceAsStream("dvdDriveIcon.png")));
        tree.setFolderIcon(new Image(getClass().getResourceAsStream("folderIcon.png")));
        tree.setTree();
        mainLayout = new BorderPane();
        mainLayout.setLeft(tree.getTreeView());
        mainLayout.setTop(new HBox(5, rbs));
        mainLayout.setCenter(folderView.getListFolderPane());
        tree.getTreeView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                File f = new File(selectedItem.getValue());
                if(rbs[0].isSelected()){
                    folderView.setFolderView(f);
                    mainLayout.setCenter(folderView.getListFolderPane());
                }
                if(rbs[1].isSelected()) {
                    folderView.setListView(f);
                    mainLayout.setCenter(folderView.getListFolderPane());
                }
            }
        });
        window.setScene(new Scene(mainLayout, 800, 600));
        window.show();
    }

    private void setView(){
    }

    public static void run(String[] args){
        launch(args);
    }

}
