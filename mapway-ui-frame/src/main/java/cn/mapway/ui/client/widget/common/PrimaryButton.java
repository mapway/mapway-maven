package cn.mapway.ui.client.widget.common;

public class PrimaryButton extends ButtonEx{
	
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
