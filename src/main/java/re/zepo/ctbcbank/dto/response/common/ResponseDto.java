package re.zepo.ctbcbank.dto.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zepore
 * @version 1.0
 * @description: TODO
 * @date 2024/5/15 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Object data;
    private String message;
    private CodeEnum code;
    public ResponseDto(Object data) {
        this.data = data;
        this.message = "success";
        this.code = CodeEnum.SUCCESS;
    }
}
