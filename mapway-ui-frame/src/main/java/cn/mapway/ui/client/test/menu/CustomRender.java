package cn.mapway.ui.client.test.menu;

import cn.mapway.ui.client.widget.menu.IMenuItem;
import cn.mapway.ui.client.widget.menu.render.IMenuRender;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Custom render.
 */
public class CustomRender implements IMenuRender {

    /**
     * The constant INSTANCE.
     */
    public static CustomRender INSTANCE = new CustomRender();


    /**
     * Instantiates a new Custom render.
     */
    protected CustomRender() {
    }

    @Override
    public Widget render(Widget parent, IMenuItem menu) {

        if (menu.getSubMenus().size() == 0) {
            return null;
        }
        final VerticalPanel vp = new VerticalPanel();
        vp.setBorderWidth(2);
        vp.setSpacing(5);

        for (final IMenuItem item : menu.getSubMenus()) {
            final Button lb = new Button(item.getName());
            vp.add(lb);
            lb.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {

                    item.getHandler().execute(lb, item);
                }
            });
        }
        return vp;
    }
}
