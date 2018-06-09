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
 * 数据库表 角色表
 */
@Table("s_role")
@Doc("s_role(角色表)")
public class S_ROLEObj implements java.io.Serializable, com.google.gwt.user.client.rpc.IsSerializable,
        com.ksyzt.gwt.client.data.IFieldValue {
    /**
     * 缺省的序列化值.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 表角色表名称.
     */
    public static final String TBL_S_ROLE = "s_role";

    /**
     * Instantiates a new S role obj.
     */
    public S_ROLEObj() {
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
            if (FLD_SUMMARY.equals(fieldName)) {
                return summary;
            }
        } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 3) {
            if (fieldIndex == 0) {
                return id;
            }
            if (fieldIndex == 1) {
                return name;
            }
            if (fieldIndex == 2) {
                return summary;
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * 角色ID id
     */
    public static final String FLD_ID = "id";

    /**
     * 角色ID id
     */
    public static final Integer IDX_ID = 0;
    /**
     * 字段id 角色ID
     */
    @Id
    @ApiField(value = "角色ID", example = "")
    private Long id;

    /**
     * 返回字段id 角色ID的值.
     *
     * @return id 角色ID  bigint
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置字段id 角色ID的值.
     *
     * @param id 角色ID  bigint
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 角色名称 name
     */
    public static final String FLD_NAME = "name";

    /**
     * 角色名称 name
     */
    public static final Integer IDX_NAME = 1;
    /**
     * 字段name 角色名称
     */
    @Column("name")
    @ApiField(value = "角色名称", example = "")
    private String name;

    /**
     * 返回字段name 角色名称的值.
     *
     * @return name 角色名称  varchar
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字段name 角色名称的值.
     *
     * @param name 角色名称  varchar
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 角色说明 summary
     */
    public static final String FLD_SUMMARY = "summary";

    /**
     * 角色说明 summary
     */
    public static final Integer IDX_SUMMARY = 2;
    /**
     * 字段summary 角色说明
     */
    @Column("summary")
    @ApiField(value = "角色说明", example = "")
    private String summary;

    /**
     * 返回字段summary 角色说明的值.
     *
     * @return summary 角色说明  varchar
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置字段summary 角色说明的值.
     *
     * @param summary 角色说明  varchar
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

}
