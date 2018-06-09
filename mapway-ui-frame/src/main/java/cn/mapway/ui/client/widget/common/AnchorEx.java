package cn.mapway.ui.client.widget.common;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.Anchor;

/**
 * The type Anchor ex.
 */
public class AnchorEx extends Anchor implements IValidator {

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
     * Instantiates a new Anchor ex.
     */
    public AnchorEx() {

    }

    /**
     * 按钮的数据.
     */
    private Object data;

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

    /**
     * Gets the command.
     *
     * @return the command
     */
    public Integer getCommand() {
        return command;
    }

    /**
     * Sets the command.
     *
     * @param command the new command
     */
    public void setCommand(Integer command) {
        this.command = command;
    }

    /**
     * 按钮的命令.
     */
    private Integer command;
}
