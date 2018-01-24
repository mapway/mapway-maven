package cn.ennwifi.webframe.ui.client.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

import cn.mapway.ui.client.widget.common.AnchorEx;




/**
 * 工具条 链接形式.
 *
 * @author zhangjianshe
 */
public class Links extends HorizontalPanel implements HasMessageHandlers, ClickHandler {

  /**
   * Instantiates a new links.
   */
  public Links() {
    super();
    this.setSpacing(3);
    this.setStyleName("home-Links");
  }

  /**
   * 添加命令.
   *
   * @param command the command
   * @param text the text
   * @param data the data
   */
  public void addCommand(Integer command, String text, Object data) {
    AnchorEx AnchorEx = new AnchorEx();
    AnchorEx.setText(text);
    AnchorEx.setData(data);
    AnchorEx.setCommand(command);
    AnchorEx.addClickHandler(this);
    if (this.getWidgetCount() > 0) {
      this.add(new HTML("&nbsp;&nbsp;"));
    }
    this.add(AnchorEx);
  }

  /**
   * 加入一个链接.
   *
   * @param text 链接名字
   * @param url 链接地址
   * @param target 目标窗口
   */
  public void addLink(String text, String url, String target) {
    AnchorEx AnchorEx = new AnchorEx();
    AnchorEx.setText(text);
    AnchorEx.setHref(url);
    AnchorEx.setTarget(target);
    if (this.getWidgetCount() > 0) {
      this.add(new HTML("&nbsp;&nbsp;"));
    }
    this.add(AnchorEx);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.
   * MessageHandler)
   */
  @Override
  public HandlerRegistration addMessageHandler(MessageHandler handler) {
    return addHandler(handler, MessageEvent.TYPE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent
   * )
   */
  @Override
  public void onClick(ClickEvent event) {
    AnchorEx AnchorEx = (AnchorEx) event.getSource();
    MessageEvent ev = new MessageEvent(AnchorEx.getCommand(), AnchorEx.getData());
    fireEvent(ev);
  }
}
