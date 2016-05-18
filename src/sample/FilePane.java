package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sun.plugin2.jvm.RemoteJVMLauncher;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;

/**
 * Created by ZloiY on 17.05.2016.
 */
public class FilePane {
    private HBox listFolderPane;
    private BorderPane tablePane;
    private GridPane grid;
    private TextField fileNameTF;
    private File currentFile;
    private TableView<TableData> listFiles;
    private FolderTableView folderTableView;
    final IconView[] folder;

    FilePane() {
        folderTableView = new FolderTableView();
        folderTableView.setRandTableColumns(listFiles);
        tablePane = new BorderPane();
        listFolderPane = new HBox(10);
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(0, 10, 20, 10));
        ScrollPane sc = new ScrollPane(grid);
        sc.setFitToHeight(true);
        folder = new IconView[1];
        folder[0] = new IconView();
        fileNameTF = new TextField();
        listFolderPane.getChildren().addAll(grid, sc, fileNameTF);
    }

    public HBox getListFolderPane() {
        return listFolderPane;
    }

    public BorderPane getTablePane() {
        return tablePane;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public void setFolderView(File path) {
        grid.getChildren().remove(0, grid.getChildren().size());
        Integer folderRow = 0;
        Integer folderColumn = 0;
        for (File f : path.listFiles()) {
            if (f.isDirectory()) {
                folder[0] = new IconView(new ImageView(new Image(getClass().getResourceAsStream("folderIconBig.png"))), 70.0, 70.0);
                folder[0].setFolder(f);
                if (folderColumn < 5) {
                    grid.add(folder[0].getFolder(), folderColumn, folderRow);
                    folderColumn++;
                } else {
                    folderColumn = 0;
                    folderRow++;
                }
                folder[0].getFolder().setOnMouseClicked(e -> {
                    if (e.getButton().equals(MouseButton.PRIMARY)){
//                        folder[0].getFolder().setStyle("-fx-background-color: #4cafff;");
                        fileNameTF.setText(f.getName());
                        if(e.getClickCount() == 2){
                            currentFile = f;
                            setFolderView(f);
                        }
                    }
                });
            }
        }
    }

    public void setListView(File path) {
        grid.getChildren().remove(0, grid.getChildren().size());
        Integer folderRow = 0;
        for (File f : path.listFiles()) {
            if (f.isFile()) {
                folder[0] = new IconView(new ImageView(new Image(getClass().getResourceAsStream("fileIcon.png"))), 40.0, 40.0);
                folder[0].setFolderList(f);
                grid.add(folder[0].getListFolder(), 0, folderRow);
                folderRow++;
                folder[0].getListFolder().setOnMouseClicked(e -> {
                    if(e.getButton().equals(MouseButton.PRIMARY)) {
//                        folder[0].getListFolder().setStyle("-fx-background-color: #4cafff;");
                        fileNameTF.setText(f.getName());
                        if (e.getClickCount() == 2){
                            currentFile = f;
                        }
                    }
                });
            } else {
                folder[0] = new IconView(new ImageView(new Image(getClass().getResourceAsStream("folderIconBig.png"))), 40.0, 40.0);
                folder[0].setFolderList(f);
                grid.add(folder[0].getListFolder(), 0, folderRow);
                folderRow++;
                folder[0].getListFolder().setOnMouseClicked(e -> {
                    if(e.getButton().equals(MouseButton.PRIMARY)) {
                        fileNameTF.setText(f.getName());
//                        folder[0].getListFolder().setStyle("-fx-background-color: #4cafff;");
                        if (e.getClickCount() == 2) {
                            currentFile = f;
                            setListView(f);
                        }
                    }
                });
            }
        }
    }

    public void setTableView(File path){
        grid.getChildren().clear();
        for (File f: path.listFiles()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                listFiles.getItems().add(new TableData(f.getName(), getFileExtension(f), f.length(), sdf.toString()));
                Callback<TableColumn, TableCell> cellFactory =
                        new Callback<TableColumn, TableCell>() {
                            public TableCell call(TableColumn param) {
                                TableCell cell = new TableCell<TableData, String>(){
                                    public void updateItem(String item, boolean empty){
                                        super.updateItem(item, empty);
                                        setText(empty ? null: getString());
                                        setGraphic(null);
                                    }

                                    private String getString(){
                                        return getItem() == null ? "" : getItem().toString();
                                    }
                                };
                                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event)->{
                                    if (event.getClickCount() == 1){
                                        fileNameTF.setText(f.getName());
                                    }
                                    if (event.getClickCount() == 2){
                                        currentFile =f;
                                        setTableView(f);
                                    }
                                });
                                return cell;
                            }
                        };

        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

}
