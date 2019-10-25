package org.openjfx.model;

public interface Movable {
    enum Direction{
        LEFT, RIGHT, UP, DOWN
    }
    void move(Direction direction);
}
