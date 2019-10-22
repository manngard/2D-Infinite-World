package org.openjfx.model;

import org.openjfx.model.noise.DefaultNoiseGenerator;
import org.openjfx.model.noise.NoiseGenerator;
import org.openjfx.model.tile.Tile;
import org.openjfx.model.tile.TileFactory;

import java.util.*;

public class World {
    private TileFactory tileFactory;
    private EntityFactory entityFactory;
    LinkedList<LinkedList<Tile>> worldGrid;
    double worldVerticalSideLength;
    double worldHorizontalSideLength;

    private final double enemyDetectDistance = 7;
    private final double activeDistance = 22;
    final private List<Combatant> activeEnemies = new ArrayList<>();
    final private Map<Coordinates, Combatant> inactiveEnemies = new HashMap<>();
    final private List<Chest> activeChests = new ArrayList<>();
    final private Map<Coordinates, Chest> inactiveChests = new HashMap<>();
    final private List<Combatant> players = new ArrayList<>();
    public Player player;

    public World() {
        this(null);
    }

    /*Initiates int worldHorizontalSideLength rows for the grid matrix,
    then fills every row with int worldVerticalSideLength Tile Objects,
    the center of worldGrid matrix is set to coordinates 0,0

    Initiates inactiveEnemies and inactiveChests with a large pool of enemies/chests
     to be loaded in when their respective Coordinate coords come into activeDistance of Player
    */

    public World(NoiseGenerator noiseGenerator) {
        if (noiseGenerator != null) {
            tileFactory = new TileFactory(noiseGenerator);
        } else {
            tileFactory = new TileFactory(new DefaultNoiseGenerator());
        }
        entityFactory = new EntityFactory();


        player = new Player("Player", 0, 0, 100, 20, 2, 0);
        players.add(player);

        this.worldHorizontalSideLength = 21;
        this.worldVerticalSideLength = 13;

        for (int i = 0; i < 100000; i++) {
            Combatant enemy = entityFactory.generateEnemy();
            inactiveEnemies.put((enemy.getCoords()), enemy);
        }

        for (int i = 0; i < 100000; i++) {
            Chest chest = entityFactory.generateChest();
            inactiveChests.put((chest.getCoords()), chest);
        }

        double xCoord = 0 - ((worldHorizontalSideLength + 1) / 2) - 1;
        double yCoord;

        worldGrid = new LinkedList<>();
        for (int i = 0; i < worldHorizontalSideLength + 2; i++) {
            LinkedList<Tile> worldRow = new LinkedList<>();
            xCoord++;
            yCoord = (0 - (worldVerticalSideLength + 1) / 2);
            for (int j = 0; j < worldVerticalSideLength + 3; j++) {
                worldRow.add(tileFactory.generateTile(xCoord, yCoord));
                yCoord++;
            }
            worldGrid.add(worldRow);
        }
    }


    public List<Combatant> combatantAttacks(Combatant attacker, List<Combatant> defenders) {
        List<Combatant> combatantsHit = new ArrayList<Combatant>();
        for (Combatant defender : defenders) {
            if (inSight(attacker, defender) && isEntityWithinDistance(defender, attacker, attacker.getAtkRange()) && attacker.canAttack()) {
                attacker.setAttackOnCooldown();
                combatantsHit.add(defender);
            }
        }
        return combatantsHit;
    }

