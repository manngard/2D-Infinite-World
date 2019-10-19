package org.openjfx.model;

import org.openjfx.model.noise.DefaultNoiseGenerator;
import org.openjfx.model.noise.NoiseGenerator;
import org.openjfx.model.tile.Tile;
import org.openjfx.model.tile.TileFactory;

import java.util.*;

public class World {
    private final TileFactory tileFactory;
    private final EntityFactory entityFactory;
    LinkedList<LinkedList<Tile>> worldGrid;
    double worldVerticalSideLength;
    double worldHorizontalSideLength;

    private final double enemyDetectDistance = 7; //MOVE LATER
    private final double activeDistance = 22;
    final private List<Combatant> activeEnemies = new ArrayList<>();
    final private Map<Coords,Combatant> inactiveEnemies = new HashMap<>();
    final private List<Chest> activeChests = new ArrayList<>();
    final private Map<Coords,Chest> inactiveChests = new HashMap<>();
    final private List<Combatant> players = new ArrayList<>();
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
        entityFactory = new EntityFactory();

        player = new Player("Player",0,0,10,2, 2);
        players.add(player);

        this.worldHorizontalSideLength = 21;
        this.worldVerticalSideLength = 13;

        for(int i = 0; i < 100000; i++) {
            Combatant enemy = entityFactory.generateEnemy();
            inactiveEnemies.put((enemy.getCoords()),enemy);
        }

        for(int i = 0; i < 100000; i++) {
            Chest chest = entityFactory.generateChest();
            inactiveChests.put((chest.getCoords()),chest);
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
                yCoord++;
            }
        }
    }
    public LinkedList<LinkedList<Tile>> getWorldGrid() {
        return worldGrid;
    }

    //combat related
    public List<Combatant> combatantAttacks(Combatant attacker, List<Combatant> defenders){

        System.out.print(player.direction);

        List<Combatant> combatantsHit = new ArrayList<Combatant>();

        for(Combatant defender: defenders){
            if(inSight(attacker, defender) && isEntityWithinDistance(defender,attacker,attacker.getAtkRange()) && attacker.canAttack()){
                attacker.setAttackOnCooldown();
                combatantsHit.add(defender);
            }
        }

        return combatantsHit;

    }

    public boolean inSight(Combatant a, Entity b){
        switch(a.direction){
            case UP:
                if (b.getYcoord() < (a.getYcoord() - 0.5)) {
                    return true;
                }
                break;
            case DOWN:
                if (b.getYcoord() > (a.getYcoord() + 0.5)) {
                    return true;
                }
                break;
            case LEFT:
                if (b.getXcoord() < (a.getXcoord() - 0.5)) {
                    return true;
                }
                break;
            case RIGHT:
                if (b.getXcoord() > (a.getXcoord() + 0.5)) {
                    return true;
                }
                break;
        }

        return false;
    }

    public void attackHit(Combatant a, List<Combatant> hit){

        for(Combatant d: hit) {
            d.decHp(a.getAtk());
            System.out.print("Player hit enemy");
            if(d.getHp() <= 0){
                activeEnemies.remove(d);
            }

        }


    }

    public double distance(Entity a, Entity b){

        double xDist = Math.abs(a.getXcoord() - b.getXcoord());
        double yDist = Math.abs(a.getYcoord() - b.getYcoord());
        return Math.sqrt((yDist * yDist) + (xDist * xDist));
    }

    public boolean isPathFree(Combatant c){
        double checkX1 = c.getXcoord();
        double checkY1 = c.getYcoord();
        double checkX2 = c.getXcoord();
        double checkY2 = c.getYcoord();

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

    public boolean isEntityWithinDistance(Entity entity, Combatant attacker, double range){
        if(distance(attacker, entity) <= range)
            return true;
        return false;
    }

    public void moveMobs(){
        checkIfEntitiesInactive();
        checkIfEntitiesActive();
        //Int to use for future random mob movement
        //int rand = (int)Math.ceil(Math.random() * 2);
        for(Combatant combatant: activeEnemies){
            if(isEntityWithinDistance(player, combatant, enemyDetectDistance)){
                if(player.getXcoord() + 0.9 < combatant.getXcoord()){
                    combatant.move(Movable.Direction.LEFT);
                }
                else if(player.getXcoord() - 0.9 > combatant.getXcoord()){
                    combatant.move(Movable.Direction.RIGHT);
                }
                if(player.getYcoord() + 0.9 < combatant.getYcoord()){
                    combatant.move(Movable.Direction.UP);
                }
                else if(player.getYcoord() - 0.9 > combatant.getYcoord()){
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

    public List<Combatant> getActiveEnemies(){

        return activeEnemies;
    }

    public void checkIfEntitiesInactive() { //not optimized
        List<Combatant> newlyInactiveEnemies = new ArrayList<>();
        for (Combatant combatant : activeEnemies) {
            if (!isEntityWithinDistance(combatant, player, activeDistance)) {
                newlyInactiveEnemies.add(combatant);
            }
        }
        for (Combatant combatant : newlyInactiveEnemies){
            activeEnemies.remove(combatant);
            inactiveEnemies.put(combatant.getCoords(), combatant);
        }
        List<Chest> newlyInactiveChests = new ArrayList<>();
        for (Chest chest : activeChests) {
            if (!isEntityWithinDistance(chest, player, activeDistance)) {
                newlyInactiveChests.add(chest);
            }
        }
        for (Chest chest : newlyInactiveChests){
            activeChests.remove(chest);
            inactiveChests.put(chest.getCoords(), chest);
        }
    }

    public void checkIfEntitiesActive(){ //not optimized
        List<Combatant> newlyActive = new ArrayList<>();
        for (Combatant combatant : inactiveEnemies.values()) {
            if (isEntityWithinDistance(combatant, player, activeDistance)) {
                activeEnemies.add(combatant);
                newlyActive.add(combatant);
            }
        }
        for (Combatant combatant : newlyActive){
            inactiveEnemies.remove(combatant.getCoords());
        }
        List<Chest> newlyActive2 = new ArrayList<>();
        for (Chest chest : inactiveChests.values()) {
            if (isEntityWithinDistance(chest, player, activeDistance)) {
                activeChests.add(chest);
                newlyActive2.add(chest);
            }
        }
        for (Chest chest : newlyActive2){
            inactiveChests.remove(chest.getCoords());
        }

    }

    public List<Chest> getActiveChests() {
        return activeChests;
    }

    public Player getPlayer() {
        return player;
    }

    public void updateWorldGrid() {
        Player p = this.player;
        final double maxYViewport = p.getYcoord() + (worldVerticalSideLength - 1) / 2;
        final double minYViewport = p.getYcoord() - (worldVerticalSideLength - 1) / 2;
        final double maxXViewport = p.getXcoord() + (worldHorizontalSideLength - 1) / 2;
        final double minXViewport = p.getXcoord() - (worldHorizontalSideLength - 1) / 2;
        switch(p.direction) {
            case UP:
                for(LinkedList<Tile> column : worldGrid) {
                    column.removeLast();
                    column.addFirst(tileFactory.generateTile(column.getFirst().getXcoord(), minYViewport));
                }
                break;
            case DOWN:
                for(LinkedList<Tile> column : worldGrid) {
                    column.removeFirst();
                    column.addLast(tileFactory.generateTile(column.getFirst().getXcoord(), maxYViewport));
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

    public List<Combatant> getPlayers() {
        return players;
    }
}

