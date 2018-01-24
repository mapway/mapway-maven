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
 * 数据库表 元数据_量测信息<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  Integer id //<br/>
 *  String name //量测名字-比如温度<br/>
 *  String description //描述<br/>
 *  String catalog //分类/泛能机/锅炉<br/>
 *  String cim_name //CIM规范的名字比如TMP温度<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtName;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 *  {@literal @ }UiField TextBoxEx txtCatalog;<br/>
 *  {@literal @ }UiField TextBoxEx txtCim_name;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;量测名字-比如温度&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtName" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;描述&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;分类/泛能机/锅炉&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCatalog" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;CIM规范的名字比如TMP温度&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCim_name" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setName(txtName.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * data.setCatalog(txtCatalog.getValue());<br/>
 * data.setCim_name(txtCim_name.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtName.setValue(obj.getName());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 * txtCatalog.setValue(obj.getCatalog());<br/>
 * txtCim_name.setValue(obj.getCim_name());<br/>
 */

@Table("cim_meta_metric")
@Doc("cim_meta_metric(元数据_量测信息)")
public class CIM_META_METRICObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表元数据_量测信息名称. 
     */
  public static final String TBL_CIM_META_METRIC="cim_meta_metric";
  public CIM_META_METRICObj() {
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
      if (FLD_NAME.equals(fieldName)) {
        return name;
      }
      if (FLD_DESCRIPTION.equals(fieldName)) {
        return description;
      }
      if (FLD_CATALOG.equals(fieldName)) {
        return catalog;
      }
      if (FLD_CIM_NAME.equals(fieldName)) {
        return cim_name;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 5) {
      if (fieldIndex == 0) {
        return id;
      }
      if (fieldIndex == 1) {
        return name;
      }
      if (fieldIndex == 2) {
        return description;
      }
      if (fieldIndex == 3) {
        return catalog;
      }
      if (fieldIndex == 4) {
        return cim_name;
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
	@Id(auto = false)
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
   * 量测名字-比如温度 name
 */
  public static final String FLD_NAME="name";

  /**
 * 量测名字-比如温度 name
 */
  public static final Integer IDX_NAME=1;
 /**
   * 字段name 量测名字-比如温度
   */
	@Column("name")
  @ApiField(value="量测名字-比如温度",example="")
  private String name;

  /**
   * 返回字段name 量测名字-比如温度的值.
   * @return name  量测名字-比如温度  varchar
  */
  public String getName() {
    return name;
  }

  /**
   * 设置字段name 量测名字-比如温度的值.
   * @param name  量测名字-比如温度  varchar
   */
  public void setName(String name) {
    this.name=name;
  }

  /**
   * 描述 description
 */
  public static final String FLD_DESCRIPTION="description";

  /**
 * 描述 description
 */
  public static final Integer IDX_DESCRIPTION=2;
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

  /**
   * 分类/泛能机/锅炉 catalog
 */
  public static final String FLD_CATALOG="catalog";

  /**
 * 分类/泛能机/锅炉 catalog
 */
  public static final Integer IDX_CATALOG=3;
 /**
   * 字段catalog 分类/泛能机/锅炉
   */
	@Column("catalog")
  @ApiField(value="分类/泛能机/锅炉",example="")
  private String catalog;

  /**
   * 返回字段catalog 分类/泛能机/锅炉的值.
   * @return catalog  分类/泛能机/锅炉  varchar
  */
  public String getCatalog() {
    return catalog;
  }

  /**
   * 设置字段catalog 分类/泛能机/锅炉的值.
   * @param catalog  分类/泛能机/锅炉  varchar
   */
  public void setCatalog(String catalog) {
    this.catalog=catalog;
  }

  /**
   * CIM规范的名字比如TMP温度 cim_name
 */
  public static final String FLD_CIM_NAME="cim_name";

  /**
 * CIM规范的名字比如TMP温度 cim_name
 */
  public static final Integer IDX_CIM_NAME=4;
 /**
   * 字段cim_name CIM规范的名字比如TMP温度
   */
	@Column("cim_name")
  @ApiField(value="CIM规范的名字比如TMP温度",example="")
  private String cim_name;

  /**
   * 返回字段cim_name CIM规范的名字比如TMP温度的值.
   * @return cim_name  CIM规范的名字比如TMP温度  varchar
  */
  public String getCim_name() {
    return cim_name;
  }

  /**
   * 设置字段cim_name CIM规范的名字比如TMP温度的值.
   * @param cim_name  CIM规范的名字比如TMP温度  varchar
   */
  public void setCim_name(String cim_name) {
    this.cim_name=cim_name;
  }

}
