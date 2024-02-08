package action;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import model.PictureNode;
import java.io.File;
public class shuxingAction {

    public  shuxingAction()
    {
        if(PictureNode.getSelectedPictures().size()==1){
            PictureNode pictureNode = PictureNode.getSelectedPictures().get(0);
            Text text = new Text("文件名："+pictureNode.getPictureFile().getImageName());
            Text text1 = new Text("文件类型："+pictureNode.getPictureFile().getImageName().toLowerCase().substring(pictureNode.getPictureFile().getImageName().lastIndexOf(".")+1)+"文件");
            Text text2 = new Text("文件大小："+pictureNode.getPictureFile().length()+"KB");
            Text text3 = new Text("文件路径："+pictureNode.getPictureFile().getImageFile().getPath());
            Stage stage = new Stage();
            stage.setTitle("属性");
            VBox vBox= new VBox(20);
            vBox.getChildren().addAll(text,text1,text2,text3);
            Scene scene = new Scene(vBox, 300, 250);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Stage stage= new Stage();
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(new Text("只能选择一个文件"));
            Scene scene=new Scene(borderPane,300,250);
            stage.setScene(scene);
            stage.show();
            System.out.println("只能选择一个文件");
        }
    }
}
