package cn.ennwifi.webframe.ui.client.modules.authority.authoritydefine;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.common.ImageUploader;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.UploadFileReturn;
import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.ModuleFactory;
import cn.mapway.ui.client.mvc.ModuleInfo;

import cn.mapway.ui.client.widget.common.PrimaryButton;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 编辑权限点.
 *
 * @author zhangjianshe
 */
public class EditAuthority extends MessageComposite {

    /**
     * The m authority.
     */
    S_RESOURCEObj mAuthority;

    /**
     * The txt NAME.
     */
    @UiField
    TextBox txtName;

    /**
     * The Lb id.
     */
    @UiField
    Label lbID;

    /**
     * The txt VALUE.
     */
    @UiField
    TextBox txtValue;

    /**
     * The txt PATH.
     */
    @UiField
    TextBox txtPath;

    /**
     * The txt STYLE.
     */
    @UiField
    TextBox txtStyle;

    /**
     * The txt RANK.
     */
    @UiField
    TextBox txtRank;

    /**
     * The txt SUMMARY.
     */
    @UiField
    TextArea txtSummary;

    /**
     * The ddl MODULE.
     */
    @UiField
    ListBox ddlModule;

    /**
     * The btn create.
     */
    @UiField
    PrimaryButton btnCreate;

    /**
     * The btn delete.
     */
    @UiField
    PrimaryButton btnDelete;

    /**
     * The btn OK.
     */
    @UiField
    PrimaryButton btnOk;

    /**
     * The icon.
     */
    @UiField
    ImageUploader icon;

    /**
     * The Tools.
     */
    @UiField
    HorizontalPanel tools;

