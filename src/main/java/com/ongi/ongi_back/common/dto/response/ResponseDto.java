package com.ongi.ongi_back.common.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class ResponseDto {
    private String code;
    private String message;

    public ResponseDto() {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    public static ResponseEntity<ResponseDto> success(HttpStatus status) {
        ResponseDto body = new ResponseDto();
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<ResponseDto> warning(HttpStatus status) {
        ResponseDto body = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.WARNING);
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<ResponseDto> deleted(HttpStatus status) {
        ResponseDto body = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.DELETED);
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<ResponseDto> withdrawned(HttpStatus status) {
        ResponseDto body = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.WITHDRAWNED);
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<ResponseDto> alreadyWithdrawned(HttpStatus status) {
        ResponseDto body = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.ALREADY_WITHDRAWNED);
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<ResponseDto> validationFail() {
        ResponseDto body = new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> productNotFound() {
        ResponseDto body = new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.PRODUCT_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> existUser() {
        ResponseDto body = new ResponseDto(ResponseCode.EXIST_USER, ResponseMessage.EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> unverifiedPhoneNumber() {
        ResponseDto body = new ResponseDto(ResponseCode.UNVERIFIED_PHONE_NUMBER, ResponseMessage.UNVERIFIED_PHONE_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidPhoneNumberFormat() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_PHONE_NUMBER_FORMAT, ResponseMessage.INVALID_PHONE_NUMBER_FORMAT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidAuthenticationNumber() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_AUTHENTICATION_NUMBER, ResponseMessage.INVALID_AUTHENTICATION_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidToken() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_TOKEN, ResponseMessage.INVALID_TOKEN);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> mismatchPreviousPassword() {
        ResponseDto body = new ResponseDto(ResponseCode.MISMATCH_PREVIOUS_PASSWORD, ResponseMessage.MISMATCH_PREVIOUS_PASSWORD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> alreadyLeftAnswer() {
        ResponseDto body = new ResponseDto(ResponseCode.ALREADY_LEFT_ANSWER, ResponseMessage.ALREADY_LEFT_ANSWER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> existPayment() {
        ResponseDto body = new ResponseDto(ResponseCode.EXIST_PAYMENT, ResponseMessage.EXIST_PAYMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> alreadyProcessedPayment() {
        ResponseDto body = new ResponseDto(ResponseCode.ALREADY_PROCESSED_PAYMENT, ResponseMessage.ALREADY_PROCESSED_PAYMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidPaymentKey() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_PAYMENT_KEY, ResponseMessage.INVALID_PAYMENT_KEY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidRequest() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_REQUEST, ResponseMessage.INVALID_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidApiKey() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_API_KEY, ResponseMessage.INVALID_API_KEY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidStoppedCard() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_STOPPED_CARD, ResponseMessage.INVALID_STOPPED_CARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> exceedMaxDailyPaymentCount() {
        ResponseDto body = new ResponseDto(ResponseCode.EXCEED_MAX_DAILY_PAYMENT_COUNT, ResponseMessage.EXCEED_MAX_DAILY_PAYMENT_COUNT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> exceedMaxPaymentAmount() {
        ResponseDto body = new ResponseDto(ResponseCode.EXCEED_MAX_DAILY_PAYMENT_COUNT, ResponseMessage.EXCEED_MAX_DAILY_PAYMENT_COUNT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidAuthorizeAuth() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_AUTHORIZE_AUTH, ResponseMessage.INVALID_AUTHORIZE_AUTH);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidCardNumber() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_CARD_NUMBER, ResponseMessage.INVALID_CARD_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> notAvailablePayment() {
        ResponseDto body = new ResponseDto(ResponseCode.NOT_AVAILABLE_PAYMENT, ResponseMessage.NOT_AVAILABLE_PAYMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidDate() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_DATE, ResponseMessage.INVALID_DATE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidTransactionKey() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_TRANSACTION_KEY, ResponseMessage.INVALID_TRANSACTION_KEY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> alreadyCanceledPayment() {
        ResponseDto body = new ResponseDto(ResponseCode.ALREADY_CANCELED_PAYMENT, ResponseMessage.ALREADY_CANCELED_PAYMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noExistProduct() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_EXIST_PRODUCT, ResponseMessage.NO_EXIST_PRODUCT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_EXIST_USER, ResponseMessage.NO_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noExistNeedhelperPost() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_EXIST_NEEDHELPERPOST, ResponseMessage.NO_EXIST_NEEDHELPERPOST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noExistChatRoom() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_EXIST_CHATROOM, ResponseMessage.NO_EXIST_CHATROOM);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noExistPost() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_EXIST_POST, ResponseMessage.NO_EXIST_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noExsitComment() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_EXIST_COMMENT, ResponseMessage.NO_EXIST_COMMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noExistSchedule() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_EXIST_SCHEDULE, ResponseMessage.NO_EXIST_SCHEDULE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> notEnoughPoint() {
        ResponseDto body = new ResponseDto(ResponseCode.NOT_ENOUGH_POINT, ResponseMessage.NOT_ENOUGH_POINT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> noSearchKeyword() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_SEARCH_KEYWORD, ResponseMessage.NO_SEARCH_KEYWORD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> alreadyLikedPost() {
        ResponseDto body = new ResponseDto(ResponseCode.ALREADY_LIKED_POST, ResponseMessage.ALREADY_LIKED_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<ResponseDto> unauthorizedKey() {
        ResponseDto body = new ResponseDto(ResponseCode.UNAUTHORIZED_KEY, ResponseMessage.UNAUTHORIZED_KEY);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    public static ResponseEntity<ResponseDto> wrongPassword() {
        ResponseDto body = new ResponseDto(ResponseCode.WRONG_PASSWORD, ResponseMessage.WRONG_PASSWORD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    public static ResponseEntity<ResponseDto> authFail() {
        ResponseDto body = new ResponseDto(ResponseCode.AUTH_FAIL, ResponseMessage.AUTH_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto body = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static ResponseEntity<ResponseDto> rejectAccountPayment() {
        ResponseDto body = new ResponseDto(ResponseCode.REJECT_ACCOUNT_PAYMENT, ResponseMessage.REJECT_ACCOUNT_PAYMENT);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static ResponseEntity<ResponseDto> rejectCardCompany() {
        ResponseDto body = new ResponseDto(ResponseCode.REJECT_CARD_COMPANY, ResponseMessage.REJECT_CARD_COMPANY);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static ResponseEntity<ResponseDto> forbiddenRequest() {
        ResponseDto body = new ResponseDto(ResponseCode.FORBIDDEN_REQUEST, ResponseMessage.FORBIDDEN_REQUEST);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static ResponseEntity<ResponseDto> invalidPassword() {
        ResponseDto body = new ResponseDto(ResponseCode.INVALID_PASSWORD, ResponseMessage.INVALID_PASSWORD);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static ResponseEntity<ResponseDto> forbiddenConsecutiveRequest() {
        ResponseDto body = new ResponseDto(ResponseCode.FORBIDDEN_CONSECUTIVE_REQUEST, ResponseMessage.FORBIDDEN_CONSECUTIVE_REQUEST);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static ResponseEntity<ResponseDto> notCancelablePayment() {
        ResponseDto body = new ResponseDto(ResponseCode.NOT_CANCELABLE_PAYMENT, ResponseMessage.NOT_CANCELABLE_PAYMENT);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static ResponseEntity<ResponseDto> notFoundPayment() {
        ResponseDto body = new ResponseDto(ResponseCode.NOT_FOUND_PAYMENT, ResponseMessage.NOT_FOUND_PAYMENT);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    public static ResponseEntity<ResponseDto> alreadyApplied() {
        ResponseDto body = new ResponseDto(ResponseCode.ALREADY_APPLIED, ResponseMessage.ALREADY_APPLIED);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto body = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    public static ResponseEntity<ResponseDto> openAIError() {
        ResponseDto body = new ResponseDto(ResponseCode.OPEN_AI_ERROR, ResponseMessage.OPEN_AI_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    public static ResponseEntity<ResponseDto> failedInternalSystemProcessing() {
        ResponseDto body = new ResponseDto(ResponseCode.FAILED_INTERNAL_SYSTEM_PROCESSING, ResponseMessage.FAILED_INTERNAL_SYSTEM_PROCESSING);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    public static ResponseEntity<ResponseDto> failedPaymentInternalSystemProcessing() {
        ResponseDto body = new ResponseDto(ResponseCode.FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING, ResponseMessage.FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
