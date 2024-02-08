package controller;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import model.PictureNode;
import service.ChangeService;


import java.net.URL;
import java.util.ResourceBundle;

import action.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Menu;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;

public class ViewUIController implements Initializable {
    @FXML
    private HBox hbox;
    @FXML
    private ImageView imageView;
    @FXML
    private ToolBar toolbar;

    private Image image;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
    }


    private void initData() {
        image = new Image(PictureNode.getSelectedPictures().get(0).getURL());
        ChangeService.file = PictureNode.getSelectedPictures().get(0).getImageFile();

        ChangeService.origin = new ImageView(image);
        ChangeService.change = new ImageView(image);

        ChangeService.originHeight = imageView.getFitHeight();
        ChangeService.originWidth = imageView.getFitWidth();
        //设置图片自适应
        imageView.setPreserveRatio(true);
        //设置图片平滑
        imageView.setSmooth(true);
        //设置图片缓存
        imageView.setImage(image);
        //toolbar.setVisible(true);
    }

    public ImageView getImageView() {
        return imageView;
    }

    @FXML
    private void PPTAction(ActionEvent e) {
        new PPTAction();
    }

    @FXML
    private void Daozhi(ActionEvent e) {
        if (imageView.getNodeOrientation().name().equals("RIGHT_TO_LEFT")) {
            imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        } else {
            imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }

    // Event Listener on Button[#enlargeBtn].onAction
    @FXML
    public void enlargeAction(ActionEvent event) {
        Enlarge_Small_Action.enlarge(imageView);

    }

    // Event Listener on Button[#smallBtn].onAction
    @FXML
    public void smallAction(ActionEvent event) {
        Enlarge_Small_Action.small(imageView);
    }

    // Event Listener on Button[#resetBtn].onAction
    @FXML
    public void resetAction(ActionEvent event) {
        new ResetAction(imageView);
    }

    // Event Listener on Button[#rotateBtn].onAction
    @FXML
    public void rotateAction(ActionEvent event) {
        new RotateAction(imageView);
    }


    // Event Listener on Button[#previousImageBtn].onAction
    @FXML
    public void previousAction(ActionEvent event) {
        Previous_next_Action.changePicture(imageView, false);
//        Previous_next_Action.setPage(ChangeService.files.indexOf(ChangeService.file));
    }

    // Event Listener on Button[#nextImageBtn].onAction
    @FXML
    public void nextAction(ActionEvent event) {
        Previous_next_Action.changePicture(imageView, true);
//        Previous_next_Action.setPage(ChangeService.files.indexOf(ChangeService.file));
    }
}
