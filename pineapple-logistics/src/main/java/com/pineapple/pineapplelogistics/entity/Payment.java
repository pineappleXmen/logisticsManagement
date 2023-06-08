package com.pineapple.pineapplelogistics.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 1:08 2023/4/25
 * @Modifier by:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {
    private Long id;
    private Long orderId;
    private Double totalPrice;
    private Double goodsPrice;
    private Double receivePrice;
    private Double deliveryPrice;
    private LocalDateTime createTime;
    private Boolean status;
}
