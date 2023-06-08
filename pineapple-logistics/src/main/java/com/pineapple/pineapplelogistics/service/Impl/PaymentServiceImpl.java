package com.pineapple.pineapplelogistics.service.Impl;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.mapper.PaymentMapper;
import com.pineapple.pineapplelogistics.service.IPaymentService;
import com.pineapple.pineapplelogistics.utils.DateUtils;
import com.pineapple.pineapplelogistics.utils.SnowflakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pineapple.pineapplelogistics.constriant.SnowflakeConst.*;


/**
 * @Author:Pineapple
 * @Description:
 * @Date: 1:05 2023/4/25
 * @Modifier by:
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public ResultBean addPayment(Payment payment,long orderId) {
        SnowflakeUtils snowflakeUtils = new SnowflakeUtils(START_TIME_STAMP,PAYMENT_WORKER_ID);
        long paymentId = snowflakeUtils.get();
        payment.setId(paymentId);
        int result = paymentMapper.addPayment(payment);
        if (result > 0) {
            return ResultBean.success(ResultEnum.PAYMENT_CREATED,paymentId);
        } else {
            return ResultBean.error(ResultEnum.PAYMENT_SERVICE_ERROR);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public ResultBean addBatchPayments(List<Payment> paymentList,long orderId) {
        SnowflakeUtils snowflakeUtils = new SnowflakeUtils(START_TIME_STAMP,PAYMENT_WORKER_ID);
        List<Long> paymentIds = new ArrayList<>();
        for (Payment payment:paymentList){
            long paymentId = snowflakeUtils.get();
            payment.setId(paymentId);
        }
        int result = paymentMapper.insertBatchPayments(paymentList);
        if (result > 0) {
            return ResultBean.success(ResultEnum.PAYMENT_CREATED,paymentIds);
        } else {
            return ResultBean.error(ResultEnum.PAYMENT_SERVICE_ERROR);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public ResultBean updatePayment(Payment payment) {
        int result = paymentMapper.updatePayment(payment);
        if (result > 0) {
            return ResultBean.success(ResultEnum.PAYMENT_UPDATED);
        } else {
            return ResultBean.error(ResultEnum.PAYMENT_SERVICE_ERROR);
        }
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public ResultBean updatePaymentStatus(Long id,Boolean status) {
        int result = paymentMapper.updatePaymentStatus(id,status);
        if (result > 0) {
            return ResultBean.success(ResultEnum.PAYMENT_UPDATED);
        } else {
            return ResultBean.error(ResultEnum.PAYMENT_SERVICE_ERROR);
        }
    }

    @Override
    public ResultBean getPaymentById(Long paymentId) {
        Payment payment = paymentMapper.getPaymentById(paymentId);
        if (payment != null) {
            return ResultBean.success(payment);
        } else {
            return ResultBean.error(ResultEnum.PAYMENT_SERVICE_ERROR);
        }
    }
}
