package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateUtil {
	
	// 날짜 포맷 (Date formats)
	public final static String DATE_FORMAT_COMPACT = "yyyyMMdd";
	public final static String DATE_FORMAT_STANDARD = "yyyy-MM-dd";
	public final static String DATE_FORMAT_MONTH_COMPACT = "yyyyMM";
	public final static String DATE_FORMAT_MONTH_STANDARD = "yyyy-MM";

	// 시간 포맷 (Time formats)
	public final static String TIME_FORMAT_COMPACT = "HHmmss";
	public final static String TIME_FORMAT_STANDARD = "HH:mm:ss";
	
	// 날짜시간 포맷 (Datetime formats)
	public final static String DATETIME_FORMAT_COMPACT = "yyyyMMddHHmmss";
	public final static String DATETIME_FORMAT_STANDARD = "yyyy-MM-dd HH:mm:ss";
	public final static String DATETIME_FORMAT_MILLIS_COMPACT = "yyyyMMddHHmmssSSS";
	public final static String DATETIME_FORMAT_MILLIS_STANDARD = "yyyy-MM-dd HH:mm:ss.SSS";
	
	private final static List<String> formats = Arrays.asList(
			DATE_FORMAT_COMPACT,
            DATE_FORMAT_STANDARD,
            DATE_FORMAT_MONTH_COMPACT,
            DATE_FORMAT_MONTH_STANDARD,
            TIME_FORMAT_COMPACT,
            TIME_FORMAT_STANDARD,
            DATETIME_FORMAT_COMPACT,
            DATETIME_FORMAT_STANDARD,
            DATETIME_FORMAT_MILLIS_COMPACT,
            DATETIME_FORMAT_MILLIS_STANDARD);
	
	/**
	 * 문자열 형식의 날짜에 특정 일수를 더한 날짜를 지정된 형식의 문자열로 반환
	 * 
	 * @param time   원본 날짜 문자열 (변환 가능한 날짜 형식)
	 * @param days   더할 일수 (양수 또는 음수)
	 * @param format 결과 날짜의 출력 형식 (생략 또는 null인 경우 원본 형식 유지) 
	 * @return 날짜 계산 결과 문자열, 유효하지 않은 경우 null 반환 
	 */
	public static String plusDays(String time, int days) { return plusDays(time, days, null); }
	public static String plusDays(String time, int days, String format) {
		
		String inputTimeFormat = findMatchingFormat(time);
		
		if(inputTimeFormat != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(inputTimeFormat);
			LocalDateTime ldt = LocalDateTime.parse(time, dtf);
			
			if(format == null) {
				return ldt.plusDays(days).format(DateTimeFormatter.ofPattern(inputTimeFormat));
			} else {
				return ldt.plusDays(days).format(DateTimeFormatter.ofPattern(format));
			}
		}
		
		return null;
	}
	
	private static String findMatchingFormat(String time) {
		if (time == null || time.trim().isEmpty()) {
			throw new IllegalArgumentException("Time input cannot be empty.");
		}
		
		for (String format : formats) {
			if (format.length() == time.length() && isValidFormat(time, format)) {
                return format;
            }
        }
		
		throw new IllegalArgumentException(String.format("Unsupported time format. Input time: '%s'", time));
	}
	
	private static boolean isValidFormat(String dateStr, String format) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
			dtf.parse(dateStr);
			
			return true;
			
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}