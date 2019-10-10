package org.openjfx.model;

import org.openjfx.model.noise.DefaultNoiseGenerator;
import org.openjfx.model.noise.NoiseGenerator;
import org.openjfx.model.tile.Tile;
import org.openjfx.model.tile.TileFactory;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.spi.LocaleNameProvider;

public class World {
    private final TileFactory tileFactory;
    LinkedList<LinkedList<Tile>> worldGrid;
    double worldVerticalSideLength;
    double worldHorizontalSideLength;
    private final int enemyDetectDistance = 10;

    final private ArrayList<Combatant> enemies = new ArrayList<Combatant>();
    final private ArrayList<Chest> chests = new ArrayList<Chest>();

    public Player player;

    /*Initiates int worldHorizontalSideLength rows for the grid matrix,
    then fills every row with int worldVerticalSideLength Tile Objects,
    the center of matrix has coordinates 0,0*/

    public World() {
        this(null);
    }

    public World(NoiseGenerator noiseGenerator){
        if(noiseGenerator != null) {
            tileFactory = new TileFactory(noiseGenerator);
        }
        else {
            tileFactory = new TileFactory(new DefaultNoiseGenerator());
        }

        player = new Player("Player",0,0,10,10, 2);

        this.worldHorizontalSideLength = 21;
        this.worldVerticalSideLength = 13;

        for(int i = 0; i < 4; i++) {
            Random rand = new Random();
            enemies.add(new Enemy("Goblin", (rand.nextInt(10) - 5), rand.nextInt(10) - 5, 100, 10, 1));
        }

        double xCoord = 0 - ((worldHorizontalSideLength - 1)/2) - 1;
        double yCoord;

        worldGrid = new LinkedList<>();
        for (int i = 0; i<worldHorizontalSideLength;i++){
            worldGrid.add(new LinkedList<Tile>());
        }
        for (List<Tile> worldrow : worldGrid){
            xCoord++;
            yCoord = (0 - (worldVerticalSideLength - 1)/2);
            for (int i = 0; i<worldVerticalSideLength;i++){
                worldrow.add(tileFactory.generateTile(xCoord,yCoord));
                Chest chest = worldrow.get(i).getChest();
                if (chest != null){
                    chests.add(chest);
                }
                yCoord++;
            }
        }
    }
    public LinkedList<LinkedList<Tile>> getWorldGrid() {
        return worldGrid;
    }

    //combat related
    public ArrayList<Combatant> playerAttacks(Combatant a, ArrayList<Combatant> d){

        System.out.print(player.direction);

        ArrayList<Combatant> combatantsHit = new ArrayList<Combatant>();

        for(Combatant c: d){
            if(inSight(a, c) & isEntityWithinDistance(c,enemyDetectDistance)){
                combatantsHit.add(c);

            }

        }

        return combatantsHit;

    }

    public boolean inSight(Combatant a, Entity b){

        switch(a.direction){
            case UP:
                if (b.ycoord < (a.ycoord - 0.5)) {
                    return true;
                }
                break;
            case DOWN:
                if (b.ycoord > (a.ycoord + 0.5)) {
                    return true;
                }
                break;
            case LEFT:
                if (b.xcoord < (a.xcoord - 0.5)) {
                    return true;
                }
                break;
            case RIGHT:
                if (b.xcoord > (a.xcoord + 0.5)) {
                    return true;
                }
                break;
        }

        return false;
    }

    public void attackHit(Combatant a, ArrayList<Combatant> hit){

        for(Combatant d: hit) {
            d.decHp(a.getAtk());
            System.out.print("Player hit enemy");
            if(d.getHp() <= 0){
                enemies.remove(d);
            }

        }


    }

    public double distance(Entity a, Entity b){

        double xDist = Math.abs(a.xcoord - b.xcoord);
        double yDist = Math.abs(a.ycoord - b.ycoord);
        return Math.sqrt((yDist * yDist) + (xDist * xDist));
    }

