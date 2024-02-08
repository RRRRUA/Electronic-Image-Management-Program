package action;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.MyAlert;
import service.ChangeService;

import java.io.File;
import java.net.MalformedURLException;

public class Previous_next_Action {
	public static void setPage(int page) {
		Previous_next_Action.page = page;
	}

	private static int page;
	private static Image image;

	public static void changePicture(ImageView imageView,Boolean Previous_or_next) {
		//System.out.println(page+"######");
		if(Previous_or_next) {
	    	page++;
	    }
	    if(!Previous_or_next) {
	    	page--;
	    }

		if (page < 0) {
			MyAlert.showAlert("这是第一张图片", " ", Main.mainStage);
			page++;
			return;
		}
		if (page > ChangeService.files.size() - 1) {
			MyAlert.showAlert("这是最后一张图片", " ", Main.mainStage);
			page--;
			return;
		}
		File file = ChangeService.files.get(page);
		try {
			image = new Image(file.toURI().toURL().toString());
			imageView.setImage(image);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
