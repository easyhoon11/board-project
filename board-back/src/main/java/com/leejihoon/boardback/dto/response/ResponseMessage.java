package com.leejihoon.boardback.dto.response;

public interface ResponseMessage {
    
    String SUCCESS = "Success.";

    String VAILDATION_FAILES = "Validation failed.";
    String DUPLICATED_EMAIL = "Duplicated email.";
    String DUPLICATED_NICKNAME = "Duplicated nickname.";
    String DUPLICATED_TEL_NUMBER = "Duplicated telephone number.";

    String DATABASE_ERROR = "Database error.";
}
