package com.zhiyi.medicinebox.api.application.controller.alarm;

import com.zhiyi.medicinebox.api.business.common.validator.ValidationResult;
import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.alarm.add.AlarmAddReq;
import com.zhiyi.medicinebox.api.business.service.alarm.add.AlarmAddService;
import com.zhiyi.medicinebox.api.business.service.alarm.add.AlarmAddVal;
import com.zhiyi.medicinebox.api.business.service.alarm.search.vo.AlarmListResp;
import com.zhiyi.medicinebox.api.business.service.alarm.search.vo.AlarmResp;
import com.zhiyi.medicinebox.api.business.service.alarm.search.AlarmSearchService;
import com.zhiyi.medicinebox.api.business.service.alarm.updateStatus.AlarmUpdateStatusService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/alarm")
@Slf4j
public class AlarmController {

    @Resource
    private AlarmAddVal alarmVal;

    @Autowired
    private AlarmAddService addService;

    @Autowired
    private AlarmSearchService searchService;

    @Autowired
    private AlarmUpdateStatusService updateService;

    @RequestMapping("/getImage")
    public void getIcon(String url,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        log.info("get image “"+ url + "” from server");
        String fileName = url;
        File file = new File(fileName);
        try {
            //判断文件是否存在如果不存在就返回默认图标
            if (!(file.exists() && file.canRead())) {
                log.error("alarm/getImage url {} 文件不存在", url);
                return;
            }
            BufferedImage bufferedImage = ImageIO.read(file);
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(bufferedImage, "png", os);
        } catch (Exception e){
            log.error("get image 报错：" + ExceptionUtils.getStackTrace(e));
        }
    }

    @PostMapping("/add")
    public BaseResponse add(AlarmAddReq alarmAddReq,
                            @RequestParam(value = "file", required = false) MultipartFile file) {
        BaseResponse resp = new BaseResponse();
        ValidationResult val = alarmVal.validate(alarmAddReq);
        if (!val.isValid()) {
            log.error("新建提醒参数错误 + " + val.getErrorMessage());
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg(val.getErrorMessage());
            return resp;
        }
        return addService.responseValue(alarmAddReq, file);
    }

    @PostMapping("/findAlarmByUser")
    public AlarmListResp findAlarmByUser(int userId) {
        AlarmListResp resp = new AlarmListResp();
        if (userId == 0) {
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("参数异常");
            return resp;
        }

        resp = searchService.searchByUserId(userId);
        return resp;
    }

    /***
     * @param statusId  1：未服药   2：已服药   3：跳过   4：其他
     * @return
     */
    @PostMapping("/updateStatus")
    public BaseResponse updateStatus(int alarmId, int statusId) {
        BaseResponse resp = new BaseResponse();
        if (alarmId == 0) {
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("用户ID 参数异常");
            return resp;
        }
        return updateService.updateStatus(alarmId, statusId);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public AlarmResp findById(int alarmId) {
        AlarmResp resp = new AlarmResp();
        if (alarmId == 0) {
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("用户ID参数异常");
            return resp;
        }
        return searchService.searchByAlarmId(alarmId);
    }
}
