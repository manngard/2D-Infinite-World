package org.openjfx.model.event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Generic event system to replace the deprecated one from java
  Used by: Controller, Model
  Uses: EventListener
  */

public class Event<MsgT extends Enum> {

    private List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener el) {
        listeners.add(el);
    }

    public void removeListener(EventListener eventListener) {
        listeners = listeners.stream()
                .filter(el -> !el.equals(eventListener))
                .collect(Collectors.toList());
    }

    public void dispatch(MsgT em, Object data) {
        for(EventListener l : listeners) {
            l.func(em, data);
        }
    }
}
