package com.ongi.ongi_back.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class TossErrorStatusHandler {
  private static final Map<String, HttpStatus> FAILURE_CODE_STATUS_MAP = Map.ofEntries(
    // 400 Bad Request
    Map.entry("ALREADY_PROCESSED_PAYMENT", HttpStatus.BAD_REQUEST),
    Map.entry("PROVIDER_ERROR", HttpStatus.BAD_REQUEST),
    Map.entry("EXCEED_MAX_CARD_INSTALLMENT_PLAN", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_REQUEST", HttpStatus.BAD_REQUEST),
    Map.entry("NOT_ALLOWED_POINT_USE", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_API_KEY", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_REJECT_CARD", HttpStatus.BAD_REQUEST),
    Map.entry("BELOW_MINIMUM_AMOUNT", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_CARD_EXPIRATION", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_STOPPED_CARD", HttpStatus.BAD_REQUEST),
    Map.entry("EXCEED_MAX_DAILY_PAYMENT_COUNT", HttpStatus.BAD_REQUEST),
    Map.entry("NOT_SUPPORTED_INSTALLMENT_PLAN_CARD_OR_MERCHANT", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_CARD_INSTALLMENT_PLAN", HttpStatus.BAD_REQUEST),
    Map.entry("NOT_SUPPORTED_MONTHLY_INSTALLMENT_PLAN", HttpStatus.BAD_REQUEST),
    Map.entry("EXCEED_MAX_PAYMENT_AMOUNT", HttpStatus.BAD_REQUEST),
    Map.entry("NOT_FOUND_TERMINAL_ID", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_AUTHORIZE_AUTH", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_CARD_LOST_OR_STOLEN", HttpStatus.BAD_REQUEST),
    Map.entry("RESTRICTED_TRANSFER_ACCOUNT", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_CARD_NUMBER", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_UNREGISTERED_SUBMALL", HttpStatus.BAD_REQUEST),
    Map.entry("NOT_REGISTERED_BUSINESS", HttpStatus.BAD_REQUEST),
    Map.entry("EXCEED_MAX_ONE_DAY_WITHDRAW_AMOUNT", HttpStatus.BAD_REQUEST),
    Map.entry("EXCEED_MAX_ONE_TIME_WITHDRAW_AMOUNT", HttpStatus.BAD_REQUEST),
    Map.entry("CARD_PROCESSING_ERROR", HttpStatus.BAD_REQUEST),
    Map.entry("EXCEED_MAX_AMOUNT", HttpStatus.BAD_REQUEST),
    Map.entry("INVALID_ACCOUNT_INFO_RE_REGISTER", HttpStatus.BAD_REQUEST),
    Map.entry("NOT_AVAILABLE_PAYMENT", HttpStatus.BAD_REQUEST),
    Map.entry("UNAPPROVED_ORDER_ID", HttpStatus.BAD_REQUEST),
    Map.entry("EXCEED_MAX_MONTHLY_PAYMENT_AMOUNT", HttpStatus.BAD_REQUEST),

    // 401 Unauthorized
    Map.entry("UNAUTHORIZED_KEY", HttpStatus.UNAUTHORIZED),

    // 403 Forbidden
    Map.entry("REJECT_ACCOUNT_PAYMENT", HttpStatus.FORBIDDEN),
    Map.entry("REJECT_CARD_PAYMENT", HttpStatus.FORBIDDEN),
    Map.entry("REJECT_CARD_COMPANY", HttpStatus.FORBIDDEN),
    Map.entry("FORBIDDEN_REQUEST", HttpStatus.FORBIDDEN),
    Map.entry("REJECT_TOSSPAY_INVALID_ACCOUNT", HttpStatus.FORBIDDEN),
    Map.entry("EXCEED_MAX_AUTH_COUNT", HttpStatus.FORBIDDEN),
    Map.entry("EXCEED_MAX_ONE_DAY_AMOUNT", HttpStatus.FORBIDDEN),
    Map.entry("NOT_AVAILABLE_BANK", HttpStatus.FORBIDDEN),
    Map.entry("INVALID_PASSWORD", HttpStatus.FORBIDDEN),
    Map.entry("INCORRECT_BASIC_AUTH_FORMAT", HttpStatus.FORBIDDEN),
    Map.entry("FDS_ERROR", HttpStatus.FORBIDDEN),

    // 404 Not Found
    Map.entry("NOT_FOUND_PAYMENT", HttpStatus.NOT_FOUND),
    Map.entry("NOT_FOUND_PAYMENT_SESSION", HttpStatus.NOT_FOUND)
);

public static HttpStatus resolve(String failureCode) {
    return FAILURE_CODE_STATUS_MAP.getOrDefault(failureCode, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
