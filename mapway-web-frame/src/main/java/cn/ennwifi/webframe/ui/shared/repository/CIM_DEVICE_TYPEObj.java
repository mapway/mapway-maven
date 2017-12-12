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
 * 数据库表 元数据_设备类型<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  Integer id //设备类型ID<br/>
 *  String name //设备类型<br/>
 *  String code //设备类型代码<br/>
 *  String description //描述信息<br/>
 *  String catalog //设备类型分类<br/>
 *  String icon //设备类型图标<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtName;<br/>
 *  {@literal @ }UiField TextBoxEx txtCode;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 *  {@literal @ }UiField TextBoxEx txtCatalog;<br/>
 *  {@literal @ }UiField TextBoxEx txtIcon;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;设备类型&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtName" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备类型代码&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCode" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;描述信息&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备类型分类&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCatalog" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备类型图标&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtIcon" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setName(txtName.getValue());<br/>
 * data.setCode(txtCode.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * data.setCatalog(txtCatalog.getValue());<br/>
 * data.setIcon(txtIcon.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtName.setValue(obj.getName());<br/>
 * txtCode.setValue(obj.getCode());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 * txtCatalog.setValue(obj.getCatalog());<br/>
 * txtIcon.setValue(obj.getIcon());<br/>
 */

@Table("cim_device_type")
@Doc("cim_device_type(元数据_设备类型)")
public class CIM_DEVICE_TYPEObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表元数据_设备类型名称. 
     */
  public static final String TBL_CIM_DEVICE_TYPE="cim_device_type";
  public CIM_DEVICE_TYPEObj() {
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
      if (FLD_CODE.equals(fieldName)) {
        return code;
      }
      if (FLD_DESCRIPTION.equals(fieldName)) {
        return description;
      }
      if (FLD_CATALOG.equals(fieldName)) {
        return catalog;
      }
      if (FLD_ICON.equals(fieldName)) {
        return icon;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 6) {
      if (fieldIndex == 0) {
        return id;
      }
      if (fieldIndex == 1) {
        return name;
      }
      if (fieldIndex == 2) {
        return code;
      }
      if (fieldIndex == 3) {
        return description;
      }
      if (fieldIndex == 4) {
        return catalog;
      }
      if (fieldIndex == 5) {
        return icon;
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   * 设备类型ID id
 */
  public static final String FLD_ID="id";

  /**
 * 设备类型ID id
 */
  public static final Integer IDX_ID=0;
 /**
   * 字段id 设备类型ID
   */
	@Id
  @ApiField(value="设备类型ID",example="")
  private Integer id;

  /**
   * 返回字段id 设备类型ID的值.
   * @return id  设备类型ID  int
  */
  public Integer getId() {
    return id;
  }

  /**
   * 设置字段id 设备类型ID的值.
   * @param id  设备类型ID  int
   */
  public void setId(Integer id) {
    this.id=id;
  }

  /**
   * 设备类型 name
 */
  public static final String FLD_NAME="name";

  /**
 * 设备类型 name
 */
  public static final Integer IDX_NAME=1;
 /**
   * 字段name 设备类型
   */
	@Column("name")
  @ApiField(value="设备类型",example="")
  private String name;

  /**
   * 返回字段name 设备类型的值.
   * @return name  设备类型  varchar
  */
  public String getName() {
    return name;
  }

  /**
   * 设置字段name 设备类型的值.
   * @param name  设备类型  varchar
   */
  public void setName(String name) {
    this.name=name;
  }

  /**
   * 设备类型代码 code
 */
  public static final String FLD_CODE="code";

  /**
 * 设备类型代码 code
 */
  public static final Integer IDX_CODE=2;
 /**
   * 字段code 设备类型代码
   */
	@Column("code")
  @ApiField(value="设备类型代码",example="")
  private String code;

  /**
   * 返回字段code 设备类型代码的值.
   * @return code  设备类型代码  varchar
  */
  public String getCode() {
    return code;
  }

  /**
   * 设置字段code 设备类型代码的值.
   * @param code  设备类型代码  varchar
   */
  public void setCode(String code) {
    this.code=code;
  }

  /**
   * 描述信息 description
 */
  public static final String FLD_DESCRIPTION="description";

  /**
 * 描述信息 description
 */
  public static final Integer IDX_DESCRIPTION=3;
 /**
   * 字段description 描述信息
   */
	@Column("description")
  @ApiField(value="描述信息",example="")
  private String description;

  /**
   * 返回字段description 描述信息的值.
   * @return description  描述信息  varchar
  */
  public String getDescription() {
    return description;
  }

  /**
   * 设置字段description 描述信息的值.
   * @param description  描述信息  varchar
   */
  public void setDescription(String description) {
    this.description=description;
  }

  /**
   * 设备类型分类 catalog
 */
  public static final String FLD_CATALOG="catalog";

  /**
 * 设备类型分类 catalog
 */
  public static final Integer IDX_CATALOG=4;
 /**
   * 字段catalog 设备类型分类
   */
	@Column("catalog")
  @ApiField(value="设备类型分类",example="")
  private String catalog;

  /**
   * 返回字段catalog 设备类型分类的值.
   * @return catalog  设备类型分类  varchar
  */
  public String getCatalog() {
    return catalog;
  }

  /**
   * 设置字段catalog 设备类型分类的值.
   * @param catalog  设备类型分类  varchar
   */
  public void setCatalog(String catalog) {
    this.catalog=catalog;
  }

  /**
   * 设备类型图标 icon
 */
  public static final String FLD_ICON="icon";

  /**
 * 设备类型图标 icon
 */
  public static final Integer IDX_ICON=5;
 /**
   * 字段icon 设备类型图标
   */
	@Column("icon")
  @ApiField(value="设备类型图标",example="")
  private String icon;

  /**
   * 返回字段icon 设备类型图标的值.
   * @return icon  设备类型图标  varchar
  */
  public String getIcon() {
    return icon;
  }

  /**
   * 设置字段icon 设备类型图标的值.
   * @param icon  设备类型图标  varchar
   */
  public void setIcon(String icon) {
    this.icon=icon;
  }

}
