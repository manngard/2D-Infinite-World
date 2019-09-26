package org.openjfx.utils.event;

public abstract class EventListener<MsgT> {
    public abstract void func(MsgT emsg, Object data);
}
