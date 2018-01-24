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
 * 数据库表 设备信息<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  String cim_id //CIMID<br/>
 *  String con_cim_id //容器CIMID<br/>
 *  String name //CIM-名称<br/>
 *  Long ctime //创建时间<br/>
 *  Long utime //更新时间<br/>
 *  String cuser_id //创建人<br/>
 *  String uuser_id //更信任ID<br/>
 *  String alias //设备别名<br/>
 *  String description //描述<br/>
 *  String status //状态<br/>
 *  String type //设备类型<br/>
 *  String sno //设备序列号<br/>
 *  String kks //kks编码<br/>
 *  String path //设备路径<br/>
 *  String container_id //容器id<br/>
 *  String device_id //设备id<br/>
 * =========== 用于GWT模型 <br/>
 *  {@literal @ }UiField TextBoxEx txtCim_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtCon_cim_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtName;<br/>
 *  {@literal @ }UiField TextBoxEx txtCuser_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtUuser_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtAlias;<br/>
 *  {@literal @ }UiField TextBoxEx txtDescription;<br/>
 *  {@literal @ }UiField TextBoxEx txtStatus;<br/>
 *  {@literal @ }UiField TextBoxEx txtType;<br/>
 *  {@literal @ }UiField TextBoxEx txtSno;<br/>
 *  {@literal @ }UiField TextBoxEx txtKks;<br/>
 *  {@literal @ }UiField TextBoxEx txtPath;<br/>
 *  {@literal @ }UiField TextBoxEx txtContainer_id;<br/>
 *  {@literal @ }UiField TextBoxEx txtDevice_id;<br/>
 * =========== 用于GWT UIBinder <br/>
 *  &lt;tr&gt;&lt;td&gt;CIMID&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCim_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;容器CIMID&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCon_cim_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;CIM-名称&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtName" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;创建人&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtCuser_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;更信任ID&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtUuser_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备别名&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtAlias" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;描述&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDescription" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;状态&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtStatus" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备类型&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtType" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备序列号&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtSno" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;kks编码&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtKks" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备路径&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtPath" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;容器id&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtContainer_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 *  &lt;tr&gt;&lt;td&gt;设备id&lt;/td&gt;&lt;td&gt;&ltc:TextBoxEx ui:field="txtDevice_id" &gt;&lt/c:TextBoxEx&gt;&lt;/td&gt; &lt;td&gt;&lt;/td&gt; &lt;/tr&gt;<br/>
 * =========== 用于fromUI <br/>
 * data.setCim_id(txtCim_id.getValue());<br/>
 * data.setCon_cim_id(txtCon_cim_id.getValue());<br/>
 * data.setName(txtName.getValue());<br/>
 * data.setCuser_id(txtCuser_id.getValue());<br/>
 * data.setUuser_id(txtUuser_id.getValue());<br/>
 * data.setAlias(txtAlias.getValue());<br/>
 * data.setDescription(txtDescription.getValue());<br/>
 * data.setStatus(txtStatus.getValue());<br/>
 * data.setType(txtType.getValue());<br/>
 * data.setSno(txtSno.getValue());<br/>
 * data.setKks(txtKks.getValue());<br/>
 * data.setPath(txtPath.getValue());<br/>
 * data.setContainer_id(txtContainer_id.getValue());<br/>
 * data.setDevice_id(txtDevice_id.getValue());<br/>
 * =========== 用于toUI <br/>
 * txtCim_id.setValue(obj.getCim_id());<br/>
 * txtCon_cim_id.setValue(obj.getCon_cim_id());<br/>
 * txtName.setValue(obj.getName());<br/>
 * txtCuser_id.setValue(obj.getCuser_id());<br/>
 * txtUuser_id.setValue(obj.getUuser_id());<br/>
 * txtAlias.setValue(obj.getAlias());<br/>
 * txtDescription.setValue(obj.getDescription());<br/>
 * txtStatus.setValue(obj.getStatus());<br/>
 * txtType.setValue(obj.getType());<br/>
 * txtSno.setValue(obj.getSno());<br/>
 * txtKks.setValue(obj.getKks());<br/>
 * txtPath.setValue(obj.getPath());<br/>
 * txtContainer_id.setValue(obj.getContainer_id());<br/>
 * txtDevice_id.setValue(obj.getDevice_id());<br/>
 */

