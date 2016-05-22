package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.File;
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
    private TextField tableNameTF;
    private ChoiceBox<String> folderListCB;
    private ChoiceBox<String> tableCB;
    private SimpleDateFormat sdf;
    private Button openListBtn;
    private Button openTableBtn;
    private Callback<TableColumn<TableData, String>, TableCell<TableData, String>> cellFactory;
    final IconView[] folder;

    FilePane() {
        listFiles = new TableView<>();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        folderTableView = new FolderTableView();
        folderTableView.setRandTableColumns(listFiles);
        folderTableView.setTable();
        cellFactory =
                new Callback<TableColumn<TableData, String>, TableCell<TableData, String>>() {
                    public TableCell call(TableColumn p) {
                        TableCell<TableData, String> cell = new TableCell<TableData, String>() {
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                setText(empty ? null : getString());
                                setGraphic(null);
                            }
                            private String getString() {
                                return getItem() == null ? "" : getItem().toString();
                            }
                        };
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent event) {
                                if (event.getClickCount() == 1) {
                                    File file = new File(getCurrentFile() + "/" + cell.getText());
                                    if (file.isFile())
                                    tableNameTF.setText(cell.getText());
                                }
                                if (event.getClickCount() == 2){
                                    File currentFile = new File(getCurrentFile() + "/"+cell.getText());
                                    setCurrentFile(currentFile);
                                    App.currPath.setText(currentFile.getPath());
                                    setTableView(currentFile);
                                }
                            }
                        });
                        return cell;
                    }
                };

        tablePane = new BorderPane();
        folderListCB = new ChoiceBox<>();
        folderListCB.getItems().addAll("*", "txt", "xml", "sys");
        folderListCB.setValue("*");
        folderListCB.setOnAction(e ->{
            setListView(getCurrentFile());
        });
        tableCB = new ChoiceBox<>();
        tableCB.getItems().addAll("*", "txt", "xml", "sys");
        tableCB.setValue("*");
        tableCB.setOnAction(e ->{
            setTableView(getCurrentFile());
        });
        tablePane.setCenter(folderTableView.getTable());
        listFolderPane = new HBox(10);
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(0, 10, 20, 10));
        ScrollPane sc = new ScrollPane(grid);
        sc.setFitToHeight(true);
        openListBtn =new Button("Open");
        openListBtn.setOnAction(e ->{
            File finaFile = new File(currentFile.getPath() + "/" + fileNameTF.getText());
            App.currPath.setText(finaFile.getPath());
        });
        openTableBtn = new Button("Open");
        openTableBtn.setOnAction(e ->{
            File finaFile = new File(currentFile.getPath() + "/" + tableNameTF.getText());
            App.currPath.setText(finaFile.getPath());
        });
        folder = new IconView[1];
        folder[0] = new IconView();
        fileNameTF = new TextField();
        fileNameTF.setOnAction(e ->{
           setListView(getCurrentFile());
        });
        tableNameTF = new TextField();
        tableNameTF.setOnAction(e ->{
            setTableView(getCurrentFile());
        });
        listFolderPane.getChildren().addAll(grid, sc, new VBox(fileNameTF, folderListCB, openListBtn));
        tablePane.setRight(new VBox(tableNameTF, tableCB, openTableBtn));
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
                folder[0] = new IconView(new ImageView(new Image("res/folderIconBig.png")), 70.0, 70.0);
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
                            App.currPath.setText(f.getPath());
                        }
                    }
                });
            }
        }
    }

    public void setListView(File path) {
        grid.getChildren().clear();
        Integer folderRow = 0;
        for (File f : path.listFiles()) {
            if (f.isFile() && (getFileExtension(f).equals(folderListCB.getValue()) || folderListCB.getValue().equals("*"))) {
                folder[0] = new IconView(new ImageView(new Image("res/fileIcon.png")), 40.0, 40.0);
                folder[0].setFolderList(f);
                folder[0].getListFolder().setOnMouseClicked(e -> {
                    if(e.getButton().equals(MouseButton.PRIMARY)) {
//                        folder[0].getListFolder().setStyle("-fx-background-color: #4cafff;");
                        fileNameTF.setText(f.getName());
                        if (e.getClickCount() == 2){
                            currentFile = f;
                        }
                    }
                });
                if (fileNameTF.getText().isEmpty()){
                grid.add(folder[0].getListFolder(), 0, folderRow);
                folderRow++;
                }else{
                    if (f.getName().toLowerCase().contains(fileNameTF.getText())){
                        grid.add(folder[0].getListFolder(), 0, folderRow);
                        folderRow++;
                    }
                }
            } else {
                if (!f.isFile()) {
                    folder[0] = new IconView(new ImageView(new Image("res/folderIconBig.png")), 40.0, 40.0);
                    folder[0].setFolderList(f);
                    grid.add(folder[0].getListFolder(), 0, folderRow);
                    folderRow++;
                    folder[0].getListFolder().setOnMouseClicked(e -> {
                        if (e.getButton().equals(MouseButton.PRIMARY)) {
                            fileNameTF.setText(f.getName());
//                        folder[0].getListFolder().setStyle("-fx-background-color: #4cafff;");
                            if (e.getClickCount() == 2) {
                                currentFile = f;
                                setListView(f);
                                App.currPath.setText(f.getPath());
                            }
                        }
                    });
                }
            }
        }
    }

    public void setTableView(File path){
        listFiles.getItems().clear();
        folderTableView.getTable().getItems().clear();
        for (File f: path.listFiles()){
            if (f.isFile() && (getFileExtension(f).equals(tableCB.getValue()) || tableCB.getValue().equals("*"))) {
                if (tableNameTF.getText().isEmpty()) {
                    listFiles.getItems().add(new TableData(f.getName(), getFileExtension(f), f.length(), sdf.format(f.lastModified())));
                }else{
                    if (f.getName().toLowerCase().contains(tableNameTF.getText())){
                        listFiles.getItems().add(new TableData(f.getName(), getFileExtension(f), f.length(), sdf.format(f.lastModified())));
                    }
                }
            }else{
                if (!f.isFile()){
                    listFiles.getItems().add(new TableData(f.getName(), getFileExtension(f), f.length(), sdf.format(f.lastModified())));
                }
            }

        }
        folderTableView.getItems(listFiles);
        folderTableView.getNameColumn().setCellFactory(cellFactory);
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "Folder";
    }

}
