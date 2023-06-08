package com.pineapple.pineapplelogistics.service.Impl;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.bean.ResultEnum;
import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.mapper.GoodsMapper;
import com.pineapple.pineapplelogistics.service.IGoodsService;
import com.pineapple.pineapplelogistics.utils.SnowflakeUtils;
import com.pineapple.pineapplelogistics.vo.GoodsVo;
import com.pineapple.pineapplelogistics.vo.ItemVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.pineapple.pineapplelogistics.constriant.SnowflakeConst.*;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 23:18 2023/4/25
 * @Modifier by:
 */
@Service
@AllArgsConstructor
public class GoodsServiceImpl implements IGoodsService {

    private final GoodsMapper goodsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ResultBean addGoods(Goods goods,long orderId) {
        SnowflakeUtils snowflakeUtils = new SnowflakeUtils(START_TIME_STAMP,GOODS_WORKER_ID);
        long goodsId = snowflakeUtils.get();
        goods.setId(goodsId);
        int result = goodsMapper.addGoods(goods);
        if (result > 0) {
            return ResultBean.success(ResultEnum.GOODS_CREATED, goodsId);
        } else {
            return ResultBean.error(ResultEnum.GOODS_SERVICE_ERROR);
        }
    }

    @Override
    public ResultBean getGoodsByOrderId(Long orderId) {
        List<Goods> goodsByOrderId = goodsMapper.getGoodsByOrderId(orderId);
        return ResultBean.success(ResultEnum.GOODS_UPDATED,goodsByOrderId);
    }

    @Override
    public ResultBean addBatchGoods(List<Goods> goodsList,long orderId) {
        SnowflakeUtils snowflakeUtils = new SnowflakeUtils(START_TIME_STAMP,GOODS_WORKER_ID);
        List<Long> goodsIdList = new ArrayList<>();
        for (Goods goods:goodsList){
            long goodsId = snowflakeUtils.get();
            goods.setId(goodsId);
            goodsIdList.add(goodsId);
        }
        if (goodsList.size()<1){
            return ResultBean.error(ResultEnum.GOODS_SERVICE_ERROR);
        }
        int result = goodsMapper.batchAddGoods(orderId,goodsList);
        if (result > 0) {
            return ResultBean.success(ResultEnum.GOODS_CREATED, goodsIdList);
        } else {
            return ResultBean.error(ResultEnum.GOODS_SERVICE_ERROR);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ResultBean updateGoods(Goods goods) {

        double deliveryPrice = goods.getWeightOrVolume()*goods.getUnitPrice();
        goods.setFreight(deliveryPrice);

        int result = goodsMapper.updateGoods(goods);
        if (result > 0) {
            return ResultBean.success(ResultEnum.GOODS_UPDATED);
        } else {
            return ResultBean.error(ResultEnum.GOODS_SERVICE_ERROR);
        }
    }
}
