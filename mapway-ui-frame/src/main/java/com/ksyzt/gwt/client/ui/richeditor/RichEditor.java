package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class RichEditor.
 */
public class RichEditor extends VerticalPanel {


  /**
   * Instantiates a new rich editor.
   */
  @UiConstructor
  public RichEditor() {
    super();
    m_editor = new RichTextArea();
    toolbar = new RichTextToolbar();
    toolbar.setRichTextArea(m_editor);
    this.add(toolbar);
    this.add(m_editor);
    m_editor.setWidth("100%");
  }

  /** The toolbar. */
  private RichTextToolbar toolbar;

  /** The m editor. */
  private RichTextArea m_editor;


  /**
   * Sets the html.
   *
   * @param html the new html
   */
  public void setValue(String html) {
    m_editor.setHTML(html);
  }

  /**
   * Gets the html.
   *
   * @return the html
   */
  public String getValue() {
    return m_editor.getHTML();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.Widget#onDetach()
   */
  @Override
  protected void onDetach() {
    super.onDetach();
    toolbar.hidePopup();
  }



}
