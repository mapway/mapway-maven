/**
 * 
 */
package cn.ennwifi.webframe.service.module;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.Record;

/**
 * @author ShaoRuolin
 */
public class SimpleTableResp {

  private List<Record> data = new ArrayList<Record>();// 返回前端数据

  public List<Record> getData() {
    return data;
  }

  public void setData(List<Record> data) {
    this.data = data;
  }

}
