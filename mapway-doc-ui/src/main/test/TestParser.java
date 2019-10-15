
import cn.mapway.doc2.parser.SourceParser;
import cn.mapway.doc2.parser.exception.NotFoundException;
import cn.mapway.doc2.parser.module.ApiDoc2;
import cn.mapway.doc2.parser.module.ApiGroup;
import cn.mapway.document.test.TestController;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

/**
 * 测试解析器
 */
public class TestParser {
    public static void main(String[] args) {
        String root = "E:\\dev\\IdeaProjects\\mapway-maven\\mapway-doc-ui\\src\\main\\java";
        SourceParser parser = new SourceParser(root);
        try {
            parser.parseController(TestController.class.getTypeName());
        } catch (NotFoundException e) {
            parser.log("ERROR:201111->" + e.getMessage());
        }
        ApiDoc2 doc = new ApiDoc2();
        doc.copyright = "copyright&copy;2018";
        doc.date = "2019-01-12";
        doc.rootGroup = ApiGroup.findGroup("/");
        doc.title = "API接口定义文档";
        doc.version = "1.03";

        System.out.println("var data="+Json.toJson(doc,JsonFormat.forLook()));
    }


}
