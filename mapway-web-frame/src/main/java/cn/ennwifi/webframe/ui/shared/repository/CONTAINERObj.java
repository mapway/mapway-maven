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
 * 数据库表 <br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  String cim_id //CIMID<br/>
 *  String name //CIM-名称<br/>
 *  Long ctime //<br/>
 *  Long utime //<br/>
 *  String cuser_id //创建人<br/>
 *  String uuser_id //<br/>
 *  String alias //<br/>
 *  String description //<br/>
 *  String status //容器状态<br/>
 *  String type //容器类型<br/>
 *  String parent_id //父容器<br/>
 *  String root_id //根容器ID<br/>
 *  String domain //容器业务域<br/>
 *  String container_id //容器id<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtCim_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtName;<br/>
 *  {@literal @ }UiField TextBoxEx txtCuser_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtUuser_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtAlias;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 *  {@literal @ }UiField TextBoxEx txtStatus;<br/>
 *  {@literal @ }UiField TextBoxEx txtType;<br/>
 *  {@literal @ }UiField TextBoxEx txtParent_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtRoot_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtDomain;<br/>
 *  {@literal @ }UiField TextBoxEx txtContainer_id;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;CIMID&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCim_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;CIM-名称&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtName" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;创建人&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCuser_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtUuser_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtAlias" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;容器状态&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtStatus" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;容器类型&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtType" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;父容器&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtParent_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;根容器ID&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtRoot_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;容器业务域&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDomain" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;容器id&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtContainer_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setCim_id(txtCim_id.getValue());<br/>
 * data.setName(txtName.getValue());<br/>
 * data.setCuser_id(txtCuser_id.getValue());<br/>
 * data.setUuser_id(txtUuser_id.getValue());<br/>
 * data.setAlias(txtAlias.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * data.setStatus(txtStatus.getValue());<br/>
 * data.setType(txtType.getValue());<br/>
 * data.setParent_id(txtParent_id.getValue());<br/>
 * data.setRoot_id(txtRoot_id.getValue());<br/>
 * data.setDomain(txtDomain.getValue());<br/>
 * data.setContainer_id(txtContainer_id.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtCim_id.setValue(obj.getCim_id());<br/>
 * txtName.setValue(obj.getName());<br/>
 * txtCuser_id.setValue(obj.getCuser_id());<br/>
 * txtUuser_id.setValue(obj.getUuser_id());<br/>
 * txtAlias.setValue(obj.getAlias());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 * txtStatus.setValue(obj.getStatus());<br/>
 * txtType.setValue(obj.getType());<br/>
 * txtParent_id.setValue(obj.getParent_id());<br/>
 * txtRoot_id.setValue(obj.getRoot_id());<br/>
 * txtDomain.setValue(obj.getDomain());<br/>
 * txtContainer_id.setValue(obj.getContainer_id());<br/>
 */

@Table("container")
@Doc("container()")
public class CONTAINERObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表名称. 
     */
  public static final String TBL_CONTAINER="container";
  public CONTAINERObj() {
  }
  /**
   * 根据字段名称获取字段的值. 
   */
  @Override
  public Object getFieldValue(String fieldName,Integer fieldIndex) {
    if (fieldName != null && fieldName.length() > 0) {
      if (FLD_CIM_ID.equals(fieldName)) {
        return cim_id;
      }
      if (FLD_NAME.equals(fieldName)) {
        return name;
      }
      if (FLD_CTIME.equals(fieldName)) {
        return ctime;
      }
      if (FLD_UTIME.equals(fieldName)) {
        return utime;
      }
      if (FLD_CUSER_ID.equals(fieldName)) {
        return cuser_id;
      }
      if (FLD_UUSER_ID.equals(fieldName)) {
        return uuser_id;
      }
      if (FLD_ALIAS.equals(fieldName)) {
        return alias;
      }
      if (FLD_DESCRIPTION.equals(fieldName)) {
        return description;
      }
      if (FLD_STATUS.equals(fieldName)) {
        return status;
      }
      if (FLD_TYPE.equals(fieldName)) {
        return type;
      }
      if (FLD_PARENT_ID.equals(fieldName)) {
        return parent_id;
      }
      if (FLD_ROOT_ID.equals(fieldName)) {
        return root_id;
      }
      if (FLD_DOMAIN.equals(fieldName)) {
        return domain;
      }
      if (FLD_CONTAINER_ID.equals(fieldName)) {
        return container_id;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 14) {
      if (fieldIndex == 0) {
        return cim_id;
      }
      if (fieldIndex == 1) {
        return name;
      }
      if (fieldIndex == 2) {
        return ctime;
      }
      if (fieldIndex == 3) {
        return utime;
      }
      if (fieldIndex == 4) {
        return cuser_id;
      }
      if (fieldIndex == 5) {
        return uuser_id;
      }
      if (fieldIndex == 6) {
        return alias;
      }
      if (fieldIndex == 7) {
        return description;
      }
      if (fieldIndex == 8) {
        return status;
      }
      if (fieldIndex == 9) {
        return type;
      }
      if (fieldIndex == 10) {
        return parent_id;
      }
      if (fieldIndex == 11) {
        return root_id;
      }
      if (fieldIndex == 12) {
        return domain;
      }
      if (fieldIndex == 13) {
        return container_id;
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   * CIMID cim_id
 */
  public static final String FLD_CIM_ID="cim_id";

  /**
 * CIMID cim_id
 */
  public static final Integer IDX_CIM_ID=0;
 /**
   * 字段cim_id CIMID
   */
	@Name
  @ApiField(value="CIMID",example="")
  private String cim_id;

  /**
   * 返回字段cim_id CIMID的值.
   * @return cim_id  CIMID  varchar
  */
  public String getCim_id() {
    return cim_id;
  }

  /**
   * 设置字段cim_id CIMID的值.
   * @param cim_id  CIMID  varchar
   */
  public void setCim_id(String cim_id) {
    this.cim_id=cim_id;
  }

  /**
   * CIM-名称 name
 */
  public static final String FLD_NAME="name";

  /**
 * CIM-名称 name
 */
  public static final Integer IDX_NAME=1;
 /**
   * 字段name CIM-名称
   */
	@Column("name")
  @ApiField(value="CIM-名称",example="")
  private String name;

  /**
   * 返回字段name CIM-名称的值.
   * @return name  CIM-名称  varchar
  */
  public String getName() {
    return name;
  }

  /**
   * 设置字段name CIM-名称的值.
   * @param name  CIM-名称  varchar
   */
  public void setName(String name) {
    this.name=name;
  }

  /**
   *  ctime
 */
  public static final String FLD_CTIME="ctime";

  /**
 *  ctime
 */
  public static final Integer IDX_CTIME=2;
 /**
   * 字段ctime 
   */
	@Column("ctime")
  @ApiField(value="",example="")
  private Long ctime;

  /**
   * 返回字段ctime 的值.
   * @return ctime    bigint
  */
  public Long getCtime() {
    return ctime;
  }

  /**
   * 设置字段ctime 的值.
   * @param ctime    bigint
   */
  public void setCtime(Long ctime) {
    this.ctime=ctime;
  }

  /**
   *  utime
 */
  public static final String FLD_UTIME="utime";

  /**
 *  utime
 */
  public static final Integer IDX_UTIME=3;
 /**
   * 字段utime 
   */
	@Column("utime")
  @ApiField(value="",example="")
  private Long utime;

  /**
   * 返回字段utime 的值.
   * @return utime    bigint
  */
  public Long getUtime() {
    return utime;
  }

  /**
   * 设置字段utime 的值.
   * @param utime    bigint
   */
  public void setUtime(Long utime) {
    this.utime=utime;
  }

  /**
   * 创建人 cuser_id
 */
  public static final String FLD_CUSER_ID="cuser_id";

  /**
 * 创建人 cuser_id
 */
  public static final Integer IDX_CUSER_ID=4;
 /**
   * 字段cuser_id 创建人
   */
	@Column("cuser_id")
  @ApiField(value="创建人",example="")
  private String cuser_id;

  /**
   * 返回字段cuser_id 创建人的值.
   * @return cuser_id  创建人  varchar
  */
  public String getCuser_id() {
    return cuser_id;
  }

  /**
   * 设置字段cuser_id 创建人的值.
   * @param cuser_id  创建人  varchar
   */
  public void setCuser_id(String cuser_id) {
    this.cuser_id=cuser_id;
  }

  /**
   *  uuser_id
 */
  public static final String FLD_UUSER_ID="uuser_id";

  /**
 *  uuser_id
 */
  public static final Integer IDX_UUSER_ID=5;
 /**
   * 字段uuser_id 
   */
	@Column("uuser_id")
  @ApiField(value="",example="")
  private String uuser_id;

  /**
   * 返回字段uuser_id 的值.
   * @return uuser_id    varchar
  */
  public String getUuser_id() {
    return uuser_id;
  }

  /**
   * 设置字段uuser_id 的值.
   * @param uuser_id    varchar
   */
  public void setUuser_id(String uuser_id) {
    this.uuser_id=uuser_id;
  }

  /**
   *  alias
 */
  public static final String FLD_ALIAS="alias";

  /**
 *  alias
 */
  public static final Integer IDX_ALIAS=6;
 /**
   * 字段alias 
   */
	@Column("alias")
  @ApiField(value="",example="")
  private String alias;

  /**
   * 返回字段alias 的值.
   * @return alias    varchar
  */
  public String getAlias() {
    return alias;
  }

  /**
   * 设置字段alias 的值.
   * @param alias    varchar
   */
  public void setAlias(String alias) {
    this.alias=alias;
  }

  /**
   *  description
 */
  public static final String FLD_DESCRIPTION="description";

  /**
 *  description
 */
  public static final Integer IDX_DESCRIPTION=7;
 /**
   * 字段description 
   */
	@Column("description")
  @ApiField(value="",example="")
  private String description;

  /**
   * 返回字段description 的值.
   * @return description    varchar
  */
  public String getDescription() {
    return description;
  }

  /**
   * 设置字段description 的值.
   * @param description    varchar
   */
  public void setDescription(String description) {
    this.description=description;
  }

  /**
   * 容器状态 status
 */
  public static final String FLD_STATUS="status";

  /**
 * 容器状态 status
 */
  public static final Integer IDX_STATUS=8;
 /**
   * 字段status 容器状态
   */
	@Column("status")
  @ApiField(value="容器状态",example="")
  private String status;

  /**
   * 返回字段status 容器状态的值.
   * @return status  容器状态  varchar
  */
  public String getStatus() {
    return status;
  }

  /**
   * 设置字段status 容器状态的值.
   * @param status  容器状态  varchar
   */
  public void setStatus(String status) {
    this.status=status;
  }

  /**
   * 容器类型 type
 */
  public static final String FLD_TYPE="type";

  /**
 * 容器类型 type
 */
  public static final Integer IDX_TYPE=9;
 /**
   * 字段type 容器类型
   */
	@Column("type")
  @ApiField(value="容器类型",example="")
  private String type;

  /**
   * 返回字段type 容器类型的值.
   * @return type  容器类型  varchar
  */
  public String getType() {
    return type;
  }

  /**
   * 设置字段type 容器类型的值.
   * @param type  容器类型  varchar
   */
  public void setType(String type) {
    this.type=type;
  }

  /**
   * 父容器 parent_id
 */
  public static final String FLD_PARENT_ID="parent_id";

  /**
 * 父容器 parent_id
 */
  public static final Integer IDX_PARENT_ID=10;
 /**
   * 字段parent_id 父容器
   */
	@Column("parent_id")
  @ApiField(value="父容器",example="")
  private String parent_id;

  /**
   * 返回字段parent_id 父容器的值.
   * @return parent_id  父容器  varchar
  */
  public String getParent_id() {
    return parent_id;
  }

  /**
   * 设置字段parent_id 父容器的值.
   * @param parent_id  父容器  varchar
   */
  public void setParent_id(String parent_id) {
    this.parent_id=parent_id;
  }

  /**
   * 根容器ID root_id
 */
  public static final String FLD_ROOT_ID="root_id";

  /**
 * 根容器ID root_id
 */
  public static final Integer IDX_ROOT_ID=11;
 /**
   * 字段root_id 根容器ID
   */
	@Column("root_id")
  @ApiField(value="根容器ID",example="")
  private String root_id;

  /**
   * 返回字段root_id 根容器ID的值.
   * @return root_id  根容器ID  varchar
  */
  public String getRoot_id() {
    return root_id;
  }

  /**
   * 设置字段root_id 根容器ID的值.
   * @param root_id  根容器ID  varchar
   */
  public void setRoot_id(String root_id) {
    this.root_id=root_id;
  }

  /**
   * 容器业务域 domain
 */
  public static final String FLD_DOMAIN="domain";

  /**
 * 容器业务域 domain
 */
  public static final Integer IDX_DOMAIN=12;
 /**
   * 字段domain 容器业务域
   */
	@Column("domain")
  @ApiField(value="容器业务域",example="")
  private String domain;

  /**
   * 返回字段domain 容器业务域的值.
   * @return domain  容器业务域  varchar
  */
  public String getDomain() {
    return domain;
  }

  /**
   * 设置字段domain 容器业务域的值.
   * @param domain  容器业务域  varchar
   */
  public void setDomain(String domain) {
    this.domain=domain;
  }

  /**
   * 容器id container_id
 */
  public static final String FLD_CONTAINER_ID="container_id";

  /**
 * 容器id container_id
 */
  public static final Integer IDX_CONTAINER_ID=13;
 /**
   * 字段container_id 容器id
   */
	@Column("container_id")
  @ApiField(value="容器id",example="")
  private String container_id;

  /**
   * 返回字段container_id 容器id的值.
   * @return container_id  容器id  varchar
  */
  public String getContainer_id() {
    return container_id;
  }

  /**
   * 设置字段container_id 容器id的值.
   * @param container_id  容器id  varchar
   */
  public void setContainer_id(String container_id) {
    this.container_id=container_id;
  }

}
