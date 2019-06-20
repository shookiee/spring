package kr.or.ddit.typeConvert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class StringDateConverter implements Converter<String, Date>{

	private String pattern = "yyyy-MM-dd";
	
	private static final Logger logger = LoggerFactory.getLogger(StringDateConverter.class);
	
	@Override
	public Date convert(String source) {
		// source : 2019-08-08
		logger.debug("StringDateConverter");
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date dt = null;
		try {
			dt = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}

	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	
}
