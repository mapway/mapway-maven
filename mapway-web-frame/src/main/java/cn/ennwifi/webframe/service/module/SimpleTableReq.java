/**
 * 
 */
package cn.ennwifi.webframe.service.module;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Simple table req.
 *
 * @author ShaoRuolin
 */
public class SimpleTableReq {

  private Map<String, String> queryMap = new HashMap<String, String>();// 条件查询

    /**
     * Gets query map.
     *
     * @return the query map
     */
    public Map<String, String> getQueryMap() {
    return queryMap;
  }

    /**
     * Sets query map.
     *
     * @param queryMap the query map
     */
    public void setQueryMap(Map<String, String> queryMap) {
    this.queryMap = queryMap;
  }

}
