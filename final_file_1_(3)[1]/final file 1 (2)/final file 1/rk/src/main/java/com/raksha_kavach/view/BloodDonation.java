package com.raksha_kavach.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BloodDonation {

    Scene bloodScene, donarScene, receiverScene;
    Stage primaryStage;

    public void setBloodScene(Scene bloodScene) {
        this.bloodScene = bloodScene;
    }

    public void setDonarScene(Scene donarScene) {
        this.donarScene = donarScene;
    }

    public void setReceiverScene(Scene receiverScene) {
        this.receiverScene = receiverScene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start(Stage myStage) {
        this.primaryStage = myStage;

        bloodScene = new Scene(createScene(this::initializedPage2, this::initializedPage3),  1250, 650);
        primaryStage.setScene(bloodScene);
        primaryStage.setTitle("Blood Bank Home");
        primaryStage.show();
    }

    public StackPane createScene(Runnable goToDonor, Runnable goToReceiver) {
        StackPane root = new StackPane();

        // === 1. Blurred Background Image ===
        ImageView backgroundImage = new ImageView();
        try {
            Image img = new Image(getClass().getResource("/Assets//Images//bloodMain.png")
                    .toExternalForm());
            backgroundImage.setImage(img);
        } catch (Exception e) {
            System.out.println("Could not load image: " + e.getMessage());
        }

        backgroundImage.setFitWidth(1250);
        backgroundImage.setFitHeight(650);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setEffect(new GaussianBlur(8)); // Adjust blur here
        root.getChildren().add(backgroundImage);

        // === 2. Gradient Overlay ===
        Pane gradientOverlay = new Pane();
        BackgroundFill gradientFill = new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#007ACC", 0.6)),
                        new Stop(0.5, Color.web("#55e8f0ff", 0.6)),
                        new Stop(1, Color.web("#2ECC71", 0.6))),
                CornerRadii.EMPTY,
                Insets.EMPTY);
        gradientOverlay.setBackground(new Background(gradientFill));
        root.getChildren().add(gradientOverlay);

        // === 3. Buttons ===
        Button donarButton = new Button("Donate Blood");
        donarButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        donarButton.setPrefSize(220, 50);
        donarButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: black; -fx-background-radius: 5px;");

        Button receiverButton = new Button("Get Blood");
        receiverButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        receiverButton.setPrefSize(220, 50);
        receiverButton.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: black; -fx-background-radius: 5px;");

        donarButton.setOnAction(e -> goToDonor.run());
        receiverButton.setOnAction(e -> goToReceiver.run());

        Button backToHomeBtn = new Button("Back to Home");
        backToHomeBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backToHomeBtn.setPrefWidth(220);
        backToHomeBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: black; -fx-background-radius: 5px;");
        backToHomeBtn.setOnAction(e -> {
            HomePage homePage = new HomePage();
            homePage.setHomestage(primaryStage);
            Scene homeScene = new Scene(homePage.createHomePage(() -> {
            }),  1250, 600);
            primaryStage.setScene(homeScene);
        });

        VBox buttonContainer = new VBox(40, donarButton, receiverButton, backToHomeBtn);
        buttonContainer.setAlignment(Pos.CENTER);

        root.getChildren().add(buttonContainer);

        return root;
    }

    private void initializedPage2() {
        donorBlood p2 = new donorBlood();
        BorderPane donorLayout = p2.createScene(this::handleBackToMainPage);
        donarScene = new Scene(donorLayout,  1250, 650);

        p2.setPreviousScene(donarScene);
        p2.setPrimaryStage(primaryStage);
        primaryStage.setScene(donarScene);
    }

    private void initializedPage3() {
        receiverBlood p3 = new receiverBlood();
        BorderPane receiverLayout = p3.createScene(this::handleBackToMainPage);
        receiverScene = new Scene(receiverLayout,  1250, 650);
        p3.setp3Scene(receiverScene);
        primaryStage.setScene(receiverScene);
    }

    private void handleBackToMainPage() {
        primaryStage.setScene(bloodScene);
    }
}
