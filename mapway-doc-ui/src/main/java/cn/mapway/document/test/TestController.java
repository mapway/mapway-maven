package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiStyle;
import cn.mapway.document.annotation.Doc;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * TestController.
 * A type parameter.
 * 制定公司层战略<b>最流行的方法之一就是BCG矩阵</b>。该方法是由波士顿咨询集团（Boston Consulting Group, BCG）在上世纪70年代初开发的。BCG矩阵将组织的每一个战略事业单位（SBUs）标在一种2维的矩阵图上，从而显示出哪个SBUs提供高额的潜在收益，以及哪个SBUs是组织资源的漏斗。BCG矩阵的发明者、波士顿公司的创立者布鲁斯认为“公司若要取得成功，就必须拥有增长率和市场份额各不相同的产品组合。组合的构成取决于现金流量的平衡。”如此看来，BCG的实质是为了通过业务的优化组合实现企业的现金流量平衡
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
     * @doc.tags v3, time
     * @author 王明丽
     */
    @RequestMapping(value = "/touch", method = {RequestMethod.POST, RequestMethod.POST})

    public @ResponseBody
    Ret<TestObj> touch(
                              @RequestBody Req<Float> req) {
        return new Ret();
    }


}
