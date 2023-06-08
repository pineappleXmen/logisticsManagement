package com.pineapple.pineapplelogistics.factory;

import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.utils.DateUtils;
import com.pineapple.pineapplelogistics.utils.SnowflakeUtils;
import com.pineapple.pineapplelogistics.vo.PaymentVo;

import java.util.ArrayList;
import java.util.List;

import static com.pineapple.pineapplelogistics.constriant.SnowflakeConst.*;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 21:45 2023/5/6
 * @Modifier by:
 */
public class PaymentFactory {
    public static List<Payment> createPayment(List<PaymentVo> paymentVos, long orderId) {
        List<Payment> payments = new ArrayList<>();
        for (PaymentVo paymentVo:paymentVos){
            Payment payment = new Payment();
            SnowflakeUtils snowflakeUtils = new SnowflakeUtils(START_TIME_STAMP,PAYMENT_WORKER_ID);
            long paymentId = snowflakeUtils.get();
            payment.setOrderId(orderId);
            payment.setId(paymentId);
            payment.setCreateTime(DateUtils.now());
            payment.setTotalPrice(paymentVo.getTotalPrice());
            payment.setGoodsPrice(paymentVo.getGoodsPrice());
            payment.setReceivePrice(paymentVo.getReceivePrice());
            payment.setDeliveryPrice(paymentVo.getDeliveryPrice());
            payment.setStatus(false);
            payments.add(payment);
        }
        return payments;
    }
}
