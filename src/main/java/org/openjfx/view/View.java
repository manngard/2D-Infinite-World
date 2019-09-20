package org.openjfx.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.openjfx.model.Player;
import org.openjfx.model.World;
import org.openjfx.model.tilepackage.Tile;
import org.openjfx.utils.Event;

import java.util.List;

import static org.openjfx.utils.Event.EventMessage.UPDATE;

public class View {
    private Stage stage;
    @FXML
    private Canvas gameScreen;

    public View(Stage stage, EventHandler<KeyEvent> handler, Event modelHasUpdatedEvent) {
        this.stage = stage;

        StackPane layers = new StackPane();
        Scene scene = new Scene(layers, 600, 400);

        gameScreen = new Canvas(600,400);
        layers.getChildren().add(gameScreen);

        //Uncomment below if you want to testdraw a image

        //Player player = new Player("Grass",200,100,true,10,10);
        //drawObject(player.getId(),player.getXcoord(),player.getYcoord());

        stage.setScene(scene);
        stage.show();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, handler);
        modelHasUpdatedEvent.addListener(new Event.EventListener() {
            @Override
            public void func(Event.EventMessage emsg, Object data) {
                rerender(emsg, data);
            }
        });
    }

    private void rerender(Event.EventMessage emsg, Object data) {
        switch (emsg) {
            case UPDATE:
                gameScreen.getGraphicsContext2D().clearRect(0, 0, 1000, 1000);
                World world = (World) data;
                renderTileWorld(world);
                drawObject(world.player.getId(), world.player.getXcoord(), world.player.getYcoord());

        }
    }

    //Draws object with String id
    public void drawObject(String id, int x, int y){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        graphics.drawImage(ResourceHandler.getResource(id),x,y);
    }
    public void renderTileWorld(World world){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        int tilePixelsize = -32;
        int yOffset, xOffset = tilePixelsize;
        for (List<Tile> tileRow: world.getWorldGrid()){
            yOffset = tilePixelsize;
            xOffset += 32;
            for (Tile tile: tileRow){
                yOffset += 32;
                graphics.drawImage(ResourceHandler.getResource(tile.getId()),tile.getXcoord() + xOffset,tile.getYcoord()+ yOffset);
            }
        }
    }

    public Canvas getGameScreen() {
        return gameScreen;
    }
}
