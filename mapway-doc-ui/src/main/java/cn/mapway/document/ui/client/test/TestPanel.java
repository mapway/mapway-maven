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
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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

        String his = readHistory();
        if (his.length() == 0) {
            his = entry.input().get(0).json();
        }
        editor.setValue(his);
        editor.redisplay();
        result.setValue("");
    }

    /**
     * Read history.
     *
     * @return the string
     */
    private String readHistory() {
        String r = "";
        String v = LocalStorage.val(mEntry.relativePath());
        if (v == null || v.length() == 0) {
            return "";
        }

        String[] vs = v.split("\\|");

        if (vs.length > 0) {
            String[] itemdata = vs[0].split("`");
            r = itemdata[1];
            return r;
        }

        return r;
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

    /**
     * On execute.
     *
     * @param ev the ev
     */
    @UiHandler("btnExecute")
    void onExecute(ClickEvent ev) {

        imgLoadding.setVisible(true);
        result.setValue("");
        // 保存到本地
        String v = LocalStorage.val(mEntry.relativePath());
        Date d = new Date();
        String key =
                (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-" + d.getDate() + " " + d.getHours()
                        + ":" + d.getMinutes() + ":" + d.getSeconds();
        String va = editor.getValue();

        if (v == null || v.length() == 0) {
            LocalStorage.save(mEntry.relativePath(), key + "`" + va);
        } else {
            LocalStorage.save(mEntry.relativePath(), key + "`" + va + "|" + v);
        }
        try {
            ApiDocProxy.fetchString(mEntry.url(), va, "", mEntry.invokeMethods().get(0),
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

    /**
     * The pop.
     */
    PopupPanel pop = null;

    /**
     * The history panel.
     */
    InputHistoryPanel historyPanel;

    /**
     * The item selected.
     */
    private CloseHandler<HistoryData> itemSelected = new CloseHandler<HistoryData>() {

        @Override
        public void onClose(CloseEvent<HistoryData> event) {
            editor.setValue(event.getTarget().value);
            pop.hide();
        }
    };

    /**
     * 显示历史记录.
     *
     * @param e the e
     */
    @UiHandler("btnHistory")
    void onHistory(ClickEvent e) {
        if (pop == null) {
            pop = new PopupPanel(true, true);
            historyPanel = new InputHistoryPanel();
            historyPanel.addCloseHandler(itemSelected);
            pop.add(historyPanel);
        }
        pop.showRelativeTo(btnHistory);
        historyPanel.render(mEntry.relativePath());
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
