package re.zepo.ctbcbank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zepore
 * @version 1.0
 * @description: TODO
 * @date 2024/5/15 13:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class URLDecResponse {
    private String dec = "";
    private int decErrCode = -1;
    private String status = "-1";
    private String errcode = "";
    private String errDesc = "";
    private String OutMac = "";
    private String merID = "-1";
    private String authAmt = "-1";
    private String lidm = "";
    private String xid = "";
    private String authCode = "";
    private String Last4digitPAN = "";
    private String AuthResURL = "";
    private String OffsetAmt = "-1";
    private String OriginalAmt = "-1";
    private String UtilizedPoint = "-1";
    private String AwardedPoint = "-1";
    private String PointBalance = "-1";
    private String ProdCode = "";
    private String TermSeq = "";
    private String NumberOfPay = "-1";
    private boolean CheckURLOutMac = false;
    private String CardNumber = "";
    private String ErrorCode = "";
    private String ErrorMessage = "";
}
