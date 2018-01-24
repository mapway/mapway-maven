package cn.mapway.ui.client.widget.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;

public class DashBoardItem extends MessageComposite {

	private static DashBoardItemUiBinder uiBinder = GWT.create(DashBoardItemUiBinder.class);

	interface DashBoardItemUiBinder extends UiBinder<Widget, DashBoardItem> {
	}

	public DashBoardItem() {
		initWidget(uiBinder.createAndBindUi(this));
		addStyleName("gwtEx-DashBoard-item");
		title.addStyleName("gwtEx-left");
		link.addStyleName("gwtEx-right");
	}
	
	@UiField
	LabelEx title;
	
	@UiField
	LinkLabelEx link;
	
	@UiField
	HTMLPanel center;

	public void setTitle(String title) {
		this.title.setText(title);
	}
	
	public LinkLabelEx getLink(){
		return this.link;
	}
	
	public void setLink( String link ){
		this.link.setText(link);
	}
	
	public HTMLPanel getCenter(){
		return this.center;
	}

	public void addCenter(Widget widget) {
		this.center.add(widget);
	}
	
}
