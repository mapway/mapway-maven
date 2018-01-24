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
 *  String cim_dev_id //设备标识<br/>
 *  String attr_name //属性名<br/>
 *  String value //属性值<br/>
 *  Long ctime //创建时间<br/>
 *  String catalog //登记<br/>
 *  Long rank //等级<br/>
 *  String description //描述<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtCim_dev_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtAttr_name;<br/>
 *  {@literal @ }UiField TextBoxEx txtValue;<br/>
 *  {@literal @ }UiField TextBoxEx txtCatalog;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;设备标识&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCim_dev_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;属性名&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtAttr_name" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;属性值&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtValue" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;登记&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCatalog" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;描述&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setCim_dev_id(txtCim_dev_id.getValue());<br/>
 * data.setAttr_name(txtAttr_name.getValue());<br/>
 * data.setValue(txtValue.getValue());<br/>
 * data.setCatalog(txtCatalog.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtCim_dev_id.setValue(obj.getCim_dev_id());<br/>
 * txtAttr_name.setValue(obj.getAttr_name());<br/>
 * txtValue.setValue(obj.getValue());<br/>
 * txtCatalog.setValue(obj.getCatalog());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 */

@Table("device_attr")
@Doc("device_attr()")
@PK({"cim_dev_id","attr_name"})
public class DEVICE_ATTRObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表名称. 
     */
  public static final String TBL_DEVICE_ATTR="device_attr";
  public DEVICE_ATTRObj() {
  }
  /**
   * 根据字段名称获取字段的值. 
   */
  @Override
  public Object getFieldValue(String fieldName,Integer fieldIndex) {
    if (fieldName != null && fieldName.length() > 0) {
      if (FLD_CIM_DEV_ID.equals(fieldName)) {
        return cim_dev_id;
      }
      if (FLD_ATTR_NAME.equals(fieldName)) {
        return attr_name;
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
        return cim_dev_id;
      }
      if (fieldIndex == 1) {
        return attr_name;
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
   * 设备标识 cim_dev_id
 */
  public static final String FLD_CIM_DEV_ID="cim_dev_id";

  /**
 * 设备标识 cim_dev_id
 */
  public static final Integer IDX_CIM_DEV_ID=0;
 /**
   * 字段cim_dev_id 设备标识
   */
	@Column("cim_dev_id")
  @ApiField(value="设备标识",example="")
  private String cim_dev_id;

  /**
   * 返回字段cim_dev_id 设备标识的值.
   * @return cim_dev_id  设备标识  varchar
  */
  public String getCim_dev_id() {
    return cim_dev_id;
  }

  /**
   * 设置字段cim_dev_id 设备标识的值.
   * @param cim_dev_id  设备标识  varchar
   */
  public void setCim_dev_id(String cim_dev_id) {
    this.cim_dev_id=cim_dev_id;
  }

  /**
   * 属性名 attr_name
 */
  public static final String FLD_ATTR_NAME="attr_name";

  /**
 * 属性名 attr_name
 */
  public static final Integer IDX_ATTR_NAME=1;
 /**
   * 字段attr_name 属性名
   */
	@Column("attr_name")
  @ApiField(value="属性名",example="")
  private String attr_name;

  /**
   * 返回字段attr_name 属性名的值.
   * @return attr_name  属性名  varchar
  */
  public String getAttr_name() {
    return attr_name;
  }

  /**
   * 设置字段attr_name 属性名的值.
   * @param attr_name  属性名  varchar
   */
  public void setAttr_name(String attr_name) {
    this.attr_name=attr_name;
  }

  /**
   * 属性值 value
 */
  public static final String FLD_VALUE="value";

  /**
 * 属性值 value
 */
  public static final Integer IDX_VALUE=2;
 /**
   * 字段value 属性值
   */
	@Column("value")
  @ApiField(value="属性值",example="")
  private String value;

  /**
   * 返回字段value 属性值的值.
   * @return value  属性值  varchar
  */
  public String getValue() {
    return value;
  }

  /**
   * 设置字段value 属性值的值.
   * @param value  属性值  varchar
   */
  public void setValue(String value) {
    this.value=value;
  }

  /**
   * 创建时间 ctime
 */
  public static final String FLD_CTIME="ctime";

  /**
 * 创建时间 ctime
 */
  public static final Integer IDX_CTIME=3;
 /**
   * 字段ctime 创建时间
   */
	@Column("ctime")
  @ApiField(value="创建时间",example="")
  private Long ctime;

  /**
   * 返回字段ctime 创建时间的值.
   * @return ctime  创建时间  bigint
  */
  public Long getCtime() {
    return ctime;
  }

  /**
   * 设置字段ctime 创建时间的值.
   * @param ctime  创建时间  bigint
   */
  public void setCtime(Long ctime) {
    this.ctime=ctime;
  }

  /**
   * 登记 catalog
 */
  public static final String FLD_CATALOG="catalog";

  /**
 * 登记 catalog
 */
  public static final Integer IDX_CATALOG=4;
 /**
   * 字段catalog 登记
   */
	@Column("catalog")
  @ApiField(value="登记",example="")
  private String catalog;

  /**
   * 返回字段catalog 登记的值.
   * @return catalog  登记  varchar
  */
  public String getCatalog() {
    return catalog;
  }

  /**
   * 设置字段catalog 登记的值.
   * @param catalog  登记  varchar
   */
  public void setCatalog(String catalog) {
    this.catalog=catalog;
  }

  /**
   * 等级 rank
 */
  public static final String FLD_RANK="rank";

  /**
 * 等级 rank
 */
  public static final Integer IDX_RANK=5;
 /**
   * 字段rank 等级
   */
	@Column("rank")
  @ApiField(value="等级",example="")
  private Long rank;

  /**
   * 返回字段rank 等级的值.
   * @return rank  等级  bigint
  */
  public Long getRank() {
    return rank;
  }

  /**
   * 设置字段rank 等级的值.
   * @param rank  等级  bigint
   */
  public void setRank(Long rank) {
    this.rank=rank;
  }

  /**
   * 描述 description
 */
  public static final String FLD_DESCRIPTION="description";

  /**
 * 描述 description
 */
  public static final Integer IDX_DESCRIPTION=6;
 /**
   * 字段description 描述
   */
	@Column("description")
  @ApiField(value="描述",example="")
  private String description;

  /**
   * 返回字段description 描述的值.
   * @return description  描述  varchar
  */
  public String getDescription() {
    return description;
  }

  /**
   * 设置字段description 描述的值.
   * @param description  描述  varchar
   */
  public void setDescription(String description) {
    this.description=description;
  }

}
