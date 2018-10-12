package com.zhiyi.medicinebox.api.business.common.validator;

import lombok.Data;

@Data
public class ValidationResult {

    private boolean isValid;

    private String errorMessage;
}
