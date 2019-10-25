package org.openjfx.model.entity.tile;

import javafx.util.Pair;
import org.openjfx.model.noise.NoiseGenerator;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {
    private final List<Pair<String, Double>> tileTypes = new ArrayList<>();
    private final NoiseGenerator noiseGenerator;

    public TileFactory(NoiseGenerator ng) {
        this.noiseGenerator = ng;

        tileTypes.add(new Pair<>("Grass", 0.4));
        tileTypes.add(new Pair<>("Lava",0.01));
        tileTypes.add(new Pair<>("Mountain", 0.08));
        double remainingChance = 1 - tileTypes.stream().map(x -> x.getValue()).reduce(0.0, (acc, x) -> acc + x);
        tileTypes.add(new Pair<>("Grass", remainingChance));
    }

    public Tile generateTile(double x, double y) {
        double tileValue = noiseGenerator.getValue((int) x, (int) y);
        double prevVal = 0.0;
        for(int i = 0; i < tileTypes.size(); i++) {
            if(prevVal <= tileValue && tileValue <= tileTypes.get(i).getValue() + prevVal) {
                return new Tile(tileTypes.get(i).getKey(), x, y);
            }
            prevVal += tileTypes.get(i).getValue();
        }

        return null;
    }
}
