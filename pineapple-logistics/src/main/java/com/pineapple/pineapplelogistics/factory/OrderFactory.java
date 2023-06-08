package com.pineapple.pineapplelogistics.factory;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.entity.Order;
import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.mapper.OrderMapper;
import com.pineapple.pineapplelogistics.utils.SnowflakeUtils;
import com.pineapple.pineapplelogistics.vo.OrderVo;
import com.pineapple.pineapplelogistics.vo.PaymentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.pineapple.pineapplelogistics.constriant.OrderConst.ORDER_CREATED;
import static com.pineapple.pineapplelogistics.constriant.SnowflakeConst.ORDER_WORKER_ID;
import static com.pineapple.pineapplelogistics.constriant.SnowflakeConst.START_TIME_STAMP;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 21:14 2023/5/6
 * @Modifier by:
 */
@Component
public class OrderFactory {

    public static Order createOrder(OrderVo orderVo) {
        Order order = new Order();
        BeanUtils.copyProperties(orderVo, order);
        // 需要手动设置id和createTime等字段
        SnowflakeUtils snowflakeUtils = new SnowflakeUtils(START_TIME_STAMP,ORDER_WORKER_ID);
        long orderId = snowflakeUtils.get();
          // 新建订单时，id为空，由数据库自动生成
        LocalDateTime now = LocalDateTime.now();

        order.setOrderId(orderId);
        order.setCreateTime(now);
        order.setUpdateTime(now);
        order.setOrderStatus(ORDER_CREATED);

        List<Payment> payments = PaymentFactory.createPayment(orderVo.getPaymentVos(),orderId);
        order.setPayments(payments);

        List<Goods> goodsList = GoodsFactory.createGood(orderVo.getGoodsList(), orderId);
        order.setGoodsList(goodsList);

        return order;
    }


}
