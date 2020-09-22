package com.man.common.config;


import com.man.common.constant.CommonConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonConstants.DATE_PATTERN);
		try {
			return simpleDateFormat.parse(source);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