    /**
     * The icon handler.
     */
    private MessageHandler iconHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {
            if (message == MessageEvent.OK) {
                UploadFileReturn r = (UploadFileReturn) value;
                if (mAuthority != null) {
                    mAuthority.setIcon(r.relPath);
                }
                icon.setUrl(ClientContext.getContext().getConfigure().getImagePrefix() + r.relPath);
                icon.setImageTitle(r.relPath);
            }
        }
    };

    /**
     * The ui binder.
     */
    private static EditAuthorityUiBinder uiBinder = GWT.create(EditAuthorityUiBinder.class);

    /**
     * The Interface EditAuthorityUiBinder.
     */
    interface EditAuthorityUiBinder extends UiBinder<Widget, EditAuthority> {
    }

    /**
     * Instantiates a new edits the authority.
     */
    public EditAuthority() {
        initWidget(uiBinder.createAndBindUi(this));
        btnOk.setEnabled(false);
        icon.setAction(ImageUploader.DEFAULT_ACTION, "func");
        icon.addMessageHandler(iconHandler);
        icon.setPixelSize(256, 180);
        populateModules();
    }

    /**
     * Populate modules.
     */
    private void populateModules() {
        ModuleFactory proxy = BaseAbstractModule.getModuleFactory();
        ModuleInfo[] items = proxy.getModules();
        sort(items);
        for (int i = 0; i < items.length; i++) {
            ModuleInfo item = items[i];
            ddlModule.addItem(item.group + "/" + item.name, item.code);
        }
    }

    /**
     * 冒泡排序
     *
     * @param items
     */
    private void sort(ModuleInfo[] items) {
        if (items == null) {
            return;
        }
        ModuleInfo temp = null;
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = i + 1; j < items.length; j++) {
                if (items[i].group == null || items[j].group == null) {
                    continue;
                }
                if (items[i].group.compareTo(items[j].group) > 0) {
                    temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }

    }

    /**
     * Edits the resource.
     *
     * @param resource the resource
     */
    public void editAuthority(S_RESOURCEObj resource) {
        toUI(resource);
        msg("");
    }

    /**
     * To UI.
     *
     * @param resource the authority
     */
    private void toUI(S_RESOURCEObj resource) {
        if (resource == null) {
            resource = new S_RESOURCEObj();
            if (mAuthority != null) {
                resource.setPath(mAuthority.getPath());
                resource.setPid(mAuthority.getPid());
                resource.setRootid(mAuthority.getRootid());
            }
            if (resource.getPid() == null) {
                resource.setPid(0L);
                resource.setRootid(null);
            }
            resource.setIcon("");
            resource.setRank(1);
            resource.setClick((long) 0);

            mAuthority = resource;
        } else {
            mAuthority = resource;
        }

        txtName.setValue(mAuthority.getName());
        txtPath.setText(mAuthority.getPath());
        txtStyle.setValue(mAuthority.getStyle());
        txtSummary.setValue(mAuthority.getSummary());
        txtRank.setValue(mAuthority.getRank() + "");
        lbID.setText(mAuthority.getId() == null ? "" : (mAuthority.getId() + ""));
        for (int i = 0; i < ddlModule.getItemCount(); i++) {
            if (ddlModule.getValue(i).equals(mAuthority.getCode())) {
                ddlModule.setSelectedIndex(i);
                break;
            }
        }

        txtValue.setValue(mAuthority.getPara());

        if (mAuthority.getIcon() == null || mAuthority.getIcon().length() == 0) {
            icon.setUrl("");
        } else {
            icon.setUrl(ClientContext.getContext().getConfigure().getImagePrefix() + mAuthority.getIcon());
        }

        btnDelete.setEnabled(mAuthority.getId() != null);
        btnOk.setEnabled(true);
        msg("");
    }

    /**
     * The lb message.
     */
    @UiField
    Label lbMessage;

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
        WebFrameProxy.get().saveMenu(mAuthority, new AsyncCallback<S_RESOURCEObj>() {

            @Override
            public void onSuccess(S_RESOURCEObj result) {
                editAuthority(result);
                MessageEvent ev = new MessageEvent(MessageEvent.REFRESH, result);
                fireEvent(ev);
                msg("保存成功");
            }

            @Override
            public void onFailure(Throwable caught) {
                ClientContext.getContext().processFailure(caught);
                message(caught.getMessage());
                msg(caught.getMessage());
            }
        });
    }

    /**
     * From UI.
     */
    private void fromUI() {
        mAuthority.setName(txtName.getValue());
        mAuthority.setPath(txtPath.getValue());
        mAuthority.setCode(ddlModule.getSelectedValue());
        mAuthority.setSummary(txtSummary.getValue());
        mAuthority.setStyle(txtStyle.getValue());
        mAuthority.setRank(Integer.parseInt(txtRank.getValue()));
        mAuthority.setPara(txtValue.getValue());

    }

    /**
     * On create.
     *
     * @param ev the ev
     */
    @UiHandler("btnCreate")
    void onCreate(ClickEvent ev) {
        if (mAuthority != null && mAuthority.getId() != null) {
            S_RESOURCEObj m = new S_RESOURCEObj();
            m.setPid(mAuthority.getId());
            m.setPath(mAuthority.getPath() + "/" + mAuthority.getName());
            m.setRank(1);
            m.setPara("");
            m.setIcon("");
            m.setRootid(mAuthority.getRootid());
            editAuthority(m);
        } else {
            editAuthority(null);
        }
    }

    /**
     * On delete click.
     *
     * @param ev the ev
     */
    @UiHandler("btnDelete")
    void onDeleteClick(ClickEvent ev) {
        ClientContext.getContext().confirm("操作提示", "确认删除", new Callback<Void, Void>() {

            @Override
            public void onSuccess(Void result) {
                WebFrameProxy.get().deleteResource(mAuthority.getId(), new AsyncCallback<Void>() {

                    @Override
                    public void onSuccess(Void result) {
                        editAuthority(null);
                        MessageEvent ev = new MessageEvent(MessageEvent.REFRESH, null);
                        fireEvent(ev);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        ClientContext.getContext().processFailure(caught);
                        message(caught.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Void reason) {

            }
        });
    }
}
