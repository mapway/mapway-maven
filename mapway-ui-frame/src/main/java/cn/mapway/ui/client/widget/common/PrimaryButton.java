package cn.mapway.ui.client.widget.common;

/**
 * The type Primary button.
 */
public class PrimaryButton extends ButtonEx{

    /**
     * Instantiates a new Primary button.
     */
    public PrimaryButton() {
		setStyleName("gwtEx-Button gwtEx-PrimayButton");
	}
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
