package sample;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;

/**
 * Created by ZloiY on 17.05.2016.
 */
public class IconView {
    private HBox listFolder;
    private VBox folder;
    private ImageView folderIcon;
    private File f;

    public VBox getFolder() {
        return folder;
    }

    public HBox getListFolder() {
        return listFolder;
    }

    public ImageView getFolderIcon() {
        return folderIcon;
    }

    public File getF() {

        return f;
    }

    IconView(){}

    IconView(ImageView folderIcon, double height, double width){
        this.folderIcon = folderIcon;
        this.folderIcon.setFitHeight(height);
        this.folderIcon.setFitWidth(width);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GREY);
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        folderIcon.setEffect(shadow);
    }

    public void setFolder(File file){
        f = new File(file.getPath());
        folder = new VBox(10);
        Label name = new Label(f.getName());
        name.setMaxWidth(folderIcon.getFitWidth());
        folder.getChildren().addAll(folderIcon, name);
    }

    public void setFolderList(File file){
        f = new File(file.getPath());
        listFolder = new HBox(10);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GREY);
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        folderIcon.setEffect(shadow);
        Label name = new Label(f.getName());
        listFolder.getChildren().addAll(folderIcon, name);
    }
}
