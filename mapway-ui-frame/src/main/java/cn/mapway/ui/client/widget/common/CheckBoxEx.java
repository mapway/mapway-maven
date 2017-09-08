package cn.mapway.ui.client.widget.common;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;

public class CheckBoxEx extends CheckBox{
	public CheckBoxEx() {
		setStyleName("gwtEx-CheckBox");
		init();
	}
	
	private void init() {
	    this.getElement().getStyle().setMargin(0, Unit.PX);
	  }
	
	 /** 按钮的数据. */
	  private Object data;

	  public CheckBoxEx(String name) {
	    super(name);
	    init();
	  }

	  /**
	   * Gets the data.
	   *
	   * @return the data
	   */
	  public Object getData() {
	    return data;
	  }

	  /**
	   * Sets the data.
	   *
	   * @param data the new data
	   */
	  public void setData(Object data) {
	    this.data = data;
	  }

	  public String getTag() {
	    return tag;
	  }

	  public void setTag(String tag) {
	    this.tag = tag;
	  }

	  private String tag;
}
