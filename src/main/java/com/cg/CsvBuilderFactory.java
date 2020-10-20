package com.cg;

public class CsvBuilderFactory {
public static ICsvBuilder createCsvBuilder() {
	return new OpenCsvBuilder();
}
}
