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
            TreeItem<String> diskNode;
            if (fsv.getSystemTypeDescription(list.get(index)).equals(harDrive)){
                diskNode = new TreeItem<>(list.get(index).toString(), new ImageView(hardDriveIcon));
            }else {
                if (fsv.getSystemTypeDescription(list.get(index)).equals(flashDrive)) {
                    diskNode = new TreeItem<>(list.get(index).toString(), new ImageView(flashDriveIcon));
                }else{
                    if (fsv.getSystemTypeDescription(list.get(index)).equals(dvdDrive)){
                        diskNode = new TreeItem<>(list.get(index).toString(), new ImageView(dvdDriveIcon));
                    }else{
                        diskNode = new TreeItem<>(list.get(index).toString());
                    }
                }
            }
            String diskPath = diskNode.getValue();
            diskPath = diskPath.substring(0,2) + "/";
            File folderList = new File(diskPath);
            List<File> secList = new ArrayList<File>();
            folderSearch(folderList, diskNode);
            rootNode.getChildren().add(diskNode);
        }
        treeView = new TreeView<>(rootNode);
    }

    private void folderSearch(File folderList, TreeItem<String> rootNode){
        File folders = new File(folderList.getPath());
        Path path = FileSystems.getDefault().getPath(folders.getPath());
        List<File> secList = new ArrayList<File>();
        if (Files.isReadable(path) && !Files.isSymbolicLink(path)) {
            for (File f : folders.listFiles()) {
//                System.out.println(f.getPath());
                if (f.isDirectory()) {
                    folderSearch(f, new TreeItem<String>(f.getName(), new ImageView(folderIcon)));
                    secList.add(new File(f.getPath()));
                }
            }
            for (Integer folderIndex = 0; folderIndex < secList.size(); folderIndex++) {
                TreeItem<String> folderLeaf = new TreeItem<String>(secList.get(folderIndex).toString(), new ImageView(folderIcon));
                rootNode.getChildren().add(folderLeaf);
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
