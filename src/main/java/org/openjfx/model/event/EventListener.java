package org.openjfx.model.event;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public abstract class EventListener<MsgT> {
    public abstract void func(MsgT emsg, Object data);
}
