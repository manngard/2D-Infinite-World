package org.openjfx.model.event;

public abstract class EventListener<MsgT> {
    public abstract void func(MsgT emsg, Object data);
}
