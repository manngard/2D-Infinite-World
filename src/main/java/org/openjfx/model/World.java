package org.openjfx.model;

import org.openjfx.model.tile.Tile;
import org.openjfx.model.tile.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final TileFactory tileFactory = new TileFactory();
    List<List<Tile>> worldGrid;
    double worldVerticalSideLength;
    double worldHorizontalSideLength;

    ArrayList<Enemy> EnemiesInView;

    public Player player;

    /*Initiates int worldHorizontalSideLength rows for the grid matrix,
    then fills every row with int worldVerticalSideLength Tile Objects,
    the center of matrix has coordinates 0,0*/

    public World(){

        player = new Player("Player",0,0,10,10, 1);
        this.worldHorizontalSideLength = 21;
        this.worldVerticalSideLength = 13;
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
                worldrow.add(tileFactory.generateTile(xCoord,yCoord));
                yCoord++;
            }
        }
    }
    public List<List<Tile>> getWorldGrid() {
        return worldGrid;
    }
    public void makeAttack(Combatant c){

        double xDist;
        double yDist;

        if(c.id == "Player") {
            switch (c.direction) {
                case UP: {
                    for(int i = 0; i < EnemiesInView.size();i++){
                        if(EnemiesInView.get(i).ycoord > (c.ycoord + 0.5)){

                            xDist = c.xcoord - EnemiesInView.get(i).xcoord;
                            yDist = c.ycoord - EnemiesInView.get(i).ycoord;
                            if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                                attackHit(c,EnemiesInView.get(i));
                            }
                        }
                    }

                }
                case DOWN: {
                    for(int i = 0; i < EnemiesInView.size();i++){
                        if(EnemiesInView.get(i).ycoord < (c.ycoord - 0.5)){

                            xDist = c.xcoord - EnemiesInView.get(i).xcoord;
                            yDist = c.ycoord - EnemiesInView.get(i).ycoord;
                            if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                                attackHit(c,EnemiesInView.get(i));
                            }
                        }
                    }

                }
                case RIGHT: {
                    for(int i = 0; i < EnemiesInView.size();i++){
                        if(EnemiesInView.get(i).xcoord > (c.xcoord + 0.5)){

                            xDist = c.xcoord - EnemiesInView.get(i).xcoord;
                            yDist = c.ycoord - EnemiesInView.get(i).ycoord;
                            if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                                attackHit(c,EnemiesInView.get(i));
                            }
                        }
                    }

                }
                case LEFT: {
                    for(int i = 0; i < EnemiesInView.size();i++){
                        if(EnemiesInView.get(i).ycoord < (c.ycoord - 0.5)){

                            xDist = c.xcoord - EnemiesInView.get(i).xcoord;
                            yDist = c.ycoord - EnemiesInView.get(i).ycoord;
                            if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                                attackHit(c,EnemiesInView.get(i));
                            }
                        }
                    }

                }
            }
        }
        if(c.id == "Enemy") {
            switch (c.direction) {
                case UP: {
                    if(player.ycoord > (c.ycoord + 0.5)){

                        xDist = c.xcoord - player.xcoord;
                        yDist = c.ycoord - player.ycoord;
                        if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                            attackHit(c,player);
                        }
                    }

                }
                case DOWN: {
                    if(player.ycoord < (c.ycoord - 0.5)){

                        xDist = c.xcoord - player.xcoord;
                        yDist = c.ycoord - player.ycoord;
                        if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                            attackHit(c,player);
                        }
                    }

                }
                case RIGHT: {
                    if(player.xcoord > (c.xcoord + 0.5)){

                        xDist = c.xcoord - player.xcoord;
                        yDist = c.ycoord - player.ycoord;
                        if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                            attackHit(c,player);
                        }
                    }

                }
                case LEFT: {
                    if(player.xcoord < (c.xcoord - 0.5)){

                        xDist = c.xcoord - player.xcoord;
                        yDist = c.ycoord - player.ycoord;
                        if(c.atkRange > (xDist * xDist) + (yDist * yDist)){
                            attackHit(c,player);
                        }
                    }

                }
            }
        }

    }

    public void attackHit(Combatant a, Combatant d){

        d.decHp(a.getAtk());
    }
}
