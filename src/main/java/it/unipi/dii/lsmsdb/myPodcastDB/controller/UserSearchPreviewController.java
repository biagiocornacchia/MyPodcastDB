package it.unipi.dii.lsmsdb.myPodcastDB.controller;

import it.unipi.dii.lsmsdb.myPodcastDB.model.User;
import it.unipi.dii.lsmsdb.myPodcastDB.utility.ImageCache;
import it.unipi.dii.lsmsdb.myPodcastDB.utility.Logger;
import it.unipi.dii.lsmsdb.myPodcastDB.view.StageManager;
import it.unipi.dii.lsmsdb.myPodcastDB.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class UserSearchPreviewController {
    private User user;

    @FXML
    private Label userFound;

    @FXML
    private ImageView userPicture;

    @FXML
    private HBox boxUserPreview;

    @FXML
    private Button followUser;

    @FXML
    void onClickUser(MouseEvent event) throws IOException {
        Logger.info("Clicked on user " + this.user.getUsername());
        StageManager.showPage(ViewNavigator.USERPAGE.getPage(), this.user.getUsername());
    }

    @FXML
    void changeBackgroundUser(MouseEvent event) {
        this.boxUserPreview.setStyle("-fx-background-color: #eeeeee;");
    }

    @FXML
    void restoreBackgroundUser(MouseEvent event) {
        this.boxUserPreview.setStyle("-fx-background-color: transparent;");
    }

    /****** Events on follow button ******/
    @FXML
    void onClickFollowButton(MouseEvent event) {
        Logger.info("Clicked on Follow " + this.user.getUsername());
    }
    @FXML
    void onMouseExitedFollowButton(MouseEvent event) {
        followUser.setStyle("-fx-background-color: white; -fx-background-radius: 25px; -fx-border-color: #c9c9c9; -fx-border-radius: 27px;");
    }

    @FXML
    void onMouseHoverFollowButton(MouseEvent event) {
        followUser.setStyle("-fx-background-color: #eaeaea; -fx-background-radius: 25px; -fx-border-color: #c9c9c9; -fx-border-radius: 27px;");
    }

    public void setData(User user) {
        this.user = user;
        userFound.setText(user.getUsername());

        Image image = ImageCache.getImageFromLocalPath(user.getPicturePath());
        userPicture.setImage(image);
    }
}
