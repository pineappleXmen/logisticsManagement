package com.pineapple.pineapplelogistics.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:52 2023/4/25
 * @Modifier by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVo {
    private long itemId;
    private long goodsId;
    private String itemName;
    private int itemNum;
    private int itemWeight;
    private int itemUnitPrice;
    private long itemPrice;
}
