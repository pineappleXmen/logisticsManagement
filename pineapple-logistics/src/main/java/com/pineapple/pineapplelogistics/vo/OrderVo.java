package com.pineapple.pineapplelogistics.vo;

import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 1:02 2023/4/25
 * @Modifier by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderVo {
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private String senderCity;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String receiverCity;
    private List<PaymentVo> paymentVos;
    private List<GoodsVo> goodsList;
}
