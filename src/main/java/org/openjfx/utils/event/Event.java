package org.openjfx.utils.event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
