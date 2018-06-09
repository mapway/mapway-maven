package cn.ennwifi.webframe.ui.client.data;

import java.util.ArrayList;
import java.util.List;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.common.RowOperator;
import cn.ennwifi.webframe.ui.shared.module.PagerData;
import cn.ennwifi.webframe.ui.shared.module.SearchReq;

import com.google.gwt.core.client.Callback;
import com.ksyzt.gwt.client.data.IFieldValue;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 分页数据提供器
 *
 * @param <T> the type parameter
 * @author zhangjianshe
 */
public class PagerDataProvider<T extends IFieldValue> extends AbstractDataProvider {

    /**
     * The M data.
     */
    public PagerData<T> mData = new PagerData<T>();

    /**
     * The Headers.
     */
    public List<HeaderInfo> headers;

    /**
     * The M req.
     */
    protected SearchReq mReq;


    /**
     * Instantiates a new Pager data provider.
     */
    public PagerDataProvider() {
        mReq = new SearchReq();
        headers = new ArrayList<HeaderInfo>();

    }

    @Override
    public java.util.List<HeaderInfo> getHeaders() {
        return headers;
    }

    ;

    @Override
    public int getPageSize() {

        return mData.getPageSize();
    }

    @Override
    public int getPageNumber() {

        return mData.getPage();
    }

    @Override
    public String getTitle() {
        return "资金提供方";
    }

    @Override
    public Integer getTotalCount() {
        return mData.getTotal();
    }

    @Override
    public long getCount() {
        return mData.getData().size();
    }

    @Override
    public IFieldValue getRowData(int row) {
        return mData.getData().get(row);
    }


    /**
     * Load.
     *
     * @param req the req
     */
    public void load(SearchReq req) {
        mReq = req;

    }

    private MessageHandler operatorHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {

            final T data = (T) value;
            if (message == MessageEvent.DELETE) {
                ClientContext.getContext().confirm("删除操作", "确定删除?", new Callback<Void, Void>() {

                    @Override
                    public void onSuccess(Void result) {
                        deleteData(data);
                    }


                    @Override
                    public void onFailure(Void reason) {

                    }
                });
            } else if (message == MessageEvent.EDIT) {
                editData(data);
            }
        }
    };

    /**
     * Gets operator.
     *
     * @param row the row
     * @return operator
     */
    protected RowOperator getOperator(int row) {
        RowOperator operator = new RowOperator();
        operator.addMessageHandler(operatorHandler);
        T data = mData.getData().get(row);
        operator.setData(data);
        return operator;
    }

    /**
     * Edit data.
     *
     * @param data the data
     */
    protected void editData(T data) {

    }

    /**
     * Delete data.
     *
     * @param data the data
     */
    protected void deleteData(T data) {

    }
}
