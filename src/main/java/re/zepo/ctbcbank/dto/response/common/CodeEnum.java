package re.zepo.ctbcbank.dto.response.common;

public enum CodeEnum {
    SUCCESS(200, "操作成功"),
    LACK(900, "参数或数据缺失"),
    FAIL(901, "操作失败"),
    ERROR(500, "服务器错误");

    private final int code;
    private final String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}