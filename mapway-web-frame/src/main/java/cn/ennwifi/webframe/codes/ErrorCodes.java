package cn.ennwifi.webframe.codes;

/**
 * 错误编码.
 * 
 * @author zhangjianshe
 */
public class ErrorCodes {

  public static final ErrorCode SUCCESS = new ErrorCode(0, "操作成功");
  public static final ErrorCode ERROR_RESOURCE_NOT_FIND = new ErrorCode(1, "访问的资源不存在");
  public static final ErrorCode ERROR_REQUEST_METHOD_NOT_SUPPORT = new ErrorCode(2, "调用方法不被支持");
  public static final ErrorCode ERROR_REQUEST_BODY = new ErrorCode(3, "接口请求JSON数据格式错误:${0}");
  public static final ErrorCode ERROR_PARAMETER = new ErrorCode(4, "检查输入参数:${0}");
  public static final ErrorCode ERROR_RUNTIME = new ErrorCode(5, "运行时错误");
  public static final ErrorCode ERROR_REQUEST_AUTHORITY_LOST = new ErrorCode(6, "HTTP请求头需要包含认证信息:${0}");
  public static final ErrorCode ERROR_REQUEST_AUTHORITY = new ErrorCode(7, "HTTP请求头的认证信息不对");

  public static final ErrorCode ERROR_GATEWAY_AUTHORITY = new ErrorCode(8, "网关未认证");
  public static final ErrorCode ERROR_DATA_UPLOAD = new ErrorCode(9, "上传数据失败");
  public static final ErrorCode ERROR_LOGIN = new ErrorCode(10, "登录失败");
  public static final ErrorCode ERROR_QUERY_NEWESTDATA = new ErrorCode(11, "获取最新数据失败");
  public static final ErrorCode ERROR_GATEWAY_NOT_EXIST = new ErrorCode(12, "修改的网关不存在");
  public static final ErrorCode ERROR_DATA_QUERY = new ErrorCode(13, "查询数据失败");
  public static final ErrorCode ERROR_DEVICE_NOEXIST = new ErrorCode(14, "设备不存在");
  public static final ErrorCode ERROR_USER_TYPE = new ErrorCode(15, "账号类型不符合要求");
  public static final ErrorCode ERROR_PASSWORD = new ErrorCode(16, "密码错误");
  public static final ErrorCode ERROR_DEVICE = new ErrorCode(17, "该设备已经添加过");
  public static final ErrorCode ERROR_DEVICENO_EXIST = new ErrorCode(18, "设备编号已经存在,不能修改");
  public static final ErrorCode ERROR_DEVICE_GATEWAY = new ErrorCode(19, "设备不在该网关下");
  public static final ErrorCode ERROR_DEVICE_ORDER_IN_GATEWAY = new ErrorCode(20, "设备在网关中的序号大于255");
  public static final ErrorCode ERROR_DEVICE_ORDER_IN_GATEWAY_EXIST = new ErrorCode(20, "设备在网关中的序号已经存在");
  public static final ErrorCode ERROR_USER_NOT_EXIST = new ErrorCode(21, "该用户不存在");
  public static final ErrorCode ERROR_VERCODE = new ErrorCode(22, "验证码错误");
  public static final ErrorCode ERROR_USERNAME_EXIST = new ErrorCode(23, "用户名不存在");


  public static final ErrorCode ERROR_MESSAGE_UNEXIST = new ErrorCode(301, "系统消息不存在");
  public static final ErrorCode ERROR_PRICESHEET_EXIST = new ErrorCode(302, "报价单已存在");
  public static final ErrorCode ERROR_COOPERATION_EXIST = new ErrorCode(303, "合作意向已存在");
  public static final ErrorCode ERROR_FAVOR_EXIST = new ErrorCode(304, "用户已收藏");
  public static final ErrorCode ERROR_FAVOR_UNEXIST = new ErrorCode(305, "用户未收藏");

  public static final ErrorCode ERROR_USERNAME_EXIST_YES = new ErrorCode(111, "用户名已经存在");
  public static final ErrorCode ERROR_PHONE_EXIST_YES = new ErrorCode(112, "手机号已经存在");
  public static final ErrorCode ERROR_PASSWORD_ATYPISM = new ErrorCode(113, "两次密码不一致");

  public static final ErrorCode ERROR_MAIL_AUTHENTICATE = new ErrorCode(115, "认证信息不对");
  public static final ErrorCode ERROR_AGREEMENT_REGIST = new ErrorCode(116, "您未接受企业注册协议");
  public static final ErrorCode ERROR_PASSWORD_OLD = new ErrorCode(117, "原始密码错误");

  public static final ErrorCode ERROR_ENTERPRISE_EXIST = new ErrorCode(200, "后台已经存在:${0}");
  public static final ErrorCode ERROR_ENTERPRISE_NOTEXIST = new ErrorCode(201, "后台不存在:${0}");
  public static final ErrorCode ERROR_DEVICE_EXIST = new ErrorCode(202, "后台已经存在:${0}");
  public static final ErrorCode ERROR_DEVICE_NOTEXIST = new ErrorCode(203, "后台不存在:${0}");
  public static final ErrorCode ERROR_ENTERPRISE_AGREEMENT_REGIST = new ErrorCode(204, "请接受企业注册协议");

  public final static ErrorCode ERROR_INPUT_FILE = new ErrorCode(306, "输入上传的文件");
  public static final ErrorCode ERROR_FILE_FORMAT_UNACCEPTABLE = new ErrorCode(307, "文件格式不被接受${0}");
  public static final ErrorCode ERROR_EXCEPTION = new ErrorCode(308, "异常消息:${0}");
  public static final ErrorCode ERROR_APPLY_COMMITTED = new ErrorCode(309, "重复提交报名表");;
  public static final ErrorCode ERROR_INPUT_IS_NULL = new ErrorCode(310, "有输入项为空");

  public final static ErrorCode ERROR_META_TYPE_NULL = new ErrorCode(400, "搜索元数据不存在");


  public final static ErrorCode ERROR_USER_NOT_ENTERPRISE = new ErrorCode(500, "该用户不是企业用户");

  public final static ErrorCode ERROR_USER_ENTERPRISE_NOT_SH = new ErrorCode(501, "该企业用户未审核");
}
