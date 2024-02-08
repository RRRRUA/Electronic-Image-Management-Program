package model;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import controller.MainUIController;
import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FileTree {

    private MainUIController mainUI;
    private TreeView<PictureFile> treeView;
    private TreeItem<PictureFile> root;

    private final File[] rootPath = File.listRoots();//返回指示可用文件系统根目录的文件对象数组

    public FileTree(MainUIController mainUI, TreeView<PictureFile> treeView) {

        this.mainUI = mainUI;
        this.treeView = treeView;
        root = new TreeItem<PictureFile>(new PictureFile("root"));
        //树项展开
        root.setExpanded(true);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        buildFileTree();
        getSelected();
    }
    //创建系统树
    private void buildFileTree() {
        for (int i = 0; i < rootPath.length; i++) {
            FileTreeItem item = new FileTreeItem(new PictureFile(rootPath[i]));
            root.getChildren().add(item);
        }
    }

    public TreeView<PictureFile> gettreeView() {
        return treeView;
    }


    //显示目录文件下的图片

    private void getSelected() {
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<PictureFile>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<PictureFile>> observable, TreeItem<PictureFile> oldValue,
                                TreeItem<PictureFile> newValue) {
                mainUI.getFlowPane().getChildren().remove(0, mainUI.getFlowPane().getChildren().size());
                PictureFile pFile = treeView.getSelectionModel().getSelectedItem().getValue();
                if(pFile.isDirectory()) {
                    // 在合适的地方定义一个 Runnable 对象，用于在后台线程中执行图片加载和UI更新的操作
                    Runnable loadPicturesTask = () -> {
                        MainUIController.theFilePath = pFile.getImageFile().getAbsolutePath();
                        int total = 0;
                        int size = 0;

                        PictureFile[] pictureFiles = pFile.listPictures();
                        mainUI.clearPictures();

                        for (PictureFile pictureFile : pictureFiles) {
                            if (pictureFile.isPicture()) {
                                total++;
                                size += pictureFile.getImageFile().length();
                                try {
                                    PictureNode pictureNode = new PictureNode(pictureFile, mainUI);
                                    // 使用 Platform.runLater() 方法在 JavaFX 应用程序主线程中执行添加子节点的操作
                                    Platform.runLater(() -> {
                                        mainUI.getFlowPane().getChildren().add(pictureNode);
                                        mainUI.addPictures(pictureNode);
                                    });
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        // 使用 Platform.runLater() 方法在 JavaFX 应用程序主线程中执行 UI 更新操作
                        int finalTotal = total;
                        int finalSize = size;
                        Platform.runLater(() -> {
                            mainUI.showPicture();
                            mainUI.getText().setText(finalTotal + "张图片， 共" + finalSize + "KB");
                        });
                    };

                    // 创建并启动后台线程来执行图片加载和UI更新的操作
                    Thread thread = new Thread(loadPicturesTask);
                    thread.start();
                }
            }
        });
    }
}
