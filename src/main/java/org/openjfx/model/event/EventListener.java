package org.openjfx.model.event;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: The interface to implement EventListeners for the Event class
  Used by: Event
  */

public abstract class EventListener<MsgT> {
    public abstract void func(MsgT emsg, Object data);
}
