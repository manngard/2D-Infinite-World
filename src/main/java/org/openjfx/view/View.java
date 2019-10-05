package org.openjfx.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.openjfx.model.EventMessage;
import org.openjfx.model.World;
import org.openjfx.model.tilepackage.Tile;
import org.openjfx.utils.event.Event;
import org.openjfx.utils.event.EventListener;

import java.util.List;

public class View {
    private Stage stage;
    private Canvas gameScreen;
    private Rectangle healthbar;

    final int pixelSize = 32;



    public View(Stage stage, EventHandler<KeyEvent> handler, Event<EventMessage> modelHasUpdateEvent) {
        this.stage = stage;

        StackPane layers = new StackPane();
        int screenXSize = pixelSize*21 + 21; //why is constant offset needed here? (1 pizel gap between tiles?)
        int screenYSize = pixelSize*13 + 13; //why is constant offset needed here?
        Scene scene = new Scene(layers, screenXSize,screenYSize);

        gameScreen = new Canvas(screenXSize,screenYSize);
        layers.getChildren().add(gameScreen);

        healthbar = new Rectangle(180,30,Color.color(1, 0.2, 0.2)
        );

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
                int playerHP = world.player.getHp();
                gameScreen.getGraphicsContext2D().clearRect(0, 0, 1000,1000);
                renderTileWorld(world,-modelX,-modelY);
                drawObject(world.player.getId(),translateX(0),translateY(0));
                renderOverlay(playerHP);
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

    private void renderOverlay(int HP){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        int healthbarWidth = 18*HP;
        healthbar.setWidth(healthbarWidth);
        graphics.setFill(Color.WHITE);
        graphics.fillRect(gameScreen.getWidth() -200, 20, 180,30);
        graphics.setFill(healthbar.getFill());
        graphics.fillRect(gameScreen.getWidth() -200,20,healthbarWidth,healthbar.getHeight());
        graphics.drawImage(ResourceHandler.getResource("HealthbarOutliner"),gameScreen.getWidth() -200, 20);
    }

    public void renderTileWorld(World world, double playerX, double playerY){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        int xOffset = -21;
        for (List<Tile> tileRow: world.getWorldGrid()){
            int yOffset = -24;
            xOffset += 32;
            for (Tile tile: tileRow){
                yOffset += 32;
                graphics.drawImage(ResourceHandler.getResource(tile.getId()),tile.getXcoord() + xOffset + playerX,tile.getYcoord()+ yOffset + playerY);
                if (tile.getChest() != null){
                    graphics.drawImage(ResourceHandler.getResource(tile.getChest().getId()),tile.getXcoord() + xOffset + playerX, tile.getYcoord() + yOffset + playerX);
                }
            }
        }
    }

    public Canvas getGameScreen() {
        return gameScreen;
    }
}
