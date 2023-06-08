package com.pineapple.pineapplelogistics.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:51 2023/4/25
 * @Modifier by:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {
    private String name;
    private Double weightOrVolume;
    private Double unitPrice;
    private Double freight;
    private Integer count;
}