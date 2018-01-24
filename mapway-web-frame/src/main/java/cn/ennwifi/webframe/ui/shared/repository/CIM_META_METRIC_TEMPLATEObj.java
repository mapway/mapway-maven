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
 * 数据库表 元数据_设备类型_量测模板<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  Integer device_type_id //<br/>
 *  String alias //<br/>
 *  String description //<br/>
 *  String type //根据不同的量测类型，填写不同的字段<br/>
 *  String point_id //<br/>
 *  String com_port //<br/>
 *  Integer com_fre //<br/>
 *  Integer reg_start //<br/>
 *  String metric_path //<br/>
 *  Integer reg_length //<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtAlias;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 *  {@literal @ }UiField TextBoxEx txtType;<br/>
 *  {@literal @ }UiField TextBoxEx txtPoint_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtCom_port;<br/>
 *  {@literal @ }UiField TextBoxEx txtMetric_path;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtAlias" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;根据不同的量测类型，填写不同的字段&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtType" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtPoint_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCom_port" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtMetric_path" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setAlias(txtAlias.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * data.setType(txtType.getValue());<br/>
 * data.setPoint_id(txtPoint_id.getValue());<br/>
 * data.setCom_port(txtCom_port.getValue());<br/>
 * data.setMetric_path(txtMetric_path.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtAlias.setValue(obj.getAlias());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 * txtType.setValue(obj.getType());<br/>
 * txtPoint_id.setValue(obj.getPoint_id());<br/>
 * txtCom_port.setValue(obj.getCom_port());<br/>
 * txtMetric_path.setValue(obj.getMetric_path());<br/>
 */

@Table("cim_meta_metric_template")
@Doc("cim_meta_metric_template(元数据_设备类型_量测模板)")
public class CIM_META_METRIC_TEMPLATEObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表元数据_设备类型_量测模板名称. 
     */
  public static final String TBL_CIM_META_METRIC_TEMPLATE="cim_meta_metric_template";
  public CIM_META_METRIC_TEMPLATEObj() {
  }
  /**
   * 根据字段名称获取字段的值. 
   */
  @Override
  public Object getFieldValue(String fieldName,Integer fieldIndex) {
    if (fieldName != null && fieldName.length() > 0) {
      if (FLD_DEVICE_TYPE_ID.equals(fieldName)) {
        return device_type_id;
      }
      if (FLD_ALIAS.equals(fieldName)) {
        return alias;
      }
      if (FLD_DESCRIPTION.equals(fieldName)) {
        return description;
      }
      if (FLD_TYPE.equals(fieldName)) {
        return type;
      }
      if (FLD_POINT_ID.equals(fieldName)) {
        return point_id;
      }
      if (FLD_COM_PORT.equals(fieldName)) {
        return com_port;
      }
      if (FLD_COM_FRE.equals(fieldName)) {
        return com_fre;
      }
      if (FLD_REG_START.equals(fieldName)) {
        return reg_start;
      }
      if (FLD_METRIC_PATH.equals(fieldName)) {
        return metric_path;
      }
      if (FLD_REG_LENGTH.equals(fieldName)) {
        return reg_length;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 10) {
      if (fieldIndex == 0) {
        return device_type_id;
      }
      if (fieldIndex == 1) {
        return alias;
      }
      if (fieldIndex == 2) {
        return description;
      }
      if (fieldIndex == 3) {
        return type;
      }
      if (fieldIndex == 4) {
        return point_id;
      }
      if (fieldIndex == 5) {
        return com_port;
      }
      if (fieldIndex == 6) {
        return com_fre;
      }
      if (fieldIndex == 7) {
        return reg_start;
      }
      if (fieldIndex == 8) {
        return metric_path;
      }
      if (fieldIndex == 9) {
        return reg_length;
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   *  device_type_id
 */
  public static final String FLD_DEVICE_TYPE_ID="device_type_id";

  /**
 *  device_type_id
 */
  public static final Integer IDX_DEVICE_TYPE_ID=0;
 /**
   * 字段device_type_id 
   */
	@Column("device_type_id")
  @ApiField(value="",example="")
  private Integer device_type_id;

  /**
   * 返回字段device_type_id 的值.
   * @return device_type_id    int
  */
  public Integer getDevice_type_id() {
    return device_type_id;
  }

  /**
   * 设置字段device_type_id 的值.
   * @param device_type_id    int
   */
  public void setDevice_type_id(Integer device_type_id) {
    this.device_type_id=device_type_id;
  }

  /**
   *  alias
 */
  public static final String FLD_ALIAS="alias";

  /**
 *  alias
 */
  public static final Integer IDX_ALIAS=1;
 /**
   * 字段alias 
   */
	@Column("alias")
  @ApiField(value="",example="")
  private String alias;

  /**
   * 返回字段alias 的值.
   * @return alias    text
  */
  public String getAlias() {
    return alias;
  }

  /**
   * 设置字段alias 的值.
   * @param alias    text
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
  public static final Integer IDX_DESCRIPTION=2;
 /**
   * 字段description 
   */
	@Column("description")
  @ApiField(value="",example="")
  private String description;

  /**
   * 返回字段description 的值.
   * @return description    text
  */
  public String getDescription() {
    return description;
  }

  /**
   * 设置字段description 的值.
   * @param description    text
   */
  public void setDescription(String description) {
    this.description=description;
  }

  /**
   * 根据不同的量测类型，填写不同的字段 type
 */
  public static final String FLD_TYPE="type";

  /**
 * 根据不同的量测类型，填写不同的字段 type
 */
  public static final Integer IDX_TYPE=3;
 /**
   * 字段type 根据不同的量测类型，填写不同的字段
   */
	@Column("type")
  @ApiField(value="根据不同的量测类型，填写不同的字段",example="")
  private String type;

  /**
   * 返回字段type 根据不同的量测类型，填写不同的字段的值.
   * @return type  根据不同的量测类型，填写不同的字段  text
  */
  public String getType() {
    return type;
  }

  /**
   * 设置字段type 根据不同的量测类型，填写不同的字段的值.
   * @param type  根据不同的量测类型，填写不同的字段  text
   */
  public void setType(String type) {
    this.type=type;
  }

  /**
   *  point_id
 */
  public static final String FLD_POINT_ID="point_id";

  /**
 *  point_id
 */
  public static final Integer IDX_POINT_ID=4;
 /**
   * 字段point_id 
   */
	@Column("point_id")
  @ApiField(value="",example="")
  private String point_id;

  /**
   * 返回字段point_id 的值.
   * @return point_id    text
  */
  public String getPoint_id() {
    return point_id;
  }

  /**
   * 设置字段point_id 的值.
   * @param point_id    text
   */
  public void setPoint_id(String point_id) {
    this.point_id=point_id;
  }

  /**
   *  com_port
 */
  public static final String FLD_COM_PORT="com_port";

  /**
 *  com_port
 */
  public static final Integer IDX_COM_PORT=5;
 /**
   * 字段com_port 
   */
	@Column("com_port")
  @ApiField(value="",example="")
  private String com_port;

  /**
   * 返回字段com_port 的值.
   * @return com_port    text
  */
  public String getCom_port() {
    return com_port;
  }

  /**
   * 设置字段com_port 的值.
   * @param com_port    text
   */
  public void setCom_port(String com_port) {
    this.com_port=com_port;
  }

  /**
   *  com_fre
 */
  public static final String FLD_COM_FRE="com_fre";

  /**
 *  com_fre
 */
  public static final Integer IDX_COM_FRE=6;
 /**
   * 字段com_fre 
   */
	@Column("com_fre")
  @ApiField(value="",example="")
  private Integer com_fre;

  /**
   * 返回字段com_fre 的值.
   * @return com_fre    int
  */
  public Integer getCom_fre() {
    return com_fre;
  }

  /**
   * 设置字段com_fre 的值.
   * @param com_fre    int
   */
  public void setCom_fre(Integer com_fre) {
    this.com_fre=com_fre;
  }

  /**
   *  reg_start
 */
  public static final String FLD_REG_START="reg_start";

  /**
 *  reg_start
 */
  public static final Integer IDX_REG_START=7;
 /**
   * 字段reg_start 
   */
	@Column("reg_start")
  @ApiField(value="",example="")
  private Integer reg_start;

  /**
   * 返回字段reg_start 的值.
   * @return reg_start    int
  */
  public Integer getReg_start() {
    return reg_start;
  }

  /**
   * 设置字段reg_start 的值.
   * @param reg_start    int
   */
  public void setReg_start(Integer reg_start) {
    this.reg_start=reg_start;
  }

  /**
   *  metric_path
 */
  public static final String FLD_METRIC_PATH="metric_path";

  /**
 *  metric_path
 */
  public static final Integer IDX_METRIC_PATH=8;
 /**
   * 字段metric_path 
   */
	@Column("metric_path")
  @ApiField(value="",example="")
  private String metric_path;

  /**
   * 返回字段metric_path 的值.
   * @return metric_path    text
  */
  public String getMetric_path() {
    return metric_path;
  }

  /**
   * 设置字段metric_path 的值.
   * @param metric_path    text
   */
  public void setMetric_path(String metric_path) {
    this.metric_path=metric_path;
  }

  /**
   *  reg_length
 */
  public static final String FLD_REG_LENGTH="reg_length";

  /**
 *  reg_length
 */
  public static final Integer IDX_REG_LENGTH=9;
 /**
   * 字段reg_length 
   */
	@Column("reg_length")
  @ApiField(value="",example="")
  private Integer reg_length;

  /**
   * 返回字段reg_length 的值.
   * @return reg_length    int
  */
  public Integer getReg_length() {
    return reg_length;
  }

  /**
   * 设置字段reg_length 的值.
   * @param reg_length    int
   */
  public void setReg_length(Integer reg_length) {
    this.reg_length=reg_length;
  }

}
