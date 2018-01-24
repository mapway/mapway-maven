package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.ksyzt.gwt.client.common.SysResource;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class ColorPopup.
 */
public class ColorPopup extends PopupPanel implements HasMessageHandlers {

	/** The panel. */
	private FlowPanel panel;
	
	/** The hue saturation. */
	private Image hueSaturation;
	
	/** The lightness. */
	private Image lightness;
	
	/** The preview. */
	private Label preview;
	
	/** The down. */
	private boolean down = false;

	/** The h. */
	float h = 200;
	
	/** The s. */
	float s = 2 / 3f;
	
	/** The l. */
	float l = 1 / 3f;

	/**
	 * Instantiates a new color popup.
	 */
	ColorPopup() {
		super(true);

		this.panel = new FlowPanel();
		this.hueSaturation = new Image(SysResource.INSTANCE.hueSaturation());
		this.lightness = new Image(SysResource.INSTANCE.lightness());
		this.preview = new Label();

		panel.setSize("220px", "100px");
		preview.setSize("20px", "100px");

		panel.add(hueSaturation);
		panel.add(lightness);
		panel.add(preview);
		setWidget(panel);
		addStyleName("fp-cp");

		DOM.setStyleAttribute(hueSaturation.getElement(), "cursor", "crosshair");
		DOM.setStyleAttribute(lightness.getElement(), "cursor", "ns-resize");
		DOM.setStyleAttribute(preview.getElement(), "float", "right");
		DOM.setStyleAttribute(preview.getElement(), "cssFloat", "right");
		DOM.setStyleAttribute(preview.getElement(), "styleFloat", "right");

		setColor();

		hueSaturation.addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				event.preventDefault();
				setHueSaturation(event.getNativeEvent());
				down = true;
			}
		});

		hueSaturation.addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				setHueSaturation(event.getNativeEvent());
				down = false;
			}
		});

		hueSaturation.addMouseMoveHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (down)
					setHueSaturation(event.getNativeEvent());
			}
		});

		hueSaturation.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				down = false;
			}
		});

		/* --- */

		lightness.addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				event.preventDefault();
				setLightness(event.getNativeEvent());
				down = true;
			}
		});

		lightness.addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				setLightness(event.getNativeEvent());
				down = false;
			}
		});

		lightness.addMouseMoveHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (down)
					setLightness(event.getNativeEvent());
			}
		});

		lightness.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				down = false;
			}
		});

		/* --- */

		preview.addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				String c = getHex();
				String[] colors = new String[2];
				colors[0] = c;
				colors[1] = getTextColor();
				MessageEvent ev = new MessageEvent(MessageEvent.COLORS, colors);
				fireEvent(ev);
				hide();
			}
		});
	}

	/**
	 * Gets the hex.
	 *
	 * @return the hex
	 */
	public String getHex() {
		return new Color(h, s, l).toString();
	}

	/**
	 * Sets the hex.
	 *
	 * @param colorString the new hex
	 */
	public void setHex(String colorString) {
		if (colorString.startsWith("#") && colorString.length() == 7) {
			Color rgb = new Color(colorString);
			h = rgb.getHue();
			s = rgb.getSaturation();
			l = rgb.getLightness();
			setColor();
		}
	}

	/**
	 * Gets the text color.
	 *
	 * @return the text color
	 */
	public String getTextColor() {
		if (this.l < 0.5) {
			return "#ffffff";
		} else {
			return "#000000";
		}
	}

	/**
	 * Sets the color.
	 */
	private void setColor() {
		Color p = new Color(h, s, l);
		DOM.setStyleAttribute(preview.getElement(), "backgroundColor",
				p.toString());
		Color l = new Color(h, s, 0.5f);
		DOM.setStyleAttribute(lightness.getElement(), "backgroundColor",
				l.toString());

	}

	/**
	 * Sets the hue saturation.
	 *
	 * @param event the new hue saturation
	 */
	private void setHueSaturation(NativeEvent event) {
		int x = event.getClientX() - hueSaturation.getAbsoluteLeft();
		int y = event.getClientY() - hueSaturation.getAbsoluteTop();

		if (x > -1 && x < 181 && y > -1 && y < 101) {
			h = x * 2;
			s = (float) (100 - y) / 100f;

			setColor();
		} else {
			down = false;
		}
	}

	/**
	 * Sets the lightness.
	 *
	 * @param event the new lightness
	 */
	private void setLightness(NativeEvent event) {
		int y = event.getClientY() - lightness.getAbsoluteTop();

		if (y > -1 && y < 101) {
			l = (float) (100 - y) / 100f;
			setColor();
		} else {
			down = false;
		}
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.MessageHandler)
	 */
	@Override
	public HandlerRegistration addMessageHandler(MessageHandler handler) {
		return addHandler(handler, MessageEvent.TYPE);

	}

}