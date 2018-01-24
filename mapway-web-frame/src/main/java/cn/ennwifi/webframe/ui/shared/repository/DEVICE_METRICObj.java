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
 * 数据库表 设备量测定义表<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  String cim_id //<br/>
 *  String dev_cim_id //设备ID<br/>
 *  String alias //别名<br/>
 *  String description //描述<br/>
 *  String status //指标状态<br/>
 *  String name //CIM-名称<br/>
 *  Long ctime //创建时间<br/>
 *  Long utime //更新时间<br/>
 *  String cuser_id //创建人<br/>
 *  String uuser_id //<br/>
 *  String type //量测类型<br/>
 *  String point_id //连接点<br/>
 *  String com_port //端口<br/>
 *  Integer com_fre //波特率<br/>
 *  Integer reg_start //起始地址<br/>
 *  String metric_path //指标路径<br/>
 *  Integer reg_length //寄存器长度<br/>
 *  String data //指标数据<br/>
 *  String metric_id //量测id<br/>
 *  Long data_time //数据产生的时间<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtCim_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtDev_cim_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtAlias;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 *  {@literal @ }UiField TextBoxEx txtStatus;<br/>
 *  {@literal @ }UiField TextBoxEx txtName;<br/>
 *  {@literal @ }UiField TextBoxEx txtCuser_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtUuser_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtType;<br/>
 *  {@literal @ }UiField TextBoxEx txtPoint_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtCom_port;<br/>
 *  {@literal @ }UiField TextBoxEx txtMetric_path;<br/>
 *  {@literal @ }UiField TextBoxEx txtData;<br/>
 *  {@literal @ }UiField TextBoxEx txtMetric_id;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCim_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备ID&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDev_cim_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;别名&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtAlias" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;描述&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;指标状态&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtStatus" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;CIM-名称&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtName" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;创建人&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCuser_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtUuser_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;量测类型&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtType" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;连接点&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtPoint_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;端口&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCom_port" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;指标路径&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtMetric_path" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;指标数据&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtData" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;量测id&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtMetric_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setCim_id(txtCim_id.getValue());<br/>
 * data.setDev_cim_id(txtDev_cim_id.getValue());<br/>
 * data.setAlias(txtAlias.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * data.setStatus(txtStatus.getValue());<br/>
 * data.setName(txtName.getValue());<br/>
 * data.setCuser_id(txtCuser_id.getValue());<br/>
 * data.setUuser_id(txtUuser_id.getValue());<br/>
 * data.setType(txtType.getValue());<br/>
 * data.setPoint_id(txtPoint_id.getValue());<br/>
 * data.setCom_port(txtCom_port.getValue());<br/>
 * data.setMetric_path(txtMetric_path.getValue());<br/>
 * data.setData(txtData.getValue());<br/>
 * data.setMetric_id(txtMetric_id.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtCim_id.setValue(obj.getCim_id());<br/>
 * txtDev_cim_id.setValue(obj.getDev_cim_id());<br/>
 * txtAlias.setValue(obj.getAlias());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 * txtStatus.setValue(obj.getStatus());<br/>
 * txtName.setValue(obj.getName());<br/>
 * txtCuser_id.setValue(obj.getCuser_id());<br/>
 * txtUuser_id.setValue(obj.getUuser_id());<br/>
 * txtType.setValue(obj.getType());<br/>
 * txtPoint_id.setValue(obj.getPoint_id());<br/>
 * txtCom_port.setValue(obj.getCom_port());<br/>
 * txtMetric_path.setValue(obj.getMetric_path());<br/>
 * txtData.setValue(obj.getData());<br/>
 * txtMetric_id.setValue(obj.getMetric_id());<br/>
 */

@Table("device_metric")
@Doc("device_metric(设备量测定义表)")
public class DEVICE_METRICObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表设备量测定义表名称. 
     */
  public static final String TBL_DEVICE_METRIC="device_metric";
  public DEVICE_METRICObj() {
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
      if (FLD_DEV_CIM_ID.equals(fieldName)) {
        return dev_cim_id;
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
      if (FLD_DATA.equals(fieldName)) {
        return data;
      }
      if (FLD_METRIC_ID.equals(fieldName)) {
        return metric_id;
      }
      if (FLD_DATA_TIME.equals(fieldName)) {
        return data_time;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 20) {
      if (fieldIndex == 0) {
        return cim_id;
      }
      if (fieldIndex == 1) {
        return dev_cim_id;
      }
      if (fieldIndex == 2) {
        return alias;
      }
      if (fieldIndex == 3) {
        return description;
      }
      if (fieldIndex == 4) {
        return status;
      }
      if (fieldIndex == 5) {
        return name;
      }
      if (fieldIndex == 6) {
        return ctime;
      }
      if (fieldIndex == 7) {
        return utime;
      }
      if (fieldIndex == 8) {
        return cuser_id;
      }
      if (fieldIndex == 9) {
        return uuser_id;
      }
      if (fieldIndex == 10) {
        return type;
      }
      if (fieldIndex == 11) {
        return point_id;
      }
      if (fieldIndex == 12) {
        return com_port;
      }
      if (fieldIndex == 13) {
        return com_fre;
      }
      if (fieldIndex == 14) {
        return reg_start;
      }
      if (fieldIndex == 15) {
        return metric_path;
      }
      if (fieldIndex == 16) {
        return reg_length;
      }
      if (fieldIndex == 17) {
        return data;
      }
      if (fieldIndex == 18) {
        return metric_id;
      }
      if (fieldIndex == 19) {
        return data_time;
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   *  cim_id
 */
  public static final String FLD_CIM_ID="cim_id";

  /**
 *  cim_id
 */
  public static final Integer IDX_CIM_ID=0;
 /**
   * 字段cim_id 
   */
	@Name
  @ApiField(value="",example="")
  private String cim_id;

  /**
   * 返回字段cim_id 的值.
   * @return cim_id    varchar
  */
  public String getCim_id() {
    return cim_id;
  }

  /**
   * 设置字段cim_id 的值.
   * @param cim_id    varchar
   */
  public void setCim_id(String cim_id) {
    this.cim_id=cim_id;
  }

  /**
   * 设备ID dev_cim_id
 */
  public static final String FLD_DEV_CIM_ID="dev_cim_id";

  /**
 * 设备ID dev_cim_id
 */
  public static final Integer IDX_DEV_CIM_ID=1;
 /**
   * 字段dev_cim_id 设备ID
   */
	@Column("dev_cim_id")
  @ApiField(value="设备ID",example="")
  private String dev_cim_id;

  /**
   * 返回字段dev_cim_id 设备ID的值.
   * @return dev_cim_id  设备ID  varchar
  */
  public String getDev_cim_id() {
    return dev_cim_id;
  }

  /**
   * 设置字段dev_cim_id 设备ID的值.
   * @param dev_cim_id  设备ID  varchar
   */
  public void setDev_cim_id(String dev_cim_id) {
    this.dev_cim_id=dev_cim_id;
  }

  /**
   * 别名 alias
 */
  public static final String FLD_ALIAS="alias";

  /**
 * 别名 alias
 */
  public static final Integer IDX_ALIAS=2;
 /**
   * 字段alias 别名
   */
	@Column("alias")
  @ApiField(value="别名",example="")
  private String alias;

  /**
   * 返回字段alias 别名的值.
   * @return alias  别名  varchar
  */
  public String getAlias() {
    return alias;
  }

  /**
   * 设置字段alias 别名的值.
   * @param alias  别名  varchar
   */
  public void setAlias(String alias) {
    this.alias=alias;
  }

  /**
   * 描述 description
 */
  public static final String FLD_DESCRIPTION="description";

  /**
 * 描述 description
 */
  public static final Integer IDX_DESCRIPTION=3;
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
   * 指标状态 status
 */
  public static final String FLD_STATUS="status";

  /**
 * 指标状态 status
 */
  public static final Integer IDX_STATUS=4;
 /**
   * 字段status 指标状态
   */
	@Column("status")
  @ApiField(value="指标状态",example="")
  private String status;

  /**
   * 返回字段status 指标状态的值.
   * @return status  指标状态  char
  */
  public String getStatus() {
    return status;
  }

  /**
   * 设置字段status 指标状态的值.
   * @param status  指标状态  char
   */
  public void setStatus(String status) {
    this.status=status;
  }

  /**
   * CIM-名称 name
 */
  public static final String FLD_NAME="name";

  /**
 * CIM-名称 name
 */
  public static final Integer IDX_NAME=5;
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
   * 创建时间 ctime
 */
  public static final String FLD_CTIME="ctime";

  /**
 * 创建时间 ctime
 */
  public static final Integer IDX_CTIME=6;
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
   * 更新时间 utime
 */
  public static final String FLD_UTIME="utime";

  /**
 * 更新时间 utime
 */
  public static final Integer IDX_UTIME=7;
 /**
   * 字段utime 更新时间
   */
	@Column("utime")
  @ApiField(value="更新时间",example="")
  private Long utime;

  /**
   * 返回字段utime 更新时间的值.
   * @return utime  更新时间  bigint
  */
  public Long getUtime() {
    return utime;
  }

  /**
   * 设置字段utime 更新时间的值.
   * @param utime  更新时间  bigint
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
  public static final Integer IDX_CUSER_ID=8;
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
  public static final Integer IDX_UUSER_ID=9;
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
   * 量测类型 type
 */
  public static final String FLD_TYPE="type";

  /**
 * 量测类型 type
 */
  public static final Integer IDX_TYPE=10;
 /**
   * 字段type 量测类型
   */
	@Column("type")
  @ApiField(value="量测类型",example="")
  private String type;

  /**
   * 返回字段type 量测类型的值.
   * @return type  量测类型  varchar
  */
  public String getType() {
    return type;
  }

  /**
   * 设置字段type 量测类型的值.
   * @param type  量测类型  varchar
   */
  public void setType(String type) {
    this.type=type;
  }

  /**
   * 连接点 point_id
 */
  public static final String FLD_POINT_ID="point_id";

  /**
 * 连接点 point_id
 */
  public static final Integer IDX_POINT_ID=11;
 /**
   * 字段point_id 连接点
   */
	@Column("point_id")
  @ApiField(value="连接点",example="")
  private String point_id;

  /**
   * 返回字段point_id 连接点的值.
   * @return point_id  连接点  varchar
  */
  public String getPoint_id() {
    return point_id;
  }

  /**
   * 设置字段point_id 连接点的值.
   * @param point_id  连接点  varchar
   */
  public void setPoint_id(String point_id) {
    this.point_id=point_id;
  }

  /**
   * 端口 com_port
 */
  public static final String FLD_COM_PORT="com_port";

  /**
 * 端口 com_port
 */
  public static final Integer IDX_COM_PORT=12;
 /**
   * 字段com_port 端口
   */
	@Column("com_port")
  @ApiField(value="端口",example="")
  private String com_port;

  /**
   * 返回字段com_port 端口的值.
   * @return com_port  端口  varchar
  */
  public String getCom_port() {
    return com_port;
  }

  /**
   * 设置字段com_port 端口的值.
   * @param com_port  端口  varchar
   */
  public void setCom_port(String com_port) {
    this.com_port=com_port;
  }

  /**
   * 波特率 com_fre
 */
  public static final String FLD_COM_FRE="com_fre";

  /**
 * 波特率 com_fre
 */
  public static final Integer IDX_COM_FRE=13;
 /**
   * 字段com_fre 波特率
   */
	@Column("com_fre")
  @ApiField(value="波特率",example="")
  private Integer com_fre;

  /**
   * 返回字段com_fre 波特率的值.
   * @return com_fre  波特率  int
  */
  public Integer getCom_fre() {
    return com_fre;
  }

  /**
   * 设置字段com_fre 波特率的值.
   * @param com_fre  波特率  int
   */
  public void setCom_fre(Integer com_fre) {
    this.com_fre=com_fre;
  }

  /**
   * 起始地址 reg_start
 */
  public static final String FLD_REG_START="reg_start";

  /**
 * 起始地址 reg_start
 */
  public static final Integer IDX_REG_START=14;
 /**
   * 字段reg_start 起始地址
   */
	@Column("reg_start")
  @ApiField(value="起始地址",example="")
  private Integer reg_start;

  /**
   * 返回字段reg_start 起始地址的值.
   * @return reg_start  起始地址  int
  */
  public Integer getReg_start() {
    return reg_start;
  }

  /**
   * 设置字段reg_start 起始地址的值.
   * @param reg_start  起始地址  int
   */
  public void setReg_start(Integer reg_start) {
    this.reg_start=reg_start;
  }

  /**
   * 指标路径 metric_path
 */
  public static final String FLD_METRIC_PATH="metric_path";

  /**
 * 指标路径 metric_path
 */
  public static final Integer IDX_METRIC_PATH=15;
 /**
   * 字段metric_path 指标路径
   */
	@Column("metric_path")
  @ApiField(value="指标路径",example="")
  private String metric_path;

  /**
   * 返回字段metric_path 指标路径的值.
   * @return metric_path  指标路径  varchar
  */
  public String getMetric_path() {
    return metric_path;
  }

  /**
   * 设置字段metric_path 指标路径的值.
   * @param metric_path  指标路径  varchar
   */
  public void setMetric_path(String metric_path) {
    this.metric_path=metric_path;
  }

  /**
   * 寄存器长度 reg_length
 */
  public static final String FLD_REG_LENGTH="reg_length";

  /**
 * 寄存器长度 reg_length
 */
  public static final Integer IDX_REG_LENGTH=16;
 /**
   * 字段reg_length 寄存器长度
   */
	@Column("reg_length")
  @ApiField(value="寄存器长度",example="")
  private Integer reg_length;

  /**
   * 返回字段reg_length 寄存器长度的值.
   * @return reg_length  寄存器长度  int
  */
  public Integer getReg_length() {
    return reg_length;
  }

  /**
   * 设置字段reg_length 寄存器长度的值.
   * @param reg_length  寄存器长度  int
   */
  public void setReg_length(Integer reg_length) {
    this.reg_length=reg_length;
  }

  /**
   * 指标数据 data
 */
  public static final String FLD_DATA="data";

  /**
 * 指标数据 data
 */
  public static final Integer IDX_DATA=17;
 /**
   * 字段data 指标数据
   */
	@Column("data")
  @ApiField(value="指标数据",example="")
  private String data;

  /**
   * 返回字段data 指标数据的值.
   * @return data  指标数据  varchar
  */
  public String getData() {
    return data;
  }

  /**
   * 设置字段data 指标数据的值.
   * @param data  指标数据  varchar
   */
  public void setData(String data) {
    this.data=data;
  }

  /**
   * 量测id metric_id
 */
  public static final String FLD_METRIC_ID="metric_id";

  /**
 * 量测id metric_id
 */
  public static final Integer IDX_METRIC_ID=18;
 /**
   * 字段metric_id 量测id
   */
	@Column("metric_id")
  @ApiField(value="量测id",example="")
  private String metric_id;

  /**
   * 返回字段metric_id 量测id的值.
   * @return metric_id  量测id  varchar
  */
  public String getMetric_id() {
    return metric_id;
  }

  /**
   * 设置字段metric_id 量测id的值.
   * @param metric_id  量测id  varchar
   */
  public void setMetric_id(String metric_id) {
    this.metric_id=metric_id;
  }

  /**
   * 数据产生的时间 data_time
 */
  public static final String FLD_DATA_TIME="data_time";

  /**
 * 数据产生的时间 data_time
 */
  public static final Integer IDX_DATA_TIME=19;
 /**
   * 字段data_time 数据产生的时间
   */
	@Column("data_time")
  @ApiField(value="数据产生的时间",example="")
  private Long data_time;

  /**
   * 返回字段data_time 数据产生的时间的值.
   * @return data_time  数据产生的时间  bigint
  */
  public Long getData_time() {
    return data_time;
  }

  /**
   * 设置字段data_time 数据产生的时间的值.
   * @param data_time  数据产生的时间  bigint
   */
  public void setData_time(Long data_time) {
    this.data_time=data_time;
  }

}
