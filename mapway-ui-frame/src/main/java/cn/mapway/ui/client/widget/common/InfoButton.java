package cn.mapway.ui.client.widget.common;

/**
 * The type Info button.
 */
public class InfoButton extends ButtonEx{
    /**
     * Instantiates a new Info button.
     */
    public InfoButton() {
		setStyleName("gwtEx-Button gwtEx-InfoButton");
	}
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
