package re.zepo.ctbcbank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zepore
 * @version 1.0
 * @description: TODO
 * @date 2024/5/15 10:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestParam {
    String lidm;    //訂單編號，請自訂，最長19碼(提供英數字及底線_，其餘符號禁止使用)
    String purchAmt; //交易金額
}
