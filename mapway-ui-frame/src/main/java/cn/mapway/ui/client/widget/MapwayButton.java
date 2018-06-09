package cn.mapway.ui.client.widget;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.annotations.IsSafeHtml;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * Button widget.
 *
 * @author zhangjianshe
 */
public class MapwayButton extends ButtonBase {

    /**
     * Creates a Button widget that wraps an existing &lt;button&gt; element.
     * <p>
     * This element must already be attached to the document. If the element is
     * removed from the document, you must call
     * {@link RootPanel#detachNow(Widget)}.
     *
     * @param element the element to be wrapped
     * @return the mapway button
     */
    public static MapwayButton wrap(com.google.gwt.dom.client.Element element) {
        // Assert that the element is attached.
        assert Document.get().getBody().isOrHasChild(element);

        MapwayButton button = new MapwayButton(element);

        // Mark it attached and remember it for cleanup.
        button.onAttach();
        RootPanel.detachOnWindowClose(button);
        return button;
    }

    /**
     * Creates a button with no caption.
     */
    public MapwayButton() {
        super(Document.get().createPushButtonElement());
        setStyleName("mapway-Button");
    }

    /**
     * Creates a button with the given HTML caption.
     *
     * @param html the HTML caption
     */
    public MapwayButton(SafeHtml html) {
        this(html.asString());
    }

    /**
     * Creates a button with the given HTML caption.
     *
     * @param html the HTML caption
     */
    public MapwayButton(@IsSafeHtml String html) {
        this();
        setHTML(html);
    }


    /**
     * Creates a button with the given HTML caption and click listener.
     *
     * @param html    the html caption
     * @param handler the click handler
     */
    public MapwayButton(SafeHtml html, ClickHandler handler) {
        this(html.asString(), handler);
    }

    /**
     * Creates a button with the given HTML caption and click listener.
     *
     * @param html    the HTML caption
     * @param handler the click handler
     */
    public MapwayButton(@IsSafeHtml String html, ClickHandler handler) {
        this(html);
        addClickHandler(handler);
    }

    /**
     * This constructor may be used by subclasses to explicitly use an existing
     * element. This element must be a &lt;button&gt; element.
     *
     * @param element the element to be used
     */
    protected MapwayButton(com.google.gwt.dom.client.Element element) {
        super(element.<Element>cast());
        ButtonElement.as(element);
    }

    /**
     * Programmatic equivalent of the user clicking the button.
     */
    public void click() {
        getButtonElement().click();
    }

    /**
     * Get the underlying button element.
     *
     * @return the {@link ButtonElement}
     */
    protected ButtonElement getButtonElement() {
        return getElement().cast();
    }
}
