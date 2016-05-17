package sample;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by ZloiY on 17.05.2016.
 */
public class FolderView{
    private HBox folder;
    private ImageView folderIcon;

    public void setFolderIcon(ImageView folderIcon) {
        this.folderIcon = folderIcon;
    }

    public HBox getFolder() {
        return folder;
    }

    FolderView(){};

    public void setFolder(String folderName){
        folder = new HBox(10);
        Label name = new Label(folderName);
        folder.getChildren().addAll(folderIcon, name);
    }
}
