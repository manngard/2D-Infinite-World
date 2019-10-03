package org.openjfx.model;

import org.openjfx.model.tilepackage.Tile;
import org.openjfx.model.tilepackage.TileFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private final TileFactory tileFactory = new TileFactory();
    List<List<Tile>> worldGrid;
    double worldVerticalSideLength;
    double worldHorizontalSideLength;

    final private ArrayList<Combatant> enemies = new ArrayList<Combatant>();

    public Player player;

    /*Initiates int worldHorizontalSideLength rows for the grid matrix,
    then fills every row with int worldVerticalSideLength Tile Objects,
    the center of matrix has coordinates 0,0*/

    public World(){

        player = new Player("Player",0,0,10,10, 2);
        this.worldHorizontalSideLength = 21;
        this.worldVerticalSideLength = 13;

        for(int i = 0; i < 4; i++) {
            Random rand = new Random();
            enemies.add(new Enemy("Mob", (rand.nextInt(10) - 5), rand.nextInt(10) - 5, 100, 10, 1));
        }

        double xCoord = 0 - ((worldHorizontalSideLength - 1)/2) - 1;
        double yCoord;
        worldGrid = new ArrayList<List<Tile>>();
        for (int i = 0; i<worldHorizontalSideLength;i++){
            worldGrid.add(new ArrayList<Tile>());
        }
        for (List<Tile> worldrow : worldGrid){
            xCoord++;
            yCoord = (0 - (worldVerticalSideLength - 1)/2);
            for (int i = 0; i<worldVerticalSideLength;i++){
                worldrow.add(tileFactory.getRandomTile(xCoord,yCoord));
                yCoord++;
            }
        }
    }
    public List<List<Tile>> getWorldGrid() {
        return worldGrid;
    }

    //combat related
    public ArrayList<Combatant> playerAttacks(Combatant a, ArrayList<Combatant> d){

        System.out.print(player.direction);

        ArrayList<Combatant> combatantsHit = new ArrayList<Combatant>();

        for(Combatant c: d){
            switch (a.direction) {
                case UP:
                    System.out.print(" : " + c.ycoord + " " + player.ycoord);
                    if (c.ycoord < (a.ycoord)) {
                        System.out.print(" valid ");
                        if (a.atkRange > distance(c, a)) {
                            combatantsHit.add(c);
                        }
                    }
                    break;
                case DOWN:
                    System.out.print(" : " + c.ycoord + " " + player.ycoord);
                    if (c.ycoord > (a.ycoord)) {
                        System.out.print(" valid ");
                        if (a.atkRange > distance(c, a)) {
                            combatantsHit.add(c);
                        }
                    }
                    break;
                case RIGHT:
                    System.out.print(" : " + c.xcoord + " " + player.xcoord);
                    if (c.xcoord > (a.xcoord)) {
                        System.out.print(" valid ");
                        if (a.atkRange > distance(c, a)) {
                            combatantsHit.add(c);
                        }
                    }
                    break;
                case LEFT:
                    System.out.print(" : " + c.xcoord + " " + player.xcoord);
                    if (c.xcoord < (a.xcoord)) {
                        System.out.print(" valid ");
                        if (a.atkRange > distance(c, a)) {
                            combatantsHit.add(c);
                        }
                    }

                    break;
            }

        }

        return combatantsHit;

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

    public void moveToEntity(Combatant a, Entity b){
        double xDistance = Math.abs(player.xcoord - a.xcoord);
        double yDistance = Math.abs(player.ycoord - a.ycoord);

        if(xDistance >= yDistance && a.xcoord > b.xcoord){
            a.move(Movable.Direction.LEFT);
        }
        else if(xDistance >= yDistance && a.xcoord < b.xcoord){
            a.move(Movable.Direction.RIGHT);
        }
        else if(yDistance > xDistance && a.ycoord > b.ycoord){
            a.move(Movable.Direction.DOWN);
        }
        else if(yDistance > xDistance && a.ycoord < b.ycoord){
            a.move(Movable.Direction.UP);
        }
    }

    //getters and setters

    public ArrayList<Combatant> getEnemies(){

        return enemies;
    }
}