    public boolean isPathFree(Combatant c){
        double checkX1 = c.xcoord;
        double checkY1 = c.ycoord;
        double checkX2 = c.xcoord;
        double checkY2 = c.ycoord;

        switch(c.direction) {
            case UP:
                checkY1 = +0.5;
                checkX1 = +0.5;
                checkX2 = -0.5;
                checkY2 = +0.5;
                break;
            case DOWN:
                checkY1 = -0.5;
                checkX1 = +0.5;
                checkX2 = -0.5;
                checkY2 = -0.5;
                break;
            case RIGHT:
                checkY1 = +0.5;
                checkX1 = +0.5;
                checkX2 = +0.5;
                checkY2 = -0.5;
                break;
            case LEFT:
                checkY1 = +0.5;
                checkX1 = -0.5;
                checkX2 = -0.5;
                checkY2 = -0.5;
                break;
        }

        if (worldGrid.get(Math.toIntExact(Math.round(checkY1))).get(Math.toIntExact(Math.round(checkX1))).getISSolid()){
            return false;
        }
        else if (worldGrid.get(Math.toIntExact(Math.round(checkY2))).get(Math.toIntExact(Math.round(checkX2))).getISSolid()){
            return false;
        }

        return true;

    }

    public boolean isEntityWithinDistance(Entity entity, double range){
        if(distance(player, entity) <= range)
            return true;
        return false;
    }

    public void moveMobs(){

        //Int to use for future random mob movement
        //int rand = (int)Math.ceil(Math.random() * 2);
        for(Combatant combatant: enemies){
            if(isEntityWithinDistance(combatant, enemyDetectDistance)){
                if(player.xcoord + 0.9 < combatant.xcoord){
                    combatant.move(Movable.Direction.LEFT);
                }
                else if(player.xcoord - 0.9 > combatant.xcoord){
                    combatant.move(Movable.Direction.RIGHT);
                }
                if(player.ycoord + 0.9 < combatant.ycoord){
                    combatant.move(Movable.Direction.UP);
                }
                else if(player.ycoord - 0.9 > combatant.ycoord){
                    combatant.move(Movable.Direction.DOWN);
                }
            }
            //  If mobs are not within distance the mobs shall move freely.
            else{
                int rand = (int)Math.ceil(Math.random() * 5);
                switch (rand){
                    case 1:
                        combatant.move(Movable.Direction.DOWN);
                        break;
                    case 2:
                        combatant.move(Movable.Direction.UP);
                        break;
                    case 3:
                        combatant.move(Movable.Direction.LEFT);
                        break;
                    case 4:
                        combatant.move(Movable.Direction.RIGHT);
                        break;
                    case 5:
                        break;
                }
            }
        }

    }

    //getters and setters

    public ArrayList<Combatant> getEnemies(){

        return enemies;
    }

    public ArrayList<Chest> getChests() {
        return chests;
    }

    public Player getPlayer() {
        return player;
    }

    public void updateWorldGrid() {
        Player p = this.player;
        final double maxYViewport = p.ycoord + (worldVerticalSideLength - 1) / 2;
        final double minYViewport = p.ycoord - (worldVerticalSideLength - 1) / 2;
        final double maxXViewport = p.xcoord + (worldHorizontalSideLength - 1) / 2;
        final double minXViewport = p.xcoord - (worldHorizontalSideLength - 1) / 2;
        switch(p.direction) {
            case UP:
                for(LinkedList<Tile> column : worldGrid) {
                    column.removeLast();
                    column.addFirst(tileFactory.generateTile(column.getFirst().xcoord, minYViewport));
                }
                break;
            case DOWN:
                for(LinkedList<Tile> column : worldGrid) {
                    column.removeFirst();
                    column.addLast(tileFactory.generateTile(column.getFirst().xcoord, maxYViewport));
                }
                break;
            case LEFT:
                worldGrid.removeLast();
                LinkedList<Tile> newFirstColumn = new LinkedList<>();
                for(int y = (int)minYViewport; y <= (int)maxYViewport; y++) {
                    newFirstColumn.addLast(tileFactory.generateTile(minXViewport, y));
                }
                worldGrid.addFirst(newFirstColumn);
                break;
            case RIGHT:
                worldGrid.removeFirst();
                LinkedList<Tile> newLastColumn = new LinkedList<>();
                for(int y = (int)minYViewport; y <= (int)maxYViewport; y++) {
                    newLastColumn.addLast(tileFactory.generateTile(maxXViewport, y));
                }
                worldGrid.addLast(newLastColumn);
                break;
        }
    }
}
