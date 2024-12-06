package com.custom.utils;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DateUtilTest {

	@ParameterizedTest
	@CsvSource({
		// 윤년 테스트
	    "2024-02-28, 2024-02-29",  // 윤년
	    "2024-02-29, 2024-03-01",  // 윤년 마지막 날
	    "2025-02-28, 2025-03-01",  // 평년
	    
	    // 월 말/년 말 경계
	    "20241130, 20241201",
	    "2024-12-31, 2025-01-01",
	    
	    // 시간 포함 날짜
	    "20240101000000, 20240102000000",
	    "2024-01-01 00:00:00, 2024-01-02 00:00:00"
	})
	void 날짜_더하기(String inputDate, String expectedPlusOneDay) {
		
		String result = DateUtil.plusDays(inputDate, 1);
		assertEquals(expectedPlusOneDay, result);
	}
}
