package com.pineapple.pineapplelogistics.annotation;

import com.pineapple.pineapplelogistics.utils.PhoneUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;


/**
 * @Author:Pineapple
 * @Description:
 * @Date: 19:55 2023/4/23
 * @Modifier by:
 */
public class PhoneValidator implements ConstraintValidator<Phone,String> {

    private boolean required = false;
    @Override
    public void initialize(Phone constraintAnnotation) {
        required = constraintAnnotation.required();

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return PhoneUtils.isValid(s);
        }else {
            if(StringUtils.isEmpty(s)){
                return false;
            }else {
                return PhoneUtils.isValid(s);
            }
        }
    }


}
