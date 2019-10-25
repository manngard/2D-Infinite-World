package org.openjfx.model.entity;

public interface Movable {
    enum Direction{
        LEFT, RIGHT, UP, DOWN
    }
    void move(Direction direction);
}
