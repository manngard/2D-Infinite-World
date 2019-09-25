package org.openjfx.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.openjfx.model.EventMessage;
import org.openjfx.model.World;
import org.openjfx.model.tilepackage.Tile;
import org.openjfx.utils.Event;
import org.openjfx.utils.Event.EventListener;

import java.util.List;

public class View {
    private Stage stage;
    @FXML
    private Canvas gameScreen;
    final int pixelSize = 32;

    public View(Stage stage, EventHandler<KeyEvent> handler, Event<EventMessage> modelHasUpdateEvent) {
        this.stage = stage;

        StackPane layers = new StackPane();
        int screenXSize = pixelSize*21 + 21; //why is constant offset needed here? (1 pizel gap between tiles?)
        int screenYSize = pixelSize*13 + 13; //why is constant offset needed here?
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
                double modelX = world.player.getXcoord();
                double modelY = world.player.getYcoord();
                gameScreen.getGraphicsContext2D().clearRect(0, 0, 1000,1000);
                renderTileWorld(world);
                drawObject(world.player.getId(), translateX(modelX) , translateY(modelY));
        }
    }

    public double translateY(double modelY){
        return gameScreen.getHeight()/2 + modelY - 16;
    }

    public double translateX(double modelX){
        return gameScreen.getWidth()/2 + modelX - 16;
    }

    //Draws object with String id
    public void drawObject(String id, double x, double y){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        graphics.drawImage(ResourceHandler.getResource(id),x,y);
    }
    public void renderTileWorld(World world){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        int xOffset = -21;
        for (List<Tile> tileRow: world.getWorldGrid()){
            int yOffset = -24;
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
