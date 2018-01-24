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
 * 数据库表 用户角色表<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  Long user_id //用户ID<br/>
 *  Long role_id //角色ID<br/>
 * =========== 用于GWT模型 <br/>
 * =========== 用于GWT UIBinder <br/>
 * =========== 用于fromUI <br/>
 * =========== 用于toUI <br/>
 */

@Table("s_user_role")
@Doc("s_user_role(用户角色表)")
@PK({"user_id","role_id"})
public class S_USER_ROLEObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表用户角色表名称. 
     */
  public static final String TBL_S_USER_ROLE="s_user_role";
  public S_USER_ROLEObj() {
  }
  /**
   * 根据字段名称获取字段的值. 
   */
  @Override
  public Object getFieldValue(String fieldName,Integer fieldIndex) {
    if (fieldName != null && fieldName.length() > 0) {
      if (FLD_USER_ID.equals(fieldName)) {
        return user_id;
      }
      if (FLD_ROLE_ID.equals(fieldName)) {
        return role_id;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 2) {
      if (fieldIndex == 0) {
        return user_id;
      }
      if (fieldIndex == 1) {
        return role_id;
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   * 用户ID user_id
 */
  public static final String FLD_USER_ID="user_id";

  /**
 * 用户ID user_id
 */
  public static final Integer IDX_USER_ID=0;
 /**
   * 字段user_id 用户ID
   */
	@Column("user_id")
  @ApiField(value="用户ID",example="")
  private Long user_id;

  /**
   * 返回字段user_id 用户ID的值.
   * @return user_id  用户ID  bigint
  */
  public Long getUser_id() {
    return user_id;
  }

  /**
   * 设置字段user_id 用户ID的值.
   * @param user_id  用户ID  bigint
   */
  public void setUser_id(Long user_id) {
    this.user_id=user_id;
  }

  /**
   * 角色ID role_id
 */
  public static final String FLD_ROLE_ID="role_id";

  /**
 * 角色ID role_id
 */
  public static final Integer IDX_ROLE_ID=1;
 /**
   * 字段role_id 角色ID
   */
	@Column("role_id")
  @ApiField(value="角色ID",example="")
  private Long role_id;

  /**
   * 返回字段role_id 角色ID的值.
   * @return role_id  角色ID  bigint
  */
  public Long getRole_id() {
    return role_id;
  }

  /**
   * 设置字段role_id 角色ID的值.
   * @param role_id  角色ID  bigint
   */
  public void setRole_id(Long role_id) {
    this.role_id=role_id;
  }

}
