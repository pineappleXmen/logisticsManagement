package com.pineapple.pineapplelogistics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 17:35 2023/5/6
 * @Modifier by:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Long id;
    private Long orderId;
    private String name;
    private Double weightOrVolume;
    private Double unitPrice;
    private Double freight;
    private Integer count;

}
