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
    private TableColumn<TableData, String> nameColumn = new TableColumn<>("Name");
    private TableColumn<TableData, String> endFileColumn = new TableColumn<>("Extension");
    private TableColumn<TableData, String> sizeFileColumn = new TableColumn<>("Size");
    private TableColumn<TableData, String> lastFileChangedColumn = new TableColumn<>("Last time changed");

    public void setTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameFile"));
        endFileColumn.setCellValueFactory(new PropertyValueFactory<>("endFile"));
        sizeFileColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        lastFileChangedColumn.setCellValueFactory(new PropertyValueFactory<>("lastChangeFile"));
        table.getColumns().addAll(nameColumn, endFileColumn, sizeFileColumn, lastFileChangedColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void getItems(TableView<TableData> data){
        tbl.setAll(data.getItems());
        table.setItems(tbl);
    }

    public TableView<TableData> getTable() {
        return table;
    }

    public void setRandTableColumns (TableView<TableData> data){
        data.getColumns().addAll(nameColumn, endFileColumn, sizeFileColumn, lastFileChangedColumn);
    }
}
