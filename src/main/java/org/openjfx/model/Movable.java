package org.openjfx.model;

public interface Movable {
    public enum Direction{
        LEFT, RIGHT, UP, DOWN;
    }
    public void move(Direction direction);
}
