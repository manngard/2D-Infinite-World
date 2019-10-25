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

import org.openjfx.model.entity.Chest;
import org.openjfx.model.entity.Combatant;
import org.openjfx.model.item.Item;
import org.openjfx.model.entity.tile.Tile;

import org.openjfx.model.event.Event;
import org.openjfx.model.event.EventListener;

import java.util.List;

public class View {
    private final Canvas gameScreen;
    private final int screenXSize;
    private final int screenYSize;
    private final Rectangle playerHealthbar;
    private Event<ViewEventMessages> viewEvent;
    private final int pixelSize = 32;
    private final GraphicsContext graphics;

    public View(Stage stage, Model model) {


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

        model.registerListener(new EventListener<ModelEventMessage>() {
            @Override
            public void func(ModelEventMessage emsg, Object data) {
                rerender(emsg, data);
            }
        });
    }


    /*Receives a ModelEventMessage UPDATE and renders the Object data and overlays onto the screen*/

    private void rerender(ModelEventMessage emsg, Object data) {
        switch (emsg) {
            case UPDATE:
                World world = (World) data;

                double xOffset = (screenXSize / 2) - pixelSize / 2;
                double yOffset = (screenYSize / 2) - pixelSize / 2;
                double playerX = translateX(world.player.getXCoord()) - xOffset;
                double playerY = translateY(world.player.getYCoord()) - yOffset;
                int playerHP = world.player.getHp();

                Item[] playerInventory = world.player.getInventory();

                gameScreen.getGraphicsContext2D().clearRect(0, 0, 1000,1000);
                renderTileWorld(world,playerX,playerY);
                renderEntities(world.getActiveEnemies(), world.getActiveChests(),playerX,playerY);
                drawObject(world.player.getId(),translateX(0),translateY(0));
                renderOverlay(playerHP,playerInventory);

        }
    }


    /*Translates x-coordinate from the coordinate system in model to JavaFX coordinate system*/

    private double translateY(double modelY) {
        return gameScreen.getHeight() / 2 + modelY * pixelSize - 16;
    }

    /*Translates y-coordinate from the coordinate system in model to JavaFX coordinate system*/

    private double translateX(double modelX) {
        return gameScreen.getWidth() / 2 + modelX * pixelSize - 16;
    }

    /*Draws sprite for object with String id onto coordinates (x,y)*/

    private void drawObject(String id, double x, double y) {
        graphics.drawImage(ResourceHandler.getResource(id), x, y);
    }

    /*Takes in HP and inventory and uses this to render overlays for the gameScreen*/

    private void renderOverlay(int HP, Item[] inventory){
        double healthBarWidth = 1.8 * HP;
        playerHealthbar.setWidth(healthBarWidth);
        graphics.setFill(Color.WHITE);
        graphics.fillRect(gameScreen.getWidth() - 250, 20, 180, 30);
        graphics.setFill(playerHealthbar.getFill());
        graphics.fillRect(gameScreen.getWidth() - 250, 20, healthBarWidth, playerHealthbar.getHeight());
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

    /*Takes in enemies and chests and renders all of the entities onto the screen offset from Player position*/

    private void renderEntities(List <Combatant> enemies, List<Chest> chests, double playerX, double playerY){
        for (Chest chest : chests){
            graphics.drawImage(ResourceHandler.getResource(chest.getId()), translateX(chest.getXCoord()) - playerX, translateY(chest.getYCoord()) - playerY);
        }
        for(Combatant e : enemies){
            double healthBarXCoord = translateX(e.getXCoord())-playerX - 15;
            double healthBarYCoord = translateY(e.getYCoord())-playerY - pixelSize/2;
            drawObject(e.getId(), translateX(e.getXCoord())-playerX, translateY(e.getYCoord())-playerY);
            graphics.setFill(playerHealthbar.getFill());
            graphics.fillRect(healthBarXCoord,healthBarYCoord,6*e.getHp()/10,10);
            drawObject("EnemyHealthbarContainer",healthBarXCoord,healthBarYCoord);
        }
    }

    /*Takes in the world and renders all tiles onto the screen offset from Player position*/

    private void renderTileWorld(World world, double playerX, double playerY) {
        for (List<Tile> tileRow: world.getWorldGrid()){
            for (Tile tile: tileRow){
                graphics.drawImage(ResourceHandler.getResource(tile.getId()),translateX(tile.getXCoord()) - playerX,translateY(tile.getYCoord()) - playerY);
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
