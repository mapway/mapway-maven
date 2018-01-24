package cn.ennwifi.webframe.ui.shared.module;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;

public class MetaHiveSubInfo implements IsSerializable {

  public Integer metaId;

  public S_METAObj meta;

  public List<S_METAObj> subMeta;

  public Integer getMetaId() {
    return metaId;
  }

  public void setMetaId(Integer metaId) {
    this.metaId = metaId;
  }

  public S_METAObj getMeta() {
    return meta;
  }

  public void setMeta(S_METAObj meta) {
    this.meta = meta;
  }

  public List<S_METAObj> getSubMeta() {
    return subMeta;
  }

  public void setSubMeta(List<S_METAObj> subMeta) {
    this.subMeta = subMeta;
  }

}
