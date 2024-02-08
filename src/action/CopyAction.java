package action;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import model.PictureNode;

public class CopyAction {
	
	public CopyAction() {
		if(PictureNode.getSelectedPictures().size()<=0) {
			return;
		}
		//检测选中图片剪切情况
		if(PictureNode.getCutedPictures().size() > 0) {
			for(PictureNode pNode : PictureNode.getCutedPictures()) {
				pNode.getImageView().setEffect(null);
			}
			PictureNode.getCutedPictures().clear();
		}


		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent clipboardContent = new ClipboardContent();
		clipboard.clear();//清空剪切板
		for(PictureNode pNode : PictureNode.getSelectedPictures()) {
			PictureNode.getSelectedPictureFiles().add(pNode.getImageFile());
		}
		clipboardContent.putFiles(PictureNode.getSelectedPictureFiles());
		clipboard.setContent(clipboardContent);
		clipboard = null;
		clipboardContent = null;
	}

}
