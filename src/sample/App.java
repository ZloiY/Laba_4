package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
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

    private TreeClass tree;
    private RadioButton[] rbs;
    private FilePane folderView;
    private BorderPane mainLayout;
    private File treeFile;
    private Button openBtn;
    private File file;
    private Stage window;
    public static Label currPath;


    public void start(Stage window) throws Exception{
        window.setTitle("Custom FileChooser");
        setWindow();
        window.setScene(new Scene(mainLayout, 800, 600));
        window.show();
    }

    private void setTreeView(){
        for (Integer index =0; index < tree.getTreeView().getRoot().getChildren().size(); index++){
            TreeItem<String> selectedFolder = (TreeItem<String>) tree.getTreeView().getRoot().getChildren().get(index);
            if(selectedFolder.equals(folderView.getCurrentFile().getPath())){
                selectedFolder.setExpanded(true);
            }
        }
    }

    public String getFilePath(){
        return currPath.getText();
    }

    public File setWindow(){
        window = new Stage();
        window.setTitle("Custom FileChooser");
        tree = new TreeClass();
        openBtn = new Button("Open");
        file = null;
        folderView = new FilePane();
        rbs = new RadioButton[3];
        rbs[0] = new RadioButton("Folder view");
        rbs[0].setGraphic(new ImageView(new Image("res/folderViewIcon.png")));
        rbs[1] = new RadioButton("List view");
        rbs[1].setGraphic(new ImageView(new Image("res/listIcon.png")));
        rbs[2] = new RadioButton("Table view");
        rbs[2].setGraphic(new ImageView(new Image("res/tableIcon.png")));
        currPath = new Label("My computer");
        ToggleGroup toggleGroup =new ToggleGroup();
        rbs[0].setToggleGroup(toggleGroup);
        rbs[1].setToggleGroup(toggleGroup);
        rbs[2].setToggleGroup(toggleGroup);
        rbs[0].setSelected(true);
        Button upFolderButton = new Button("",new ImageView(new Image("res/upfolderIcon.png")));
        Button homeFolder = new Button("", new ImageView(new Image("res/homefolderIcon.png")));
        tree.setComputerIcon(new Image("res/mycomputerIcon.png"));
        tree.setHardDriveIcon(new Image("res/diskIcon.png"));
        tree.setFlashDriveIcon(new Image("res/flashDriveIcon.png"));
        tree.setDvdDriveIcon(new Image("res/dvdDriveIcon.png"));
        tree.setFolderIcon(new Image("res/folderIcon.png"));
        tree.setTree();
        mainLayout = new BorderPane();
        mainLayout.setLeft(tree.getTreeView());
        HBox viewHBox = new HBox(10);
        HBox pathHBox = new HBox(50);
        viewHBox.setAlignment(Pos.CENTER_LEFT);
        viewHBox.getChildren().addAll(rbs);
        viewHBox.getChildren().add(upFolderButton);
        viewHBox.getChildren().add(homeFolder);
        pathHBox.getChildren().addAll(currPath, openBtn);
        mainLayout.setTop(viewHBox);
        mainLayout.setCenter(folderView.getListFolderPane());
        mainLayout.setBottom(pathHBox);
        window.setScene(new Scene(mainLayout, 800, 600));
        window.show();
        openBtn.setOnAction(e ->{
            file = new File(currPath.getText());
            window.close();
        });
        homeFolder.setOnAction(e ->{
            File homeFile = new File(System.getProperty("user.home"));
            folderView.setCurrentFile(homeFile);
            if (rbs[0].isSelected()){
                setFolderView(homeFile);
            }
            if(rbs[1].isSelected()){
                setListView(homeFile);
            }
            if(rbs[2].isSelected()){
                setTableView(homeFile);
            }
            currPath.setText(homeFile.getPath());
        });
        rbs[0].setOnAction(e ->{
            if (folderView.getCurrentFile() == null){
                folderView.setFolderView(treeFile);
            }else {
                setFolderView(folderView.getCurrentFile());
            }
            currPath.setText(folderView.getCurrentFile().getPath());
        });
        rbs[1].setOnAction(e ->{
            if (folderView.getCurrentFile() == null){
                folderView.setListView(treeFile);
            }else {
                setListView(folderView.getCurrentFile());
            }
            currPath.setText(folderView.getCurrentFile().getPath());
        });
        rbs[2].setOnAction(e ->{
            if(folderView.getCurrentFile() == null){
                folderView.setTableView(treeFile);
            }else{
                setTableView(folderView.getCurrentFile());
            }
            currPath.setText(folderView.getCurrentFile().getPath());
        });
        tree.getTreeView().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                treeFile = new File(selectedItem.getValue());
                tree.folderSearch(treeFile, selectedItem);
                if(rbs[0].isSelected()){
                    folderView.setFolderView(treeFile);
//                    setTreeView();
                    mainLayout.setCenter(folderView.getListFolderPane());
                }
                if(rbs[1].isSelected()) {
                    folderView.setListView(treeFile);
                    mainLayout.setCenter(folderView.getListFolderPane());
                }
                if(rbs[2].isSelected()){
                    folderView.setTableView(treeFile);
                    mainLayout.setCenter(folderView.getTablePane());
                }
                folderView.setCurrentFile(treeFile);
                currPath.setText(treeFile.getPath());
            }
        });
        upFolderButton.setOnAction(e -> {
            File prevFolder;
            if (folderView.getCurrentFile() == null){
                prevFolder = treeFile.getParentFile();
                folderView.setCurrentFile(prevFolder);
            }else{
                prevFolder = folderView.getCurrentFile().getParentFile();
                folderView.setCurrentFile(prevFolder);
            }
            if (rbs[0].isSelected()){
                setFolderView(prevFolder);
            }
            if (rbs[1].isSelected()){
                setListView(prevFolder);
            }
            if (rbs[2].isSelected()){
                setTableView(prevFolder);
            }
            currPath.setText(prevFolder.getPath());
        });
        return file;
    }

    public File getFileFunction(){
        file = new File(getFilePath());
        return file;
    }


    private void setFolderView(File currentFile){
        folderView.setFolderView(currentFile);
        mainLayout.setCenter(folderView.getListFolderPane());
    }

    private void setListView(File currentFile){
        folderView.setListView(currentFile);
        mainLayout.setCenter(folderView.getListFolderPane());
    }

    private  void setTableView(File currentFile){
        folderView.setTableView(currentFile);
        mainLayout.setCenter(folderView.getTablePane());
    }

    public static void run(String[] args){
        launch(args);
    }

}
