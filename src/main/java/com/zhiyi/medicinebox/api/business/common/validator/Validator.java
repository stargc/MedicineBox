package com.zhiyi.medicinebox.api.business.common.validator;

/**
 * 验证器接口，用于验证对象的字段是否符合要求
 * @param <T>
 */
public interface Validator<T> {

    /**
     * 验证传入的对象是否符合要求
     * @param object
     * @return
     */
    ValidationResult validate(T object);
}
