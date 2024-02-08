package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class FileTreeItem extends TreeItem<PictureFile> {
    //从本地系统获取动态树
    private boolean isLeaf;
    private boolean isFirstChildren = true;
    private boolean isFirstLeaf = true;

    public FileTreeItem(PictureFile pictureFile) {
        super(pictureFile);
    }

    @Override
    public ObservableList<TreeItem<PictureFile>> getChildren() {
        if (isFirstChildren) {
            isFirstChildren = false;
            //将构建的子节点列表设置为当前节点的子节点列表
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    @Override
    public boolean isLeaf() {
        if (isFirstLeaf) {
            isFirstLeaf = false;
            PictureFile pictureFile = getValue();
            PictureFile[] pictureFiles = pictureFile.listPictures();
            if (pictureFiles == null ||pictureFiles.length == 0 ) {
                isLeaf = true;
            } else {
                isLeaf = true;
                //判断是否存在子目录
                for (PictureFile pictureFile2 : pictureFiles) {
                    if (pictureFile2.isDirectory()) {
                        isLeaf = false;
                    }
                }
            }

        }
        return isLeaf;
    }
    private ObservableList<TreeItem<PictureFile>> buildChildren(TreeItem<PictureFile> TreeItem) {
        PictureFile pictureFile = TreeItem.getValue();
        if (pictureFile != null && pictureFile.isDirectory()) {
            //获取该文件夹下的所有文件
            PictureFile[] pictureFiles = pictureFile.listPictures();
            if (pictureFiles != null && pictureFiles.length != 0) {
                ObservableList<TreeItem<PictureFile>> children = FXCollections.observableArrayList();
                for (PictureFile childFile : pictureFiles) {
                    if (childFile.isHidden() || childFile.isFile()) {
                        continue;
                    }
                    children.add(new FileTreeItem(childFile));
                }
                return children;
            }
        }
        return FXCollections.emptyObservableList();
    }
}