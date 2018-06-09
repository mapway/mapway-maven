package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;

// TODO: Auto-generated Javadoc

/**
 * The Class ImageUploader.
 */
public class ImageUploader extends MessageComposite {

    /**
     * The ui binder.
     */
    private static ImageUploaderUiBinder uiBinder = GWT
            .create(ImageUploaderUiBinder.class);

    /**
     * The Interface ImageUploaderUiBinder.
     */
    interface ImageUploaderUiBinder extends UiBinder<Widget, ImageUploader> {
    }

    /**
     * The root.
     */
    @UiField
    AbsolutePanel root;

    /**
     * The btn cancel.
     */
    @UiField
    Button btnCancel;

    /**
     * The m image click.
     */
    private ClickHandler m_image_click = new ClickHandler() {

        public void onClick(ClickEvent event) {
            panelLoader.setVisible(true);
        }
    };

    /**
     * The m cancel click.
     */
    ClickHandler m_cancel_click = new ClickHandler() {

        public void onClick(ClickEvent event) {
            panelLoader.setVisible(false);

        }
    };

    /**
     * The info.
     */
    @UiField
    Label info;

    /**
     * The m mouse over.
     */
    private MouseOverHandler m_mouse_over = new MouseOverHandler() {

        public void onMouseOver(MouseOverEvent event) {

            if (!panelLoader.isVisible()) {
                info.setVisible(true);
            }
        }
    };

    /**
     * The m mouse out.
     */
    MouseOutHandler m_mouse_out = new MouseOutHandler() {

        public void onMouseOut(MouseOutEvent event) {
            info.setVisible(false);
        }
    };

    /**
     * Instantiates a new image uploader.
     */
    @UiConstructor
    public ImageUploader() {
        initWidget(uiBinder.createAndBindUi(this));
        html.addClickHandler(m_image_click);

        btnCancel.addClickHandler(m_cancel_click);

        uploader.setName("fileset");
        uploader.addChangeHandler(m_file_change);

        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);
        form.setAction(GWT.getModuleBaseURL() + "../gwtfileuploader");
        form.addSubmitHandler(m_submit_handler);
        form.addSubmitCompleteHandler(m_submit_complete_handler);
        panelLoader.setVisible(false);

        html.addMouseOverHandler(m_mouse_over);

        html.addMouseOutHandler(m_mouse_out);

    }

    /**
     * Can upload.
     *
     * @param ft the ft
     * @return true, if successful
     */
    public boolean canUpload(String ft) {
        boolean b = false;

        if (ft.compareToIgnoreCase("zip") == 0
                || ft.compareToIgnoreCase("png") == 0
                || ft.compareToIgnoreCase("bmp") == 0
                || ft.compareToIgnoreCase("jpg") == 0
                || ft.compareToIgnoreCase("jpeg") == 0
                || ft.compareToIgnoreCase("swf") == 0
                || ft.compareToIgnoreCase("doc") == 0
                || ft.compareToIgnoreCase("docx") == 0
                || ft.compareToIgnoreCase("gif") == 0
                || ft.compareToIgnoreCase("txt") == 0)
            b = true;
        return b;
    }

    /**
     * The m file change.
     */
    private ChangeHandler m_file_change = new ChangeHandler() {

        public void onChange(ChangeEvent event) {
            String fn = uploader.getFilename();
            if (uploader.getFilename().equals("")) {

            } else {
                int lastdot = fn.lastIndexOf('.');
                if (lastdot > 0) {
                    String filetype = fn.substring(lastdot + 1);
                    if (canUpload(filetype)) {
                        form.submit();
                    } else {
                        message("不能上传此文件格式");
                    }
                }
            }
        }
    };

    /* (non-Javadoc)
     * @see com.ksyzt.gwt.client.common.MessageComposite#message(java.lang.String)
     */
    public void message(String msg) {
        MessageEvent ev = new MessageEvent(MessageEvent.MESSAGE, msg);
        fireEvent(ev);
    }


    /**
     * The m submit handler.
     */
    private SubmitHandler m_submit_handler = new SubmitHandler() {

        public void onSubmit(SubmitEvent event) {

        }
    };

    /**
     * The html.
     */
    @UiField
    HTML html;

    /**
     * The m submit complete handler.
     */
    private SubmitCompleteHandler m_submit_complete_handler = new SubmitCompleteHandler() {

        public void onSubmitComplete(SubmitCompleteEvent event) {

            String jsonstr = event.getResults();
            JSONValue rvalue = JSONParser.parse(jsonstr);
            JSONObject robj = rvalue.isObject();

            JSONString msg = (JSONString) robj.get("err").isString();
            if (msg.stringValue().equals("")) {
                JSONObject data = robj.get("data").isObject();

                String path = data.get("path").isString().stringValue();
                String url = data.get("url").isString().stringValue();
                mUrl = url;
                int w = root.getOffsetWidth() - 6;
                int h = root.getOffsetHeight() - 6;

                String imgdata = "<img src='" + url + "' width=" + w
                        + " height=" + h + " />";
                html.setHTML(imgdata);
                MessageEvent ev = new MessageEvent(MessageEvent.SUCCESS, path);
                fireEvent(ev);
                message("上传成功");
            } else {
                message(msg.stringValue());
            }
            panelLoader.setVisible(false);
        }
    };

    /**
     * The panel loader.
     */
    @UiField
    FlowPanel panelLoader;

    /**
     * The uploader.
     */
    @UiField
    FileUpload uploader;

    /**
     * The thumb width.
     */
    @UiField
    Hidden thumbWidth;

    /**
     * The thumb height.
     */
    @UiField
    Hidden thumbHeight;

    /**
     * The m height.
     */
    int m_height = 334;

    /**
     * The m width.
     */
    int m_width = 228;

    /**
     * Sets the thumb size.
     *
     * @param w the w
     * @param h the h
     */
    public void setThumbSize(int w, int h) {
        if (w > 0) {
            m_width = w;
        }
        if (h > 0) {
            m_height = h;
        }
    }

    /**
     * The form.
     */
    @UiField
    FormPanel form;

    /**
     * 设置初始化图像.
     *
     * @param url the new image
     */
    public void setImage(String url) {
        if (url == null || url.length() == 0) {

            int w = this.getOffsetWidth() - 6;
            int h = this.getOffsetHeight() - 6;
            url = SysResource.INSTANCE.upload().getSafeUri().asString();
            String imgdata = "<img src='" + url + "' width=" + w + " height="
                    + h + " />";
            html.setHTML(imgdata);

        } else {
            int w = this.getOffsetWidth() - 6;
            int h = this.getOffsetHeight() - 6;
            String imgdata = "<img src='" + url + "' width=" + w + " height="
                    + h + " />";
            html.setHTML(imgdata);
        }
        panelLoader.setVisible(false);
    }

    /**
     * The btn remove picture.
     */
    @UiField
    Button btnRemovePicture;

    /**
     * 删除新闻图片.
     *
     * @param e the e
     */
    @UiHandler("btnRemovePicture")
    void onbtnRemovePicture(ClickEvent e) {
        MessageEvent ev = new MessageEvent(MessageEvent.DELETE, 0);
        fireEvent(ev);
    }

    /**
     * Reset.
     */
    public void reset() {
        form.reset();
        thumbHeight.setValue(m_height + "");
        thumbWidth.setValue(m_width + "");
    }

    /**
     * The m url.
     */
    private String mUrl = "";

    /**
     * 获取图像Url.
     *
     * @return the image relative url
     */
    public String getImageRelativeUrl() {
        return mUrl;
    }
}
