/**
 * 
 */
package cn.ennwifi.webframe.service.module;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShaoRuolin
 */
public class SimpleTableReq {

  private Map<String, String> queryMap = new HashMap<String, String>();// 条件查询

  public Map<String, String> getQueryMap() {
    return queryMap;
  }

  public void setQueryMap(Map<String, String> queryMap) {
    this.queryMap = queryMap;
  }

}
