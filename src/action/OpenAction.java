package action;

import application.Main;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MyAlert;
import model.PictureNode;
import service.ChangeService;
import service.MouseEvenHandler;


public class OpenAction {

	public OpenAction() {
	if (ChangeService.files == null||PictureNode.getN()==0) {
			MyAlert.showAlert("没有选中图片", " ", Main.mainStage);
			//return;
		} else{
			try {
				//重新搭stage
				Stage newStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/View/ViewUI2.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				newStage.setScene(scene);
				newStage.setTitle("pic_showing...");
				Previous_next_Action.setPage(ChangeService.files.indexOf(ChangeService.file));
				newStage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	PictureNode.setN(0);
	}
}
