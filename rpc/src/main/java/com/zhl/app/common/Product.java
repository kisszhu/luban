package com.zhl.app.common;

import lombok.*;

import java.math.BigDecimal;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/17 07:30
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    /**
     * id
     */
    private Long id;
    /**
     * 产品编号
     */
    private String sn;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品价格
     */
    private BigDecimal price;
}