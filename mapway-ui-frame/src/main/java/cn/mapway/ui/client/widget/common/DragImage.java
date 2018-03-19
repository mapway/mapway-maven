package cn.mapway.ui.client.widget.common;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DragEvent;
import com.google.gwt.event.dom.client.DragHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.ui.Image;

/**
 * The type Drag image.
 */
public class DragImage extends Image implements DragStartHandler, DragHandler {
    /**
     * Instantiates a new Drag image.
     */
    public DragImage() {
    initDnd();
  }

  private void initDnd() {
    getElement().setDraggable(Element.DRAGGABLE_TRUE);
    addDragStartHandler(this);
  }

  @Override
  public void onDrag(DragEvent event) {
    NativeEvent e = event.getNativeEvent();
    GWT.log("drag " + e.getClientX() + "," + e.getClientY());

  }

  @Override
  public void onDragStart(DragStartEvent event) {

  }

}
