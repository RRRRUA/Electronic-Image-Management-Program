package action;

import controller.MainUIController;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import model.PictureFile;
import model.PictureNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class PasteAction {
	MainUIController mainUIController;
	public PasteAction(MainUIController mainUI) {
		this.mainUIController = mainUI;
		Clipboard clipboard = Clipboard.getSystemClipboard();
		List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
		if (files.size() <= 0) {
			return;
		}
		if (PictureNode.getCutedPictures().size() > 0) {
			//检查列表中的第一个文件是否与当前文件路径具有相同的父目录
			File first = files.get(0);
			if(first.getParentFile().getAbsolutePath().compareTo(MainUIController.theFilePath) == 0){
				for(PictureNode pNode : PictureNode.getCutedPictures()) {
					pNode.getImageView().setEffect(null);
				}
				PictureNode.clearSelected();
				PictureNode.getCutedPictures().clear();
				PictureNode.getSelectedPictureFiles().clear();
				clipboard.clear();
				return;	
			}
		}
		for(File oldFile : files) {
			String newName = Pasterename(MainUIController.theFilePath,oldFile.getName());
			File newFile = new File(MainUIController.theFilePath+File.separator+newName);
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(newFile.exists()) {
				try {
					copyFile(oldFile,newFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			try {
				mainUI.getPictures().add(new PictureNode(new PictureFile(newFile), mainUIController));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(PictureNode.getCutedPictures().size()>0) {
				oldFile.delete();
			}
			
			mainUIController.showPicture();
		}

		PictureNode.clearSelected();
		PictureNode.getSelectedPictureFiles().clear();
		clipboard.clear();
	}
	private void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(fromFile);
		FileOutputStream outputStream = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int byteRead;
		while ((byteRead = inputStream.read(b)) > 0) {
			outputStream.write(b, 0, byteRead);
		}
		inputStream.close();
		outputStream.close();
		
	}
	private String Pasterename(String theFilePath, String name) {
		String newName = name;
		File fatherPathFile = new File(theFilePath);
		File[] filesInFatherPath = fatherPathFile.listFiles();
		for (File fileInFatherPath : filesInFatherPath) {
			String fileName = fileInFatherPath.getName();
			int cmp = newName.compareTo(fileName);
			if (cmp == 0) {
				String str = null;
				int end = newName.lastIndexOf("."), start = newName.lastIndexOf("_副本");
				if (start != -1) {
					str = newName.substring(start, end);
					int num = 1;
					try {
						num = Integer.parseInt(str.substring(str.lastIndexOf("_副本") + 3)) + 1;
						int cnt = 0, d = num - 1;
						while (d != 0) {
							d /= 10;
							cnt++;
						}
						newName = newName.substring(0, end - cnt) + num + newName.substring(end);
					} catch (Exception e) {
						newName = newName.substring(0, end) + "_副本1" + newName.substring(end);
					}

				} else {
					newName = newName.substring(0, end) + "_副本1" + newName.substring(end);
				}
			}
		}
		return newName;
	}
	

}
