package cn.mapway.ui.client.widget.editor.xeditor;

import cn.mapway.ui.client.widget.common.IValidator;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The Class RichEditor.
 */
public class RichEditorBox extends VerticalPanel implements IValidator {


  /**
   * Instantiates a new rich editor.
   */
  @UiConstructor
  public RichEditorBox() {
    super();
    m_editor = new RichTextArea();
    toolbar = new RichTextToolbar();
    toolbar.setRichTextArea(m_editor);
    this.add(toolbar);
    this.add(m_editor);
    m_editor.setWidth("100%");
  }


  public void setUploadAction(String action) {
    toolbar.setAction(action);
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

  @Override
  public void setMessage(String msg) {
    this.msg = msg;
  }

  private String msg = "";
  protected RegExp regex = null;
  private boolean required = false;

  @Override
  public String getMessage() {
    return msg;
  }

  @Override
  public void setPattern(String pattern) {

  }

  @Override
  public void setRequired(boolean b) {
    required = b;
  }

  @Override
  public boolean isValidate() {
    String v = getValue();

    if (required) {

      if (minLength != null) {
        if (v == null || v.length() == 0 || v.length() < minLength) {
          return false;
        }
      }
      if (maxLength != null) {
        if (v == null || v.length() == 0 || v.length() > maxLength) {
          return false;
        }
      }

      if (v == null || v.length() == 0) {
        return false;
      } else {
        return regex == null ? true : regex.test(v);
      }
    } else {
      if (v == null || v.length() == 0) {
        return true;
      }

      if (minLength != null) {
        if (v.length() < minLength) {
          return false;
        }
      }
      if (maxLength != null) {
        if (v.length() > maxLength) {
          return false;
        }
      }
      return true;
    }
  }

  Integer minLength = null;
  Integer maxLength = null;


  /**
   * 设置最小长度
   * @param minLength
   */
  public void setMinLength(int minLength) {
    this.minLength = minLength;

  }

  /**
   * 设置最大长度
   * @param maxLength
   */
  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }


  public void setImagePrefix(String prefix) {
    toolbar.setImagePrefix(prefix);
  }
}
