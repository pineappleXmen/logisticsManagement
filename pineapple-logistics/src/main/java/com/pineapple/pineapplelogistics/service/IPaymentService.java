package com.pineapple.pineapplelogistics.service;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.vo.PaymentVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:32 2023/4/25
 * @Modifier by:
 */
public interface IPaymentService {
    ResultBean addPayment(Payment payment,long orderId);

    ResultBean addBatchPayments(List<Payment> paymentList,long orderId);
    ResultBean updatePayment(Payment payment);

    ResultBean updatePaymentStatus(Long id,Boolean status);
    ResultBean getPaymentById(Long paymentId);
}
