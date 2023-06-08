package com.pineapple.pineapplelogistics.mapper;

import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.vo.ItemVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 23:21 2023/4/25
 * @Modifier by:
 */
@Mapper
public interface GoodsMapper {

    @Insert("INSERT INTO t_goods(id, order_id, name, weight_or_volume, unit_price, freight, count) " +
            "VALUES(#{id}, #{orderId}, #{name}, #{weightOrVolume}, #{unitPrice}, #{freight}, #{count})")
    int addGoods(Goods goods);

    @Update("UPDATE t_goods SET name=#{name}, weight_or_volume=#{weightOrVolume}, unit_price=#{unitPrice}, freight=#{freight}, count=#{count} WHERE id=#{id}")
    int updateGoods(Goods goods);

    @Select("SELECT id, order_id as orderId, name, weight_or_volume as weightOrVolume, unit_price as unitPrice, freight, count FROM t_goods WHERE order_id=#{orderId}")
    List<Goods> getGoodsByOrderId(Long orderId);

    @Insert({
            "<script>",
            "INSERT INTO t_goods(id,order_id, name, weight_or_volume, unit_price, freight, count) VALUES ",
            "<foreach collection='goodsList' item='item' separator=','>",
            "(#{item.id},#{orderId}, #{item.name}, #{item.weightOrVolume}, #{item.unitPrice}, #{item.freight}, #{item.count})",
            "</foreach>",
            "</script>"
    })
    int batchAddGoods(@Param("orderId") Long orderId, @Param("goodsList") List<Goods> goodsList);

}
