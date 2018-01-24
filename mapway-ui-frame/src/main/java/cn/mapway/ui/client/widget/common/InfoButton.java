package cn.mapway.ui.client.widget.common;

public class InfoButton extends ButtonEx{
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
