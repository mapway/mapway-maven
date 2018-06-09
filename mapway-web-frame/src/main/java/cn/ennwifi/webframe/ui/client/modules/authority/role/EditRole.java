package cn.ennwifi.webframe.ui.client.modules.authority.role;


import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;

import cn.ennwifi.webframe.ui.shared.repository.S_ROLEObj;
import cn.mapway.ui.client.widget.common.DialogBoxEx;


/**
 * 角色编辑.
 *
 * @author zhangjianshe
 */
public class EditRole extends DialogBoxEx {

    /**
     * The ui binder.
     */
    private static EditRoleUiBinder uiBinder = GWT.create(EditRoleUiBinder.class);

    /**
     * The Interface EditRoleUiBinder.
     */
    interface EditRoleUiBinder extends UiBinder<Widget, EditRole> {
    }

    /**
     * Instantiates a new edits the role.
     */
    public EditRole() {
        setWidget(uiBinder.createAndBindUi(this));
        setText("角色编辑");
        edit(null);
    }

    /**
     * The m role.
     */
    S_ROLEObj mRole;

    /**
     * Edits the.
     *
     * @param role the role
     */
    public void edit(S_ROLEObj role) {
        message("");
        if (role == null) {
            role = new S_ROLEObj();
            role.setName("输入角色名称");
            role.setSummary("角色说明");
        }
        mRole = role;
        toUserInterface();
    }

    /**
     * To UI.
     */
    private void toUserInterface() {
        txtName.setValue(mRole.getName());
        txtSummary.setValue(mRole.getSummary());
    }

    /**
     * The txt NAME.
     */
    @UiField
    TextBox txtName;

    /**
     * The txt SUMMARY.
     */
    @UiField
    TextArea txtSummary;


    /**
     * On delete click.
     *
     * @param e the e
     */
    void onDeleteClick(ClickEvent e) {
        WebFrameProxy.get().deleteAdminRole(mRole.getId(), new AsyncCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean result) {
                hide();
            }

            @Override
            public void onFailure(Throwable caught) {
                message(caught.getMessage());
            }
        });
    }


    /**
     * On save click.
     *
     * @param e the e
     */
    @UiHandler("btnSave")
    void onSaveClick(ClickEvent e) {
        fromUserInterface();
        String checked = checkObject(mRole);

        if (checked.length() > 0) {
            message(checked);
            return;
        }
        WebFrameProxy.get().saveAdminRole(mRole, new AsyncCallback<S_ROLEObj>() {

            @Override
            public void onSuccess(S_ROLEObj result) {
                edit(result);
                MessageEvent ev = new MessageEvent(MessageEvent.SAVE, null);
                fireEvent(ev);
                hide();
            }

            @Override
            public void onFailure(Throwable caught) {

            }
        });
    }

    /**
     * The Lb message.
     */
    @UiField
    Label lbMessage;

    private void message(String checked) {
        lbMessage.setText(checked);
    }

    /**
     * 监测用户输入数据.
     *
     * @param mRole2 the m role 2
     * @return the string
     */
    private String checkObject(S_ROLEObj mRole2) {
        String rmsg = "";
        if (mRole2.getName() == null || mRole2.getName().length() == 0 || mRole2.getName().length() > 64) {
            return "角色名称需要1到64个字符串长度";
        }
        if (mRole2.getSummary() != null && mRole2.getSummary().length() > 128) {
            return "角色说明不能大于128个字符";
        }
        return rmsg;
    }

    /**
     * From UI.
     */
    private void fromUserInterface() {
        mRole.setName(txtName.getValue());
        mRole.setSummary(txtSummary.getValue());
    }


}
