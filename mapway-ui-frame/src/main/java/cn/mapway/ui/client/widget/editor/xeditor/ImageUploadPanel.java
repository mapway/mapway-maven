package cn.mapway.ui.client.widget.editor.xeditor;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

/**
 * The Class ImageUploadPanel.
 */
public class ImageUploadPanel extends MessageComposite {

    /**
     * The ui binder.
     */
    private static ImageUploadPanelUiBinder uiBinder = GWT.create(ImageUploadPanelUiBinder.class);

    /**
     * The Interface ImageUploadPanelUiBinder.
     */
    interface ImageUploadPanelUiBinder extends UiBinder<Widget, ImageUploadPanel> {
    }

    /**
     * The m file change.
     */
    private ChangeHandler m_file_change = new ChangeHandler() {

        @Override
        public void onChange(ChangeEvent event) {
            String fn = upload.getFilename();
            if (upload.getFilename().equals("")) {

            } else {
                int lastdot = fn.lastIndexOf('.');
                if (lastdot > 0) {
                    String filetype = fn.substring(lastdot + 1);
                    if (canUpload(filetype)) {
                        frmPanel.submit();
                    } else {
                        message("不能上传此文件格式");
                    }
                }
            }
        }
    };

    /**
     * The m submit handler.
     */
    private SubmitHandler m_submit_handler = new SubmitHandler() {

        @Override
        public void onSubmit(SubmitEvent event) {
            // TODO Auto-generated method stub

        }
    };

    /**
     * The m submit complete handler.
     */
    private SubmitCompleteHandler m_submit_complete_handler = new SubmitCompleteHandler() {

        @Override
        public void onSubmitComplete(SubmitCompleteEvent event) {

            String jsonstr = event.getResults();
            GWT.log(jsonstr);
            JSONValue rvalue = JSONParser.parseStrict(jsonstr);
            JSONObject robj = rvalue.isObject();

            JSONString msg = robj.get("msg").isString();
            int retCode = Integer.parseInt(robj.get("retCode").isNumber().toString());

            if (retCode == 0) {

                String path = robj.get("relPath").isString().stringValue();
                String fileName = robj.get("fileName").isString().stringValue();
                frmPanel.setVisible(false);
                txtURL.setVisible(true);
                txtURL.setValue(path);
                message("上传成功");
            } else {
                message(msg.stringValue());
            }
        }
    };

    /**
     * Can upload.
     *
     * @param ft the ft
     * @return true, if successful
     */
    public boolean canUpload(String ft) {
        boolean b = false;

        if (ft.compareToIgnoreCase("zip") == 0 || ft.compareToIgnoreCase("png") == 0
                || ft.compareToIgnoreCase("bmp") == 0 || ft.compareToIgnoreCase("jpg") == 0
                || ft.compareToIgnoreCase("jpeg") == 0 || ft.compareToIgnoreCase("swf") == 0
                || ft.compareToIgnoreCase("doc") == 0 || ft.compareToIgnoreCase("docx") == 0
                || ft.compareToIgnoreCase("gif") == 0 || ft.compareToIgnoreCase("txt") == 0)
            b = true;
        return b;
    }

    /**
     * Instantiates a new image upload panel.
     */
    public ImageUploadPanel() {
        initWidget(uiBinder.createAndBindUi(this));

        upload.setName("fileset");
        upload.addChangeHandler(m_file_change);
        frmPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        frmPanel.setMethod(FormPanel.METHOD_POST);
        frmPanel.setAction(GWT.getModuleBaseURL() + "../fileupload");
        frmPanel.addSubmitHandler(m_submit_handler);
        frmPanel.addSubmitCompleteHandler(m_submit_complete_handler);
        frmPanel.setVisible(false);
    }

    /**
     * The txt URL.
     */
    @UiField
    TextBox txtURL;

    /**
     * The btn upload.
     */
    @UiField
    Button btnUpload;

    /**
     * The upload.
     */
    @UiField
    FileUpload upload;

    /**
     * The frm panel.
     */
    @UiField
    FormPanel frmPanel;

    /**
     * The lb message.
     */
    @UiField
    Label lbMessage;

    /*
     * (non-Javadoc)
     *
     * @see com.ksyzt.gwt.client.common.MessageComposite#message(java.lang.String)
     */
    @Override
    public void message(String msg) {
        lbMessage.setText(msg);
    }

    /**
     * On upload click.
     *
     * @param e the e
     */
    @UiHandler("btnUpload")
    void onUploadClick(ClickEvent e) {
        if (btnUpload.getText().equals("上传")) {
            txtURL.setVisible(false);
            frmPanel.setVisible(true);
            btnUpload.setText("网络");
        } else {
            txtURL.setVisible(true);
            frmPanel.setVisible(false);
            btnUpload.setText("上传");
        }
    }


    /**
     * On OK.
     *
     * @param e the e
     */
    @UiHandler("btnOK")
    void onOK(ClickEvent e) {
        String url = txtURL.getValue();
        if (url != null && !url.equals("")) {

            String html = "<img src='" + getImagePrefix() + url + "' " + getImageAttribute() + " />";
            MessageEvent ev = new MessageEvent(MessageEvent.OK, html);
            fireEvent(ev);
        }
    }

    /**
     * On cancel.
     *
     * @param e the e
     */
    @UiHandler("btnCancel")
    void onCancel(ClickEvent e) {
        MessageEvent ev = new MessageEvent(MessageEvent.CANCEL, null);
        fireEvent(ev);
    }

    /**
     * The txt width.
     */
    @UiField
    TextBox txtWidth;

    /**
     * The txt height.
     */
    @UiField
    TextBox txtHeight;

    /**
     * The txt border.
     */
    @UiField
    TextBox txtBorder;

    /**
     * The txt H space.
     */
    @UiField
    TextBox txtHSpace;

    /**
     * The txt V space.
     */
    @UiField
    TextBox txtVSpace;

    /**
     * The txt replace text.
     */
    @UiField
    TextBox txtReplaceText;

    /**
     * The ddl align.
     */
    @UiField
    ListBox ddlAlign;

    /**
     * Gets the image attribute.
     *
     * @return the image attribute
     */
    public String getImageAttribute() {
        String r = "";
        if (!txtWidth.getValue().equals("")) {
            r = "width='" + txtWidth.getValue() + "' ";
        }
        if (!txtHeight.getValue().equals("")) {
            r += "height='" + txtHeight.getValue() + "' ";
        }
        if (!txtReplaceText.getValue().equals("")) {
            r += "alt='" + txtReplaceText.getValue() + "' ";
        }

        return r;
    }

    /**
     * Sets action.
     *
     * @param mAction the m action
     */
    public void setAction(String mAction) {
        if (mAction != null && mAction.length() > 0) {
            frmPanel.setAction(mAction);
        }
    }

    private String prefix = "";

    /**
     * Sets image prefix.
     *
     * @param prefix the prefix
     */
    public void setImagePrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets image prefix.
     *
     * @return the image prefix
     */
    public String getImagePrefix() {
        return this.prefix;
    }
}
