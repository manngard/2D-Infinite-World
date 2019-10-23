package org.openjfx.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.openjfx.model.*;

import org.openjfx.model.tile.Tile;

import org.openjfx.utils.event.Event;
import org.openjfx.utils.event.EventListener;

import java.util.List;

public class View {
    private Stage stage;
    private Canvas gameScreen;
    private int screenXSize;
    private int screenYSize;
    private Rectangle playerHealthbar;
    private Event<ViewEventMessages> viewEvent;
    private final int pixelSize = 32;
    private GraphicsContext graphics;

    public View(Stage stage, Event<EventMessage> modelHasUpdateEvent) {
        this.stage = stage;

        StackPane layers = new StackPane();

        screenXSize = pixelSize * 21;
        screenYSize = pixelSize * 13;
        Scene scene = new Scene(layers, screenXSize, screenYSize);

        gameScreen = new Canvas(screenXSize, screenYSize);
        layers.getChildren().add(gameScreen);
        graphics = gameScreen.getGraphicsContext2D();

        playerHealthbar = new Rectangle(180, 30, Color.color(1, 0.2, 0.2)
        );

        stage.setScene(scene);
        stage.show();

        viewEvent = new Event<>();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> viewEvent.dispatch(ViewEventMessages.KEYPRESS ,event.getCode()));
        viewEvent = new Event<>();

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

                double xOffset = (screenXSize / 2) - pixelSize / 2;
                double yOffset = (screenYSize / 2) - pixelSize / 2;
                double playerX = translateX(world.player.getXcoord()) - xOffset;
                double playerY = translateY(world.player.getYcoord()) - yOffset;
                int playerHP = world.player.getHp();

                Item [] playerInventory = world.player.getInventory();

                gameScreen.getGraphicsContext2D().clearRect(0, 0, 1000,1000);
                renderTileWorld(world,playerX,playerY);
                renderEntities(world.getActiveEnemies(), world.getActiveChests(),playerX,playerY);
                drawObject(world.player.getId(),translateX(0),translateY(0));
                renderOverlay(playerHP,playerInventory);

        }
    }


    private double translateY(double modelY) {
        return gameScreen.getHeight() / 2 + modelY * pixelSize - 16;
    }

    private double translateX(double modelX) {
        return gameScreen.getWidth() / 2 + modelX * pixelSize - 16;
    }

    //Draws object with String id

    private void drawObject(String id, double x, double y) {
        graphics.drawImage(ResourceHandler.getResource(id), x, y);
    }


    private void renderOverlay(int HP, Item[] inventory){
        double healthbarWidth = 1.8 * HP;
        playerHealthbar.setWidth(healthbarWidth);
        graphics.setFill(Color.WHITE);
        graphics.fillRect(gameScreen.getWidth() - 250, 20, 180, 30);
        graphics.setFill(playerHealthbar.getFill());
        graphics.fillRect(gameScreen.getWidth() - 250, 20, healthbarWidth, playerHealthbar.getHeight());
        graphics.drawImage(ResourceHandler.getResource("PlayerHealthbarContainer"), gameScreen.getWidth() - 250, 20);

        for (int i = 0; i < inventory.length; i++) {
            double xCoord = gameScreen.getWidth() - (pixelSize * 2 * (inventory.length - i) + 20);
            double yCoord = gameScreen.getHeight() - pixelSize * 2 - 20;


            graphics.setFill(Color.WHITE);
            graphics.fillRect(xCoord, yCoord, 64, 64);

            if (inventory[i] != null) {
                if(inventory[i].getIsItemSelected()){
                    graphics.setFill(Color.ORANGE);
                    graphics.fillRect(xCoord, yCoord, 64,64);
                }
                graphics.drawImage(ResourceHandler.getResource(inventory[i].getId()), xCoord, yCoord);
            }
            graphics.drawImage(ResourceHandler.getResource("ItemContainer"), xCoord, yCoord);
        }
    }

    private void renderEntities(List <Combatant> enemies, List<Chest> chests, double playerX, double playerY){
        for (Chest chest : chests){
            graphics.drawImage(ResourceHandler.getResource(chest.getId()), translateX(chest.getXcoord()) - playerX, translateY(chest.getYcoord()) - playerY);
        }
        for(Combatant e : enemies){
            double healthbarXCoord = translateX(e.getXcoord())-playerX - 15;
            double healthbarYCoord = translateY(e.getYcoord())-playerY - pixelSize/2;
            drawObject(e.getId(), translateX(e.getXcoord())-playerX, translateY(e.getYcoord())-playerY);
            graphics.setFill(playerHealthbar.getFill());
            graphics.fillRect(healthbarXCoord,healthbarYCoord,6*e.getHp()/10,10);
            drawObject("EnemyHealthbarContainer",healthbarXCoord,healthbarYCoord);
        }
    }

    private void renderTileWorld(World world, double playerX, double playerY) {
        for (List<Tile> tileRow: world.getWorldGrid()){
            for (Tile tile: tileRow){
                graphics.drawImage(ResourceHandler.getResource(tile.getId()),translateX(tile.getXcoord()) - playerX,translateY(tile.getYcoord()) - playerY);
            }
        }
    }

    private Canvas getGameScreen() {
        return gameScreen;
    }

    public Event<ViewEventMessages> getViewEvent() {
        return viewEvent;
    }
}
