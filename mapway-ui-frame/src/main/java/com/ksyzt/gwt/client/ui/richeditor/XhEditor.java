package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.TextArea;


/**
 * The Class XhEditor.
 */
public class XhEditor extends TextArea {

  /**
   * Instantiates a new xh editor.
   */
  @UiConstructor
  public XhEditor() {

  }

  /**
   * Instantiates a new xh editor.
   *
   * @param element the element
   */
  public XhEditor(Element element) {
    super(element);
  }

  /** The proxy. */
  private RichBox proxy;

  /**
   * Inits the control.
   *
   * @param option the option
   */
  public void initControl(EditOption option) {
    if (option == null) {
      option = EditOption.getSimpleOption();
    }
    proxy = RichBox.initialize(getElement(), option);
  }

  /**
   * 设置HTML内容.
   *
   * @param html the new source
   */
  public void setSource(String html) {
    proxy.html(html);
  }

  /**
   * 获取HTML内容.
   *
   * @return the source
   */
  public String getSource() {
    return proxy.html();
  }

  /**
   * 卸载组件.
   */
  public void unload() {
    proxy.unLoad();
  }

  /**
   * Focus.
   */
  public void focus() {
    proxy.focus();
  }

  /**
   * 获取HTML.
   *
   * @return the value
   */
  @Override
  public String getValue() {
    return this.getSource();
  }

  /**
   * 设置HTML.
   *
   * @param html the new value
   */
  @Override
  public void setValue(String html) {
    this.setSource(html);
  }
}
