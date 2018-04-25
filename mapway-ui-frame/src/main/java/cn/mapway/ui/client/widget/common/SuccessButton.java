package cn.mapway.ui.client.widget.common;

/**
 * The type Success button.
 */
public class SuccessButton extends ButtonEx{
    /**
     * Instantiates a new Success button.
     */
    public SuccessButton() {
		setStyleName("gwtEx-Button gwtEx-SuccessButton");
	}
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

}
