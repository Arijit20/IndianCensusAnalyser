package com.cg.commoncsv;


public class CsvBuilderFactory {
	public static ICsvBuilder createCsvBuilder() {
		return new CommonCsvBuilder();
	}
}
