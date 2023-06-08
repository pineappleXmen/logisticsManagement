package com.pineapple.pineapplelogistics.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 1:05 2023/4/25
 * @Modifier by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientVo {
    private long clientId;
    private long userId;
    private String clientName;
    private String clientAddr;
    private long clientPhone;
    private List<Long> connections;
}
