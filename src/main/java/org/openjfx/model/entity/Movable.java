package org.openjfx.model.entity;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Encompasses behaviour shared for all classes that are Movable.
  Used by: Combatant, World and Model
  Uses:
  */

public interface Movable {
    enum Direction{
        LEFT, RIGHT, UP, DOWN
    }
    void move(Direction direction);
}
