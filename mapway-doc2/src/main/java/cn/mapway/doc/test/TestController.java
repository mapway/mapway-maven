package cn.mapway.doc.test;

import cn.mapway.document.annotation.Doc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;


/**
 * TestController.
 * A type parameter.
 * <br/><code>&lt;<b>U</b>> U getU() { ... }</code>
 * <br/><code>class D &lt;<b>@Brain T extends B & A & @Tripe C</b>> { ... }</code>
 * <p>U and T are type parameter names.
 * <br/>B, A, and C are type parameter bounds.
 * <br/>Tripe is an annotation on type parameter bound C.
 * <br/>Brain is an annotation on type parameter T.
 *
 * @author 张建设  <zhangjianshe@gmail.com>
 * @doc.group /doc/special
 * @doc.refs ref/hello.md  ref/abc.html
 */
@Doc(value = "ABCD", group = "/泛能云/APP接口/Tst", desc = "然后循环提取某个键值对的key和value，"
        + "对于value我们可以直接使用之前的策略进行反序列化，因为这部分的结构也是固定的。", refs = {"ref/hello.md"})
@Controller
@RequestMapping(value = "/doc/123")
public class TestController {


    /**
     * 這裏是重要的央視
     * 我们的意图是明确的，将返回的多个Person实体对象放到一个Map结构中。但是问题就在于返回的JSON中的keys是不固定的
     * (比如上述JSON中的keys是人名)，这导致反序列化失败。毕竟默认配置下的ObjectMapper也没有聪明到这种程度，
     * 能够猜测你是想要将多个实体放到Map中。
     *
     * @param day 生成日期 我的数据;
     *            doc.min:32;
     *            doc.max:64;
     *            doc.example:1234;
     *            doc.default:098;
     *            doc.mandatory:true;
     *            doc.length:64;
     *            Hello is a good bye
     * @param req the req;
     *            doc.example:99;
     *            ta是谁
     * @return the name
     * @doc.tags IMPORTANT, API-TAG
     * @author 王明丽
     */
    @RequestMapping(value = "/touch/{day}", method = {RequestMethod.POST, RequestMethod.GET})
    public TestObj touch(@PathVariable Date day,
                         @RequestBody Req req) {
        return new TestObj();
    }
}
