package com.zhiyi.medicinebox.api.application.controller;

import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {


    @RequestMapping("/test")
    @ResponseBody
    public ParmResponse test(String name) throws IOException {
        return ResponseUtils.getBeanResponse("SUCCESS", "");
    }

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "";
        String s3 = " ";
        String s4 = null;
        System.out.println(StringUtils.isBlank(s1) + "  " + StringUtils.isEmpty(s1));
        System.out.println(StringUtils.isBlank(s2) + "  " + StringUtils.isEmpty(s2));
        System.out.println(StringUtils.isBlank(s3) + "  " + StringUtils.isEmpty(s3));
        System.out.println(StringUtils.isBlank(s4) + "  " + StringUtils.isEmpty(s4));
    }
}
