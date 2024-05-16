package re.zepo.ctbcbank.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.zepo.ctbcbank.dto.request.OrderRequestParam;
import re.zepo.ctbcbank.dto.response.URLDecResponse;
import re.zepo.ctbcbank.dto.response.URLEncResponse;
import re.zepo.ctbcbank.dto.response.common.CodeEnum;
import re.zepo.ctbcbank.dto.response.common.ResponseDto;
import re.zepo.ctbcbank.service.CTBCBankService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zepore
 * @version 1.0
 * @description: TODO
 * @date 2024/5/15 9:50
 */
@Slf4j
@RestController
@RequestMapping("/ctbc-bank")
public class CTBCBankController {
    @Autowired
    private CTBCBankService ctbcBankService;
    @RequestMapping("/hello")
    public String hello() {
        return "Hello, CTBC Bank!";
    }

    // get-url-enc
    @PostMapping("/get-url-enc")
    public ResponseDto getURLEnc(OrderRequestParam orderRequestParam) {
        log.info("getURLEnc方法 --- orderRequestParam: " + orderRequestParam);
        URLEncResponse data = ctbcBankService.getURLEnc(orderRequestParam);
        return new ResponseDto(data);
    }

    // callback
    @PostMapping("/request-pay-callback")
    public ResponseDto requestPayCallback(String URLResEnc, String merID) {
        log.info("requestPayCallback方法 --- URLResEnc: " + URLResEnc);
        URLDecResponse urlDec = ctbcBankService.getURLDec(URLResEnc);
        ResponseDto responseDto = new ResponseDto(urlDec);
        responseDto.setData(urlDec);
        if(urlDec == null) {
            responseDto.setCode(CodeEnum.LACK);
            responseDto.setMessage("request-pay-callback failed: " + urlDec.getErrDesc());
        } else if (!urlDec.getStatus().equals("0")) {
            System.out.println("urlDec: " + urlDec);
            responseDto.setCode(CodeEnum.FAIL);
            responseDto.setMessage("request-pay-callback failed: " + urlDec.getErrDesc());
        } else {
            // 等于0表示成功
            responseDto.setMessage("request-pay-callback success");
        }
        return responseDto;
    }
}
