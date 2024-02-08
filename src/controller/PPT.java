package controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import action.OpenAction;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import service.ChangeService;

public class PPT implements Initializable {
    private Timeline timeline;
    private int count = 1;


    @FXML
    private ImageView imageview;
    @FXML
    private Button start, stop;

    @FXML
    private void Begin(ActionEvent e) {
        timeline.play();
    }

    @FXML
    private void Stop(ActionEvent e) {
        timeline.pause();
    }

    @FXML
    private void Press(MouseEvent e) {
        if (start.isVisible()) {
            start.setVisible(false);
            stop.setVisible(false);
        } else {
            start.setVisible(true);
            stop.setVisible(true);
        }
    }

    @FXML
    private void Back(ActionEvent e) {
        new OpenAction();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        imageview.setImage(ChangeService.change.getImage());
        imageview.setEffect(ChangeService.change.getEffect());
        imageview.setViewport(ChangeService.change.getViewport());
        imageview.setNodeOrientation(ChangeService.change.getNodeOrientation());
        imageview.setRotate(ChangeService.change.getRotate());


        timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);
        //动画处理
        KeyValue keyValue = new KeyValue(imageview.scaleXProperty(), 2);
        KeyValue keyValue2 = new KeyValue(imageview.scaleYProperty(), 2);
        Duration duration = Duration.seconds(5);
        //动画结束后的处理
        EventHandler<ActionEvent> onFinished = (ActionEvent t) -> {
            if (count < ChangeService.files.size()) {
                try {
                    imageview.setImage(new Image(ChangeService.files.get(count).toURI().toURL().toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else if (count == ChangeService.files.size()) {
                count = 0;
                imageview.setScaleX(1.0);
                imageview.setScaleY(1.0);
                timeline.stop();
            }
            count++;
        };
        KeyFrame keyFrame1 = new KeyFrame(duration, onFinished, keyValue, keyValue2);

        timeline.getKeyFrames().add(keyFrame1);

    }

}
