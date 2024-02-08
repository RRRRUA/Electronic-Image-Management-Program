package action;

import javafx.scene.image.ImageView;
import service.ChangeService;

public class Enlarge_Small_Action {

	private static int changeNum = 0;

	public static void enlarge(ImageView imageView) {
		changeNum +=1;
//		Image image = imageView.getImage();
		imageView.setFitWidth(ChangeService.originWidth*(changeNum*0.1+1));
		imageView.setFitHeight(ChangeService.originHeight*(changeNum*0.1+1));
		imageView.setPreserveRatio(true);
	}
	public static void small(ImageView imageView) {
		changeNum -=1;
//		Image image = imageView.getImage();
		imageView.setFitWidth(ChangeService.originWidth*(changeNum*0.1+1));
		imageView.setFitHeight(ChangeService.originHeight*(changeNum*0.1+1));
		imageView.setPreserveRatio(true);
	}

	public static void setChangeNum(int changeNum) {
		Enlarge_Small_Action.changeNum = changeNum;
	}
}
