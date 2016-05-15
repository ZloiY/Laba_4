package sample;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZloiY on 15.05.2016.
 */
public class TreeClass {
    private TreeView<String> treeView;
    private FileSystemView fsv;
    private Image computerIcon;
    private Image hardDriveIcon;
    private Image dvdDriveIcon;
    private Image flashDriveIcon;
    private Image folderIcon;

    public void setTree(){
        File[] fileList = File.listRoots();
        List<File> list = new ArrayList<>();
        Integer i = 0;
        for (File f:fileList){
            list.add(new File(f.getPath()));
            i++;
        }
        Node myComputerIcon = new ImageView(computerIcon);
        TreeItem<String> rootNode = new TreeItem<>("My Computer",myComputerIcon);
        fsv = FileSystemView.getFileSystemView();
        rootNode.setExpanded(true);
        String harDrive = fsv.getSystemTypeDescription(list.get(0));
        String dvdDrive = fsv.getSystemTypeDescription(list.get(2));
        String flashDrive = fsv.getSystemTypeDescription(list.get(3));
        for (Integer index =0; index < list.size(); index++){
            TreeItem<String> diskLeaf;
            if (fsv.getSystemTypeDescription(list.get(index)).equals(harDrive)){
                diskLeaf = new TreeItem<>(list.get(index).toString(), new ImageView(hardDriveIcon));
            }else {
                if (fsv.getSystemTypeDescription(list.get(index)).equals(flashDrive)) {
                    diskLeaf = new TreeItem<>(list.get(index).toString(), new ImageView(flashDriveIcon));
                }else{
                    if (fsv.getSystemTypeDescription(list.get(index)).equals(dvdDrive)){
                        diskLeaf = new TreeItem<>(list.get(index).toString(), new ImageView(dvdDriveIcon));
                    }else{
                        diskLeaf = new TreeItem<>(list.get(index).toString());
                    }
                }
            }
            rootNode.getChildren().add(diskLeaf);
        }
        treeView = new TreeView<>(rootNode);
    }

    public TreeView getTreeView(){
        return treeView;
    }

    public void setComputerIcon(Image computerIcon) {
        this.computerIcon = computerIcon;
    }

    public void setHardDriveIcon(Image hardDriveIcon) {
        this.hardDriveIcon = hardDriveIcon;
    }

    public void setDvdDriveIcon(Image dvdDriveIcon) {
        this.dvdDriveIcon = dvdDriveIcon;
    }

    public void setFlashDriveIcon(Image flashDriveIcon) {
        this.flashDriveIcon = flashDriveIcon;
    }

    public void setFolderIcon(Image folderIcon) {
        this.folderIcon = folderIcon;
    }
}
