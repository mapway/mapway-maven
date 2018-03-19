package cn.mapway.ui.client.widget.common;

/**
 * The type Danger button.
 */
public class DangerButton extends ButtonEx{
    /**
     * Instantiates a new Danger button.
     */
    public DangerButton() {
		setStyleName("gwtEx-Button gwtEx-DangerButton");
	}
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
