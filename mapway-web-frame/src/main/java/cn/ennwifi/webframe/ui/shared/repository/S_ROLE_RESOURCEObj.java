/*******************************************************************************
 数据库持久化层(For MySQL5.1 Java)
 创建日期  Fri Dec 08 18:14:58 CST 2017
 创建程序 Program 版本(3.0)
 程序设计 张建设 EMAIL:zhangjianshe@gmail.com PHONE:15910868680

<pre>

           =============================================================
           -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -
           -    __] |__| |__| |\ | | __  | | |__| |\ | [__  |__| |___  -
           -   [___ |  | |  | | \| |__| _| | |  | | \| ___] |  | |___  -
           -           http://hi.baidu.com/zhangjianshe                -
           =============================================================

</pre>
/******************************************************************************/

package cn.ennwifi.webframe.ui.shared.repository;

import java.util.Date;

import org.nutz.dao.entity.annotation.*;
import java.math.BigDecimal;
import cn.mapway.document.annotation.*;

/**
 * 数据库表 角色资源ID
 */
@Table("s_role_resource")
@Doc("s_role_resource(角色资源ID)")
@PK({"role_id","res_id"})
public class S_ROLE_RESOURCEObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

    /**
     * 表角色资源ID名称.
     */
    public static final String TBL_S_ROLE_RESOURCE="s_role_resource";

    /**
     * Instantiates a new S role resource obj.
     */
    public S_ROLE_RESOURCEObj() {
  }
  /**
   * 根据字段名称获取字段的值. 
   */
  @Override
  public Object getFieldValue(String fieldName,Integer fieldIndex) {
    if (fieldName != null && fieldName.length() > 0) {
      if (FLD_ROLE_ID.equals(fieldName)) {
        return role_id;
      }
      if (FLD_RES_ID.equals(fieldName)) {
        return res_id;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 2) {
      if (fieldIndex == 0) {
        return role_id;
      }
      if (fieldIndex == 1) {
        return res_id;
      }
    } else {
      return null;
    }
    return null;
  }

    /**
     * 角色ID role_id
     */
    public static final String FLD_ROLE_ID="role_id";

    /**
     * 角色ID role_id
     */
    public static final Integer IDX_ROLE_ID=0;
 /**
   * 字段role_id 角色ID
   */
	@Column("role_id")
  @ApiField(value="角色ID",example="")
  private Long role_id;

    /**
     * 返回字段role_id 角色ID的值.
     *
     * @return role_id 角色ID  bigint
     */
    public Long getRole_id() {
    return role_id;
  }

    /**
     * 设置字段role_id 角色ID的值.
     *
     * @param role_id 角色ID  bigint
     */
    public void setRole_id(Long role_id) {
    this.role_id=role_id;
  }

    /**
     * 资源ID res_id
     */
    public static final String FLD_RES_ID="res_id";

    /**
     * 资源ID res_id
     */
    public static final Integer IDX_RES_ID=1;
 /**
   * 字段res_id 资源ID
   */
	@Column("res_id")
  @ApiField(value="资源ID",example="")
  private Long res_id;

    /**
     * 返回字段res_id 资源ID的值.
     *
     * @return res_id 资源ID  bigint
     */
    public Long getRes_id() {
    return res_id;
  }

    /**
     * 设置字段res_id 资源ID的值.
     *
     * @param res_id 资源ID  bigint
     */
    public void setRes_id(Long res_id) {
    this.res_id=res_id;
  }

}
