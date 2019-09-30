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

    final private ArrayList<Enemy> EnemiesInView = new ArrayList<Enemy>();

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
        Random rand = new Random();

        for(int i = 0; i < 4; i++) {
            EnemiesInView.add(new Enemy("Mob", (rand.nextInt(100) - 50), rand.nextInt(100) - 50, 100, 10, 1));
        }

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
    public void didPlayerHit(){
        switch (player.direction) {
            case UP: {
                for(int i = 0; i < EnemiesInView.size();i++){
                    if(EnemiesInView.get(i).ycoord > (player.ycoord + 0.5)){
                        if(player.atkRange > distance(EnemiesInView.get(i), player)){
                            attackHit(player,EnemiesInView.get(i));
                        }
                    }
                }

            }
            case DOWN: {
                for(int i = 0; i < EnemiesInView.size();i++){
                    if(EnemiesInView.get(i).ycoord < (player.ycoord - 0.5)){
                        if(player.atkRange > distance(EnemiesInView.get(i), player)){
                            attackHit(player,EnemiesInView.get(i));
                        }
                    }
                }

            }
            case RIGHT: {
                for(int i = 0; i < EnemiesInView.size();i++){
                    if(EnemiesInView.get(i).xcoord > (player.xcoord + 0.5)){
                        if(player.atkRange > distance(EnemiesInView.get(i), player)){
                            attackHit(player,EnemiesInView.get(i));
                        }
                    }
                }

            }
            case LEFT: {
                for(int i = 0; i < EnemiesInView.size();i++){
                    if(EnemiesInView.get(i).ycoord < (player.ycoord - 0.5)){
                        if(player.atkRange > distance(EnemiesInView.get(i), player)){
                            attackHit(player,EnemiesInView.get(i));
                        }
                    }
                }

            }
        }

    }

    public void didEnemyHit(Enemy e){
        switch (e.direction) {
            case UP: {
                if(player.ycoord > (e.ycoord + 0.5)){
                    if(e.atkRange > distance(e, player)){
                        attackHit(e, player);
                    }
                }
            }
            case DOWN: {
                if(player.ycoord < (e.ycoord - 0.5)){
                    if(e.atkRange > distance(e, player)){
                        attackHit(e, player);
                    }
                }
            }
            case RIGHT: {
                if(player.xcoord > (e.xcoord + 0.5)){
                    if(e.atkRange > distance(e, player)){
                        attackHit(e, player);
                    }
                }

            }
            case LEFT: {
                if(player.xcoord < (e.xcoord - 0.5)){
                    if(e.atkRange > distance(e, player)){
                        attackHit(e, player);
                    }
                }

            }
        }

    }

    public void attackHit(Combatant a, Combatant d){

        d.decHp(a.getAtk());
    }

    public double distance(Entity a, Entity b){

        double xDist = Math.abs(a.xcoord - b.xcoord);
        double yDist = Math.abs(a.ycoord - b.ycoord);
        return Math.sqrt((yDist * yDist) + (xDist * xDist));
    }

    //getters and setters

    public List<Enemy> getEnemiesInView(){

        return EnemiesInView;
    }
}
