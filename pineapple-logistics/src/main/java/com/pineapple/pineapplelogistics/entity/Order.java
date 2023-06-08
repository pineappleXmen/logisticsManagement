package com.pineapple.pineapplelogistics.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 18:24 2023/5/6
 * @Modifier by:
 */
@Data
public class Order {
    private Long id;
    private Long orderId;
    private Integer orderStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private String senderCity;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String receiverCity;
    private List<Payment> payments;
    private List<Goods> goodsList;
}
