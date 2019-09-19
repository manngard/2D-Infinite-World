package org.openjfx.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.openjfx.utils.Event;

public class View {
    private final ResourceHandler imageHandler = new ResourceHandler();
    private Stage stage;
    @FXML
    private Canvas gameScreen;

    //Image playerImage = getAsset("Player.png");

    public View(Stage stage, EventHandler<KeyEvent> handler, Event modelHasUpdatedEvent) {
        this.stage = stage;

        StackPane layers = new StackPane();
        Scene scene = new Scene(layers, 600, 400);

        gameScreen = new Canvas(600,400);
        layers.getChildren().add(gameScreen);

        //Uncomment below if you want to testdraw a image

        /*Player player = new Player("Player",200,100,true,10,10);
        drawObject(player.getId(),player.getXcoord(),player.getYcoord());*/

        stage.setScene(scene);
        stage.show();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, handler);
        modelHasUpdatedEvent.addListener(new Event.EventListener() {
                 @Override
                 public void func(Event.EventMessage emsg, Object data) {
                     rerender();
                 }
             }
        );
    }

    private void rerender() {

    }

    //Draws object with String id
    public void drawObject(String id, int x, int y){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        graphics.drawImage(imageHandler.getResource(id),x,y);
    }

    public Canvas getGameScreen() {
        return gameScreen;
    }
}
