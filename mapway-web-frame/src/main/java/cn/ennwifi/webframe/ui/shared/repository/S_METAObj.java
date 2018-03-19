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
 * 数据库表 元数据信息
 *
 */
@Table("s_meta")
@Doc("s_meta(元数据信息)")
public class S_METAObj implements java.io.Serializable,com.google.gwt.user.client.rpc.IsSerializable,
    com.ksyzt.gwt.client.data.IFieldValue{
  /**
   * 缺省的序列化值.
  */
  private static final long serialVersionUID = 1L;

    /**
     * 表元数据信息名称.
     */
    public static final String TBL_S_META="s_meta";

    /**
     * Instantiates a new S meta obj.
     */
    public S_METAObj() {
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
      if (FLD_CATALOG.equals(fieldName)) {
        return catalog;
      }
      if (FLD_NAME.equals(fieldName)) {
        return name;
      }
      if (FLD_CODE.equals(fieldName)) {
        return code;
      }
      if (FLD_RANK.equals(fieldName)) {
        return rank;
      }
      if (FLD_PID.equals(fieldName)) {
        return pid;
      }
      if (FLD_DESCRIPTION.equals(fieldName)) {
        return description;
      }
      if (FLD_ICON.equals(fieldName)) {
        return icon;
      }
      if (FLD_ROOT_ID.equals(fieldName)) {
        return root_id;
      }
    } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 9) {
      if (fieldIndex == 0) {
        return id;
      }
      if (fieldIndex == 1) {
        return catalog;
      }
      if (fieldIndex == 2) {
        return name;
      }
      if (fieldIndex == 3) {
        return code;
      }
      if (fieldIndex == 4) {
        return rank;
      }
      if (fieldIndex == 5) {
        return pid;
      }
      if (fieldIndex == 6) {
        return description;
      }
      if (fieldIndex == 7) {
        return icon;
      }
      if (fieldIndex == 8) {
        return root_id;
      }
    } else {
      return null;
    }
    return null;
  }

    /**
     * 元数据ID id
     */
    public static final String FLD_ID="id";

    /**
     * 元数据ID id
     */
    public static final Integer IDX_ID=0;
 /**
   * 字段id 元数据ID
   */
	@Id
  @ApiField(value="元数据ID",example="")
  private Integer id;

    /**
     * 返回字段id 元数据ID的值.
     *
     * @return id 元数据ID  int
     */
    public Integer getId() {
    return id;
  }

    /**
     * 设置字段id 元数据ID的值.
     *
     * @param id 元数据ID  int
     */
    public void setId(Integer id) {
    this.id=id;
  }

    /**
     * 分类目录 catalog
     */
    public static final String FLD_CATALOG="catalog";

    /**
     * 分类目录 catalog
     */
    public static final Integer IDX_CATALOG=1;
 /**
   * 字段catalog 分类目录
   */
	@Column("catalog")
  @ApiField(value="分类目录",example="")
  private String catalog;

    /**
     * 返回字段catalog 分类目录的值.
     *
     * @return catalog 分类目录  varchar
     */
    public String getCatalog() {
    return catalog;
  }

    /**
     * 设置字段catalog 分类目录的值.
     *
     * @param catalog 分类目录  varchar
     */
    public void setCatalog(String catalog) {
    this.catalog=catalog;
  }

    /**
     * 名称 name
     */
    public static final String FLD_NAME="name";

    /**
     * 名称 name
     */
    public static final Integer IDX_NAME=2;
 /**
   * 字段name 名称
   */
	@Column("name")
  @ApiField(value="名称",example="")
  private String name;

    /**
     * 返回字段name 名称的值.
     *
     * @return name 名称  varchar
     */
    public String getName() {
    return name;
  }

    /**
     * 设置字段name 名称的值.
     *
     * @param name 名称  varchar
     */
    public void setName(String name) {
    this.name=name;
  }

    /**
     * code code
     */
    public static final String FLD_CODE="code";

    /**
     * code code
     */
    public static final Integer IDX_CODE=3;
 /**
   * 字段code code
   */
	@Column("code")
  @ApiField(value="code",example="")
  private String code;

    /**
     * 返回字段code code的值.
     *
     * @return code code  varchar
     */
    public String getCode() {
    return code;
  }

    /**
     * 设置字段code code的值.
     *
     * @param code code  varchar
     */
    public void setCode(String code) {
    this.code=code;
  }

    /**
     * 排序 rank
     */
    public static final String FLD_RANK="rank";

    /**
     * 排序 rank
     */
    public static final Integer IDX_RANK=4;
 /**
   * 字段rank 排序
   */
	@Column("rank")
  @ApiField(value="排序",example="")
  private Integer rank;

    /**
     * 返回字段rank 排序的值.
     *
     * @return rank 排序  int
     */
    public Integer getRank() {
    return rank;
  }

    /**
     * 设置字段rank 排序的值.
     *
     * @param rank 排序  int
     */
    public void setRank(Integer rank) {
    this.rank=rank;
  }

    /**
     * 父ID pid
     */
    public static final String FLD_PID="pid";

    /**
     * 父ID pid
     */
    public static final Integer IDX_PID=5;
 /**
   * 字段pid 父ID
   */
	@Column("pid")
  @ApiField(value="父ID",example="")
  private Integer pid;

    /**
     * 返回字段pid 父ID的值.
     *
     * @return pid 父ID  int
     */
    public Integer getPid() {
    return pid;
  }

    /**
     * 设置字段pid 父ID的值.
     *
     * @param pid 父ID  int
     */
    public void setPid(Integer pid) {
    this.pid=pid;
  }

    /**
     * 描述信息 description
     */
    public static final String FLD_DESCRIPTION="description";

    /**
     * 描述信息 description
     */
    public static final Integer IDX_DESCRIPTION=6;
 /**
   * 字段description 描述信息
   */
	@Column("description")
  @ApiField(value="描述信息",example="")
  private String description;

    /**
     * 返回字段description 描述信息的值.
     *
     * @return description 描述信息  varchar
     */
    public String getDescription() {
    return description;
  }

    /**
     * 设置字段description 描述信息的值.
     *
     * @param description 描述信息  varchar
     */
    public void setDescription(String description) {
    this.description=description;
  }

    /**
     * 图标 icon
     */
    public static final String FLD_ICON="icon";

    /**
     * 图标 icon
     */
    public static final Integer IDX_ICON=7;
 /**
   * 字段icon 图标
   */
	@Column("icon")
  @ApiField(value="图标",example="")
  private String icon;

    /**
     * 返回字段icon 图标的值.
     *
     * @return icon 图标  varchar
     */
    public String getIcon() {
    return icon;
  }

    /**
     * 设置字段icon 图标的值.
     *
     * @param icon 图标  varchar
     */
    public void setIcon(String icon) {
    this.icon=icon;
  }

    /**
     * 跟ID root_id
     */
    public static final String FLD_ROOT_ID="root_id";

    /**
     * 跟ID root_id
     */
    public static final Integer IDX_ROOT_ID=8;
 /**
   * 字段root_id 跟ID
   */
	@Column("root_id")
  @ApiField(value="跟ID",example="")
  private Integer root_id;

    /**
     * 返回字段root_id 跟ID的值.
     *
     * @return root_id 跟ID  int
     */
    public Integer getRoot_id() {
    return root_id;
  }

    /**
     * 设置字段root_id 跟ID的值.
     *
     * @param root_id 跟ID  int
     */
    public void setRoot_id(Integer root_id) {
    this.root_id=root_id;
  }

}
