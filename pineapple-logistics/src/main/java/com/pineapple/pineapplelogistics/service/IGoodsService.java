package com.pineapple.pineapplelogistics.service;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.entity.Goods;
import com.pineapple.pineapplelogistics.entity.Payment;
import com.pineapple.pineapplelogistics.vo.GoodsVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:49 2023/4/25
 * @Modifier by:
 */
public interface IGoodsService {
    ResultBean addGoods(Goods goods,long orderId);
    ResultBean addBatchGoods(List<Goods> goodsList,long orderId);
    ResultBean getGoodsByOrderId(Long orderId);
    ResultBean updateGoods(Goods goods);
}
