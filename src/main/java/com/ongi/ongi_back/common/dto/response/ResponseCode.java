package com.ongi.ongi_back.common.dto.response;

public interface ResponseCode {
    // HTTP/1.1 200 OK
    String SUCCESS = "SU";
    // ============================================== //
    // HTTP/1.1 400 Bad Request
    String VALIDATION_FAIL = "VF";
    String EXIST_USER = "EU";

    String UNVERIFIED_PHONE_NUMBER = "UP";
    String INVALID_PHONE_NUMBER_FORMAT = "IPF";
    String INVALID_AUTHENTICATION_NUMBER = "IA";
    String INVALID_TOKEN = "IT";

    String MISMATCH_PREVIOUS_PASSWORD = "MP";

    String ALREADY_LEFT_ANSWER = "AL";

    String EXIST_PAYMENT = "EP";
    String ALREADY_PROCESSED_PAYMENT = "ALREADY_PROCESSED_PAYMENT";
    String INVALID_PAYMENT_KEY = "INVALID_PAYMENT_KEY";
    String INVALID_REQUEST = "INVALID_REQUEST";
    String INVALID_API_KEY = "INVALID_API_KEY";
    String INVALID_STOPPED_CARD = "INVALID_STOPPED_CARD";
    String EXCEED_MAX_DAILY_PAYMENT_COUNT = "EXCEED_MAX_DAILY_PAYMENT_COUNT";
    String EXCEED_MAX_PAYMENT_AMOUNT = "EXCEED_MAX_PAYMENT_AMOUNT";
    String INVALID_AUTHORIZE_AUTH = "INVALID_AUTHORIZE_AUTH";
    String INVALID_CARD_NUMBER = "INVALID_CARD_NUMBER";
    String NOT_AVAILABLE_PAYMENT = "NOT_AVAILABLE_PAYMENT";

    String ALREADY_CANCELED_PAYMENT = "ALREADY_CANCELED_PAYMENT";
    
    String INVALID_DATE = "INVALID_DATE";
    String INVALID_TRANSACTION_KEY = "INVALID_TRANSACTION_KEY";

    String NO_EXIST_PRODUCT = "NEP";

    String NO_EXIST_USER = "NU";
    
    String NO_EXIST_NEEDHELPERPOST = "NN";
    String NO_EXIST_CHATROOM = "NC";
    String NO_EXIST_POST = "NPS";
    String NO_EXIST_COMMENT = "NEC";
    String NO_EXIST_SCHEDULE = "NS";
    
    String NOT_ENOUGH_POINT = "NPO";
    // ============================================== //
    // HTTP/1.1 401 Unauthorized
    String UNAUTHORIZED_KEY = "UNAUTHORIZED_KEY";
    
    String WRONG_PASSWORD = "WP";
    String AUTH_FAIL = "AF";
    // ============================================== //
    // HTTP/1.1 403 Forbidden
    String NO_PERMISSION = "NP";

    String REJECT_ACCOUNT_PAYMENT = "REJECT_ACCOUNT_PAYMENT";
    String REJECT_CARD_COMPANY = "REJECT_CARD_COMPANY";
    String FORBIDDEN_REQUEST = "FORBIDDEN_REQUEST";
    String INVALID_PASSWORD = "INVALID_PASSWORD";

    String FORBIDDEN_CONSECUTIVE_REQUEST = "FORBIDDEN_CONSECUTIVE_REQUEST";
    
    String NOT_CANCELABLE_PAYMENT = "NOT_CANCELABLE_PAYMENT";
    // ============================================== //
    // HTTP/1.1 404 Not Found
    String NOT_FOUND_PAYMENT = "NOT_FOUND_PAYMENT";
    // ============================================== //
    // HTTP/1.1 409 Conflict
    String ALREADY_APPLIED = "AA";
    // ============================================== //
    // HTTP/1.1 500 Internal Server Error
    String DATABASE_ERROR = "DBE";
    String OPEN_AI_ERROR = "OAE";

    String FAILED_INTERNAL_SYSTEM_PROCESSING = "FAILED_INTERNAL_SYSTEM_PROCESSING";    

    String FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING = "FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING";
}
