# mapway-maven

## how to compile and deploy
   build.bat 1.1.8-SNAPSHOT

## API文档使用方法
 
 API文档采用了Spring的 AutoConfigure技术，引入Jar包后直接可以通过application.properties或者application.yaml配置即可使用
 不需要自己建立servlet BEAN.
- 引用JAVA包

```xml
<dependency>
    <groupId>cn.mapway</groupId>
    <artifactId>mapway-doc-ui</artifactId>
    <version>1.18-SNAPSHOT</version>
</dependency>
```
- 配置属性
```yaml

mapway:
    apidoc:
       enabled: true #开启或者关闭文档
       url: /doc/* #文档访问路径URL
       author: zjs #文档管理员
       copyright: 新奥泛能  #版权人
       title: CIM模型标准库 #文档标题
       subTitle: CIM LAB  #文档副标题
       scans: cn.enn.cim.api  #扫描的包
       #文档的LOGO 可以用64编码的图片或者直接是网络上的图片如 http://www.baidi.com/logo.gif
       logo: data:image/png;base64,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
       style: .queryStyle{border:solid 1px green;} #自定义样式
```

- 编写文档
  在Controller类中添加 @Doc注解
  在COntroller的方法中添加 @Doc注解
  在输入输出参数中的字段上添加 @ApiField注解
  
- 如何支持markdown文件和html文件
  可以通过编写markdown文件直接关联到@Doc注解的 refs属性上如 
```java
@RequestMapping(value = "/api/v2")
@Doc(value = "容器设备管理", order = 2,group = "/V2",refs ={"ref/test.md"})
public class EquipmentController extends BaseController {
```    
  group属性将会在文档左侧按照树形结构展示，多个group会合并为一颗API树，group的级别用/号分割
  
  ref/test.md 是在当前类目录下的子目录ref中的test.md markdown文件,也可以是*.html文件

- 图片的支持
  在html或者markdown文件中可以使用同一目录中的图片，图片的URL格式使用方法为 **ref/{PACKAGE}/**文件名
  粗体部分为固定格式不能变
  也可直接使用网络上的图片如 http://www.abidu.com/logo.gif
  
- 接口标签支持
  在方法中添加注解@Doc后可以将该方法输出文档
```java
@Doc(value = "根据容器ID查询容器", group = "/容器", order = 2, tags = {"查询"}, state = DevelopmentState.FINISH, author = Authors.AUTHOR_SUN_JIYANG)
    @RequestMapping(value = "/container/query/by/id", method = RequestMethod.POST)
    public ContainerQueryByIdResp containerQueryById(@RequestBody ContainerQueryByIdReq req) {
        return getEquipmentService().findContainerById(req);
    }
```  
   tags属性为API接口的标签是一个字符串数组,每一个标签的格式为 标签名-自定义样式 比如
   
   查询-queryStyle 查询是标签名queryStyle是自定义样式，自定义样式通过属性配置传入文档系统参加属性配置章节   
   
# mapway-maven cong 1.1.9-SNAPSHOT 插件变更参数
  生成数据JavaBean增加5个控制参数，之前的参数不再起作用
  重构数据库Bean生成代码 修改Maven插件 增加5个控制参数

|参数|说明|缺省值|
|---|---|---|
|useNutz|是否使用Nutz数据库关联注解|1|
|useDocument|是否使用mapwayDocument注解,用于生成API文档|1|
|useGwt|是否使用GWT相关接口|0|
|useFieldIndex|是否生成数据库字段索引|1|
|useUppercase|是否大写数据库关联字段|0|

使用方法，在POM文件Plugins增加下面的代码
```xml
    <plugin>
        <groupId>cn.mapway</groupId>
        <artifactId>mapway-maven-plugin</artifactId>
        <version>1.1.9-SNAPSHOT</version>
        <executions>
            <!-- 生成数据库BEAN -->
            <execution>
                <id>genbean1</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>genbean</goal>
                </goals>
                <inherited>false</inherited>
                <configuration>
                    <!--数据库相关配置-->
                    <driver>com.mysql.jdbc.Driver</driver>
                    <jdbcurl>jdbc:mysql://10.39.21.4:3306/se-cim</jdbcurl>
                    <user></user>
                    <pwd>se-CIM-2017</pwd>
                    <schema>se-cim</schema>
                    <!--输出JavaBean源代码路径-->
                    <path>${project.basedir}/src/main/java</path>
                    <!--JavaBean的包名-->
                    <packageName>cn.enn.cim.data.repository</packageName>
      
                    <!--是否使用Nutz数据库注解-->
                    <useNutz>1</useNutz>
                    <!--是否使用MapwayAPI文档注解-->
                    <useDocument>0</useDocument>
                    <!--是否使用GWT相关代码-->
                    <useGwt>1</useGwt>
                    <!--字段是否用全部大写来表示 LOWER_CASE UPPER_CASE CAMEL-->
                    <useFieldStyle>UPPER_CASE</useFieldStyle>
                    <!--是否生成数据库字段索引和数据库字段名称静态变量-->
                    <useFieldIndex>1</useFieldIndex>
                                                    
                </configuration>
            </execution>
        </executions>
    </plugin>
```
