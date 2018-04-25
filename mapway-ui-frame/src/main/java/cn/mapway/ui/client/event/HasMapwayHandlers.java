package cn.mapway.ui.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * The Interface HasMapwayHandlers.
 *
 * @param <T> the generic type
 */
public interface HasMapwayHandlers<T> extends HasHandlers {

    /**
     * Adds the mapway handler.
     *
     * @param handler the handler
     * @return the handler registration
     */
    HandlerRegistration addMapwayHandler(MapwayHandler<T> handler);
}
