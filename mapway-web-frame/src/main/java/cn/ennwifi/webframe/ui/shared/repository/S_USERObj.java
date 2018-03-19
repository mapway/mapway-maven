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
 * 数据库表 用户表
 */
@Table("s_user")
@Doc("s_user(用户表)")
public class S_USERObj implements java.io.Serializable, com.google.gwt.user.client.rpc.IsSerializable,
        com.ksyzt.gwt.client.data.IFieldValue {
    /**
     * 缺省的序列化值.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 表用户表名称.
     */
    public static final String TBL_S_USER = "s_user";

    /**
     * Instantiates a new S user obj.
     */
    public S_USERObj() {
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
            if (FLD_ACCOUNT_TYPE.equals(fieldName)) {
                return account_type;
            }
            if (FLD_HASH.equals(fieldName)) {
                return hash;
            }
            if (FLD_NAME.equals(fieldName)) {
                return name;
            }
            if (FLD_PWD.equals(fieldName)) {
                return pwd;
            }
            if (FLD_REAL_NAME.equals(fieldName)) {
                return real_name;
            }
            if (FLD_AVATOR.equals(fieldName)) {
                return avator;
            }
            if (FLD_GENDER.equals(fieldName)) {
                return gender;
            }
            if (FLD_EMAIL.equals(fieldName)) {
                return email;
            }
            if (FLD_MOBILE.equals(fieldName)) {
                return mobile;
            }
            if (FLD_TITLE.equals(fieldName)) {
                return title;
            }
            if (FLD_TOKEN.equals(fieldName)) {
                return token;
            }
            if (FLD_CREATE_TIME.equals(fieldName)) {
                return create_time;
            }
            if (FLD_UPDATE_TIME.equals(fieldName)) {
                return update_time;
            }
            if (FLD_IS_SEND_MESSAGE.equals(fieldName)) {
                return is_send_message;
            }
            if (FLD_ENABLED.equals(fieldName)) {
                return enabled;
            }
            if (FLD_DISABLED_MSG.equals(fieldName)) {
                return disabled_msg;
            }
            if (FLD_RECOMMEND_CODE.equals(fieldName)) {
                return recommend_code;
            }
            if (FLD_LAST_TIME.equals(fieldName)) {
                return last_time;
            }
            if (FLD_USER_TYPE.equals(fieldName)) {
                return user_type;
            }
            if (FLD_USER_IDENTITIES.equals(fieldName)) {
                return user_identities;
            }
        } else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < 21) {
            if (fieldIndex == 0) {
                return id;
            }
            if (fieldIndex == 1) {
                return account_type;
            }
            if (fieldIndex == 2) {
                return hash;
            }
            if (fieldIndex == 3) {
                return name;
            }
            if (fieldIndex == 4) {
                return pwd;
            }
            if (fieldIndex == 5) {
                return real_name;
            }
            if (fieldIndex == 6) {
                return avator;
            }
            if (fieldIndex == 7) {
                return gender;
            }
            if (fieldIndex == 8) {
                return email;
            }
            if (fieldIndex == 9) {
                return mobile;
            }
            if (fieldIndex == 10) {
                return title;
            }
            if (fieldIndex == 11) {
                return token;
            }
            if (fieldIndex == 12) {
                return create_time;
            }
            if (fieldIndex == 13) {
                return update_time;
            }
            if (fieldIndex == 14) {
                return is_send_message;
            }
            if (fieldIndex == 15) {
                return enabled;
            }
            if (fieldIndex == 16) {
                return disabled_msg;
            }
            if (fieldIndex == 17) {
                return recommend_code;
            }
            if (fieldIndex == 18) {
                return last_time;
            }
            if (fieldIndex == 19) {
                return user_type;
            }
            if (fieldIndex == 20) {
                return user_identities;
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * 用户ID id
     */
    public static final String FLD_ID = "id";

    /**
     * 用户ID id
     */
    public static final Integer IDX_ID = 0;
    /**
     * 字段id 用户ID
     */
    @Id
    @ApiField(value = "用户ID", example = "")
    private Long id;

    /**
     * 返回字段id 用户ID的值.
     *
     * @return id 用户ID  bigint
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置字段id 用户ID的值.
     *
     * @param id 用户ID  bigint
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * account_type
     */
    public static final String FLD_ACCOUNT_TYPE = "account_type";

    /**
     * account_type
     */
    public static final Integer IDX_ACCOUNT_TYPE = 1;
    /**
     * 字段account_type
     */
    @Column("account_type")
    @ApiField(value = "", example = "")
    private String account_type;

    /**
     * 返回字段account_type 的值.
     *
     * @return account_type varchar
     */
    public String getAccount_type() {
        return account_type;
    }

    /**
     * 设置字段account_type 的值.
     *
     * @param account_type varchar
     */
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    /**
     * hash
     */
    public static final String FLD_HASH = "hash";

    /**
     * hash
     */
    public static final Integer IDX_HASH = 2;
    /**
     * 字段hash
     */
    @Column("hash")
    @ApiField(value = "", example = "")
    private String hash;

    /**
     * 返回字段hash 的值.
     *
     * @return hash varchar
     */
    public String getHash() {
        return hash;
    }

    /**
     * 设置字段hash 的值.
     *
     * @param hash varchar
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * name
     */
    public static final String FLD_NAME = "name";

    /**
     * name
     */
    public static final Integer IDX_NAME = 3;
    /**
     * 字段name
     */
    @Column("name")
    @ApiField(value = "", example = "")
    private String name;

    /**
     * 返回字段name 的值.
     *
     * @return name varchar
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字段name 的值.
     *
     * @param name varchar
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * pwd
     */
    public static final String FLD_PWD = "pwd";

    /**
     * pwd
     */
    public static final Integer IDX_PWD = 4;
    /**
     * 字段pwd
     */
    @Column("pwd")
    @ApiField(value = "", example = "")
    private String pwd;

    /**
     * 返回字段pwd 的值.
     *
     * @return pwd varchar
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置字段pwd 的值.
     *
     * @param pwd varchar
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * real_name
     */
    public static final String FLD_REAL_NAME = "real_name";

    /**
     * real_name
     */
    public static final Integer IDX_REAL_NAME = 5;
    /**
     * 字段real_name
     */
    @Column("real_name")
    @ApiField(value = "", example = "")
    private String real_name;

    /**
     * 返回字段real_name 的值.
     *
     * @return real_name varchar
     */
    public String getReal_name() {
        return real_name;
    }

    /**
     * 设置字段real_name 的值.
     *
     * @param real_name varchar
     */
    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    /**
     * avator
     */
    public static final String FLD_AVATOR = "avator";

    /**
     * avator
     */
    public static final Integer IDX_AVATOR = 6;
    /**
     * 字段avator
     */
    @Column("avator")
    @ApiField(value = "", example = "")
    private String avator;

    /**
     * 返回字段avator 的值.
     *
     * @return avator text
     */
    public String getAvator() {
        return avator;
    }

    /**
     * 设置字段avator 的值.
     *
     * @param avator text
     */
    public void setAvator(String avator) {
        this.avator = avator;
    }

    /**
     * 性别 gender
     */
    public static final String FLD_GENDER = "gender";

    /**
     * 性别 gender
     */
    public static final Integer IDX_GENDER = 7;
    /**
     * 字段gender 性别
     */
    @Column("gender")
    @ApiField(value = "性别", example = "")
    private String gender;

    /**
     * 返回字段gender 性别的值.
     *
     * @return gender 性别  varchar
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置字段gender 性别的值.
     *
     * @param gender 性别  varchar
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * email
     */
    public static final String FLD_EMAIL = "email";

    /**
     * email
     */
    public static final Integer IDX_EMAIL = 8;
    /**
     * 字段email
     */
    @Column("email")
    @ApiField(value = "", example = "")
    private String email;

    /**
     * 返回字段email 的值.
     *
     * @return email varchar
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置字段email 的值.
     *
     * @param email varchar
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * mobile
     */
    public static final String FLD_MOBILE = "mobile";

    /**
     * mobile
     */
    public static final Integer IDX_MOBILE = 9;
    /**
     * 字段mobile
     */
    @Column("mobile")
    @ApiField(value = "", example = "")
    private String mobile;

    /**
     * 返回字段mobile 的值.
     *
     * @return mobile varchar
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置字段mobile 的值.
     *
     * @param mobile varchar
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * title
     */
    public static final String FLD_TITLE = "title";

    /**
     * title
     */
    public static final Integer IDX_TITLE = 10;
    /**
     * 字段title
     */
    @Column("title")
    @ApiField(value = "", example = "")
    private String title;

    /**
     * 返回字段title 的值.
     *
     * @return title text
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置字段title 的值.
     *
     * @param title text
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * token
     */
    public static final String FLD_TOKEN = "token";

    /**
     * token
     */
    public static final Integer IDX_TOKEN = 11;
    /**
     * 字段token
     */
    @Column("token")
    @ApiField(value = "", example = "")
    private String token;

    /**
     * 返回字段token 的值.
     *
     * @return token varchar
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置字段token 的值.
     *
     * @param token varchar
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * create_time
     */
    public static final String FLD_CREATE_TIME = "create_time";

    /**
     * create_time
     */
    public static final Integer IDX_CREATE_TIME = 12;
    /**
     * 字段create_time
     */
    @Column("create_time")
    @ApiField(value = "", example = "")
    private java.sql.Timestamp create_time;

    /**
     * 返回字段create_time 的值.
     *
     * @return create_time datetime
     */
    public java.sql.Timestamp getCreate_time() {
        return create_time;
    }

    /**
     * 设置字段create_time 的值.
     *
     * @param create_time datetime
     */
    public void setCreate_time(java.sql.Timestamp create_time) {
        this.create_time = create_time;
    }

    /**
     * update_time
     */
    public static final String FLD_UPDATE_TIME = "update_time";

    /**
     * update_time
     */
    public static final Integer IDX_UPDATE_TIME = 13;
    /**
     * 字段update_time
     */
    @Column("update_time")
    @ApiField(value = "", example = "")
    private java.sql.Timestamp update_time;

    /**
     * 返回字段update_time 的值.
     *
     * @return update_time datetime
     */
    public java.sql.Timestamp getUpdate_time() {
        return update_time;
    }

    /**
     * 设置字段update_time 的值.
     *
     * @param update_time datetime
     */
    public void setUpdate_time(java.sql.Timestamp update_time) {
        this.update_time = update_time;
    }

    /**
     * is_send_message
     */
    public static final String FLD_IS_SEND_MESSAGE = "is_send_message";

    /**
     * is_send_message
     */
    public static final Integer IDX_IS_SEND_MESSAGE = 14;
    /**
     * 字段is_send_message
     */
    @Column("is_send_message")
    @ApiField(value = "", example = "")
    private Integer is_send_message;

    /**
     * 返回字段is_send_message 的值.
     *
     * @return is_send_message int
     */
    public Integer getIs_send_message() {
        return is_send_message;
    }

    /**
     * 设置字段is_send_message 的值.
     *
     * @param is_send_message int
     */
    public void setIs_send_message(Integer is_send_message) {
        this.is_send_message = is_send_message;
    }

    /**
     * enabled
     */
    public static final String FLD_ENABLED = "enabled";

    /**
     * enabled
     */
    public static final Integer IDX_ENABLED = 15;
    /**
     * 字段enabled
     */
    @Column("enabled")
    @ApiField(value = "", example = "")
    private Integer enabled;

    /**
     * 返回字段enabled 的值.
     *
     * @return enabled int
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置字段enabled 的值.
     *
     * @param enabled int
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * disabled_msg
     */
    public static final String FLD_DISABLED_MSG = "disabled_msg";

    /**
     * disabled_msg
     */
    public static final Integer IDX_DISABLED_MSG = 16;
    /**
     * 字段disabled_msg
     */
    @Column("disabled_msg")
    @ApiField(value = "", example = "")
    private String disabled_msg;

    /**
     * 返回字段disabled_msg 的值.
     *
     * @return disabled_msg varchar
     */
    public String getDisabled_msg() {
        return disabled_msg;
    }

    /**
     * 设置字段disabled_msg 的值.
     *
     * @param disabled_msg varchar
     */
    public void setDisabled_msg(String disabled_msg) {
        this.disabled_msg = disabled_msg;
    }

    /**
     * recommend_code
     */
    public static final String FLD_RECOMMEND_CODE = "recommend_code";

    /**
     * recommend_code
     */
    public static final Integer IDX_RECOMMEND_CODE = 17;
    /**
     * 字段recommend_code
     */
    @Column("recommend_code")
    @ApiField(value = "", example = "")
    private String recommend_code;

    /**
     * 返回字段recommend_code 的值.
     *
     * @return recommend_code varchar
     */
    public String getRecommend_code() {
        return recommend_code;
    }

    /**
     * 设置字段recommend_code 的值.
     *
     * @param recommend_code varchar
     */
    public void setRecommend_code(String recommend_code) {
        this.recommend_code = recommend_code;
    }

    /**
     * 最后登录时间 last_time
     */
    public static final String FLD_LAST_TIME = "last_time";

    /**
     * 最后登录时间 last_time
     */
    public static final Integer IDX_LAST_TIME = 18;
    /**
     * 字段last_time 最后登录时间
     */
    @Column("last_time")
    @ApiField(value = "最后登录时间", example = "")
    private java.sql.Timestamp last_time;

    /**
     * 返回字段last_time 最后登录时间的值.
     *
     * @return last_time 最后登录时间  datetime
     */
    public java.sql.Timestamp getLast_time() {
        return last_time;
    }

    /**
     * 设置字段last_time 最后登录时间的值.
     *
     * @param last_time 最后登录时间  datetime
     */
    public void setLast_time(java.sql.Timestamp last_time) {
        this.last_time = last_time;
    }

    /**
     * 用户类型 user_type
     */
    public static final String FLD_USER_TYPE = "user_type";

    /**
     * 用户类型 user_type
     */
    public static final Integer IDX_USER_TYPE = 19;
    /**
     * 字段user_type 用户类型
     */
    @Column("user_type")
    @ApiField(value = "用户类型", example = "")
    private String user_type;

    /**
     * 返回字段user_type 用户类型的值.
     *
     * @return user_type 用户类型  varchar
     */
    public String getUser_type() {
        return user_type;
    }

    /**
     * 设置字段user_type 用户类型的值.
     *
     * @param user_type 用户类型  varchar
     */
    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    /**
     * 用户标识 user_identities
     */
    public static final String FLD_USER_IDENTITIES = "user_identities";

    /**
     * 用户标识 user_identities
     */
    public static final Integer IDX_USER_IDENTITIES = 20;
    /**
     * 字段user_identities 用户标识
     */
    @Column("user_identities")
    @ApiField(value = "用户标识", example = "")
    private String user_identities;

    /**
     * 返回字段user_identities 用户标识的值.
     *
     * @return user_identities 用户标识  varchar
     */
    public String getUser_identities() {
        return user_identities;
    }

    /**
     * 设置字段user_identities 用户标识的值.
     *
     * @param user_identities 用户标识  varchar
     */
    public void setUser_identities(String user_identities) {
        this.user_identities = user_identities;
    }

}
