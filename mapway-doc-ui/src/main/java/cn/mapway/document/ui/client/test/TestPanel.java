package cn.mapway.document.ui.client.test;

import cn.mapway.document.ui.client.component.Clients;
import cn.mapway.document.ui.client.component.ace.AceEditor;
import cn.mapway.document.ui.client.component.ace.AceEditorMode;
import cn.mapway.document.ui.client.main.storage.LocalStorage;
import cn.mapway.document.ui.client.module.Entry;
import cn.mapway.document.ui.client.resource.SysResource;
import cn.mapway.document.ui.client.rpc.ApiDocProxy;
import cn.mapway.document.ui.client.rpc.IOnData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.Date;


/**
 * 测试面板.
 *
 * @author zhangjianshe
 */
public class TestPanel extends Composite implements HasCloseHandlers<Void> {

    /**
     * The constant GWT_USER_HEADER.
     */
    public static final String GWT_USER_HEADER = "gwt_user_token_1";

    /**
     * The ui binder.
     */
    private static TestPanelUiBinder uiBinder = GWT.create(TestPanelUiBinder.class);
    private final SelectionHandler<HistoryData> historySelectedHandler = new SelectionHandler<HistoryData>() {
        @Override
        public void onSelection(SelectionEvent<HistoryData> selectionEvent) {
            editor.setValue(selectionEvent.getSelectedItem().value);
        }
    };
    private final ValueChangeHandler<Integer> historyValueChangedHandler = new ValueChangeHandler<Integer>() {
        @Override
        public void onValueChange(ValueChangeEvent<Integer> valueChangeEvent) {
            changeLayout(valueChangeEvent.getValue());
        }
    };
    private CloseHandler<String> itemDeleted = new CloseHandler<String>() {
        @Override
        public void onClose(CloseEvent<String> closeEvent) {

            HeaderItem item = (HeaderItem) closeEvent
                    .getSource();
            htmlHeaders.remove(item);
            headers.remove(closeEvent.getTarget());
            saveHeader();
        }
    };
    private SelectionHandler<String> historyNameEditorHandler = new SelectionHandler<String>() {
        @Override
        public void onSelection(SelectionEvent<String> selectionEvent) {
            String name = selectionEvent.getSelectedItem();
            if (name == null || name.length() == 0) {
                return;
            } else {
                saveHistory(name);
            }
            sureEditor().hide();
            history.render(mEntry.relativePath());
        }
    };

    /**
     * 保存请求数据为历史纪录
     */
    private void saveHistory(String name) {
        String v = LocalStorage.val(mEntry.relativePath());
        String key = name;
        if (v == null || v.length() == 0) {
            LocalStorage.save(mEntry.relativePath(), key + "`" + editor.getValue());
        } else {
            LocalStorage.save(mEntry.relativePath(), key + "`" + editor.getValue() + "|" + v);
        }
    }

    /**
     * The Interface TestPanelUiBinder.
     */
    interface TestPanelUiBinder extends UiBinder<Widget, TestPanel> {
    }

