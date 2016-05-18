package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by ZloiY on 18.05.2016.
 */
public class FolderTableView {
    private TableView<TableData> table = new TableView<>();
    private ObservableList<TableData> tbl = FXCollections.observableArrayList();
    private TableColumn<TableData, String> nameColumn = new TableColumn<>("Folder name");
    private TableColumn<TableData, String> endFileColumn = new TableColumn<>(".*");
    private TableColumn<TableData, String> sizeFileColumn = new TableColumn<>("Size");
    private TableColumn<TableData, String> lastFileChangedColumn = new TableColumn<>("Last time changed");

    public void setTable(TableView<TableData> data){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameFile"));
        endFileColumn.setCellValueFactory(new PropertyValueFactory<>("endFile"));
        sizeFileColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        lastFileChangedColumn.setCellValueFactory(new PropertyValueFactory<>("lastChanged"));
        table.setItems(getItems(data));
        table.getColumns().addAll(nameColumn, endFileColumn, sizeFileColumn, lastFileChangedColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private ObservableList<TableData> getItems(TableView<TableData> data){
        tbl.setAll(data.getItems());
        return tbl;
    }

    public void setRandTableColumns (TableView<TableData> table){
        table.getColumns().addAll(nameColumn, endFileColumn, sizeFileColumn, lastFileChangedColumn);
    }
}
