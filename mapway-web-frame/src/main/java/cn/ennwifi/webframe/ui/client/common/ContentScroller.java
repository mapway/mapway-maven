package cn.ennwifi.webframe.ui.client.common;

import java.util.HashMap;
import java.util.Map;

import cn.mapway.ui.client.widget.common.LabelEx;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

/**
 * 内容滚动器
 *
 * @author zhangjianshe
 */
public class ContentScroller extends MessageComposite {

  private static ContentScrollerUiBinder uiBinder = GWT.create(ContentScrollerUiBinder.class);

    /**
     * The interface Content scroller ui binder.
     */
    interface ContentScrollerUiBinder extends UiBinder<Widget, ContentScroller> {
  }

    /**
     * Instantiates a new Content scroller.
     */
    public ContentScroller() {
    initWidget(uiBinder.createAndBindUi(this));
    mapper = new HashMap<LabelEx, Widget>();
  }

    /**
     * The Navi.
     */
    @UiField
  HTMLPanel navi;

    /**
     * The List.
     */
    @UiField
  HTMLPanel list;

    /**
     * The Current.
     */
    LabelEx current = null;
  private ClickHandler linkHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      LabelEx source = (LabelEx) event.getSource();
      changeView(source);
    }


  };

  /**
   * @param source
   */
  private void changeView(LabelEx source) {
    Widget l = mapper.get(source);
    l.setVisible(true);
    list.clear();
    list.add(l);

    if (current != null) {
      current.getElement().removeAttribute("s");
    }
    current = source;
    current.getElement().setAttribute("s", "true");

    MessageEvent ev = new MessageEvent(MessageEvent.SELECT, l);
    fireEvent(ev);
  }

    /**
     * Clear.
     */
    public void clear() {
    navi.clear();
    list.clear();
  }

    /**
     * The Mapper.
     */
    Map<LabelEx, Widget> mapper;

    /**
     * Add.
     *
     * @param widget the widget
     */
    public void add(Widget widget) {
    LabelEx l = new LabelEx();
    l.setText(widget.getTitle());
    l.setStyleName("content-scroller");
    l.addClickHandler(linkHandler);
    navi.add(l);
    widget.setVisible(false);
    mapper.put(l, widget);
  }

    /**
     * Show index.
     *
     * @param index the index
     */
    public void showIndex(int index) {
    GWT.log("show index " + index);
    if (index >= 0 && index < navi.getWidgetCount()) {
      LabelEx l = (LabelEx) navi.getWidget(index);
      GWT.log(l.toString());
      changeView(l);
    }
  }
}
