package com.pineapple.pineapplelogistics.exception;

import com.pineapple.pineapplelogistics.bean.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 20:15 2023/4/23
 * @Modifier by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private ResultEnum resultEnum;
}
