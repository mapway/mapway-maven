package cn.mapway.ui.client.widget.common;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.ListBox;

/**
 * The type List box ex.
 */
public class ListBoxEx extends ListBox implements IValidator {

    private String msg = "";
    private RegExp regex = null;
    private boolean required = false;

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public void setPattern(String pattern) {
        if (pattern != null && pattern.length() > 0) {
            regex = RegExp.compile(pattern);
        } else {
            regex = null;
        }
    }

    @Override
    public void setRequired(boolean b) {
        required = b;
    }

    @Override
    public boolean isValidate() {
        return true;
    }

    @Override
    public void setMessage(String msg) {
        this.msg = msg;
    }

    /**
     * Instantiates a new List box ex.
     */
    public ListBoxEx() {
        setStyleName("gwtEx-ListBox");
    }

    private Object data;

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    private String tag;
    private String initValue;

    private String initName;

    /**
     * 选中某个值
     *
     * @param value the value
     */
    public void selectValue(String value) {
        this.initValue = value;
        if (this.initValue != null) {
            for (int i = 0; i < this.getItemCount(); i++) {
                String v = this.getValue(i);
                if (v.equals(value)) {
                    this.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    /**
     * 选中某个值
     *
     * @param name the name
     */
    public void selectName(String name) {
        this.initName = name;
        if (this.initName != null) {
            for (int i = 0; i < this.getItemCount(); i++) {
                String v = this.getItemText(i);
                if (v.equals(name)) {
                    this.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    /**
     * 选中初始化名字
     *
     * @return the boolean
     */
    public boolean selectInitName() {
        if (getInitName() != null && getInitName().length() > 0) {
            selectName(getInitName());
            return true;
        }
        return false;
    }

    /**
     * 选中初始化值
     *
     * @return the boolean
     */
    public boolean selectInitValue() {
        if (getInitValue() != null && getInitValue().length() > 0) {
            selectValue(getInitValue());
            return true;
        }
        return false;
    }

    /**
     * Gets init value.
     *
     * @return the init value
     */
    public String getInitValue() {
        return initValue;
    }

    /**
     * Sets init value.
     *
     * @param initValue the init value
     */
    public void setInitValue(String initValue) {
        this.initValue = initValue;
    }

    /**
     * Sets init name.
     *
     * @param initName the init name
     */
    public void setInitName(String initName) {
        this.initName = initName;
    }

    /**
     * Gets init name.
     *
     * @return the init name
     */
    public String getInitName() {
        return this.initName;
    }

    /**
     * Fire.
     */
    public void fire() {

    }

    /**
     * Sets disabled.
     *
     * @param disabled the disabled
     */
    public void setDisabled(boolean disabled) {
        String disabledStyle = "disabled";
        if (disabled) {
            addStyleDependentName(disabledStyle);
        } else {
            removeStyleDependentName(disabledStyle);
        }
    }

}
