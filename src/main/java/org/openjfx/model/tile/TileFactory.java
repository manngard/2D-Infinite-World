package org.openjfx.model.tile;

import javafx.util.Pair;
import org.openjfx.model.noise.Noise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileFactory {
    List<Pair<String, Double>> tileTypes = new ArrayList<>();

    public TileFactory() {
        tileTypes.add(new Pair<>("Grass", 0.4));
        tileTypes.add(new Pair<>("Lava",0.01));
        tileTypes.add(new Pair<>("Mountain", 0.08));
        double remainingChance = 1 - tileTypes.stream().map(x -> x.getValue()).reduce(0.0, (acc, x) -> acc + x);
        tileTypes.add(new Pair<>("Grass", remainingChance));
    }

    public Tile generateTile(double x, double y) {
        double tileValue = Noise.getInstance().getValue((int) x, (int) y);
        double prevVal = 0.0;
        for(int i = 0; i < tileTypes.size(); i++) {
            if(prevVal < tileValue && tileValue <= tileTypes.get(i).getValue() + prevVal) {
                return new Tile(null, tileTypes.get(i).getKey(), x, y);
            }
            prevVal += tileTypes.get(i).getValue();
        }

        return null;
    }
}