package com.pineapple.pineapplelogistics.service;

import com.pineapple.pineapplelogistics.bean.ResultBean;
import com.pineapple.pineapplelogistics.vo.ClientVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 1:04 2023/4/25
 * @Modifier by:
 */
public interface IClientService {
    ResultBean doAddClient(ClientVo clientVo, HttpServletRequest request, HttpServletResponse response);
    ResultBean doUpdateClient(ClientVo clientVo, HttpServletRequest request, HttpServletResponse response);
    ResultBean doSearchClient(ClientVo clientVo, HttpServletRequest request, HttpServletResponse response);
    ResultBean doRemoveClient(ClientVo clientVo, HttpServletRequest request, HttpServletResponse response);
}
