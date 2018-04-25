package cn.ennwifi.webframe.ui.client.modules.authority.role;

import java.util.List;

import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.repository.S_ROLEObj;
import cn.mapway.ui.client.widget.common.Item;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ksyzt.gwt.client.common.SimpleMessage;
import com.ksyzt.gwt.client.event.MessageEvent;


/**
 * 角色列表.
 *
 * @author zhangjianshe
 */
public class RoleList extends cn.mapway.ui.client.widget.common.ItemList {

    /**
     * Instantiates a new role list.
     */
    @UiConstructor
  public RoleList() {

  }

    /**
     * The m data.
     */
    List<S_ROLEObj> mData;

    /**
     * Fetch data.
     */
    public void fetchData() {
    this.clear();
    this.add(new SimpleMessage("加载系统角色数据"));
    WebFrameProxy.get().getAllRole(new AsyncCallback<List<S_ROLEObj>>() {

      @Override
      public void onSuccess(List<S_ROLEObj> result) {
        mData = result;
        refreshView();
      }

      @Override
      public void onFailure(Throwable caught) {
        MessageEvent ev = new MessageEvent(MessageEvent.MESSAGE, caught.getMessage());
        fireEvent(ev);
        clear();
        add(new SimpleMessage(caught.getMessage()));
      }
    });
  }

    /**
     * Refresh view.
     */
    public void refreshView() {

    Item item = getSelected();

    if (mData != null) {
      clear();
      for (S_ROLEObj r : mData) {
        addItem(r.getName(), r.getSummary(), "", r);
      }
    }

    if (item != null) {
      S_ROLEObj r = (S_ROLEObj) item.getData();
      for (int i = 0; i < this.getWidgetCount(); i++) {
        Item it = (Item) this.getWidget(i);
        S_ROLEObj r1 = (S_ROLEObj) it.getData();
        if (r1.getId().equals(r.getId())) {
          click(it);
        }
      }
    }

  }


}
