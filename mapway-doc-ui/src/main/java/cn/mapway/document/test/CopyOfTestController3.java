package cn.mapway.document.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mapway.document.annotation.Doc;

/**
 * The Class CopyOfTestController3.
 */
@Doc(value = "ABCD", group = "/泛能", desc = "今天我们做对了吗?")
@Controller
@RequestMapping(value = "doc/456")
public class CopyOfTestController3 {


    @Doc(value = "测试retClazz注解",retClazz = GetOperInfoByProdTypeIdResp.class)
    @RequestMapping(value = "/testretval")
    public String testRetValue(Req req)
    {
        return "success";
    }

}
