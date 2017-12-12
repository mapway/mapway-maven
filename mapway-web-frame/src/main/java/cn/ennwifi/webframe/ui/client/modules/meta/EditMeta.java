package cn.ennwifi.webframe.ui.client.modules.meta;

import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;
import cn.mapway.ui.client.widget.common.ButtonEx;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

public class EditMeta extends MessageComposite {

    private static EditMetaUiBinder uiBinder = GWT.create(EditMetaUiBinder.class);

    interface EditMetaUiBinder extends UiBinder<Widget, EditMeta> {
    }

    public EditMeta() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * The txt NAME.
     */
    @UiField
    TextBox txtName;

    /**
     * The txt VALUE.
     */
    @UiField
    TextBox txtValue;

    /**
     * The txt VALUE.
     */
    @UiField
    TextBox txtRank;

    /**
     * The txt PATH.
     */
    @UiField
    Label lbPath;
    /**
     * The btn create.
     */
    @UiField
    ButtonEx btnCreate;

    /**
     * The btn delete.
     */
    @UiField
    ButtonEx btnDelete;

    /**
     * The btn OK.
     */
    @UiField
    ButtonEx btnOk;

    @UiField
    TextArea txtDESCRIPTION;

    @UiField
    public HorizontalPanel tools;

    /**
     * Edits the authority.
     *
     * @param authority the authority
     */
    public void editMeta(S_METAObj meta) {
        toUI(meta);
    }

    S_METAObj mMeta;

    /**
     * 编辑菜单
     *
     * @param meta the authority
     */
    private void toUI(S_METAObj meta) {
        if (meta == null) {
            meta = new S_METAObj();
            meta.setPid(0);
            meta.setRank(1);
            meta.setCatalog("");
            meta.setDescription("");
            mMeta = meta;
        } else {
            mMeta = meta;
        }

        txtName.setValue(mMeta.getName());
        lbPath.setText(mMeta.getCatalog());
        lbId.setText(mMeta.getId() == null ? "" : mMeta.getId().toString());

        txtValue.setValue(mMeta.getCode());

        txtRank.setValue(mMeta.getRank() + "");
        txtDESCRIPTION.setValue(mMeta.getDescription());

        btnDelete.setEnabled(mMeta.getId() != null);
        btnOk.setEnabled(true);
        msg("");
    }

    /**
     * The lb message.
     */
    @UiField
    Label lbMessage;

    @UiField
    Label lbId;

    /**
     * Msg.
     *
     * @param text the text
     */
    private void msg(String text) {
        lbMessage.setText(text);
    }

    /**
     * On save.
     *
     * @param ev the ev
     */
    @UiHandler("btnOk")
    void onSave(ClickEvent ev) {
        fromUI();
        msg("开始保存...");
        WebFrameProxy.get().saveOrUpdateMetaData(mMeta, new AsyncCallback<S_METAObj>() {

            @Override
            public void onSuccess(S_METAObj result) {
                editMeta(result);
                MessageEvent ev = new MessageEvent(MessageEvent.REFRESH, result);
                fireEvent(ev);

                msg("保存成功");
            }

            @Override
            public void onFailure(Throwable caught) {
                message(caught.getMessage());
                msg(caught.getMessage());
            }
        });
    }

    /**
     * From UI.
     */
    private void fromUI() {
        mMeta.setName(txtName.getValue());
        mMeta.setCatalog(lbPath.getText());
        mMeta.setRank(Integer.parseInt(txtRank.getValue()));
        mMeta.setCode(txtValue.getValue());
        mMeta.setDescription(txtDESCRIPTION.getValue());
    }

    /**
     * On create.
     *
     * @param ev the ev
     */
    @UiHandler("btnCreate")
    void onCreate(ClickEvent ev) {
        if (mMeta != null && mMeta.getId() != null) {
            S_METAObj m = new S_METAObj();
            m.setPid(mMeta.getId());
            m.setCatalog(mMeta.getCatalog() + "/" + mMeta.getName());
            m.setRank(1);
            m.setCode("");
            m.setRoot_id(mMeta.getRoot_id());
            m.setDescription("");
            editMeta(m);
        } else {
            editMeta(null);
        }
    }

    /**
     * On delete click.
     *
     * @param ev the ev
     */
    @UiHandler("btnDelete")
    void onDeleteClick(ClickEvent ev) {
        WebFrameProxy.get().deleteMetaData(mMeta.getId(), new AsyncCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean result) {
                editMeta(null);
                MessageEvent ev = new MessageEvent(MessageEvent.REFRESH, null);
                fireEvent(ev);
            }

            @Override
            public void onFailure(Throwable caught) {
                msg(caught.getMessage());
            }
        });
    }

    public void createRoot() {
        mMeta = null;
        editMeta(null);

    }

}
