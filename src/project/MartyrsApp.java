package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MartyrsApp extends Application {

    ListView<String> noteListView = new ListView<>();
    TextField noteInputField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("style.css"); // Reference to your CSS file

        setupTop(root);
        setupCenter(root);
        
        primaryStage.setTitle("Simple Note App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupTop(BorderPane root) {
        HBox inputBox = new HBox(10);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addNote());

        noteInputField.setPromptText("Enter a new note here...");

        inputBox.getChildren().addAll(noteInputField, addButton);
        root.setTop(inputBox);
    }

    private void setupCenter(BorderPane root) {
        noteListView.setItems(null/* Initialize with your notes data */);
        root.setCenter(noteListView);
    }

    private void addNote() {
        String noteText = noteInputField.getText();
        if (!noteText.isEmpty()) {
            noteListView.getItems().add(noteText);
            noteInputField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
