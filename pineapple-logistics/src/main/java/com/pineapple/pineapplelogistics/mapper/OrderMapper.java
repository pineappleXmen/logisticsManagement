package com.pineapple.pineapplelogistics.mapper;

import com.pineapple.pineapplelogistics.entity.Order;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 18:27 2023/5/6
 * @Modifier by:
 */
@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO t_order (order_id, order_status, create_time, update_time, sender_name, sender_phone, sender_address, receiver_name, receiver_phone, receiver_address, sender_city, receiver_city) " +
            "VALUES (#{order.orderId}, #{order.orderStatus}, #{order.createTime}, #{order.updateTime}, #{order.senderName}, #{order.senderPhone}, #{order.senderAddress}, #{order.receiverName}, #{order.receiverPhone}, #{order.receiverAddress}, #{order.senderCity}, #{order.receiverCity})")
    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    int createOrder(@Param("order") Order order);

    @Select("SELECT * FROM t_order WHERE order_id = #{id}")
    @Results(id = "orderResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "sender_name", property = "senderName"),
            @Result(column = "sender_phone", property = "senderPhone"),
            @Result(column = "sender_address", property = "senderAddress"),
            @Result(column = "receiver_name", property = "receiverName"),
            @Result(column = "receiver_phone", property = "receiverPhone"),
            @Result(column = "receiver_address", property = "receiverAddress"),
            @Result(column = "sender_city", property = "senderCity"),
            @Result(column = "receiver_city", property = "receiverCity"),
            @Result(column = "id", property = "payments", many = @Many(select = "com.example.mapper.PaymentMapper.findPaymentsByOrderId")),
            @Result(column = "id", property = "goodsList", many = @Many(select = "com.example.mapper.GoodsMapper.findGoodsListByOrderId"))
    })
    Order findOrderById(Long id);

    @Update("UPDATE t_order SET order_status=#{order.orderStatus}, update_time=#{order.updateTime}, sender_name=#{order.senderName}, sender_phone=#{order.senderPhone}, sender_address=#{order.senderAddress}, receiver_name=#{order.receiverName}, receiver_phone=#{order.receiverPhone}, receiver_address=#{order.receiverAddress}, sender_city=#{order.senderCity}, receiver_city=#{order.receiverCity} WHERE id=#{order.id}")
    int updateOrder(@Param("order") Order order);



    @Update("UPDATE t_order SET order_status=#{orderStatus}, update_time=#{updateTime} WHERE id=#{orderId}")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatus") int orderStatus, @Param("updateTime") LocalDateTime updateTime);

}

