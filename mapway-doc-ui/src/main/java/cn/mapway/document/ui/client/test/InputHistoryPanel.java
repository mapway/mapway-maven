package cn.mapway.document.ui.client.test;

import cn.mapway.document.ui.client.main.storage.LocalStorage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Class InputHistoryPanel.
 */
public class InputHistoryPanel extends Composite implements
        HasSelectionHandlers<HistoryData>, HasValueChangeHandlers<Integer> {

    /**
     * The ui binder.
     */
    private static InputHistoryPanelUiBinder uiBinder = GWT
            .create(InputHistoryPanelUiBinder.class);


    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<HistoryData> selectionHandler) {
        return addHandler(selectionHandler, SelectionEvent.getType());
    }

    /**
     * 获取历史
     *
     * @param i
     * @return
     */
    public String get(int i) {
        if (i >= 0 && i < getHistoryCount()) {
            HistoryItem item = (HistoryItem) content.getWidget(i);
            return item.mData.value;
        }
        return "";
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> valueChangeHandler) {
        return addHandler(valueChangeHandler, ValueChangeEvent.getType());
    }

    /**
     * The Interface InputHistoryPanelUiBinder.
     */
    interface InputHistoryPanelUiBinder extends
            UiBinder<Widget, InputHistoryPanel> {
    }

    /**
     * Instantiates a new input history panel.
     */
    public InputHistoryPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * The current.
     */
    HistoryItem current = null;

    /**
     * The content.
     */
    @UiField
    HTMLPanel content;

    /**
     * The item click.
     */
    private ClickHandler itemClick = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            HistoryItem item = (HistoryItem) event.getSource();
            SelectionEvent.fire(InputHistoryPanel.this, item.getData());
        }
    };
    private CloseHandler<HistoryData> itemDelete = new CloseHandler<HistoryData>() {
        @Override
        public void onClose(CloseEvent<HistoryData> closeEvent) {
            saveCurrentHistory();
        }
    };

    /**
     * 保存当前的历史纪录
     */
    private void saveCurrentHistory() {

        String fullData = "";

        for (int i = 0; i < this.getHistoryCount(); i++) {
            HistoryItem item = (HistoryItem) this.content.getWidget(i);
            if (i == 0) {
                fullData = item.mData.title + "`" + item.mData.value;
            } else {
                fullData = "|" + item.mData.title + "`" + item.mData.value;
            }
        }

        LocalStorage.save(mRelativePath, fullData);

        ValueChangeEvent.fire(this, this.getHistoryCount());
    }

    /**
     * Cleat content.
     */
    public void cleatContent() {
        content.clear();
    }

    /**
     * Adds the item.
     *
     * @param title the title
     * @param value the value
     */
    public void addItem(String title, String value) {
        HistoryItem item = new HistoryItem();
        HistoryData hd = new HistoryData(title, value);

        item.render(hd);
        item.addClickHandler(itemClick);
        item.addCloseHandler(itemDelete);
        content.add(item);
    }

    /**
     * 历史纪录的个数
     *
     * @return
     */
    public int getHistoryCount() {
        return content.getWidgetCount();
    }

    private String mRelativePath = "";

    /**
     * Render.
     *
     * @param relativePath the relative path
     */
    public void render(String relativePath) {
        content.clear();
        mRelativePath = relativePath;
        String v = LocalStorage.val(relativePath);
        if (v == null || v.length() == 0) {
            ValueChangeEvent.fire(this, 0);
            return;
        }

        String[] vs = v.split("\\|");

        for (int index = 0; index < vs.length; index++) {
            String[] itemdata = vs[index].split("`");
            if (itemdata.length > 1) {
                addItem(itemdata[0], itemdata[1]);
            } else {
                addItem(itemdata[0], "");
            }
        }
        // 保留最后的10个记录
        if (vs.length > 10) {
            String fulldata = "";
            for (int i = 0; i < 10; i++) {
                if (i == 0) {
                    fulldata = vs[i];
                } else {
                    fulldata = "|" + vs[i];
                }
            }
            LocalStorage.save(relativePath, fulldata);
        }

        ValueChangeEvent.fire(this, this.getHistoryCount());
    }
}
