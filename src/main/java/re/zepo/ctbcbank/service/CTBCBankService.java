package re.zepo.ctbcbank.service;

import com.hyweb.posapi_npg.Decrypt;
import com.hyweb.posapi_npg.Encrypt;
import com.hyweb.posapi_npg.auth_in_mac_24;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import re.zepo.ctbcbank.dto.request.OrderRequestParam;
import re.zepo.ctbcbank.dto.response.URLDecResponse;
import re.zepo.ctbcbank.dto.response.URLEncResponse;

/**
 * @author Zepore
 * @version 1.0
 * @description: TODO
 * @date 2024/5/15 10:29
 */
@Service
public class CTBCBankService {
    @Value("${ctbc.key}")
    private String key;
    @Value("${ctbc.MerID}")
    private String MerId;
    @Value("${ctbc.MerchantID}")
    private String MerchantID;
    @Value("${ctbc.TerminalID}")
    private String TerminalID;
    @Value("${ctbc.txType}")
    private String txType;
    @Value("${ctbc.numberOfPay}")
    private String numberOfPay;
    @Value("${ctbc.callbackUrl}")
    private String callbackUrl;
    public URLEncResponse getURLEnc(OrderRequestParam orderRequestParam) {
        if (orderRequestParam == null) {
            return null;
        }
        // 获取订单参数
        String lidm = orderRequestParam.getLidm();
        //        產品代碼、分期期數或產品代碼加分期期數。純數字。
        //        (1) 一般特店請填「1」。
        //        (2) 分期特店請填長度為一到兩碼的分期期數。
        //        (3) 紅利特店請填長度為固定兩碼的產品代碼。
        //        (4) 紅利分期特店長度為三到四碼，前兩碼固定為產品代碼，後
        //        一碼或兩碼為分期期數。
        String Option = numberOfPay;
        String purchAmt = orderRequestParam.getPurchAmt();

        // 生成密文驗證值
        auth_in_mac_24 inMacTarget = new auth_in_mac_24(MerchantID, TerminalID, lidm, purchAmt, txType, Option, key);
        String inMac = inMacTarget.getMAC();

        // 生成加密值
        Encrypt enc= new Encrypt();
        enc.setMerchantID(MerchantID);
        enc.setTerminalID(TerminalID);
        enc.setMerchantName("信用卡網路測試端末機");
        enc.setLidm(lidm);//訂單編號，請自訂，最長19碼(提供英數字及底線_，其餘符號禁止使用)
        enc.setMerID(MerId);
        enc.setCustomize("0");//為特店是否使用上傳客製化授權頁。
        enc.setPurchAmt(purchAmt);
        enc.setTxType(txType);//设置分期交易
        enc.setNumberOfPay(numberOfPay);//分期期數
        //        enc.setAutoCap("0");
        //        enc.setProdCode("");
        enc.setInMac(inMac);
        enc.setAuthResURL(callbackUrl);
        enc.Encryption(key);
        String URLEnc=enc.getEnc();
        System.out.println("URLEnc="+URLEnc);

        // 构造返回体
        URLEncResponse response = new URLEncResponse();
        response.setMerID(MerId);
        response.setURLEnc(URLEnc);
        return response;
    }
    public URLDecResponse getURLDec(String URLEnc) {

        URLDecResponse urlDecResponse = new URLDecResponse();
        Decrypt dec = new Decrypt();
        dec.Decryption(URLEnc, key);
        String decResult = dec.getDec();
        int decErrCode = dec.getDecErrCode();
        String status = dec.getStatus();
        String errcode = dec.getErrcode();
        String errDesc = dec.getErrDesc();
        String outMac = dec.getOutMac();
        String merID = dec.getMerID();
        String authAmt = dec.getAuthAmt();
        String lidm = dec.getLidm();
        String xid = dec.getXid();
        String authCode = dec.getAuthCode();
        String last4digitPAN = dec.getLast4digitPAN();
        String authResURL = dec.getAuthResURL();
        String offsetAmt = dec.getOffsetAmt();
        String originalAmt = dec.getOriginalAmt();
        String utilizedPoint = dec.getUtilizedPoint();
        String awardedPoint = dec.getAwardedPoint();
        String pointBalance = dec.getPointBalance();
        String prodCode = dec.getProdCode();
        String termSeq = dec.getTermSeq();
        String numberOfPay = dec.getNumberOfPay();
        boolean checkURLOutMac = dec.CheckURLOutMac();
        String cardNumber = dec.getCardNumber();
        String errorCode = dec.getErrorCode();
        String errorMessage = dec.getErrorMessage();

        urlDecResponse.setDec(decResult);
        urlDecResponse.setDecErrCode(decErrCode);
        urlDecResponse.setStatus(status);
        urlDecResponse.setErrcode(errcode);
        urlDecResponse.setErrDesc(errDesc);
        urlDecResponse.setOutMac(outMac);
        urlDecResponse.setMerID(merID);
        urlDecResponse.setAuthAmt(authAmt);
        urlDecResponse.setLidm(lidm);
        urlDecResponse.setXid(xid);
        urlDecResponse.setAuthCode(authCode);
        urlDecResponse.setLast4digitPAN(last4digitPAN);
        urlDecResponse.setAuthResURL(authResURL);
        urlDecResponse.setOffsetAmt(offsetAmt);
        urlDecResponse.setOriginalAmt(originalAmt);
        urlDecResponse.setUtilizedPoint(utilizedPoint);
        urlDecResponse.setAwardedPoint(awardedPoint);
        urlDecResponse.setPointBalance(pointBalance);
        urlDecResponse.setProdCode(prodCode);
        urlDecResponse.setTermSeq(termSeq);
        urlDecResponse.setNumberOfPay(numberOfPay);
        urlDecResponse.setCheckURLOutMac(checkURLOutMac);
        urlDecResponse.setCardNumber(cardNumber);
        urlDecResponse.setErrorCode(errorCode);
        urlDecResponse.setErrorMessage(errorMessage);
        return urlDecResponse;
    }
}
