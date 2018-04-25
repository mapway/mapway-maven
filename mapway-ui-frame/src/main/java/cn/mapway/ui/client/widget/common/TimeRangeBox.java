package cn.mapway.ui.client.widget.common;

import cn.mapway.ui.client.widget.resource.SysResource;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 日期时间范围选择器
 *
 * @author zhangjianshe
 */
public class TimeRangeBox extends AbsolutePanel {


    /**
     * 左边安钮
     */
    DragImage btnLeftHandler;

    /**
     * 右边安钮
     */
    DragImage btnRightHandler;

    /**
     * 信息提示
     */
    Label lbInfo;

    /**
     * 背景
     */
    Label lbBackground;

    /**
     * Instantiates a new Time range box.
     */
    public TimeRangeBox() {
    btnLeftHandler = new DragImage();
    btnRightHandler = new DragImage();
    btnLeftHandler.setUrl(SysResource.INSTANCE.dragger().getSafeUri());
    btnRightHandler.setUrl(SysResource.INSTANCE.dragger().getSafeUri());

    lbInfo = new Label();
    lbBackground = new Label();

    add(lbBackground, 0, 0);
    add(btnLeftHandler, 0, 0);
    add(btnRightHandler, 0, 0);
    add(lbInfo, 0, 0);

    this.setPixelSize(300, 40);

    initEvent();
  }

  private void initEvent() {

    btnLeftHandler.getElement().setDraggable(com.google.gwt.dom.client.Element.DRAGGABLE_TRUE);


  }

  @Override
  protected void onAttach() {
    super.onAttach();
    invalidate();
  }

  private void setHandlerStyle() {
    int w = this.getOffsetWidth();
    int h = this.getOffsetHeight();

    Style style = btnLeftHandler.getElement().getStyle();
    style.setBackgroundColor("darkblue");
    style.setBorderWidth(1, Unit.PX);
    style.setBorderColor("#000");

    setSize(style, 0, 0, 10, h);

    style = btnRightHandler.getElement().getStyle();
    style.setBackgroundColor("darkblue");
    style.setBorderWidth(1, Unit.PX);
    style.setBorderColor("#000");

    setSize(style, w - 10, 0, 10, h);

  }

  /**
   * 改变大小
   */
  private void invalidate() {
    int w = this.getOffsetWidth();
    int h = this.getOffsetHeight();
    Style style;
    style = this.getElement().getStyle();
    style.setBackgroundColor("#fff");
    style.setBorderWidth(1, Unit.PX);
    style.setBorderColor("#000");

    style = lbBackground.getElement().getStyle();
    style.setBackgroundColor("skyblue");
    setBox(style, 0, h / 3, 0, h / 3);

    setHandlerStyle();
  }

  /**
   * 设置元素的大小.
   * 
   * @param style
   * @param left
   * @param top
   * @param right
   * @param bottom
   */
  private void setBox(Style style, int left, int top, int right, int bottom) {
    style.setLeft(left, Unit.PX);
    style.setRight(right, Unit.PX);
    style.setTop(top, Unit.PX);
    style.setBottom(bottom, Unit.PX);
  }

  private void setSize(Style style, int left, int top, int width, int height) {
    style.setLeft(left, Unit.PX);
    style.setTop(top, Unit.PX);

    style.setHeight(height, Unit.PX);
    style.setWidth(width, Unit.PX);
  }

  @Override
  public void setPixelSize(int width, int height) {
    super.setPixelSize(width, height);
    invalidate();
  }

  @Override
  public void setWidth(String width) {
    super.setWidth(width);
    invalidate();
  }

  @Override
  public void setHeight(String height) {
    super.setHeight(height);
    invalidate();
  }

  @Override
  public void setSize(String width, String height) {
    super.setSize(width, height);
    invalidate();
  }
}
