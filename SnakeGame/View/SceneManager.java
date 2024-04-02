package SnakeGame.View;
import javafx.stage.Stage;

// Classe Singleton pour gérer les scènes
public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;

    private SceneManager() {
        // Empêche l'instanciation directe depuis l'extérieur de la classe
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance= new SceneManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void changeScene(SceneAdaptater newScene) {
        primaryStage.setScene(newScene.getScene());
    }
    public void show(){
        primaryStage.show();
    }
}
