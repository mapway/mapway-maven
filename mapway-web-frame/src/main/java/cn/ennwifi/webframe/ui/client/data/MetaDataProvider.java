package cn.ennwifi.webframe.ui.client.data;

import java.util.ArrayList;
import java.util.List;

import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;
import cn.mapway.ui.client.mvc.ObservableImpl;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 元数据提供器
 *
 * @author zhangjianshe
 */
public class MetaDataProvider extends ObservableImpl {

  private static List<S_METAObj> mData;
  private static MetaDataProvider INSANCE;
  private AsyncCallback<List<S_METAObj>> onData = new AsyncCallback<List<S_METAObj>>() {

    @Override
    public void onSuccess(List<S_METAObj> result) {
      loadding = false;

      mData = result;
      notifyObservers(mData);
    }

    @Override
    public void onFailure(Throwable caught) {
      loadding = false;
      GWT.log(caught.getMessage());
    }
  };
  private static boolean loadding = false;

    /**
     * Instantiates a new Meta data provider.
     */
    protected MetaDataProvider() {
    mData = new ArrayList<S_METAObj>();
  }

    /**
     * 获取唯一实例
     *
     * @return meta data provider
     */
    public static MetaDataProvider get() {
    if (INSANCE == null) {
      INSANCE = new MetaDataProvider();
      INSANCE.reload(true);
    }
    return INSANCE;
  }

    /**
     * 重新加载数据
     *
     * @param force the force
     */
    public void reload(boolean force) {
    if (loadding) {
      return;
    }
    if (force || mData.size() == 0) {
      loadding = true;
      WebFrameProxy.get().getMetaData("", true, onData);
    } else {
      notifyObservers(mData);
    }
  }

    /**
     * 根据路径找到元数据
     *
     * @param path the path
     * @return list
     */
    public List<S_METAObj> findByCatalog(String path) {
    List<S_METAObj> d = new ArrayList<S_METAObj>();
    for (S_METAObj m : mData) {
      if (m.getCatalog().equals(path)) {
        d.add(m);
      }
    }
    return d;
  }

    /**
     * 找到多个code 对应的元数据信息
     *
     * @param codes the codes
     * @return list
     */
    public List<S_METAObj> findByCodes(String[] codes) {
    List<S_METAObj> ds = new ArrayList<S_METAObj>();
    for (int i = 0; i < codes.length; i++) {
      S_METAObj d = findByCode(codes[i]);
      if (d != null) {
        ds.add(d);
      }
    }
    return ds;
  }

    /**
     * 转换Code到文本
     *
     * @param codes the codes
     * @return list
     */
    public List<S_METAObj> tanslate(String codes) {
    ArrayList<S_METAObj> mCodes = new ArrayList<S_METAObj>();
    if (codes != null) {
      String[] codelist = codes.split(",");
      for (int i = 0; i < codelist.length; i++) {
        String code = codelist[i];
        code = code.trim();
        if (code != null && code.length() > 0) {
          mCodes.add(findByCode(code));
        }
      }
    }
    return mCodes;
  }

    /**
     * 根据代码找到元数据
     *
     * @param code the code
     * @return s meta obj
     */
    public S_METAObj findByCode(String code) {
    if (code == null || code.length() == 0) {
      return null;
    }
    for (S_METAObj m : mData) {
      if (m.getCode().equals(code))
        return m;
    }
    return null;
  }

    /**
     * 获取所有元数据
     *
     * @return data
     */
    public List<S_METAObj> getData() {
    return mData;
  }
}