    /**
     * Instantiates a new test panel.
     */
    public TestPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        String css = SysResource.INSTANCE.getCss().btn();
        btnExecute.setStyleName(css);
        btnHistory.setStyleName(css);
        btnHeader.setStyleName(css);
        btnFormat.setStyleName(css);
        btnClearCache.setStyleName(css);
        imgLoadding.setUrl(SysResource.INSTANCE.loading().getSafeUri());
        history.addSelectionHandler(historySelectedHandler);
        history.addValueChangeHandler(historyValueChangedHandler);
    }


    @UiField
    HorizontalPanel htmlHeaders;

    KeyValueObjs headers;

    /**
     * 恢复请求头的数据
     */
    private void loadHeader() {
        htmlHeaders.clear();
        String key = LocalStorage.val(GWT_USER_HEADER);
        headers = KeyValueObjs.parse(key);
        for (int i = 0; i < headers.length(); i++) {
            KeyValueObj kv = headers
                    .get(i);
            HeaderItem item = new HeaderItem();
            item.setData(kv.getKey(), kv.getValue());
            item.addCloseHandler(itemDeleted);
            htmlHeaders.add(item);
        }
    }

    /**
     * 恢复请求头的数据
     */
    private void saveHeader() {
        LocalStorage.save(GWT_USER_HEADER, headers.toJSON());
    }


    @UiField
    AceEditor editor;

    /**
     * The txt output.
     */
    @UiField
    AceEditor result;

    /**
     * The m entry.
     */
    Entry mEntry;

    @Override
    protected void onLoad() {
        super.onLoad();
        initJsonEditor();
        int width = Window.getClientWidth() - 200;
        int height = Window.getClientHeight() - 200;
        this.setHeight(height + "px");
        this.setWidth(width + "px");

    }

    boolean initialize = false;

    private void initJsonEditor() {
        if (initialize == false) {
            editor.startEditor();
            editor.setMode(AceEditorMode.JSON);
            editor.setShowPrintMargin(false);
            editor.setFontSize(16);

            result.startEditor();
            result.setMode(AceEditorMode.JSON);
            result.setShowPrintMargin(false);
            result.setFontSize(16);
            result.setReadOnly(true);

            initialize = true;
        }
    }

    /**
     * Invoke.
     *
     * @param entry the entry
     */
    public void invoke(Entry entry) {
        loadHeader();
        mEntry = entry;
        history.render(entry.relativePath());
        int historyCount = history.getHistoryCount();
        String his = "";
        if (historyCount > 0) {
            his = history.get(0);
        }
        if (his == null || his.length() == 0) {
            if (entry.input().length() > 0) {
                his = entry.input().get(0).json();
            } else {
                his = "";
            }
        }
        editor.setValue(his);
        editor.redisplay();
        result.setValue("");
    }

    private void changeLayout(int historyCount) {
        if (historyCount > 0) {

            hor.setWidgetHidden(history, false);
            hor.setWidgetSize(editor, (this.getOffsetWidth() - hor.getWidgetSize(history)) / 2);
        } else {
            hor.setWidgetHidden(history, true);
            hor.setWidgetSize(editor, this.getOffsetWidth() / 2);
        }
    }


    /**
     * The btn execute.
     */
    @UiField
    Button btnExecute;

    /**
     * The img loadding.
     */
    @UiField
    Image imgLoadding;

    /**
     * The btn history.
     */
    @UiField
    Button btnHistory;
    @UiField
    Button btnHeader;
    @UiField
    Button btnFormat;
    @UiField
    Button btnClearCache;
    @UiField
    SplitLayoutPanel hor;
    @UiField
    InputHistoryPanel history;

    /**
     * On execute.
     *
     * @param ev the ev
     */
    @UiHandler("btnExecute")
    void onExecute(ClickEvent ev) {

        imgLoadding.setVisible(true);
        result.setValue("");

        try {
            ApiDocProxy.fetchString(mEntry.url(), editor.getValue(), "", mEntry.invokeMethods().get(0),
                    new IOnData<String>() {
                        @Override
                        public void onError(String url, String error) {
                            result.setValue(error);
                            imgLoadding.setVisible(false);

                        }

                        @Override
                        public void onSuccess(String url, String data) {

                            JavaScriptObject obj = JsonUtils.safeEval(data);
                            result.setValue(JsonUtils.stringify(obj, "\t"));
                            imgLoadding.setVisible(false);
                            processToken(url, data);
                        }
                    });
        } catch (RequestException e) {
            result.setValue(e.getMessage());
            imgLoadding.setVisible(false);
        }
    }

    /**
     * 处理TOKEN.
     *
     * @param url  the url
     * @param data the data
     */
    protected void processToken(String url, String data) {

    }


    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.event.logical.shared.HasCloseHandlers#addCloseHandler(
     * com.google.gwt.event.logical.shared.CloseHandler)
     */
    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<Void> handler) {
        return addHandler(handler, CloseEvent.getType());
    }


    TextEditor historyNameEditor;

    TextEditor sureEditor() {
        if (historyNameEditor == null) {
            historyNameEditor = new TextEditor();
            historyNameEditor.getElement().getStyle().setZIndex(10001);
            historyNameEditor.addSelectionHandler(historyNameEditorHandler);
        }
        return historyNameEditor;
    }

    /**
     * 保存当前的请求为历史记录
     *
     * @param e the e
     */
    @UiHandler("btnHistory")
    void onHistory(ClickEvent e) {
        sureEditor().edit("输入历史记录名称", "名称", "").center();
    }

    /**
     * 格式化JSON代码
     *
     * @param e
     */
    @UiHandler("btnFormat")
    void onFormatCode(ClickEvent e) {
        String data = editor.getValue();
        if (data.length() > 0) {
            JavaScriptObject obj = JsonUtils.safeEval(data);
            editor.setValue(JsonUtils.stringify(obj, "\t"));
        }
    }

    /**
     * 格式化JSON代码
     *
     * @param e
     */
    @UiHandler("btnClearCache")
    void onClearCache(ClickEvent e) {
        LocalStorage.save(mEntry.relativePath(), "");
        invoke(mEntry);
    }

    HeaderEditor headEditor;
    DialogBox dlg;

    @UiHandler("btnHeader")
    public void btnHeaderClick(ClickEvent event) {
        if (dlg == null) {
            dlg = new DialogBox();
            dlg.getElement().getStyle().setZIndex(10000);
            headEditor = new HeaderEditor();
            dlg.setText("编辑HTTP头部信息");
            dlg.setWidget(headEditor);
            headEditor.addCloseHandler(new CloseHandler<KeyValueObjs>() {
                @Override
                public void onClose(CloseEvent<KeyValueObjs> closeEvent) {
                    dlg.hide();
                    loadHeader();
                }
            });
        }
        dlg.center();
    }
}
