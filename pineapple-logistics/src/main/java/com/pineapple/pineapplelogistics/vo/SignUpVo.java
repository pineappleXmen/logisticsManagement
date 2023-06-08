package com.pineapple.pineapplelogistics.vo;

import com.pineapple.pineapplelogistics.annotation.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:31 2023/4/25
 * @Modifier by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpVo {

    @NotNull
    private String name;

    @NotNull
    @Phone
    private String phone;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 32)
    private String password;


    @NotNull
    @Length(min = 6,max = 6)
    private String code;
}
