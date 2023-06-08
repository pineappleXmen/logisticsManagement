package com.pineapple.pineapplelogistics.service.Impl;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.entity.Order;
import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.factory.OrderFactory;
import com.pineapple.pineapplelogistics.mapper.OrderMapper;
import com.pineapple.pineapplelogistics.service.IGoodsService;
import com.pineapple.pineapplelogistics.service.IOrderService;
import com.pineapple.pineapplelogistics.service.IPaymentService;
import com.pineapple.pineapplelogistics.utils.DateUtils;
import com.pineapple.pineapplelogistics.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 18:26 2023/5/6
 * @Modifier by:
 */
@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IGoodsService goodsService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public ResultBean createOrder(OrderVo orderVo) {

        Order order = OrderFactory.createOrder(orderVo);
        Long orderId = order.getOrderId();
        orderMapper.createOrder(order);
        // 保存支付信息和商品信息

        List<Payment> payments = order.getPayments();
        int paymentSize = payments.size();
        if (paymentSize ==1){
            paymentService.addPayment(payments.get(0),orderId);
        }else if(paymentSize >1){
            paymentService.addBatchPayments(order.getPayments(),orderId);
        }else {
            return ResultBean.error(ResultEnum.PAYMENT_SERVICE_ERROR);
        }


        List<Goods> goodsList = order.getGoodsList();
        int goodSize = goodsList.size();
        if (goodSize ==1){
            goodsService.addGoods(goodsList.get(0),orderId);
        }else if(goodSize >1){
            goodsService.addBatchGoods(goodsList,orderId);
        }else {
            return ResultBean.error(ResultEnum.GOODS_SERVICE_ERROR);
        }

        return ResultBean.success(ResultEnum.ORDER_CREATED,orderId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public ResultBean updateOrderStatus(Long orderId, int orderStatus) {
        int result = orderMapper.updateOrderStatus(orderId, orderStatus,DateUtils.now());
        if (result > 0) {
            return ResultBean.success();
        } else {
            return ResultBean.error(ResultEnum.ORDER_SERVICE_ERROR);
        }
    }



    @Override
    public ResultBean findOrderById(Long orderId) {
        Order order = orderMapper.findOrderById(orderId);
        if (order!=null){
            return ResultBean.success(order );
        }else {
            return ResultBean.error(ResultEnum.ORDER_SERVICE_ERROR);
        }
    }
}
