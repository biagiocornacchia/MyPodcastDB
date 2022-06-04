package it.unipi.dii.lsmsdb.myPodcastDB.controller;

import it.unipi.dii.lsmsdb.myPodcastDB.MyPodcastDB;
import it.unipi.dii.lsmsdb.myPodcastDB.model.*;
import it.unipi.dii.lsmsdb.myPodcastDB.service.PodcastService;
import it.unipi.dii.lsmsdb.myPodcastDB.service.ReviewService;
import it.unipi.dii.lsmsdb.myPodcastDB.utility.ImageCache;
import it.unipi.dii.lsmsdb.myPodcastDB.utility.JsonDecode;
import it.unipi.dii.lsmsdb.myPodcastDB.utility.Logger;
import it.unipi.dii.lsmsdb.myPodcastDB.view.StageManager;
import it.unipi.dii.lsmsdb.myPodcastDB.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewPageController {

    @FXML
    private ComboBox<String> ascending;

    @FXML
    private Label author;

    @FXML
    private Label category;

    @FXML
    private ProgressBar fiveStars;

    @FXML
    private ProgressBar fourStars;

    @FXML
    private VBox gridWrapper;

    @FXML
    private ImageView home;

    @FXML
    private ImageView logout;

    @FXML
    private BorderPane mainPage;

    @FXML
    private Label noReviewsMessage;

    @FXML
    private Label numReviews;

    @FXML
    private ProgressBar oneStar;

    @FXML
    private ComboBox<String> orderBy;

    @FXML
    private ImageView podcastImage;

    @FXML
    private Label rating;

    @FXML
    private Button reload;

    @FXML
    private VBox reviewForm;

    @FXML
    private GridPane reviewGrid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField searchBarText;

    @FXML
    private ImageView searchButton;

    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView star4;

    @FXML
    private ImageView star5;

    @FXML
    private TextArea textContent;

    @FXML
    private TextField textTitle;

    @FXML
    private ProgressBar threeStars;

    @FXML
    private Label title;

    @FXML
    private ProgressBar twoStars;

    @FXML
    private ImageView userPicture;

    @FXML
    private VBox userPictureWrapper;

    private Review ownReview;
    private Podcast podcast;
    private List<Review> loadedReviews;
    private ReviewService service;

    private int row;
    private int column;

    @FXML
    void clickOnAuthor(MouseEvent event) throws IOException {
        Logger.info("Click on author : " + this.podcast.getAuthorId());
        StageManager.showPage(ViewNavigator.AUTHORPROFILE.getPage(), this.podcast.getId());
    }

    @FXML
    void clickOnFifthStar(MouseEvent event) {
        Image star = ImageCache.getImageFromLocalPath("/img/star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(star);
        this.star4.setImage(star);
        this.star5.setImage(star);
        this.ownReview.setRating(5);

        Logger.info("Rating set to " + this.ownReview.getRating());
    }

    @FXML
    void clickOnFirstStar(MouseEvent event) {
        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(outlineStar);
        this.star3.setImage(outlineStar);
        this.star4.setImage(outlineStar);
        this.star5.setImage(outlineStar);
        this.ownReview.setRating(1);

        Logger.info("Rating set to " + this.ownReview.getRating());
    }

    @FXML
    void clickOnFourthStar(MouseEvent event) {
        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(star);
        this.star4.setImage(star);
        this.star5.setImage(outlineStar);
        this.ownReview.setRating(4);

        Logger.info("Rating set to " + this.ownReview.getRating());
    }

    @FXML
    void clickOnLogout(MouseEvent event) throws IOException {
        MyPodcastDB.getInstance().setSession(null, null);
        StageManager.showPage(ViewNavigator.LOGIN.getPage());
    }

    @FXML
    void clickOnReload(MouseEvent event) {
        // TODO: reload
        Logger.info("Reload reviews");
    }

    @FXML
    void clickOnSecondStar(MouseEvent event) {
        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(outlineStar);
        this.star4.setImage(outlineStar);
        this.star5.setImage(outlineStar);
        this.ownReview.setRating(2);

        Logger.info("Rating set to " + this.ownReview.getRating());
    }

    @FXML
    void clickOnThirdStar(MouseEvent event) {
        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(star);
        this.star4.setImage(outlineStar);
        this.star5.setImage(outlineStar);
        this.ownReview.setRating(3);

        Logger.info("Rating set to " + this.ownReview.getRating());
    }

    @FXML
    void clickOnTitle(MouseEvent event) throws IOException {
        Logger.info("Podcast ID to load : " + this.podcast.getId());
        StageManager.showPage("PodcastPage.fxml", this.podcast.getId());
    }

    @FXML
    void mouseOnAuthor(MouseEvent event) {
        this.author.setCursor(Cursor.HAND);
        this.author.setTextFill(Color.color(0.6, 0.6, 0.6));
    }

    @FXML
    void mouseOnTitle(MouseEvent event) {
        this.title.setCursor(Cursor.HAND);
        this.title.setTextFill(Color.color(0.6, 0.6, 0.6));
    }

    @FXML
    void mouseOutAuthor(MouseEvent event) {
        this.author.setCursor(Cursor.DEFAULT);
        this.author.setTextFill(Color.color(0.0, 0.0, 1.0));
    }

    @FXML
    void mouseOutTitle(MouseEvent event) {
        this.title.setCursor(Cursor.DEFAULT);
        this.title.setTextFill(Color.color(0.0, 0.0, 0.0));
    }

    @FXML
    void onSubmit(MouseEvent event) throws IOException {
        // get the text
        String title = this.textTitle.getText();
        String content = this.textContent.getText();

        // fill the review
        this.ownReview.setTitle(title);
        this.ownReview.setContent(content);
        this.ownReview.setCreatedAt(new Date());

        // send the review
        int result = this.service.addNewReview(this.ownReview);

        // if is successful update the page
        if (result == 0) {
            // disable form
            this.disableForm();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("Review.fxml"));

            // create new review element
            AnchorPane newReview = fxmlLoader.load();
            ReviewController controller = fxmlLoader.getController();
            controller.setData(this.ownReview, this.mainPage);

            this.reviewGrid.add(newReview, this.column, this.row++);
        } else {
            // TODO: alert
        }
    }

    @FXML
    void onClickHome(MouseEvent event) throws IOException {
        Logger.info("Click on home");
        StageManager.showPage(ViewNavigator.HOMEPAGE.getPage());
    }

    @FXML
    void onClickSearch(MouseEvent event) throws IOException {
        Logger.info("Click on search");

        String searchString = this.searchBarText.getText();
        StageManager.showPage(ViewNavigator.SEARCH.getPage(), searchString);
    }

    @FXML
    void onEnterPressed(KeyEvent event) throws IOException {
        Logger.info("Enter on search");

        if (event.getCode().equals(KeyCode.ENTER)) {
            String searchString = this.searchBarText.getText();
            StageManager.showPage(ViewNavigator.SEARCH.getPage(), searchString);
        }
    }

    @FXML
    void userProfile(MouseEvent event) throws IOException {
        Logger.info("Click on user");
        String actorType = MyPodcastDB.getInstance().getSessionType();

        if (actorType.equals("Author"))
            StageManager.showPage(ViewNavigator.AUTHORPROFILE.getPage());
        else if (actorType.equals("User"))
            StageManager.showPage(ViewNavigator.USERPAGE.getPage());
        else if (actorType.equals("Admin"))
            StageManager.showPage(ViewNavigator.ADMINDASHBOARD.getPage());
        else
            Logger.error("Unidentified Actor Type");
    }

    @FXML
    void mouseOnFirstStar(MouseEvent event) {
        this.star1.setCursor(Cursor.HAND);

        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(outlineStar);
        this.star3.setImage(outlineStar);
        this.star4.setImage(outlineStar);
        this.star5.setImage(outlineStar);
    }

    @FXML
    void mouseOnSecondStar(MouseEvent event) {
        this.star2.setCursor(Cursor.HAND);

        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(outlineStar);
        this.star4.setImage(outlineStar);
        this.star5.setImage(outlineStar);
    }

    @FXML
    void mouseOnThirdStar(MouseEvent event) {
        this.star3.setCursor(Cursor.HAND);

        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(star);
        this.star4.setImage(outlineStar);
        this.star5.setImage(outlineStar);
    }

    @FXML
    void mouseOnFourthStar(MouseEvent event) {
        this.star4.setCursor(Cursor.HAND);

        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(star);
        this.star4.setImage(star);
        this.star5.setImage(outlineStar);
    }

    @FXML
    void mouseOnFifthStar(MouseEvent event) {
        this.star5.setCursor(Cursor.HAND);

        Image star = ImageCache.getImageFromLocalPath("/img/star.png");

        this.star1.setImage(star);
        this.star2.setImage(star);
        this.star3.setImage(star);
        this.star4.setImage(star);
        this.star5.setImage(star);
    }

    private void readRating() {
        Image star = ImageCache.getImageFromLocalPath("/img/star.png");
        Image outlineStar = ImageCache.getImageFromLocalPath("/img/outline_star.png");

        if (this.ownReview.getRating() == 5) {
            this.star1.setImage(star);
            this.star2.setImage(star);
            this.star3.setImage(star);
            this.star4.setImage(star);
            this.star5.setImage(star);
        } else if (this.ownReview.getRating() >= 4) {
            this.star1.setImage(star);
            this.star2.setImage(star);
            this.star3.setImage(star);
            this.star4.setImage(star);
            this.star5.setImage(outlineStar);
        } else if (this.ownReview.getRating() >= 3) {
            this.star1.setImage(star);
            this.star2.setImage(star);
            this.star3.setImage(star);
            this.star4.setImage(outlineStar);
            this.star5.setImage(outlineStar);
        } else if (this.ownReview.getRating() >= 2) {
            this.star1.setImage(star);
            this.star2.setImage(star);
            this.star3.setImage(outlineStar);
            this.star4.setImage(outlineStar);
            this.star5.setImage(outlineStar);
        } else if (this.ownReview.getRating() >= 1) {
            this.star1.setImage(star);
            this.star2.setImage(outlineStar);
            this.star3.setImage(outlineStar);
            this.star4.setImage(outlineStar);
            this.star5.setImage(outlineStar);
        } else {
            this.star1.setImage(outlineStar);
            this.star2.setImage(outlineStar);
            this.star3.setImage(outlineStar);
            this.star4.setImage(outlineStar);
            this.star5.setImage(outlineStar);
        }
    }

    @FXML
    void mouseOutFirstStar(MouseEvent event) {
        this.star1.setCursor(Cursor.DEFAULT);
        readRating();
    }

    @FXML
    void mouseOutSecondStar(MouseEvent event) {
        this.star2.setCursor(Cursor.DEFAULT);
        readRating();
    }

    @FXML
    void mouseOutThirdStar(MouseEvent event) {
        this.star3.setCursor(Cursor.DEFAULT);
        readRating();
    }

    @FXML
    void mouseOutFourthStar(MouseEvent event) {
        this.star4.setCursor(Cursor.DEFAULT);
        readRating();
    }

    @FXML
    void mouseOutFifthStar(MouseEvent event) {
        this.star5.setCursor(Cursor.DEFAULT);
        readRating();
    }

    public void initialize() throws IOException {
        // Initialize structure
        this.service = new ReviewService();
        this.podcast = new Podcast();
        this.podcast.setId(StageManager.getObjectIdentifier());
        this.loadedReviews = new ArrayList<>();
        this.ownReview = new Review();

        // actor recognition and info loading from service
        Boolean result = true;
        String sessionType = MyPodcastDB.getInstance().getSessionType();
        if (!sessionType.equals("User")) {
            // only the user can write review
            this.disableForm();
            result = this.service.loadPodcastPageForNotUser(this.podcast, this.loadedReviews, 10, "cretedAt", false);

            // if author update the profile picture
            if (sessionType.equals("Author")) {
                Author author = (Author) MyPodcastDB.getInstance().getSessionActor();
                Image picture = ImageCache.getImageFromLocalPath(author.getPicturePath());
                userPicture.setImage(picture);
            }

            // if unregisterd disable buttons
            if (sessionType.equals("Unregistered")) {
                this.userPictureWrapper.setVisible(false);
                this.userPictureWrapper.setStyle("-fx-min-width: 0; -fx-pref-width: 0; -fx-max-width: 0; -fx-min-height: 0; -fx-pref-height: 0; -fx-max-height: 0; -fx-padding: 0; -fx-margin: 0;");
            }
        } else {
            String username = ((User)MyPodcastDB.getInstance().getSessionActor()).getUsername();
            result = this.service.loadPodcastPageForUser(this.podcast, username, this.loadedReviews, this.ownReview, 10, "cretedAt", false);

            // profile picture
            User user = (User)MyPodcastDB.getInstance().getSessionActor();
            Image picture = ImageCache.getImageFromLocalPath(user.getPicturePath());
            userPicture.setImage(picture);

            // if user has already writter a review, disable form
            if (this.ownReview != null && this.ownReview.getTitle() != null)
                this.disableForm();
        }

        // check service result
        if (!result) {
            // TODO: Alert
        }

        // no reviews message
        if (!this.loadedReviews.isEmpty()) {
            this.noReviewsMessage.setVisible(false);
            this.noReviewsMessage.setPadding(new Insets(-20, 0, 0, 0));
        } else {
            this.gridWrapper.setVisible(false);
            this.gridWrapper.setStyle("-fx-min-width: 0; -fx-pref-width: 0; -fx-max-width: 0; -fx-min-height: 0; -fx-pref-height: 0; -fx-max-height: 0; -fx-padding: 0; -fx-margin: 0;");
        }

        // set fields
        this.title.setText(this.podcast.getName());
        this.author.setText(this.podcast.getAuthorName());
        this.category.setText(this.podcast.getPrimaryCategory());
        Image podcastImage = ImageCache.getImageFromURL(this.podcast.getArtworkUrl600());
        this.podcastImage.setImage(podcastImage);
        int ratingIntermediate = (int)(this.podcast.getRating() * 10);
        this.rating.setText("" + (ratingIntermediate / 10) + "," + (ratingIntermediate % 10));
        this.numReviews.setText(" out of 5.0 • " + this.podcast.getReviews().size() + " reviews");

        // TODO: progress bars

        // insert reviews in grid
        this.row = 0;
        this.column = 0;
        for (Review review : this.loadedReviews) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("Review.fxml"));

            // create new review element
            AnchorPane newReview = fxmlLoader.load();
            ReviewController controller = fxmlLoader.getController();
            controller.setData(review, this.mainPage);

            // add new podcast to grid
            this.reviewGrid.add(newReview, column, row++);
        }

        // initialize own review
        this.ownReview = new Review();
        this.ownReview.setPodcastId(podcast.getId());
        this.ownReview.setAuthorUsername(((User)MyPodcastDB.getInstance().getSessionActor()).getUsername());
        this.ownReview.setRating(0);

        // initialize combo box
        this.orderBy.getItems().add("Date of creation");
        this.orderBy.getItems().add("Rating");
        this.ascending.getItems().add("true");
        this.ascending.getItems().add("false");
    }

    private void disableForm() {
        // hide form
        this.reviewForm.setVisible(false);
        this.reviewForm.setStyle("-fx-min-width: 0; -fx-pref-width: 0px; -fx-min-height: 0; -fx-pref-height: 0px");
    }
}
