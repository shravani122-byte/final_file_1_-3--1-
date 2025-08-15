package com.raksha_kavach.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class organMain {

    private Scene organScene, nextScene;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setOrganScene(Scene organScene) {
        this.organScene = organScene;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void start(Stage myStage) {
        this.primaryStage = myStage;
        StackPane root = createScene(this::initializedPage2);
        organScene = new Scene(root,  1250, 650);
        primaryStage.setScene(organScene);
        primaryStage.setTitle("Organ Donation");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public StackPane createScene(Runnable next) {
        StackPane root = new StackPane();
        root.setPrefSize( 1250,650);

        // 🖼 Background Image
        ImageView backgroundImage = new ImageView();
        try {
            Image img = new Image(getClass().getResource("/Assets//Images//organ.png").toExternalForm());
            backgroundImage.setImage(img);
        } catch (Exception e) {
            System.out.println("Could not load background image: " + e.getMessage());
        }
        backgroundImage.setFitWidth(1250);
        backgroundImage.setFitHeight(650);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setEffect(new GaussianBlur(0));
        root.getChildren().add(backgroundImage);

        // 🎨 Gradient Overlay
        Pane gradientOverlay = new Pane();
        gradientOverlay.setPrefSize(750, 800);
        BackgroundFill gradientFill = new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#58e975ff", 0.7)),
                        new Stop(0.5, Color.web("#29b7d3ff", 0.7)),
                        new Stop(1, Color.web("#27b7c1ff", 0.7))),
                CornerRadii.EMPTY, Insets.EMPTY);
        gradientOverlay.setBackground(new Background(gradientFill));
        gradientOverlay.setMouseTransparent(true);
        root.getChildren().add(gradientOverlay);

        //  Quote + Buttons
        Text quote = new Text(
                "        Saves the lives of many people, as one Organ donation can provide life \n" +
                        "  support to 8 people's suffering from end-stage organ damage.\n" +
                        "                          Thank You for your valuable thing !!!");
        quote.setFill(Color.WHITE);
        quote.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));

        Button nextBtn = new Button("Proceed to fill the form");
        nextBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nextBtn.setPrefWidth(250);
        nextBtn.setStyle("-fx-background-color: #34db6e; -fx-text-fill: black; -fx-background-radius: 5px;");
        nextBtn.setOnAction(e -> next.run());

        Button backToHomeBtn = new Button("Back to Home");
        backToHomeBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backToHomeBtn.setPrefWidth(250);
        backToHomeBtn.setStyle("-fx-background-color: #ef1717ff; -fx-text-fill: black; -fx-background-radius: 5px;");
        backToHomeBtn.setOnAction(e -> {
            HomePage homePage = new HomePage();
            homePage.setHomestage(primaryStage);
            Scene homeScene = new Scene(homePage.createHomePage(() -> {
            }), 1250, 650);
            primaryStage.setScene(homeScene);
        });

        VBox layout = new VBox(30, quote, nextBtn, backToHomeBtn);
        layout.setAlignment(Pos.CENTER);
        root.getChildren().add(layout);

        return root;
    }

    private void initializedPage2() {
        OrganDonation formPage = new OrganDonation();
        BorderPane donorLayout = formPage.createScene(this::handleBackToMainPage);
        nextScene = new Scene(donorLayout,  1250, 650);
        formPage.setP2Scene(nextScene);
        primaryStage.setScene(nextScene);
    }

    private void handleBackToMainPage() {
        primaryStage.setScene(organScene);
    }
}
