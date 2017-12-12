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
 * 数据库表 元数据表<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  Integer id //<br/>
 *  Integer pid //<br/>
 *  String name //<br/>
 *  String catalog //<br/>
 *  String code //<br/>
 *  Integer rank //0<br/>
 *  String icon //图标<br/>
 *  Integer root_id //根ID<br/>
 *  String description //元数据描述<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtName;<br/>
 *  {@literal @ }UiField TextBoxEx txtCatalog;<br/>
 *  {@literal @ }UiField TextBoxEx txtCode;<br/>
 *  {@literal @ }UiField TextBoxEx txtIcon;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtName" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCatalog" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCode" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;图标&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtIcon" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;元数据描述&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setName(txtName.getValue());<br/>
 * data.setCatalog(txtCatalog.getValue());<br/>
 * data.setCode(txtCode.getValue());<br/>
 * data.setIcon(txtIcon.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtName.setValue(obj.getName());<br/>
 * txtCatalog.setValue(obj.getCatalog());<br/>
 * txtCode.setValue(obj.getCode());<br/>
 * txtIcon.setValue(obj.getIcon());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 */

@Table("cim_meta")
@Doc("cim_meta(元数据表)")
public class CIM_METAObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表元数据表名称. 
     */
  public static final String TBL_CIM_META="cim_meta";
  public CIM_METAObj() {
  }
  /**
   * 根据字段名称获取字段的值. 
   */
  @Override
  public Object getFieldValue(String fieldName,Integer fieldIndex) {
    if (fieldName != null && fieldName.length() > 0) {
      if (FLD_ID.equals(fieldName)) {
        return id;
      }
      if (FLD_PID.equals(fieldName)) {
        return pid;
      }
      if (FLD_NAME.equals(fieldName)) {
        return name;
      }
      if (FLD_CATALOG.equals(fieldName)) {
        return catalog;
      }
      if (FLD_CODE.equals(fieldName)) {
        return code;
      }
      if (FLD_RANK.equals(fieldName)) {
        return rank;
      }
      if (FLD_ICON.equals(fieldName)) {
        return icon;
      }
      if (FLD_ROOT_ID.equals(fieldName)) {
        return root_id;
      }
      if (FLD_DESCRIPTION.equals(fieldName)) {
        return description;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 9) {
      if (fieldIndex == 0) {
        return id;
      }
      if (fieldIndex == 1) {
        return pid;
      }
      if (fieldIndex == 2) {
        return name;
      }
      if (fieldIndex == 3) {
        return catalog;
      }
      if (fieldIndex == 4) {
        return code;
      }
      if (fieldIndex == 5) {
        return rank;
      }
      if (fieldIndex == 6) {
        return icon;
      }
      if (fieldIndex == 7) {
        return root_id;
      }
      if (fieldIndex == 8) {
        return description;
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   *  id
 */
  public static final String FLD_ID="id";

  /**
 *  id
 */
  public static final Integer IDX_ID=0;
 /**
   * 字段id 
   */
	@Id
  @ApiField(value="",example="")
  private Integer id;

  /**
   * 返回字段id 的值.
   * @return id    int
  */
  public Integer getId() {
    return id;
  }

  /**
   * 设置字段id 的值.
   * @param id    int
   */
  public void setId(Integer id) {
    this.id=id;
  }

  /**
   *  pid
 */
  public static final String FLD_PID="pid";

  /**
 *  pid
 */
  public static final Integer IDX_PID=1;
 /**
   * 字段pid 
   */
	@Column("pid")
  @ApiField(value="",example="")
  private Integer pid;

  /**
   * 返回字段pid 的值.
   * @return pid    int
  */
  public Integer getPid() {
    return pid;
  }

  /**
   * 设置字段pid 的值.
   * @param pid    int
   */
  public void setPid(Integer pid) {
    this.pid=pid;
  }

  /**
   *  name
 */
  public static final String FLD_NAME="name";

  /**
 *  name
 */
  public static final Integer IDX_NAME=2;
 /**
   * 字段name 
   */
	@Column("name")
  @ApiField(value="",example="")
  private String name;

  /**
   * 返回字段name 的值.
   * @return name    varchar
  */
  public String getName() {
    return name;
  }

  /**
   * 设置字段name 的值.
   * @param name    varchar
   */
  public void setName(String name) {
    this.name=name;
  }

  /**
   *  catalog
 */
  public static final String FLD_CATALOG="catalog";

  /**
 *  catalog
 */
  public static final Integer IDX_CATALOG=3;
 /**
   * 字段catalog 
   */
	@Column("catalog")
  @ApiField(value="",example="")
  private String catalog;

  /**
   * 返回字段catalog 的值.
   * @return catalog    varchar
  */
  public String getCatalog() {
    return catalog;
  }

  /**
   * 设置字段catalog 的值.
   * @param catalog    varchar
   */
  public void setCatalog(String catalog) {
    this.catalog=catalog;
  }

  /**
   *  code
 */
  public static final String FLD_CODE="code";

  /**
 *  code
 */
  public static final Integer IDX_CODE=4;
 /**
   * 字段code 
   */
	@Column("code")
  @ApiField(value="",example="")
  private String code;

  /**
   * 返回字段code 的值.
   * @return code    varchar
  */
  public String getCode() {
    return code;
  }

  /**
   * 设置字段code 的值.
   * @param code    varchar
   */
  public void setCode(String code) {
    this.code=code;
  }

  /**
   * 0 rank
 */
  public static final String FLD_RANK="rank";

  /**
 * 0 rank
 */
  public static final Integer IDX_RANK=5;
 /**
   * 字段rank 0
   */
	@Column("rank")
  @ApiField(value="0",example="")
  private Integer rank;

  /**
   * 返回字段rank 0的值.
   * @return rank  0  int
  */
  public Integer getRank() {
    return rank;
  }

  /**
   * 设置字段rank 0的值.
   * @param rank  0  int
   */
  public void setRank(Integer rank) {
    this.rank=rank;
  }

  /**
   * 图标 icon
 */
  public static final String FLD_ICON="icon";

  /**
 * 图标 icon
 */
  public static final Integer IDX_ICON=6;
 /**
   * 字段icon 图标
   */
	@Column("icon")
  @ApiField(value="图标",example="")
  private String icon;

  /**
   * 返回字段icon 图标的值.
   * @return icon  图标  varchar
  */
  public String getIcon() {
    return icon;
  }

  /**
   * 设置字段icon 图标的值.
   * @param icon  图标  varchar
   */
  public void setIcon(String icon) {
    this.icon=icon;
  }

  /**
   * 根ID root_id
 */
  public static final String FLD_ROOT_ID="root_id";

  /**
 * 根ID root_id
 */
  public static final Integer IDX_ROOT_ID=7;
 /**
   * 字段root_id 根ID
   */
	@Column("root_id")
  @ApiField(value="根ID",example="")
  private Integer root_id;

  /**
   * 返回字段root_id 根ID的值.
   * @return root_id  根ID  int
  */
  public Integer getRoot_id() {
    return root_id;
  }

  /**
   * 设置字段root_id 根ID的值.
   * @param root_id  根ID  int
   */
  public void setRoot_id(Integer root_id) {
    this.root_id=root_id;
  }

  /**
   * 元数据描述 description
 */
  public static final String FLD_DESCRIPTION="description";

  /**
 * 元数据描述 description
 */
  public static final Integer IDX_DESCRIPTION=8;
 /**
   * 字段description 元数据描述
   */
	@Column("description")
  @ApiField(value="元数据描述",example="")
  private String description;

  /**
   * 返回字段description 元数据描述的值.
   * @return description  元数据描述  varchar
  */
  public String getDescription() {
    return description;
  }

  /**
   * 设置字段description 元数据描述的值.
   * @param description  元数据描述  varchar
   */
  public void setDescription(String description) {
    this.description=description;
  }

}
