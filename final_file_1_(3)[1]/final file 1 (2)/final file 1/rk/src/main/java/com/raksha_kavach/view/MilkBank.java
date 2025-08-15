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

public class MilkBank {

    Scene milkScene, donorScene, receiverScene;
    Stage primaryStage;

    public void setMilkScene(Scene milkScene) {
        this.milkScene = milkScene;
    }

    public void setDonorScene(Scene donorScene) {
        this.donorScene = donorScene;
    }

    public void setReceiverScene(Scene receiverScene) {
        this.receiverScene = receiverScene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start(Stage myStage) {

        milkScene = new Scene(createScene(this::initializedPage2, this::initializedPage3), 1250 ,650);
        primaryStage.setScene(milkScene);
        primaryStage.setTitle("Blood Bank Home");
        primaryStage.show();
    }

    public StackPane createScene(Runnable goToDonor, Runnable goToReceiver) {
        StackPane root = new StackPane();

        // 1. Blurred background image
        ImageView backgroundImage = new ImageView();
        Image img = new Image(getClass().getResource("/Assets//Images//milk.png").toExternalForm());
        backgroundImage.setImage(img);
        backgroundImage.setFitWidth(1200);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setEffect(new GaussianBlur(5));
        backgroundImage.setMouseTransparent(true); // Let clicks pass through
        root.getChildren().add(backgroundImage);

        // 2. Gradient overlay
        Pane gradientPane = new Pane();
        gradientPane.setPrefSize(750, 800);
        gradientPane.setBackground(new Background(new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#55e64bff", 0.7)),
                        new Stop(0.5, Color.web("#28a8b4ff", 0.7)),
                        new Stop(1, Color.web("#21d1c2ff", 0.7))),
                CornerRadii.EMPTY, Insets.EMPTY)));
        gradientPane.setMouseTransparent(true); // Let clicks pass through
        root.getChildren().add(gradientPane);

        // 3. Tiled grid pattern
        Pane tileLayer = new Pane();
        tileLayer.setPrefSize (1250, 650);
        tileLayer.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-image: " +
                        "linear-gradient(to right, rgba(255,255,255,0.1) 1px, transparent 1px)," +
                        "linear-gradient(to bottom, rgba(255,255,255,0.1) 1px, transparent 1px);" +
                        "-fx-background-size: 40px 40px;" +
                        "-fx-background-repeat: repeat;");
        tileLayer.setMouseTransparent(true); // Let clicks pass through
        root.getChildren().add(tileLayer);

        // 4. Foreground buttons
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);

        Button b1 = new Button("Donate Milk");
        b1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        b1.setStyle("-fx-background-color: #3498DB; -fx-text-fill: black; -fx-background-radius: 5px;");
        b1.setPrefWidth(200);

        Button b2 = new Button("Get Milk");
        b2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        b2.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: black; -fx-background-radius: 5px;");
        b2.setPrefWidth(200);

        b1.setOnAction(e -> goToDonor.run());
        b2.setOnAction(e -> goToReceiver.run());

        Button backToHomeBtn = new Button("Back to Home");
        backToHomeBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backToHomeBtn.setPrefWidth(200);
        backToHomeBtn.setStyle("fx-text-fill: black;");
        backToHomeBtn.setStyle("-fx-background-color:red");
        backToHomeBtn.setOnAction(e -> {
            HomePage homePage = new HomePage();
            homePage.setHomestage(primaryStage);
            Scene homeScene = new Scene(homePage.createHomePage(() -> {
            }),  1250, 650);
            primaryStage.setScene(homeScene);
        });

        box.getChildren().addAll(b1, b2, backToHomeBtn);
        root.getChildren().add(box);

        return root;
    }

    private void initializedPage2() {
        donorMilk p2 = new donorMilk();
        BorderPane donorLayout = p2.createScene(this::handleBackToMainPage);
        donorScene = new Scene(donorLayout, 1250, 600);

        p2.setp2Scene(donorScene);
        // p2.setp2Stage(primaryStage);
        primaryStage.setScene(donorScene);
    }

    private void initializedPage3() {
        receiverMilk p3 = new receiverMilk();
        BorderPane receiverLayout = p3.createScene(this::handleBackToMainPage);
        receiverScene = new Scene(receiverLayout, 1250, 650);
        p3.setp3Scene(receiverScene);
        primaryStage.setScene(receiverScene);
    }

    private void handleBackToMainPage() {
        primaryStage.setScene(milkScene);
    }
}
