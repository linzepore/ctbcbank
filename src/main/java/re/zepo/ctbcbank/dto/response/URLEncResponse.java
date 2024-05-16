package re.zepo.ctbcbank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zepore
 * @version 1.0
 * @description: TODO
 * @date 2024/5/15 10:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class URLEncResponse {
    private String URLEnc; // 加密值
    private String MerID; // 網路特店編號
}
