package cn.ennwifi.webframe.ui.client.modules.meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ennwifi.webframe.ui.client.data.LocalStorage;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * 元数据树
 * 
 * @author zhangjianshe
 *
 */
public class MetaTree extends Tree implements OpenHandler<TreeItem>, CloseHandler<TreeItem> {


  protected List<S_METAObj> mData;

  public MetaTree() {
    this.addOpenHandler(this);
    this.addCloseHandler(this);
  }

  public void loadData() {
    WebFrameProxy.get().fetchMetaData(new AsyncCallback<List<S_METAObj>>() {

      @Override
      public void onSuccess(List<S_METAObj> result) {
        mData = result;
        refreshView();
      }

      @Override
      public void onFailure(Throwable caught) {
        MetaTree.this.clear();
        MetaTree.this.add(new Label(caught.getMessage()));
      }
    });
  }



  protected void refreshView() {
    if (mData == null) {
      return;
    }
    this.clear();
    HashMap<Integer, TreeItem> mapper = new HashMap<Integer, TreeItem>();
    TreeItem root = new TreeItem();
    root.setText("元数据");
    root.setUserObject(null);
    this.addItem(root);
    for (S_METAObj meta : mData) {
      TreeItem parent = findParent(mapper, meta);
      if (parent == null) {
        parent = root;
      }
      TreeItem item = new TreeItem();
      item.setText(meta.getName() + "-" + meta.getCode());
      parent.addItem(item);
      item.setUserObject(meta);
      mapper.put(meta.getId(), item);
      parent.setState(isOpen("meta_" + meta.getPid()));

    }
    root.setState(true);
  }

  private TreeItem findParent(Map<Integer, TreeItem> mapper, S_METAObj meta) {
    if (meta == null || meta.getPid() == null) {
      return null;
    }
    TreeItem item = mapper.get(meta.getPid());
    return item;
  }

  @Override
  public void onClose(CloseEvent<TreeItem> event) {
    S_METAObj g = (S_METAObj) event.getTarget().getUserObject();
    if (g != null) {
      setOpen("meta_" + g.getId() + "", false);
    }
  }

  @Override
  public void onOpen(OpenEvent<TreeItem> event) {
    S_METAObj g = (S_METAObj) event.getTarget().getUserObject();
    if (g != null) {
      setOpen("meta_" + g.getId() + "", true);
    }
  }

  /**
   * Sets the open.
   *
   * @param key the key
   * @param open the open
   */
  public void setOpen(String key, boolean open) {
    LocalStorage.save(key, open ? "1" : "0");
  }

  /**
   * Checks if is open.
   *
   * @param key the key
   * @return true, if is open
   */
  public boolean isOpen(String key) {
    String data = LocalStorage.val(key);
    if ("1".equals(data)) {
      return true;
    }
    return false;
  }
}