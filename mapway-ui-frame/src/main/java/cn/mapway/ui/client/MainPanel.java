package cn.mapway.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 主面板
 *
 * @author zhangjianshe
 */
public class MainPanel extends Composite {

    private static MainPanelUiBinder uiBinder = GWT.create(MainPanelUiBinder.class);

    /**
     * The interface Main panel ui binder.
     */
    interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
    }

    /**
     * Instantiates a new Main panel.
     */
    public MainPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * The Root.
     */
    @UiField
    DockLayoutPanel root;


}
