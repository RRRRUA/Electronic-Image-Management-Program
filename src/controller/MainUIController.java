package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import action.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.*;
import service.ChangeService;
import service.PaneListener;
import javafx.scene.Node;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import action.CopyAction;
import action.DeleteAction;
import action.OpenAction;
import action.PasteAction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.FlowPane;

public class MainUIController implements Initializable {

    private MainUIController mainUI;

    public void setPictures(ArrayList<PictureNode> pictures) {
        this.pictures = pictures;
    }

    private ArrayList<PictureNode> pictures;
    private ArrayList<File> files;
    public static String theFilePath;
    @FXML
    private TreeView<PictureFile> treeview;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Text text;
    @FXML
    private Text textTwo;

    public TextField getMohusearch() {
        return mohusearch;
    }

    @FXML
    private TextField mohusearch;
@FXML
    private Button mohu;


    public MainUIController() {
        mainUI = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
    }

    public void initData() {
        pictures = new ArrayList<>();
        treeview = new FileTree(mainUI, treeview).gettreeView();
        // showPicture();
        new PaneListener(flowPane,mainUI);
        new MyContextMenu(flowPane, mainUI,false);
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public  ObservableList<Node> getFlowPaneChildren() {
        return flowPane.getChildren();
    }
    public Text getText() {
        return text;
    }
    public Text getTextTwo() {
        return textTwo;
    }

    public ArrayList<PictureNode> getPictures() {
        return pictures;
    }

    public void addPictures(PictureNode pNode) {
        pictures.add(pNode);
    }

    /*
     * 用作刷新图片显示界面
     */
    public void showPicture() {
        System.out.println("Loading...");
        flowPane.getChildren().remove(0, flowPane.getChildren().size());
        for (PictureNode pNode : pictures) {
            flowPane.getChildren().add(pNode);
        }
        files=new ArrayList<File>();

        for(int i=0;i<pictures.size();i++) {
            files.add(pictures.get(i).getImageFile());
        }
        ChangeService.files=files;
    }

    //清空pictures
    public void clearPictures() {
        pictures.clear();
    }

    //删除pictures中的某个PictureNode
    public void removePictures(PictureNode pNode) {
        for (PictureNode pictureNode : pictures) {
            if (pictureNode.equals(pNode)) {
                pictures.remove(pNode);
                break;
            }
        }
    }

    // Event Listener on Button[#openBtn].onAction
    @FXML
    public void openBtnAction(ActionEvent event) {
        //openBtn.setTooltip(new Tooltip("打开"));
        new OpenAction();

    }

    // Event Listener on Button[#copyBtn].onAction
    @FXML
    public void copyBtnAction(ActionEvent event) {
        //copyBtn.setTooltip(new Tooltip("复制"));
        new CopyAction();
    }

    // Event Listener on Button[#pasteBtn].onAction
    @FXML
    public void pasteBtnAction(ActionEvent event) {
//		pasteBtn.setTooltip(new Tooltip("粘贴"));
        new PasteAction(mainUI);
    }


    // Event Listener on Button[#deleteBtn].onAction
    @FXML
    public void deleteBtnAction(ActionEvent event) {
//		deleteBtn.setTooltip(new Tooltip("删除"));
        new DeleteAction(mainUI);
    }
    @FXML
    public void PPTAction(ActionEvent event) {
//		PPT.setTooltip(new Tooltip("幻灯片播放"));
        new PPTAction();
    }

    @FXML
    public void mohuAction(ActionEvent event) {
//		PPT.setTooltip(new Tooltip("幻灯片播放"));
        new MoHuSearch(mainUI,mohusearch);
    }

}