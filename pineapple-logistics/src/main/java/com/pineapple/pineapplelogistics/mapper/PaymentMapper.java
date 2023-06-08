package com.pineapple.pineapplelogistics.mapper;

import com.pineapple.pineapplelogistics.entity.Payment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 1:08 2023/4/25
 * @Modifier by:
 */
@Mapper

public interface PaymentMapper {
    /**
     * 新增一条支付记录
     * @param payment 支付记录实体类
     * @return 影响行数
     */
    @Insert("INSERT INTO t_payment(id,order_id,total_price,goods_price,receive_price,delivery_price,create_time,status) VALUES(#{id},#{orderId},#{totalPrice},#{goodsPrice},#{receivePrice},#{deliveryPrice},#{createTime},#{status})")
    int addPayment(Payment payment);

    /**
     * 修改支付记录信息
     * @param payment 支付记录实体类
     * @return 影响行数
     */
    @Update("UPDATE t_payment SET total_price=#{totalPrice},goods_price=#{goodsPrice},receive_price=#{receivePrice},delivery_price=#{deliveryPrice},status=#{status} WHERE id=#{id}")
    int updatePayment(Payment payment);

    /**
     * 修改支付记录的状态
     * @param id 支付记录ID
     * @param status 支付状态，0-未支付，1-已支付
     * @return 影响行数
     */
    @Update("UPDATE t_payment SET status=#{status} WHERE id=#{id}")
    int updatePaymentStatus(@Param("id") Long id, @Param("status") Boolean status);

    /**
     * 根据支付记录ID查询支付记录信息
     * @param id 支付记录ID
     * @return 支付记录实体类
     */
    @Select("SELECT id, order_id as orderId, total_price as totalPrice, goods_price as goodsPrice, receive_price as receivePrice, delivery_price as deliveryPrice, create_time as createTime, status FROM t_payment WHERE id=#{id}")
    Payment getPaymentById(Long id);


    @Insert({
            "<script>",
            "INSERT INTO t_payment(id, order_id, total_price, goods_price, receive_price, delivery_price, create_time, status) VALUES ",
            "<foreach collection='payments' item='payment' separator=','>",
            "(#{payment.id}, #{payment.orderId}, #{payment.totalPrice}, #{payment.goodsPrice}, #{payment.receivePrice}, #{payment.deliveryPrice}, #{payment.createTime}, #{payment.status})",
            "</foreach>",
            "</script>"
    })
    int insertBatchPayments(@Param("payments") List<Payment> payments);
}
