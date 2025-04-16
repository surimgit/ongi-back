package com.ongi.ongi_back.common.dto.response;

public interface ResponseMessage {
    // HTTP/1.1 200 OK
    String SUCCESS = "Success.";
    // ============================================== //
    // HTTP/1.1 400 Bad Request
    String WARNING = "Warning has been sent to the reported user.";
    String DELETED = "Reported content has been deleted.";
    String WITHDRAWNED = "User has been forcibly withdrawn and phone number saved.";
    String ALREADY_WITHDRAWNED ="User already withdrawn.";

    String VALIDATION_FAIL = "Validation Fail.";
    String PRODUCT_NOT_FOUND = "Product Not Found.";
    String EXIST_USER = "Exist User.";

    String UNVERIFIED_PHONE_NUMBER = "Unverified Phone Number.";
    String INVALID_PHONE_NUMBER_FORMAT = "Invalid Phone Number Format.";
    String INVALID_AUTHENTICATION_NUMBER = "Invalid Authentication Number.";
    String INVALID_TOKEN = "Invalid Token.";

    String MISMATCH_PREVIOUS_PASSWORD = "Mismatch Previous Password.";

    String ALREADY_LEFT_ANSWER = "Already Left Answer.";

    String EXIST_PAYMENT = "Exist Payment.";
    String ALREADY_PROCESSED_PAYMENT = "이미 처리된 결제 입니다.";
    String INVALID_PAYMENT_KEY = "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요..";
    String INVALID_REQUEST = "잘못된 요청입니다.";
    String INVALID_API_KEY = "잘못된 시크릿키 연동 정보 입니다..";
    String INVALID_STOPPED_CARD = "정지된 카드 입니다.";
    String EXCEED_MAX_DAILY_PAYMENT_COUNT = "하루 결제 가능 횟수를 초과했습니다.";
    String EXCEED_MAX_PAYMENT_AMOUNT = "하루 결제 가능 금액을 초과했습니다.";
    String INVALID_AUTHORIZE_AUTH = "유효하지 않은 인증 방식입니다.";
    String INVALID_CARD_NUMBER = "카드번호를 다시 확인해주세요.";
    String NOT_AVAILABLE_PAYMENT = "결제가 불가능한 시간대입니다.";

    String ALREADY_CANCELED_PAYMENT = "이미 환불된 결제입니다.";

    String INVALID_DATE = "날짜 데이터가 잘못 되었습니다.";
    String INVALID_TRANSACTION_KEY = "잘못된 페이징 키 입니다.";

    String NO_EXIST_PRODUCT = "No Exist Product.";

    String NO_EXIST_USER = "No Exist User.";

    String NO_EXIST_NEEDHELPERPOST = "No Exist NeedhelperPost.";
    String NO_EXIST_CHATROOM = "No Exist ChatRoom.";
    String NO_EXIST_POST = "No Exist Post.";
    String NO_EXIST_COMMENT = "No Exist Comment.";
    String NO_EXIST_SCHEDULE = "No Exist Schedule.";

    String NOT_ENOUGH_POINT = "Not Enough Point.";

    String NO_SEARCH_KEYWORD = "No Search Keyword.";

    String ALREADY_LIKED_POST = "Already Liked Post.";
    // ============================================== //
    // HTTP/1.1 401 Unauthorized
    String UNAUTHORIZED_KEY = "인증되지 않은 시크릿 키 혹은 클라이언트 키 입니다.";

    String WRONG_PASSWORD = "Wrong Password.";
    String AUTH_FAIL = "Auth Fail.";
    // ============================================== //
    // HTTP/1.1 403 Forbidden
    String NO_PERMISSION = "No Permission.";

    String REJECT_ACCOUNT_PAYMENT = "잔액부족으로 결제에 실패했습니다.";
    String REJECT_CARD_COMPANY = "결제 승인이 거절되었습니다.";
    String FORBIDDEN_REQUEST = "허용되지 않은 요청입니다.";
    String INVALID_PASSWORD = "결제 비밀번호가 일치하지 않습니다.";

    String FORBIDDEN_CONSECUTIVE_REQUEST = "반복적인 요청은 허용되지 않습니다. 잠시 후 다시 시도해주세요.";
    
    String NOT_CANCELABLE_PAYMENT = "취소할 수 없는 결제입니다.";
    // ============================================== //
    // HTTP/1.1 404 Not Found
    String NOT_FOUND_PAYMENT = "존재하지 않는 결제 정보 입니다.";
    // ============================================== //
    // HTTP/1.1 409 Conflict
    String ALREADY_APPLIED = "Already applied.";
    // ============================================== //
    // HTTP/1.1 500 Internal Server Error
    String DATABASE_ERROR = "Database Error.";
    String OPEN_AI_ERROR = "Open AI Error";

    String FAILED_INTERNAL_SYSTEM_PROCESSING = "내부 시스템 처리 작업이 실패했습니다. 잠시 후 다시 시도해주세요.";    
    String FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING = "결제가 완료되지 않았어요. 다시 시도해주세요..";
}
