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
 *  String cim_con_id //<br/>
 *  String name //<br/>
 *  String value //<br/>
 *  Long ctime //<br/>
 *  String catalog //<br/>
 *  Long rank //<br/>
 *  String description //<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtCim_con_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtName;<br/>
 *  {@literal @ }UiField TextBoxEx txtValue;<br/>
 *  {@literal @ }UiField TextBoxEx txtCatalog;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCim_con_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtName" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtValue" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCatalog" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setCim_con_id(txtCim_con_id.getValue());<br/>
 * data.setName(txtName.getValue());<br/>
 * data.setValue(txtValue.getValue());<br/>
 * data.setCatalog(txtCatalog.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtCim_con_id.setValue(obj.getCim_con_id());<br/>
 * txtName.setValue(obj.getName());<br/>
 * txtValue.setValue(obj.getValue());<br/>
 * txtCatalog.setValue(obj.getCatalog());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 */

@Table("container_attr")
@Doc("container_attr()")
@PK({"cim_con_id","name"})
public class CONTAINER_ATTRObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表名称. 
     */
  public static final String TBL_CONTAINER_ATTR="container_attr";
  public CONTAINER_ATTRObj() {
  }
  /**
   * 根据字段名称获取字段的值. 
   */
  @Override
  public Object getFieldValue(String fieldName,Integer fieldIndex) {
    if (fieldName != null && fieldName.length() > 0) {
      if (FLD_CIM_CON_ID.equals(fieldName)) {
        return cim_con_id;
      }
      if (FLD_NAME.equals(fieldName)) {
        return name;
      }
      if (FLD_VALUE.equals(fieldName)) {
        return value;
      }
      if (FLD_CTIME.equals(fieldName)) {
        return ctime;
      }
      if (FLD_CATALOG.equals(fieldName)) {
        return catalog;
      }
      if (FLD_RANK.equals(fieldName)) {
        return rank;
      }
      if (FLD_DESCRIPTION.equals(fieldName)) {
        return description;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 7) {
      if (fieldIndex == 0) {
        return cim_con_id;
      }
      if (fieldIndex == 1) {
        return name;
      }
      if (fieldIndex == 2) {
        return value;
      }
      if (fieldIndex == 3) {
        return ctime;
      }
      if (fieldIndex == 4) {
        return catalog;
      }
      if (fieldIndex == 5) {
        return rank;
      }
      if (fieldIndex == 6) {
        return description;
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   *  cim_con_id
 */
  public static final String FLD_CIM_CON_ID="cim_con_id";

  /**
 *  cim_con_id
 */
  public static final Integer IDX_CIM_CON_ID=0;
 /**
   * 字段cim_con_id 
   */
	@Column("cim_con_id")
  @ApiField(value="",example="")
  private String cim_con_id;

  /**
   * 返回字段cim_con_id 的值.
   * @return cim_con_id    varchar
  */
  public String getCim_con_id() {
    return cim_con_id;
  }

  /**
   * 设置字段cim_con_id 的值.
   * @param cim_con_id    varchar
   */
  public void setCim_con_id(String cim_con_id) {
    this.cim_con_id=cim_con_id;
  }

  /**
   *  name
 */
  public static final String FLD_NAME="name";

  /**
 *  name
 */
  public static final Integer IDX_NAME=1;
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
   *  value
 */
  public static final String FLD_VALUE="value";

  /**
 *  value
 */
  public static final Integer IDX_VALUE=2;
 /**
   * 字段value 
   */
	@Column("value")
  @ApiField(value="",example="")
  private String value;

  /**
   * 返回字段value 的值.
   * @return value    varchar
  */
  public String getValue() {
    return value;
  }

  /**
   * 设置字段value 的值.
   * @param value    varchar
   */
  public void setValue(String value) {
    this.value=value;
  }

  /**
   *  ctime
 */
  public static final String FLD_CTIME="ctime";

  /**
 *  ctime
 */
  public static final Integer IDX_CTIME=3;
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
   *  catalog
 */
  public static final String FLD_CATALOG="catalog";

  /**
 *  catalog
 */
  public static final Integer IDX_CATALOG=4;
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
   *  rank
 */
  public static final String FLD_RANK="rank";

  /**
 *  rank
 */
  public static final Integer IDX_RANK=5;
 /**
   * 字段rank 
   */
	@Column("rank")
  @ApiField(value="",example="")
  private Long rank;

  /**
   * 返回字段rank 的值.
   * @return rank    bigint
  */
  public Long getRank() {
    return rank;
  }

  /**
   * 设置字段rank 的值.
   * @param rank    bigint
   */
  public void setRank(Long rank) {
    this.rank=rank;
  }

  /**
   *  description
 */
  public static final String FLD_DESCRIPTION="description";

  /**
 *  description
 */
  public static final Integer IDX_DESCRIPTION=6;
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

}
