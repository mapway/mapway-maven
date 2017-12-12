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
 * @author zhangjianshe
 *
 * @param <T>
 */
public class PagerDataProvider<T extends IFieldValue> extends AbstractDataProvider {

  public PagerData<T> mData = new PagerData<T>();

  public List<HeaderInfo> headers;

  protected SearchReq mReq;



  public PagerDataProvider() {
    mReq = new SearchReq();
    headers = new ArrayList<HeaderInfo>();

  }

  @Override
  public java.util.List<HeaderInfo> getHeaders() {
    return headers;
  };

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
   * @param row
   * @return
   */
  protected RowOperator getOperator(int row) {
    RowOperator operator = new RowOperator();
    operator.addMessageHandler(operatorHandler);
    T data = mData.getData().get(row);
    operator.setData(data);
    return operator;
  }

  protected void editData(T data) {

  }

  protected void deleteData(T data) {

  }
}
