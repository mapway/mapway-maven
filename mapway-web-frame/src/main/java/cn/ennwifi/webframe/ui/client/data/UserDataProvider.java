package cn.ennwifi.webframe.ui.client.data;

import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.PagerData;
import cn.ennwifi.webframe.ui.shared.module.SearchReq;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.mapway.ui.client.widget.common.PrimaryButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.data.IFieldValue;
import com.ksyzt.gwt.client.event.MessageEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据库
 *
 * @author zhangjianshe @gmail.com
 */
public class UserDataProvider extends AbstractDataProvider {

    /**
     * The constant ROLE_EDITOR.
     */
    public static final Integer ROLE_EDITOR = MessageEvent.CUSTOM + 6000;

    /**
     * The Page return.
     */
    PagerData<S_USERObj> pageReturn;
    /**
     * The Headers.
     */
    List<HeaderInfo> headers;
    /**
     * The Req.
     */
    SearchReq req;
    private ClickHandler editHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            PrimaryButton btn = (PrimaryButton) event.getSource();
            MessageEvent ev = new MessageEvent(MessageEvent.EDIT, btn.getData());
            fireEvent(ev);
        }
    };
    private ClickHandler roleHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            PrimaryButton btn = (PrimaryButton) event.getSource();
            MessageEvent ev = new MessageEvent(ROLE_EDITOR, btn.getData());
            fireEvent(ev);
        }
    };

    /**
     * Sets title.
     *
     * @param t the t
     */
    public void setTitle(String t) {
    }

    @Override
    public int getPageNumber() {
        if (pageReturn == null) {
            return 1;
        }
        return pageReturn.getPage();
    }

    @Override
    public int getPageSize() {
        if (pageReturn == null) {
            return 0;
        }
        return pageReturn.getPageSize();
    }

    @Override
    public String getTitle() {
        return new String("用户列表");
    }

    @Override
    public List<HeaderInfo> getHeaders() {
        return headers;
    }

    @Override
    public IFieldValue getRowData(int row) {

        if (pageReturn == null) {
            return null;
        }
        return pageReturn.getData().get(row);
    }

    @Override
    public Integer getTotalCount() {
        if (pageReturn == null) {
            return 0;
        }
        return pageReturn.getTotal();
    }

    @Override
    public long getCount() {
        if (pageReturn == null) {
            return 0;
        }
        return pageReturn.getData().size();
    }

    /**
     * Instantiates a new User data provider.
     */
    public UserDataProvider() {
        headers = new ArrayList<HeaderInfo>();
        headers.add(new HeaderInfo("ID", S_USERObj.FLD_ID));
        headers.add(new HeaderInfo("账户类型", S_USERObj.FLD_ACCOUNT_TYPE));
        headers.add(new HeaderInfo("登录名", S_USERObj.FLD_NAME));
        headers.add(new HeaderInfo("性别", S_USERObj.FLD_GENDER));
        headers.add(new HeaderInfo("真实名称", S_USERObj.FLD_REAL_NAME));
        headers.add(new HeaderInfo("邮箱", S_USERObj.FLD_EMAIL));
        headers.add(new HeaderInfo("电话", S_USERObj.FLD_MOBILE));
        headers.add(new HeaderInfo("编辑"));
        headers.add(new HeaderInfo("角色"));

    }

    @Override
    public Widget render(int row, int col, IFieldValue rowdata, Object cellData) {

        if (col == 7) {
            PrimaryButton btn = new PrimaryButton();
            btn.setText("编辑");
            btn.setData(pageReturn.getData().get(row));
            btn.addClickHandler(editHandler);
            return btn;
        }
        if (col == 8) {
            PrimaryButton btn = new PrimaryButton();
            btn.setText("角色");
            btn.setData(pageReturn.getData().get(row));
            btn.addClickHandler(roleHandler);
            return btn;
        }

        return super.render(row, col, rowdata, cellData);
    }

    /**
     * Load data.
     *
     * @param req the req
     */
    public void loadData(SearchReq req) {
        if (req == null) {
            req = new SearchReq();
        }
        this.req = req;
        WebFrameProxy.get().searchUserList(req, new AsyncCallback<PagerData<S_USERObj>>() {
            @Override
            public void onSuccess(PagerData<S_USERObj> result) {
                pageReturn = result;
                notifyDataChange();
            }

            @Override
            public void onFailure(Throwable caught) {
                GWT.log(caught.getMessage());
            }
        });
    }
}
