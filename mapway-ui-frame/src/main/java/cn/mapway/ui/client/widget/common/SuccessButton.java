package cn.mapway.ui.client.widget.common;

public class SuccessButton extends ButtonEx{
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
