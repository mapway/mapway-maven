/**
 * 
 */
package cn.ennwifi.webframe.service.module;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.Record;

/**
 * The type Simple table resp.
 *
 * @author ShaoRuolin
 */
public class SimpleTableResp {

  private List<Record> data = new ArrayList<Record>();// 返回前端数据

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<Record> getData() {
    return data;
  }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(List<Record> data) {
    this.data = data;
  }

}
