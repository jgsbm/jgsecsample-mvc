package jgs.bluemix.sample.validation;

import jgs.bluemix.sample.web.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * パスワードと確認用パスワードの比較チェックを行うためのValidatorです.
 *
 * @author ryozo
 */
@Component
public class CustomerPasswordEqualsValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerForm.class.isAssignableFrom(clazz);
    }

    /**
     * 指定されたパスワードと確認用パスワードが合致するかチェックします.
     *
     * @param target 入力値を保持するオブジェクト
     * @param errors 判定結果
     */
    @Override
    public void validate(Object target, Errors errors) {
        CustomerForm form = (CustomerForm)target;
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();

        if (isEmpty(password) || isEmpty(confirmPassword)) {
            // BeanValidator側でチェックするので当Validatorのチェック対象外
            return;
        }

        if (!password.equals(confirmPassword)) {
            errors.rejectValue("password", "customvalidation.error.passwordNotEqual");
        }
    }
}
