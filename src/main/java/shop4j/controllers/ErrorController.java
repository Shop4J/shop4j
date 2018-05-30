//package shop4j.controllers;
//
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import shop4j.annotions.shop.dataload.HeadDataLoad;
//
///**
// * @Author: weixuedong
// * @Date: 2018/5/30 17:49
// * @Description:错误处理
// */
//@Controller
//@RequestMapping("/error")
//public class ErrorController{
//
//    @GetMapping
//    @HeadDataLoad
//    public String error(Model model, HttpServletRequest request){
//        HttpStatus httpStatus = getStatus(request);
//        if(httpStatus.is4xxClientError()){
//            return "error/404";
//        }else{
//            return "error/404";
//        }
//    }
//
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if(statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return
//                HttpStatus.valueOf(statusCode);
//    }
//}
