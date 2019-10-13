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
import org.openjfx.model.EventMessage;
import org.openjfx.model.World;
import org.openjfx.model.tile.Tile;
import org.openjfx.utils.event.Event;
import org.openjfx.utils.event.EventListener;

import java.util.List;

public class View {
    private Stage stage;
    private Canvas gameScreen;
    private int screenXSize;
    private int screenYSize;
    private Rectangle healthbar;

    final int pixelSize = 32;

    private Event<ViewEventMessages> viewEvent;


    public View(Stage stage, Event<EventMessage> modelHasUpdateEvent) {
        this.stage = stage;

        StackPane layers = new StackPane();

        screenXSize = pixelSize*21;
        screenYSize = pixelSize*13;
        Scene scene = new Scene(layers, screenXSize,screenYSize);

        gameScreen = new Canvas(screenXSize,screenYSize);
        layers.getChildren().add(gameScreen);

        healthbar = new Rectangle(180,30,Color.color(1, 0.2, 0.2)
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

                double xOffset = (screenXSize/2) -pixelSize/2;
                double yOffset = (screenYSize/2) -pixelSize/2;
                double playerX = translateX(world.player.getXcoord()) - xOffset;
                double playerY = translateY(world.player.getYcoord()) - yOffset;


                int playerHP = world.player.getHp();
                Item [] playerInventory = world.player.getInventory();
                gameScreen.getGraphicsContext2D().clearRect(0, 0, 1000,1000);
                renderTileWorld(world,playerX,playerY);
                drawObject(world.player.getId(),translateX(0),translateY(0));

                for(Combatant e : world.getEnemies()){
                    drawObject(e.getId(), translateX(e.getXcoord())-playerX, translateY(e.getYcoord())-playerY);
                }

                renderOverlay(playerHP,playerInventory);
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

    private void renderOverlay(int HP, Item[] inventory){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        int healthbarWidth = 18*HP;
        healthbar.setWidth(healthbarWidth);
        graphics.setFill(Color.WHITE);
        graphics.fillRect(gameScreen.getWidth() -200, 20, 180,30);
        graphics.setFill(healthbar.getFill());
        graphics.fillRect(gameScreen.getWidth() -200,20,healthbarWidth,healthbar.getHeight());
        graphics.drawImage(ResourceHandler.getResource("HealthbarContainer"),gameScreen.getWidth() -200, 20);

        for (int i = 0; i<inventory.length; i++){
            double xCoord = gameScreen.getWidth() -(pixelSize*2*(inventory.length -i) + 20);
            double yCoord = gameScreen.getHeight() - pixelSize*2 - 20;
            graphics.setFill(Color.WHITE);
            graphics.fillRect(xCoord, yCoord, 64,64);
            if (inventory[i] != null){
                graphics.drawImage(ResourceHandler.getResource(inventory[i].getId()),xCoord,yCoord);
            }
            graphics.drawImage(ResourceHandler.getResource("ItemContainer"),xCoord,yCoord);
        }


    }

    public void renderTileWorld(World world, double playerX, double playerY){
        GraphicsContext graphics = gameScreen.getGraphicsContext2D();
        for (List<Tile> tileRow: world.getWorldGrid()){
            for (Tile tile: tileRow){
                graphics.drawImage(ResourceHandler.getResource(tile.getId()),translateX(tile.getXcoord()) - playerX,translateY(tile.getYcoord()) - playerY);
                Chest chest = tile.getChest();
                if (chest != null){
                    graphics.drawImage(ResourceHandler.getResource(chest.getId()),translateX(tile.getXcoord()) - playerX,translateY(tile.getYcoord()) - playerY);
                }
            }
        }
    }

    public Canvas getGameScreen() {
        return gameScreen;
    }

    public Event<ViewEventMessages> getViewEvent() {
        return viewEvent;
    }
}
