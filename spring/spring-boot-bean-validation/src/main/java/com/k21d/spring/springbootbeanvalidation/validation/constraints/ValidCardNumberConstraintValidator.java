package com.k21d.spring.springbootbeanvalidation.validation.constraints;





import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCardNumberConstraintValidator implements ConstraintValidator<ValidCardNumber,String> {
    @Override
    public void initialize(ValidCardNumber constraintAnnotation) {

    }

    /**
     * 校验规则
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //为什么不用String#split，原因在于该方法使用了正则表达式，其实NPE保护不够
        //如果在依赖中，没有StringUtils.delimitedListToStringArray API .可以使用StringTokenizer
        String[] parts = StringUtils.split(value, "-");
        if (ArrayUtils.getLength(parts)!=2){
            return false;
        }

        String prefix = parts[0];
        String suffix = parts[1];

        boolean isValidPrefix = prefix.equals("kk");

        boolean isValidSuffix = StringUtils.isNumeric(suffix);


        return isValidPrefix && isValidSuffix;
    }
}
