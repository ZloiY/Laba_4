package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * Created by ZloiY on 17.05.2016.
 */
public class FilePane {
    private GridPane listFolderPane;
    private BorderPane tablePane;
    enum viewType{
        FOLDER,
        LIST,
        TABLE
    }

    public GridPane getListFolderPane() {
        return listFolderPane;
    }

    public BorderPane getTablePane() {
        return tablePane;
    }

    public void setView(File path){
        FolderView folder;
        Integer folderRow = 0;
        Integer folderColumn = 0;
        for (File f:path.listFiles()){
            if(f.isDirectory()){
                folder = new FolderView();
                folder.setFolderIcon(new ImageView(new Image(getClass().getResourceAsStream("folderIconBig.png"))));
                folder.setFolder(f.getName());
                if(folderColumn < 5) {
                    listFolderPane.add(folder.getFolder(), folderColumn, folderRow);
                    folderColumn++;
                }else{
                    folderColumn = 0;
                    folderRow++;
                }
            }
        }
    }

}
