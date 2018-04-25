package cn.ennwifi.webframe.ui.shared.module;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;

/**
 * The type Meta hive sub info.
 */
public class MetaHiveSubInfo implements IsSerializable {

    /**
     * The Meta id.
     */
    public Integer metaId;

    /**
     * The Meta.
     */
    public S_METAObj meta;

    /**
     * The Sub meta.
     */
    public List<S_METAObj> subMeta;

    /**
     * Gets meta id.
     *
     * @return the meta id
     */
    public Integer getMetaId() {
    return metaId;
  }

    /**
     * Sets meta id.
     *
     * @param metaId the meta id
     */
    public void setMetaId(Integer metaId) {
    this.metaId = metaId;
  }

    /**
     * Gets meta.
     *
     * @return the meta
     */
    public S_METAObj getMeta() {
    return meta;
  }

    /**
     * Sets meta.
     *
     * @param meta the meta
     */
    public void setMeta(S_METAObj meta) {
    this.meta = meta;
  }

    /**
     * Gets sub meta.
     *
     * @return the sub meta
     */
    public List<S_METAObj> getSubMeta() {
    return subMeta;
  }

    /**
     * Sets sub meta.
     *
     * @param subMeta the sub meta
     */
    public void setSubMeta(List<S_METAObj> subMeta) {
    this.subMeta = subMeta;
  }

}
