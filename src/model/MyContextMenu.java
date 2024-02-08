package model;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import action.*;
import controller.MainUIController;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import service.ChangeService;

public class MyContextMenu {
    MainUIController mainUI;
    ContextMenu contextMenu;
    public MyContextMenu(Node node,MainUIController mainUI,boolean choice) {
        this.mainUI = mainUI;
        if(choice) {
            PictureMenu(node);
        }
        nullMenu(node);

    }
    //弹出菜单栏状态
    public void PictureMenu(Node node) {
        contextMenu = new ContextMenu();
        MenuItem open = new MenuItem("打开");
        MenuItem copy = new MenuItem("复制");
        MenuItem cut = new MenuItem("剪切");
        MenuItem rename = new MenuItem("重命名");
        MenuItem delete = new MenuItem("删除");
        MenuItem shuxing = new MenuItem("属性");

        contextMenu.getItems().addAll(open,copy,delete,cut,rename,shuxing);
        //为菜单项设置点击action
        open.setOnAction(e->{
            new OpenAction();//Lambda
        });
        copy.setOnAction(e->{
            new CopyAction();
        });
        cut.setOnAction(e->{
            new CutAction();
        });
        rename.setOnAction(e->{
            new RenameAction(mainUI);
        });
        delete.setOnAction(e->{
            new DeleteAction(mainUI);
        });
        shuxing.setOnAction(e->{
            new shuxingAction();
        });
        //鼠标单击目标
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {//鼠标单击并释放
            if (e.getButton() == MouseButton.SECONDARY)//右键单击
                contextMenu.show(node, e.getScreenX(), e.getScreenY());
            else {
                if (contextMenu.isShowing())
                    contextMenu.hide();
            }
        });
    }
    //鼠标右键选中空白事件
    public void nullMenu(Node node) {
        ContextMenu mouseRightClickMenu = new ContextMenu();
        MenuItem paste = new MenuItem("粘贴");
        MenuItem all = new MenuItem("全选");
        MenuItem sort = new MenuItem("排序");
        mouseRightClickMenu.getItems().addAll(paste, all, sort);

        paste.setOnAction(e -> {
            new PasteAction(mainUI);
        });
        all.setOnAction(e -> {//将面板上所有图片置选中状态
            for (Node childrenNode : mainUI.getFlowPane().getChildren()) {
                if (childrenNode instanceof PictureNode) {
                    ((PictureNode) childrenNode).setSelected(true);
                }
            }
        });
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node clickNode = e.getPickResult().getIntersectedNode();
            if (clickNode instanceof FlowPane && !(clickNode instanceof PictureNode)
                    && !(clickNode instanceof Text)) {// 鼠标点击非图片节点
                PictureNode.clearSelected();// 清空已选

                if (e.getButton() == MouseButton.SECONDARY) {// 鼠标右键
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
                    if (files.size() <= 0) {
                        paste.setDisable(true);// 粘贴按钮不可用
                    } else {
                        paste.setDisable(false);
                    }
                    mouseRightClickMenu.show(node, e.getScreenX(), e.getScreenY());
                } else {
                    if (mouseRightClickMenu.isShowing()) {
                        mouseRightClickMenu.hide();
                    }
                }
            } else {
                if (mouseRightClickMenu.isShowing()) {
                    mouseRightClickMenu.hide();
                }
            }
        });
        sort.setOnAction(e ->
                new SortAction(mainUI));
    }
}