@Table("device")
@Doc("device(设备信息)")
public class DEVICEObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

  /**
  * 表设备信息名称. 
     */
  public static final String TBL_DEVICE="device";
  public DEVICEObj() {
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
      if (FLD_CON_CIM_ID.equals(fieldName)) {
        return con_cim_id;
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
      if (FLD_SNO.equals(fieldName)) {
        return sno;
      }
      if (FLD_KKS.equals(fieldName)) {
        return kks;
      }
      if (FLD_PATH.equals(fieldName)) {
        return path;
      }
      if (FLD_CONTAINER_ID.equals(fieldName)) {
        return container_id;
      }
      if (FLD_DEVICE_ID.equals(fieldName)) {
        return device_id;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 16) {
      if (fieldIndex == 0) {
        return cim_id;
      }
      if (fieldIndex == 1) {
        return con_cim_id;
      }
      if (fieldIndex == 2) {
        return name;
      }
      if (fieldIndex == 3) {
        return ctime;
      }
      if (fieldIndex == 4) {
        return utime;
      }
      if (fieldIndex == 5) {
        return cuser_id;
      }
      if (fieldIndex == 6) {
        return uuser_id;
      }
      if (fieldIndex == 7) {
        return alias;
      }
      if (fieldIndex == 8) {
        return description;
      }
      if (fieldIndex == 9) {
        return status;
      }
      if (fieldIndex == 10) {
        return type;
      }
      if (fieldIndex == 11) {
        return sno;
      }
      if (fieldIndex == 12) {
        return kks;
      }
      if (fieldIndex == 13) {
        return path;
      }
      if (fieldIndex == 14) {
        return container_id;
      }
      if (fieldIndex == 15) {
        return device_id;
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
   * 容器CIMID con_cim_id
 */
  public static final String FLD_CON_CIM_ID="con_cim_id";

  /**
 * 容器CIMID con_cim_id
 */
  public static final Integer IDX_CON_CIM_ID=1;
 /**
   * 字段con_cim_id 容器CIMID
   */
	@Column("con_cim_id")
  @ApiField(value="容器CIMID",example="")
  private String con_cim_id;

  /**
   * 返回字段con_cim_id 容器CIMID的值.
   * @return con_cim_id  容器CIMID  varchar
  */
  public String getCon_cim_id() {
    return con_cim_id;
  }

  /**
   * 设置字段con_cim_id 容器CIMID的值.
   * @param con_cim_id  容器CIMID  varchar
   */
  public void setCon_cim_id(String con_cim_id) {
    this.con_cim_id=con_cim_id;
  }

  /**
   * CIM-名称 name
 */
  public static final String FLD_NAME="name";

  /**
 * CIM-名称 name
 */
  public static final Integer IDX_NAME=2;
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
   * 更新时间 utime
 */
  public static final String FLD_UTIME="utime";

  /**
 * 更新时间 utime
 */
  public static final Integer IDX_UTIME=4;
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
  public static final Integer IDX_CUSER_ID=5;
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
   * 更信任ID uuser_id
 */
  public static final String FLD_UUSER_ID="uuser_id";

  /**
 * 更信任ID uuser_id
 */
  public static final Integer IDX_UUSER_ID=6;
 /**
   * 字段uuser_id 更信任ID
   */
	@Column("uuser_id")
  @ApiField(value="更信任ID",example="")
  private String uuser_id;

  /**
   * 返回字段uuser_id 更信任ID的值.
   * @return uuser_id  更信任ID  varchar
  */
  public String getUuser_id() {
    return uuser_id;
  }

  /**
   * 设置字段uuser_id 更信任ID的值.
   * @param uuser_id  更信任ID  varchar
   */
  public void setUuser_id(String uuser_id) {
    this.uuser_id=uuser_id;
  }

  /**
   * 设备别名 alias
 */
  public static final String FLD_ALIAS="alias";

  /**
 * 设备别名 alias
 */
  public static final Integer IDX_ALIAS=7;
 /**
   * 字段alias 设备别名
   */
	@Column("alias")
  @ApiField(value="设备别名",example="")
  private String alias;

  /**
   * 返回字段alias 设备别名的值.
   * @return alias  设备别名  varchar
  */
  public String getAlias() {
    return alias;
  }

  /**
   * 设置字段alias 设备别名的值.
   * @param alias  设备别名  varchar
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
  public static final Integer IDX_DESCRIPTION=8;
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
   * 状态 status
 */
  public static final String FLD_STATUS="status";

  /**
 * 状态 status
 */
  public static final Integer IDX_STATUS=9;
 /**
   * 字段status 状态
   */
	@Column("status")
  @ApiField(value="状态",example="")
  private String status;

  /**
   * 返回字段status 状态的值.
   * @return status  状态  varchar
  */
  public String getStatus() {
    return status;
  }

  /**
   * 设置字段status 状态的值.
   * @param status  状态  varchar
   */
  public void setStatus(String status) {
    this.status=status;
  }

  /**
   * 设备类型 type
 */
  public static final String FLD_TYPE="type";

  /**
 * 设备类型 type
 */
  public static final Integer IDX_TYPE=10;
 /**
   * 字段type 设备类型
   */
	@Column("type")
  @ApiField(value="设备类型",example="")
  private String type;

  /**
   * 返回字段type 设备类型的值.
   * @return type  设备类型  varchar
  */
  public String getType() {
    return type;
  }

  /**
   * 设置字段type 设备类型的值.
   * @param type  设备类型  varchar
   */
  public void setType(String type) {
    this.type=type;
  }

  /**
   * 设备序列号 sno
 */
  public static final String FLD_SNO="sno";

  /**
 * 设备序列号 sno
 */
  public static final Integer IDX_SNO=11;
 /**
   * 字段sno 设备序列号
   */
	@Column("sno")
  @ApiField(value="设备序列号",example="")
  private String sno;

  /**
   * 返回字段sno 设备序列号的值.
   * @return sno  设备序列号  varchar
  */
  public String getSno() {
    return sno;
  }

  /**
   * 设置字段sno 设备序列号的值.
   * @param sno  设备序列号  varchar
   */
  public void setSno(String sno) {
    this.sno=sno;
  }

  /**
   * kks编码 kks
 */
  public static final String FLD_KKS="kks";

  /**
 * kks编码 kks
 */
  public static final Integer IDX_KKS=12;
 /**
   * 字段kks kks编码
   */
	@Column("kks")
  @ApiField(value="kks编码",example="")
  private String kks;

  /**
   * 返回字段kks kks编码的值.
   * @return kks  kks编码  varchar
  */
  public String getKks() {
    return kks;
  }

  /**
   * 设置字段kks kks编码的值.
   * @param kks  kks编码  varchar
   */
  public void setKks(String kks) {
    this.kks=kks;
  }

  /**
   * 设备路径 path
 */
  public static final String FLD_PATH="path";

  /**
 * 设备路径 path
 */
  public static final Integer IDX_PATH=13;
 /**
   * 字段path 设备路径
   */
	@Column("path")
  @ApiField(value="设备路径",example="")
  private String path;

  /**
   * 返回字段path 设备路径的值.
   * @return path  设备路径  varchar
  */
  public String getPath() {
    return path;
  }

  /**
   * 设置字段path 设备路径的值.
   * @param path  设备路径  varchar
   */
  public void setPath(String path) {
    this.path=path;
  }

  /**
   * 容器id container_id
 */
  public static final String FLD_CONTAINER_ID="container_id";

  /**
 * 容器id container_id
 */
  public static final Integer IDX_CONTAINER_ID=14;
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

  /**
   * 设备id device_id
 */
  public static final String FLD_DEVICE_ID="device_id";

  /**
 * 设备id device_id
 */
  public static final Integer IDX_DEVICE_ID=15;
 /**
   * 字段device_id 设备id
   */
	@Column("device_id")
  @ApiField(value="设备id",example="")
  private String device_id;

  /**
   * 返回字段device_id 设备id的值.
   * @return device_id  设备id  varchar
  */
  public String getDevice_id() {
    return device_id;
  }

  /**
   * 设置字段device_id 设备id的值.
   * @param device_id  设备id  varchar
   */
  public void setDevice_id(String device_id) {
    this.device_id=device_id;
  }

}
