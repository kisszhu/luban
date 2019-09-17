package com.zhl.app.app.client;

import com.zhl.app.common.IProductService;
import com.zhl.app.common.Product;
import com.zhl.rpc.client.RpcProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/17 07:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-application.xml")
public class AppTest {

    @Autowired
    private RpcProxy rpcProxy;

    private IProductService productService;

    @Before
    public void init() {
        productService = rpcProxy.getInstance(IProductService.class);
    }

    @Test
    public void testSave() throws Exception {
        productService.save(new Product(2L, "002", "内衣", BigDecimal.TEN));
    }

    @Test
    public void testDelete() throws Exception {
        productService.deleteById(2L);
    }

    @Test
    public void testUpdate() throws Exception {
        productService.update(new Product(2L, "002", "内衣", BigDecimal.ONE));
    }

    @Test
    public void testGet() throws Exception {
        Product product = productService.get(1L);
        System.out.println("获取到的产品信息为:" + product);
    }

}