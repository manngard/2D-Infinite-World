package org.openjfx.utils;

import java.util.ArrayList;
import java.util.List;

public class Event {

    public enum EventMessage {
        UPDATE
    }


    public abstract static class EventListener {
        public abstract void func(EventMessage emsg, Object data);
    }

    private List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener el) {
        listeners.add(el);
    }

    public void dispatch(EventMessage em, Object data) {
        for(EventListener l : listeners) {
            l.func(em, data);
        }
    }
}
