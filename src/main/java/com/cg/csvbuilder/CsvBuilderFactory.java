package com.cg.csvbuilder;

public class CsvBuilderFactory {
public static ICsvBuilder createCsvBuilder() {
	return new OpenCsvBuilder();
}
}
