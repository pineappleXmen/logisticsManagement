package com.pineapple.pineapplelogistics.controller;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.entity.Order;
import com.pineapple.pineapplelogistics.service.IOrderService;
import com.pineapple.pineapplelogistics.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 20:47 2023/5/6
 * @Modifier by:
 */
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public ResultBean createOrder(@RequestBody OrderVo orderVo) {
        System.out.println(orderVo);
        return orderService.createOrder(orderVo);
    }

    @GetMapping("/{id}")
    public ResultBean findOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PutMapping("/{id}")
    public ResultBean updateOrderStatus(@PathVariable Long id, @RequestParam int orderStatus) {
        return orderService.updateOrderStatus(id, orderStatus);
    }
}
