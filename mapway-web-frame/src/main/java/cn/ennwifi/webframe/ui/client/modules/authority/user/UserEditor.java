package cn.ennwifi.webframe.ui.client.modules.authority.user;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.common.ImageUploader;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.UploadFileReturn;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.mapway.ui.client.widget.common.DialogBoxEx;
import cn.mapway.ui.client.widget.common.LabelEx;
import cn.mapway.ui.client.widget.common.ListBoxEx;
import cn.mapway.ui.client.widget.common.TextBoxEx;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 用户编辑.
 *
 * @author zhangjianshe
 */
public class UserEditor extends DialogBoxEx {

    /**
     * The ui binder.
     */
    private static UserEditorUiBinder uiBinder = GWT.create(UserEditorUiBinder.class);

    /**
     * The Interface UserEditorUiBinder.
     */
    interface UserEditorUiBinder extends UiBinder<Widget, UserEditor> {
    }

    /**
     * The avator handler.
     */
    private MessageHandler avatorHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {
            if (message == MessageEvent.OK) {
                UploadFileReturn r = (UploadFileReturn) value;
                avator.setUrl(ClientContext.getContext().getConfigure().getImagePrefix() + r.relPath);
                avator.setImageTitle(r.relPath);
                if (mUser != null) {
                    mUser.setAvator(r.relPath);
                }

            } else if (message == MessageEvent.MESSAGE) {
                msg((String) value);
            }

        }
    };

    /**
     * Instantiates a new user editor.
     */
    public UserEditor() {
        setText("用户信息");
        setWidget(uiBinder.createAndBindUi(this));
        btnSave.setEnabled(false);
        avator.setAction(ImageUploader.DEFAULT_ACTION, "avator");
        avator.setPixelSize(300, 210);
        avator.addMessageHandler(avatorHandler);
    }

    /**
     * The btn save.
     */
    @UiField
    Button btnSave;

    /**
     * On save click.
     *
     * @param ev the ev
     */
    @UiHandler("btnSave")
    void onSaveClick(ClickEvent ev) {
        fromUserInterface();
        if (txtName.getValue().length() == 0) {
            msg("请输入登录名");
            return;
        }
        if (txtRealName.getValue().length() == 0) {
            msg("请输入真实姓名");
            return;
        }
        if (txtTel.getValue().length() == 0) {
            msg("请输入电话");
            return;
        }
        if (txtEmail.getValue().length() == 0) {
            msg("请输入邮箱");
            return;
        }
        msg("保存中....");
        WebFrameProxy.get().saveAdminUser(mUser, new AsyncCallback<S_USERObj>() {

            @Override
            public void onSuccess(S_USERObj result) {
                edit(result);
                msg("已保存");
                ClientContext.getContext().postTopic(EventTopics.SAVE_USERINFO, 0, null);
            }

            @Override
            public void onFailure(Throwable caught) {
                ClientContext.getContext().processFailure(caught);
                msg(caught.getMessage());
            }
        });
    }

    /**
     * Msg.
     *
     * @param msg the msg
     */
    private void msg(String msg) {
        lbMsg.setText(msg);
    }

    /**
     * From UI.
     */
    private void fromUserInterface() {
        mUser.setName(txtName.getValue());
        mUser.setEmail(txtEmail.getValue());
        mUser.setGender(ddlGender.getSelectedValue());
        mUser.setMobile(txtTel.getValue());
        mUser.setReal_name(txtRealName.getValue());
        mUser.setPwd(txtPassword.getValue());

    }

    /**
     * The m user.
     */
    S_USERObj mUser;

    /**
     * Edits the.
     *
     * @param user the user
     */
    public void edit(S_USERObj user) {
        mUser = user;
        msg("头像大小不能超过512K");
        toUserInterface();
    }

    /**
     * Edits the.
     *
     * @param user the user
     */
    public void edit() {
        mUser = new S_USERObj();
        msg("");
        toUserInterface();
    }

    /**
     * The txt NAME.
     */
    @UiField
    TextBoxEx txtName;

    /**
     * The txt REALNAME.
     */
    @UiField
    TextBoxEx txtRealName;

    /**
     * The txt EMAIL.
     */
    @UiField
    TextBoxEx txtEmail;

    /**
     * The txt TEL.
     */
    @UiField
    TextBoxEx txtTel;

    /**
     * The ddl GENDER.
     */
    @UiField
    ListBoxEx ddlGender;

    /**
     * The txt PASSWORD.
     */
    @UiField
    TextBoxEx txtPassword;

    /**
     * The avator.
     */
    @UiField
    ImageUploader avator;

    /**
     * The lb msg.
     */
    @UiField
    LabelEx lbMsg;

    /**
     * To UI.
     */
    private void toUserInterface() {

        btnSave.setEnabled(true);
        txtName.setValue(mUser.getName());

        if (mUser.getId() != null) {
            txtName.setReadOnly(true);
        } else {
            txtName.setReadOnly(false);
        }

        txtRealName.setValue(mUser.getReal_name());
        txtEmail.setValue(mUser.getEmail());
        txtTel.setValue(mUser.getMobile());
        avator.setUrl(mUser.getAvator());
        int index = 0;
        for (int i = 0; i < ddlGender.getItemCount(); i++) {
            String v = ddlGender.getItemText(i);
            if (v.equals(mUser.getGender())) {
                index = i;
                break;
            }
        }
        ddlGender.setSelectedIndex(index);
    }

    public void editEnable() {
        txtName.setEnabled(false);
        txtRealName.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTel.setEnabled(false);
        txtPassword.setEnabled(false);
    }
}
