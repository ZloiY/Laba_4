package sample;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
        for (File f:fileList){
            list.add(new File(f.getPath()));
        }
        Node myComputerIcon = new ImageView(computerIcon);
        TreeItem<String> rootNode = new TreeItem<>("My Computer",myComputerIcon);
        fsv = FileSystemView.getFileSystemView();
        rootNode.setExpanded(true);
        String harDrive = fsv.getSystemTypeDescription(list.get(0));
        String dvdDrive = fsv.getSystemTypeDescription(list.get(2));
        String flashDrive = fsv.getSystemTypeDescription(list.get(3));
        for (Integer index =0; index < list.size(); index++){
            File folderList;
            if (fsv.getSystemTypeDescription(list.get(index)).equals(harDrive)){
                TreeItem<String> diskNode = new TreeItem<>(list.get(index).toString(), new ImageView(hardDriveIcon));
                folderList = new File(diskNode.getValue());
                folderSearch(folderList, diskNode);
                rootNode.getChildren().add(diskNode);
            }else {
                if (fsv.getSystemTypeDescription(list.get(index)).equals(flashDrive)) {
                    TreeItem<String> flashDriveNode = new TreeItem<>(list.get(index).toString(), new ImageView(flashDriveIcon));
                    folderList = new File(flashDriveNode.getValue());
                    folderSearch(folderList, flashDriveNode);
                    rootNode.getChildren().add(flashDriveNode);
                }else{
                    if (fsv.getSystemTypeDescription(list.get(index)).equals(dvdDrive)){
                        TreeItem<String> cdDriveNode = new TreeItem<>(list.get(index).toString(), new ImageView(dvdDriveIcon));
                        folderList = new File(cdDriveNode.getValue());
                        folderSearch(folderList, cdDriveNode);
                        rootNode.getChildren().add(cdDriveNode);
                    }else{
                        TreeItem<String> Node = new TreeItem<>(list.get(index).toString());
                    }
                }
            }
//            String diskPath = diskNode.getValue();
//            diskPath = diskPath.substring(0,2) + "/";
        }
        treeView = new TreeView<>(rootNode);
    }

    private void folderSearch(File folderList, TreeItem<String> rootNode){
        File folders = new File(folderList.getPath());
        Path path = FileSystems.getDefault().getPath(folders.getPath());
        List<File> secList = new ArrayList<File>();
        if (Files.isReadable(path) && !Files.isSymbolicLink(path)) {
            TreeItem<String> folderNode;
            for (File f : folders.listFiles()) {
//                System.out.println(f.getPath());
                if (f.isDirectory()) {
                    secList.add(new File(f.getPath()));
                }
            }
            for (Integer folderIndex = 0; folderIndex < secList.size(); folderIndex++) {
                folderNode = new TreeItem<String>(secList.get(folderIndex).toString(), new ImageView(folderIcon));
                folderSearch(secList.get(folderIndex), folderNode);
                rootNode.getChildren().add(folderNode);
            }
        }
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
