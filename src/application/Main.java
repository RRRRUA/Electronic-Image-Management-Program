package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Main extends Application {

    public static Window mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainUI.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root,1250, 700);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("电子照片管理系统");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

}
