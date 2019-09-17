package com.zhl.app.server;

import com.zhl.app.common.IProductService;
import com.zhl.app.common.Product;
import com.zhl.rpc.server.RpcService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/17 07:32
 */
@Component
@RpcService(IProductService.class)
public class ProductServiceImpl implements IProductService {
    /**
     * Component 把普通的pojo实例化到spring容器
     */
    @Override
    public void save(Product product) {
        System.out.println("产品保存成功: " + product);
    }

    @Override
    public void deleteById(Long productId) {
        System.out.println("产品删除成功: " + productId);
    }

    @Override
    public void update(Product product) {
        System.out.println("产品修改成功: " + product);
    }

    @Override
    public Product get(Long productId) {
        System.out.println("产品获取成功");
        return new Product(1L, "001", "笔记本电脑", BigDecimal.TEN);
    }
}