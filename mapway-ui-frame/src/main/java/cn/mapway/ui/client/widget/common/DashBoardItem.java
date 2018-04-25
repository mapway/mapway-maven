package cn.mapway.ui.client.widget.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;

/**
 * The type Dash board item.
 */
public class DashBoardItem extends MessageComposite {

	private static DashBoardItemUiBinder uiBinder = GWT.create(DashBoardItemUiBinder.class);

    /**
     * The interface Dash board item ui binder.
     */
    interface DashBoardItemUiBinder extends UiBinder<Widget, DashBoardItem> {
	}

    /**
     * Instantiates a new Dash board item.
     */
    public DashBoardItem() {
		initWidget(uiBinder.createAndBindUi(this));
		addStyleName("gwtEx-DashBoard-item");
		title.addStyleName("gwtEx-left");
		link.addStyleName("gwtEx-right");
	}

    /**
     * The Title.
     */
    @UiField
	LabelEx title;

    /**
     * The Link.
     */
    @UiField
	LinkLabelEx link;

    /**
     * The Center.
     */
    @UiField
	HTMLPanel center;

	public void setTitle(String title) {
		this.title.setText(title);
	}

    /**
     * Get link link label ex.
     *
     * @return the link label ex
     */
    public LinkLabelEx getLink(){
		return this.link;
	}

    /**
     * Set link.
     *
     * @param link the link
     */
    public void setLink( String link ){
		this.link.setText(link);
	}

    /**
     * Get center html panel.
     *
     * @return the html panel
     */
    public HTMLPanel getCenter(){
		return this.center;
	}

    /**
     * Add center.
     *
     * @param widget the widget
     */
    public void addCenter(Widget widget) {
		this.center.add(widget);
	}
	
}
