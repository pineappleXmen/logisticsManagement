package com.pineapple.pineapplelogistics.vo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:32 2023/4/25
 * @Modifier by:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentVo {
    private Double totalPrice;
    private Double goodsPrice;
    private Double receivePrice;
    private Double deliveryPrice;
}
