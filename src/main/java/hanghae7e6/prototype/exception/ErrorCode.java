package hanghae7e6.prototype.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST 잘못된 요청 */
    ALREADY_LOGIN("이미 로그인이 되어있습니다."),
    INVALID_REGISTER_EMAIL("이메일 형식이 유효하지 않습니다."),
    INVALID_REGISTER_PASSWORD( "비밀번호 형식이 유효하지 않습니다."),
    INVALID_REGISTER_USERNAME( "이름을 입력해 주세요."),
    INVALID_LOGIN( "이메일 또는 패스워드를 확인해 주세요."),
    USER_ALREADY_EXISTS("이미 존재하는 사용자입니다."),
    INVALID_TAG("잘못된 태그입니다."),
    TAG_ALREADY_EXISTS("이미 존재하는 태그입니다."),
    POSITION_ALREADY_EXISTS("이미 존재하는 포지션입니다."),
    INVALID_POSITION("잘못되거나 없는 포지션입니다."),
    APPLICANT_ALREADY_EXISTS("해당 프로젝트에 이미 신청하셨습니다."),
    APPLICANT_IS_AUTHOR("게시글 작성자는 신청할 수 없습니다."),
    EMPTY_BODY("필요한 값이 비어있거나 잘못되어 있습니다. 값 형식을 확인해 주세요!"),
    EMPTY_POSITION("포지션이 비어있습니다."),

    /* 401 UNAUTHORIZED 인증되지 않은 사용자 */
    LOGIN_REQUIRED( "로그인이 필요합니다."),
    NOT_AUTHOR("해당 글의 작성자가 아닙니다."),

    /* 404 NOT_FOUND  리소스를 찾을 수 없음 */
    BOARD_NOT_FOUND("해당 게시글을 찾을 수 없습니다."),
    USER_NOT_FOUND("해당 사용자를 찾을 수 없습니다."),
    APPLICANT_NOT_FOUND("해당 프로젝트를 먼저 신청해 주세요!");

    private final String message;
}