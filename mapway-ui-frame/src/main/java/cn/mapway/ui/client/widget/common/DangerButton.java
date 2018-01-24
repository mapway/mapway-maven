package cn.mapway.ui.client.widget.common;

public class DangerButton extends ButtonEx{
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
