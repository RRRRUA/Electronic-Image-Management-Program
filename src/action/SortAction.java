package action;

import controller.MainUIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.PictureNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortAction {
    private MainUIController mainUIController;

    public SortAction(MainUIController mainUI) {
        this.mainUIController = mainUI;

        ArrayList<PictureNode> pictures = mainUI.getPictures();
        // Sort the pictures based on a custom comparator
        Collections.sort(pictures, new PictureComparator());

        // Refresh the UI to display the sorted pictures
        mainUIController.showPicture();
    }

    private class PictureComparator implements Comparator<PictureNode> {
        @Override
        public int compare(PictureNode p1, PictureNode p2) {
            // Implement your custom comparison logic here
            // For example, compare based on picture names
            String name1 = p1.getPictureFile().getImageName();
            String name2 = p2.getPictureFile().getImageName();
            return name1.compareTo(name2);
        }
    }
}
