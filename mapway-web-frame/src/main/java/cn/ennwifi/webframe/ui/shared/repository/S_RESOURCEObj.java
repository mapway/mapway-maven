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
 * 数据库表 系统资源表
 */
@Table("s_resource")
@Doc("s_resource(系统资源表)")
public class S_RESOURCEObj implements java.io.Serializable, com.google.gwt.user.client.rpc.IsSerializable,
        com.ksyzt.gwt.client.data.IFieldValue {
    /**
     * 缺省的序列化值.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 表系统资源表名称.
     */
    public static final String TBL_S_RESOURCE = "s_resource";

    /**
     * Instantiates a new S resource obj.
     */
    public S_RESOURCEObj() {
    }

    /**
     * 根据字段名称获取字段的值.
     */
    @Override
    public Object getFieldValue(String fieldName, Integer fieldIndex) {
        if (fieldName != null && fieldName.length() > 0) {
            if (FLD_ID.equals(fieldName)) {
                return id;
            }
            if (FLD_NAME.equals(fieldName)) {
                return name;
            }
            if (FLD_PARA.equals(fieldName)) {
                return para;
            }
            if (FLD_PID.equals(fieldName)) {
                return pid;
            }
            if (FLD_ICON.equals(fieldName)) {
                return icon;
            }
            if (FLD_PATH.equals(fieldName)) {
                return path;
            }
            if (FLD_RANK.equals(fieldName)) {
                return rank;
            }
            if (FLD_SUMMARY.equals(fieldName)) {
                return summary;
            }
            if (FLD_CODE.equals(fieldName)) {
                return code;
            }
            if (FLD_STYLE.equals(fieldName)) {
                return style;
            }
            if (FLD_CLICK.equals(fieldName)) {
                return click;
            }
            if (FLD_ROOTID.equals(fieldName)) {
                return rootid;
            }
        } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 12) {
            if (fieldIndex == 0) {
                return id;
            }
            if (fieldIndex == 1) {
                return name;
            }
            if (fieldIndex == 2) {
                return para;
            }
            if (fieldIndex == 3) {
                return pid;
            }
            if (fieldIndex == 4) {
                return icon;
            }
            if (fieldIndex == 5) {
                return path;
            }
            if (fieldIndex == 6) {
                return rank;
            }
            if (fieldIndex == 7) {
                return summary;
            }
            if (fieldIndex == 8) {
                return code;
            }
            if (fieldIndex == 9) {
                return style;
            }
            if (fieldIndex == 10) {
                return click;
            }
            if (fieldIndex == 11) {
                return rootid;
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * 资源ID id
     */
    public static final String FLD_ID = "id";

    /**
     * 资源ID id
     */
    public static final Integer IDX_ID = 0;
    /**
     * 字段id 资源ID
     */
    @Id
    @ApiField(value = "资源ID", example = "")
    private Long id;

    /**
     * 返回字段id 资源ID的值.
     *
     * @return id 资源ID  bigint
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置字段id 资源ID的值.
     *
     * @param id 资源ID  bigint
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 资源名称 name
     */
    public static final String FLD_NAME = "name";

    /**
     * 资源名称 name
     */
    public static final Integer IDX_NAME = 1;
    /**
     * 字段name 资源名称
     */
    @Column("name")
    @ApiField(value = "资源名称", example = "")
    private String name;

    /**
     * 返回字段name 资源名称的值.
     *
     * @return name 资源名称  varchar
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字段name 资源名称的值.
     *
     * @param name 资源名称  varchar
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 模块参数 para
     */
    public static final String FLD_PARA = "para";

    /**
     * 模块参数 para
     */
    public static final Integer IDX_PARA = 2;
    /**
     * 字段para 模块参数
     */
    @Column("para")
    @ApiField(value = "模块参数", example = "")
    private String para;

    /**
     * 返回字段para 模块参数的值.
     *
     * @return para 模块参数  text
     */
    public String getPara() {
        return para;
    }

    /**
     * 设置字段para 模块参数的值.
     *
     * @param para 模块参数  text
     */
    public void setPara(String para) {
        this.para = para;
    }

    /**
     * 父ID pid
     */
    public static final String FLD_PID = "pid";

    /**
     * 父ID pid
     */
    public static final Integer IDX_PID = 3;
    /**
     * 字段pid 父ID
     */
    @Column("pid")
    @ApiField(value = "父ID", example = "")
    private Long pid;

    /**
     * 返回字段pid 父ID的值.
     *
     * @return pid 父ID  bigint
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置字段pid 父ID的值.
     *
     * @param pid 父ID  bigint
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 图标相对路径 icon
     */
    public static final String FLD_ICON = "icon";

    /**
     * 图标相对路径 icon
     */
    public static final Integer IDX_ICON = 4;
    /**
     * 字段icon 图标相对路径
     */
    @Column("icon")
    @ApiField(value = "图标相对路径", example = "")
    private String icon;

    /**
     * 返回字段icon 图标相对路径的值.
     *
     * @return icon 图标相对路径  text
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置字段icon 图标相对路径的值.
     *
     * @param icon 图标相对路径  text
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 路径信息 path
     */
    public static final String FLD_PATH = "path";

    /**
     * 路径信息 path
     */
    public static final Integer IDX_PATH = 5;
    /**
     * 字段path 路径信息
     */
    @Column("path")
    @ApiField(value = "路径信息", example = "")
    private String path;

    /**
     * 返回字段path 路径信息的值.
     *
     * @return path 路径信息  varchar
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置字段path 路径信息的值.
     *
     * @param path 路径信息  varchar
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 排序 rank
     */
    public static final String FLD_RANK = "rank";

    /**
     * 排序 rank
     */
    public static final Integer IDX_RANK = 6;
    /**
     * 字段rank 排序
     */
    @Column("rank")
    @ApiField(value = "排序", example = "")
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
        this.rank = rank;
    }

    /**
     * 资源说明 summary
     */
    public static final String FLD_SUMMARY = "summary";

    /**
     * 资源说明 summary
     */
    public static final Integer IDX_SUMMARY = 7;
    /**
     * 字段summary 资源说明
     */
    @Column("summary")
    @ApiField(value = "资源说明", example = "")
    private String summary;

    /**
     * 返回字段summary 资源说明的值.
     *
     * @return summary 资源说明  varchar
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置字段summary 资源说明的值.
     *
     * @param summary 资源说明  varchar
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 模块代码 code
     */
    public static final String FLD_CODE = "code";

    /**
     * 模块代码 code
     */
    public static final Integer IDX_CODE = 8;
    /**
     * 字段code 模块代码
     */
    @Column("code")
    @ApiField(value = "模块代码", example = "")
    private String code;

    /**
     * 返回字段code 模块代码的值.
     *
     * @return code 模块代码  varchar
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置字段code 模块代码的值.
     *
     * @param code 模块代码  varchar
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * CSS样式 style
     */
    public static final String FLD_STYLE = "style";

    /**
     * CSS样式 style
     */
    public static final Integer IDX_STYLE = 9;
    /**
     * 字段style CSS样式
     */
    @Column("style")
    @ApiField(value = "CSS样式", example = "")
    private String style;

    /**
     * 返回字段style CSS样式的值.
     *
     * @return style CSS样式  varchar
     */
    public String getStyle() {
        return style;
    }

    /**
     * 设置字段style CSS样式的值.
     *
     * @param style CSS样式  varchar
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * 点击次数 click
     */
    public static final String FLD_CLICK = "click";

    /**
     * 点击次数 click
     */
    public static final Integer IDX_CLICK = 10;
    /**
     * 字段click 点击次数
     */
    @Column("click")
    @ApiField(value = "点击次数", example = "")
    private Long click;

    /**
     * 返回字段click 点击次数的值.
     *
     * @return click 点击次数  bigint
     */
    public Long getClick() {
        return click;
    }

    /**
     * 设置字段click 点击次数的值.
     *
     * @param click 点击次数  bigint
     */
    public void setClick(Long click) {
        this.click = click;
    }

    /**
     * 根ID rootid
     */
    public static final String FLD_ROOTID = "rootid";

    /**
     * 根ID rootid
     */
    public static final Integer IDX_ROOTID = 11;
    /**
     * 字段rootid 根ID
     */
    @Column("rootid")
    @ApiField(value = "根ID", example = "")
    private Long rootid;

    /**
     * 返回字段rootid 根ID的值.
     *
     * @return rootid 根ID  bigint
     */
    public Long getRootid() {
        return rootid;
    }

    /**
     * 设置字段rootid 根ID的值.
     *
     * @param rootid 根ID  bigint
     */
    public void setRootid(Long rootid) {
        this.rootid = rootid;
    }

}
