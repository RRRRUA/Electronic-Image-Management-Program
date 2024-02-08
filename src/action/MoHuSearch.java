package action;
import controller.MainUIController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.PictureFile;
import model.PictureNode;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MoHuSearch {
    private MainUIController mainUIController;
    private TextField name;
    public MoHuSearch(MainUIController mainUI,TextField name) {
        this.mainUIController = mainUI;
        this.name= name;
        performSearch(this.name.getText());
//     mainUIController.getMohusearch().setOnKeyReleased(this::handleSearch);
    }

    private void handleSearch(KeyEvent event) {

        String searchQuery = mainUIController.getMohusearch().getText().toLowerCase();
    performSearch(searchQuery);

    }
    public void performSearch(String searchQuery) {
        // Get the list of pictures from the MainUIController
        ArrayList<PictureNode> pictures = mainUIController.getPictures();

        // Create an empty filtered list
        ArrayList<PictureNode> filteredPictures = new ArrayList<>();

        // Loop through each picture and check if the search query matches the picture name
        for (PictureNode picture : pictures) {
            String pictureName = picture.getPictureFile().getImageName();

            // Perform case-insensitive search by converting both strings to lowercase
            if (pictureName.toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredPictures.add(picture);
            }
        }
        // Update the UI with the filtered pictures
        mainUIController.setPictures(filteredPictures);
        mainUIController.showPicture();
        int total = 0;
        int size = 0;
        for (PictureNode pictureFile : filteredPictures) {
           {
                total++;
                size += pictureFile.getImageFile().length();
            }
        }
        int finalTotal = total;
        int finalSize = size;
        mainUIController.getText().setText(finalTotal + "张图片， 共" + finalSize + "KB");
        mainUIController.setPictures(pictures);
    }
}
