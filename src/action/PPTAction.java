package action;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MyAlert;
import service.ChangeService;

public class PPTAction {
	public PPTAction() {
		if (ChangeService.files == null) {
			MyAlert.showAlert("没有选中图片", " ", Main.mainStage);
			return;
		} else {
			try {
				Stage newStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/View/PPT.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				newStage.setScene(scene);
				newStage.setTitle("幻灯片");
				newStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
