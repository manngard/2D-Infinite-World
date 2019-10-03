package org.openjfx.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.openjfx.model.Combatant;
import org.openjfx.model.Enemy;
import org.openjfx.model.EventMessage;
import org.openjfx.model.World;
import org.openjfx.model.tilepackage.Tile;
import org.openjfx.utils.event.Event;
import org.openjfx.utils.event.EventListener;

import java.util.List;

public class View {
    private Stage stage;
    @FXML
    private Canvas gameScreen;
    private final int pixelSize = 33; //including 1px spacing between tiles
    private int screenXSize;
    private int screenYSize;

    public View(Stage stage, EventHandler<KeyEvent> handler, Event<EventMessage> modelHasUpdateEvent) {
        this.stage = stage;

        StackPane layers = new StackPane();

        screenXSize = pixelSize*21;
        screenYSize = pixelSize*13;
        Scene scene = new Scene(layers, screenXSize,screenYSize);

        gameScreen = new Canvas(screenXSize,screenYSize);
        layers.getChildren().add(gameScreen);

        stage.setScene(scene);
        stage.show();

        stage.addEventFilter(KeyEvent.KEY_PRESSED, handler);

        modelHasUpdateEvent.addListener(new EventListener<EventMessage>() {
            @Override
            public void func(EventMessage emsg, Object data) {
                rerender(emsg, data);
            }
        });
    }

    private void rerender(EventMessage emsg, Object data) {
        switch (emsg) {
            case UPDATE:
                World world = (World) data;
                double xOffset = (screenXSize/2) -pixelSize/2;
                double yOffset = (screenYSize/2) -pixelSize/2;
                double playerX = translateX(world.player.getXcoord()) - xOffset;
                double playerY = translateY(world.player.getYcoord()) - yOffset;
                gameScreen.getGraphicsContext2D().clearRect(0, 0, 1000,1000);
                renderTileWorld(world,playerX,playerY);
                drawObject(world.player.getId(),translateX(0),translateY(0));
                for(Combatant e : world.getEnemies()){
                    drawObject(e.getId(), translateX(e.getXcoord())-playerX, translateY(e.getYcoord())-playerY);
                }

        }
    }

    public double translateY(double modelY){
        return gameScreen.getHeight()/2 + modelY*pixelSize - 16;
    }

    public double translateX(double modelX){
        return gameScreen.getWidth()/2 + modelX*pixelSize - 16;
    }

    //Draws object with String id
    public void drawObject(String id, double x, double y){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        graphics.drawImage(ResourceHandler.getResource(id),x,y);
    }
    public void renderTileWorld(World world, double playerX, double playerY){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        for (List<Tile> tileRow: world.getWorldGrid()){
            for (Tile tile: tileRow){
                graphics.drawImage(ResourceHandler.getResource(tile.getId()),translateX(tile.getXcoord()) - playerX,translateY(tile.getYcoord()) - playerY);
            }
        }
    }

    public Canvas getGameScreen() {
        return gameScreen;
    }
}
