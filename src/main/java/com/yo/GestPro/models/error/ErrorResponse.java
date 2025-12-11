package com.yo.GestPro.models.error;

import java.util.List;

public record ErrorResponse(int status, String message, List<ErrorField> error) {

    public static ErrorResponse standardError (int code, String message, List<ErrorField> errors) {
        return new ErrorResponse(code ,message, errors);
    }

}
