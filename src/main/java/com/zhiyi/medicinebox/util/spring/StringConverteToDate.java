package com.zhiyi.medicinebox.util.spring;

import com.ibm.icu.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * @author guanchen
 */
public class StringConverteToDate implements Converter<String, Date> {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    public Date convert(String source) {
        Date date = null;
        boolean flag = StringUtils.isEmpty(source);
        if (flag == false) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = df.parse(source);
            } catch (ParseException e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }

        }


        return date;
    }

}
