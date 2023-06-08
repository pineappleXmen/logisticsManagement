package com.pineapple.pineapplelogistics.factory;

import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.utils.DateUtils;
import com.pineapple.pineapplelogistics.utils.SnowflakeUtils;
import com.pineapple.pineapplelogistics.vo.GoodsVo;
import com.pineapple.pineapplelogistics.vo.PaymentVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static com.pineapple.pineapplelogistics.constriant.SnowflakeConst.*;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 21:45 2023/5/6
 * @Modifier by:
 */
public class GoodsFactory {
    public static List<Goods> createGood(List<GoodsVo> goodsVos, long orderId) {
        List<Goods> goods = new ArrayList<>();
        for (GoodsVo goodsVo:goodsVos){
            Goods good = new Goods();
            SnowflakeUtils snowflakeUtils = new SnowflakeUtils(START_TIME_STAMP,GOODS_WORKER_ID);
            long goodId = snowflakeUtils.get();
            good.setOrderId(orderId);
            good.setId(goodId);
            good.setFreight(goodsVo.getFreight());
            good.setUnitPrice(goodsVo.getUnitPrice());
            good.setCount(goodsVo.getCount());
            good.setWeightOrVolume(goodsVo.getWeightOrVolume());
            good.setName(goodsVo.getName());
            goods.add(good);
        }
        return goods;
    }
}