    public boolean inSight(Combatant a, Entity b) {
        switch (a.direction) {
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


    /*For each Combatant damaged in List<Combatant> hit, HP of damaged is decreased by Combatant attacker's
      atk value, if Combatant damaged's HP falls under 0 it is removed from world*/

    public void attackHit(Combatant attacker, List<Combatant> hit) {
        for (Combatant damaged : hit) {
            damaged.decHp(attacker.getAtk() - damaged.getDef());
            System.out.print("Player hit enemy");
            System.out.println("Enemy hp = " + damaged.getHp());
            if (damaged.getHp() <= 0) {
                activeEnemies.remove(damaged);
            }

        }


    }
    /*Calculates the absolute distance between entity a and b using the pythagorean theorem and
     returns the corresponding double value */

    public double distance(Entity a, Entity b) {
        double xDist = Math.abs(a.getXcoord() - b.getXcoord());
        double yDist = Math.abs(a.getYcoord() - b.getYcoord());
        return Math.sqrt((yDist * yDist) + (xDist * xDist));
    }

    public boolean isPathFree(Combatant c){
        double checkX1 = (worldHorizontalSideLength - 1)/2;
        double checkY1 = (worldVerticalSideLength - 1)/2;
        double checkX2 = (worldHorizontalSideLength - 1)/2;
        double checkY2 = (worldVerticalSideLength - 1)/2;

        switch(c.direction) {
            case UP:
                checkX2 += 1;
                checkY1 -= 1;
                checkY2 -= 1;
                break;
            case DOWN:
                checkX2 += 1;
                checkY1 += 2;
                checkY2 += 2;
                break;
            case RIGHT:
                checkX1 += 2;
                checkX2 += 2;
                checkY1 += 1;
                break;
            case LEFT:
                checkX1 -= 1;
                checkX2 -= 1;
                checkY1 += 1;
                break;
        }

        if (worldGrid.get((int) checkY1).get((int)checkX1).getISSolid()){
            System.out.print("tile is solid and a " + worldGrid.get((int) checkY1).get((int)checkX1).id);
            return false;
        }
        else if (worldGrid.get((int) checkY2).get((int) checkX2).getISSolid()){
            System.out.print("tile is solid and a " + worldGrid.get((int) checkY2).get((int)checkX2).id);
            return false;
        }

        System.out.print("tiles are not solid and a " + worldGrid.get((int) checkY1).get((int)checkX1).id + " and a " + worldGrid.get((int) checkY2).get((int)checkX2).id);
        return true;

    }

    public boolean isEntityInPath(Combatant a, Entity b){

        if(inSight(a,b)) {
            switch (a.direction) {
                case UP:
                case DOWN:
                    if ((a.coords.xCoord - 0.9) < b.coords.xCoord && b.coords.xCoord < (a.coords.xCoord + 0.9)) {
                        return true;
                    }
                    break;
                case LEFT:
                case RIGHT:
                    if ((a.coords.yCoord - 0.9) < b.coords.yCoord && b.coords.yCoord < (a.coords.yCoord + 0.9)) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }


    /*Checks if the absolute distance between Entity requester and Combatant target.
    If less or equal to double range, returns true. If higher than double range, returns false  */

    public boolean isEntityWithinDistance(Entity target, Combatant requester, double range) {
        if (distance(requester, target) <= range)
            return true;
        return false;
    }

    public void moveMobs() {
        checkIfEntitiesInactive();
        checkIfEntitiesActive();
        //Int to use for future random mob movement
        //int rand = (int)Math.ceil(Math.random() * 2);
        for (Combatant combatant : activeEnemies) {
            if (isEntityWithinDistance(player, combatant, enemyDetectDistance)) {
                if (player.getXcoord() + 0.9 < combatant.getXcoord()) {
                    combatant.move(Movable.Direction.LEFT);
                } else if (player.getXcoord() - 0.9 > combatant.getXcoord()) {
                    combatant.move(Movable.Direction.RIGHT);
                }
                if (player.getYcoord() + 0.9 < combatant.getYcoord()) {
                    combatant.move(Movable.Direction.UP);
                } else if (player.getYcoord() - 0.9 > combatant.getYcoord()) {
                    combatant.move(Movable.Direction.DOWN);
                }
            }
            //  If mobs are not within distance the mobs shall move freely.
            else {
                int rand = (int) Math.ceil(Math.random() * 5);
                switch (rand) {
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


    /*Checks if entities in inactiveChests and inactiveEnemies have come into viewport,
    If they have come into viewport they are removed from inactiveChests or inactiveEnemies Map
    and added to activeChests or activeEnemies List*/

    public void checkIfEntitiesInactive() {
        List<Combatant> newlyInactiveEnemies = new ArrayList<>();
        for (Combatant combatant : activeEnemies) {
            if (!isEntityWithinDistance(combatant, player, activeDistance)) {
                newlyInactiveEnemies.add(combatant);
            }
        }
        for (Combatant combatant : newlyInactiveEnemies) {
            activeEnemies.remove(combatant);
            inactiveEnemies.put(combatant.getCoords(), combatant);
        }
        List<Chest> newlyInactiveChests = new ArrayList<>();
        for (Chest chest : activeChests) {
            if (!isEntityWithinDistance(chest, player, activeDistance)) {
                newlyInactiveChests.add(chest);
            }
        }
        for (Chest chest : newlyInactiveChests) {
            activeChests.remove(chest);
            inactiveChests.put(chest.getCoords(), chest);
        }
    }

    /*Checks if entities in activeChests and activeEnemies still are in viewport,
    If they are no longer in viewport they are stored in inactiveChests or inactiveEnemies Map and
    removed from activeChests or activeEnemies List*/

        public void checkIfEntitiesActive () {
            List<Combatant> newlyActive = new ArrayList<>();
            for (Combatant combatant : inactiveEnemies.values()) {
                if (isEntityWithinDistance(combatant, player, activeDistance)) {
                    activeEnemies.add(combatant);
                    newlyActive.add(combatant);
                }
            }
            for (Combatant combatant : newlyActive) {
                inactiveEnemies.remove(combatant.getCoords());
            }
            List<Chest> newlyActive2 = new ArrayList<>();
            for (Chest chest : inactiveChests.values()) {
                if (isEntityWithinDistance(chest, player, activeDistance)) {
                    activeChests.add(chest);
                    newlyActive2.add(chest);
                }
            }
            for (Chest chest : newlyActive2) {
                inactiveChests.remove(chest.getCoords());
            }
        }

        void updateWorldGrid() {
            final double playerXcoord = Math.round(this.player.getXcoord()); // To fix rounding error
            final double playerYcoord = Math.round(this.player.getYcoord()); // To fix rounding error

            final double maxYViewport = playerYcoord + (worldVerticalSideLength + 1) / 2;
            final double minYViewport = playerYcoord - (worldVerticalSideLength + 1) / 2;
            final double maxXViewport = playerXcoord + (worldHorizontalSideLength + 1) / 2;
            final double minXViewport = playerXcoord - (worldHorizontalSideLength + 1) / 2;

            final boolean shouldUpdateTiles =
                    Math.round(this.player.getPrevYCoord()) != playerYcoord
                 || Math.round(this.player.getPrevXCoord()) != playerXcoord;

            if(shouldUpdateTiles){
                switch (this.player.direction) {
                    case UP:
                        for (LinkedList<Tile> column : worldGrid) {
                            column.removeLast();
                            column.addFirst(tileFactory.generateTile(column.getFirst().getXcoord(), minYViewport));
                        }
                        break;
                    case DOWN:
                        for (LinkedList<Tile> column : worldGrid) {
                            column.removeFirst();
                            column.addLast(tileFactory.generateTile(column.getFirst().getXcoord(), maxYViewport));
                        }
                        break;
                    case LEFT:
                        worldGrid.removeLast();
                        LinkedList<Tile> newFirstColumn = new LinkedList<>();
                        for (int y = (int) minYViewport; y <= (int) maxYViewport; y++) {
                            newFirstColumn.addLast(tileFactory.generateTile(minXViewport, y));
                        }
                        worldGrid.addFirst(newFirstColumn);
                        break;
                    case RIGHT:
                        worldGrid.removeFirst();
                        LinkedList<Tile> newLastColumn = new LinkedList<>();
                        for (int y = (int) minYViewport; y <= (int) maxYViewport; y++) {
                            newLastColumn.addLast(tileFactory.generateTile(maxXViewport, y));
                        }
                        worldGrid.addLast(newLastColumn);
                        break;
                }
            }
        }

        public List<Combatant> getPlayers () {
            return players;
        }

        public List<Combatant> getActiveEnemies () {
            return activeEnemies;
        }

        public List<Chest> getActiveChests () {
            return activeChests;
        }

        public Player getPlayer () {
            return player;
        }

        public LinkedList<LinkedList<Tile>> getWorldGrid () {
            return worldGrid;
        }

    }

