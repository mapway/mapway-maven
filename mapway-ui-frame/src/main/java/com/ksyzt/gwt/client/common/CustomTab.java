package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasAutoHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;

// TODO: Auto-generated Javadoc

/**
 * The Class CustomTab.
 */
public class CustomTab extends MessageComposite {

	/** The ui binder. */
	private static CustomTabUiBinder uiBinder = GWT
			.create(CustomTabUiBinder.class);

    /**
     * The Interface CustomTabUiBinder.
     */
    interface CustomTabUiBinder extends UiBinder<Widget, CustomTab> {
	}

    /**
     * Instantiates a new custom tab.
     */
    @UiConstructor
	public CustomTab() {
		initWidget(uiBinder.createAndBindUi(this));
		this.setStylePrimaryName("KSYZT");
		setTabAlign(HasHorizontalAlignment.ALIGN_LEFT);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.UIObject#setStylePrimaryName(java.lang.String)
	 */
	@Override
	public void setStylePrimaryName(String name) {
		super.setStylePrimaryName(name);
		this.setStyleName(this.getStylePrimaryName() + "-TAB");
		lbCaption.setStyleName(this.getStylePrimaryName() + "-CAPTION");
		for (int index = 0; index < btns.getWidgetCount(); index++) {
			Widget a1 = (Widget) btns.getWidget(index);
			a1.setStyleName(this.getStylePrimaryName() + "-Button");
		}
	}

    /**
     * Sets the tab align.
     *
     * @param align the new tab align
     */
    public void setTabAlign(HorizontalAlignmentConstant align) {
		if (HasHorizontalAlignment.ALIGN_RIGHT.getTextAlignString().equals(
				align.getTextAlignString())) {
			box.remove(lbCaption);
			box.insert(lbCaption, 0);
			box.setCellHorizontalAlignment(btns,
					HasHorizontalAlignment.ALIGN_RIGHT);
			box.setCellHorizontalAlignment(lbCaption,
					HasHorizontalAlignment.ALIGN_LEFT);
		} else {
			box.remove(lbCaption);
			box.insert(lbCaption, 1);
			box.setCellHorizontalAlignment(btns,
					HasHorizontalAlignment.ALIGN_LEFT);
			box.setCellHorizontalAlignment(lbCaption,
					HasHorizontalAlignment.ALIGN_RIGHT);
		}
	}

    /**
     * The box.
     */
    @UiField
	HorizontalPanel box;

    /**
     * The btns.
     */
    @UiField
	HorizontalPanel btns;

    /**
     * The lb caption.
     */
    @UiField
	Label lbCaption;

    /**
     * Sets the caption.
     *
     * @param text the new caption
     */
    public void setCaption(String text) {
		lbCaption.setText(text);
	}

    /**
     * Sets the select index.
     *
     * @param index the new select index
     */
    public void setSelectIndex(int index) {
		setSelectIndex(index, false);
	}

    /**
     * Sets the select index.
     *
     * @param data the new select index
     */
    public void setSelectIndex(Object data) {
		setSelectIndex(data, false);
	}

    /**
     * Sets the select index.
     *
     * @param index     the index
     * @param fireEvent the fire event
     */
    public void setSelectIndex(int index, boolean fireEvent) {
		if (index >= 0 && index < btns.getWidgetCount()) {
			Widget a = (Widget) btns.getWidget(index);
			makeAnchorSelected(a, fireEvent);
		}
	}

    /**
     * Sets the select index.
     *
     * @param data      the data
     * @param fireEvent the fire event
     */
    public void setSelectIndex(Object data, boolean fireEvent) {
		for (int i = 0; i < btns.getWidgetCount(); i++) {
			Widget a = (Widget) btns.getWidget(i);
			if (a.getElement().getPropertyObject("v").equals(data)) {
				makeAnchorSelected(a, fireEvent);
				break;
			}
		}
	}

	/**
	 * Make anchor selected.
	 *
	 * @param a the a
	 * @param fireEvent the fire event
	 */
	private void makeAnchorSelected(Widget a, boolean fireEvent) {
		if (!a.getStyleName().equals(
				this.getStylePrimaryName() + "-BUTTON-SELECED")) {
			for (int index = 0; index < btns.getWidgetCount(); index++) {
				Widget a1 = (Widget) btns.getWidget(index);
				if (a1.equals(a)) {
					a1.setStyleName(this.getStylePrimaryName()
							+ "-BUTTON-SELECTED");
				} else {
					a1.setStyleName(this.getStylePrimaryName() + "-BUTTON");
				}
			}
		}
		if (fireEvent) {
			Object v = a.getElement().getPropertyObject("v");
			MessageEvent ev = new MessageEvent(MessageEvent.ITEMCLICK, v);
			fireEvent(ev);
		}
	}

    /**
     * Adds the item.
     *
     * @param text  the text
     * @param value the value
     */
    public void addItem(String text, Object value) {
		Label a = new Label(text);
		a.addClickHandler(m_click);
		a.setStyleName(this.getStylePrimaryName() + "-BUTTON");
		a.getElement().setPropertyObject("v", value);
		a.setAutoHorizontalAlignment(HasAutoHorizontalAlignment.ALIGN_CENTER);
		btns.add(a);
	}

	/** The m click. */
	private ClickHandler m_click = new ClickHandler() {
		public void onClick(ClickEvent event) {
			Widget a = (Widget) event.getSource();
			makeAnchorSelected(a, true);
		}
	};
}
