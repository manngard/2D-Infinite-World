package org.openjfx.model;

public interface Movable {
    enum directions{
        LEFT, RIGHT, UP, DOWN;
    }

    public void moveLeft();
    public void moveRight();
    public void moveUp();
    public void moveDown();
}
