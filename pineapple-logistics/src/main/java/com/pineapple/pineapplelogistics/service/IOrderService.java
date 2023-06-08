package com.pineapple.pineapplelogistics.service;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.entity.Order;
import com.pineapple.pineapplelogistics.vo.OrderVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 1:02 2023/4/25
 * @Modifier by:
 */
public interface IOrderService {
    ResultBean createOrder(OrderVo orderVo);

    ResultBean updateOrderStatus(Long orderId, int orderStatus);


    ResultBean findOrderById(Long orderId);
}
