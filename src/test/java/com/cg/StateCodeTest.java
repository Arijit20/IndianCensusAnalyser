package com.cg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateCodeTest {
	private CensusAnalyser censusAnalyser;
	
	   public static final String STATE_CODE_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/IndiaStateCode.csv";
	   public static final String STATE_CODE_WRONG_DELIMITER_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/IndianStateCodeDelimiter.csv";
	   public static final String STATE_CODE_WRONG_HEADER_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/IndiaStateCensusData.csv";
	   public static final String STATE_CODE_WRONG_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/src/test/resources/IndiaState.csv";
	   public static final String STATE_CODE_WRONG_TYPE_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/Data1.txt";
	   
	   @Before
	   public void initialize() {
		    censusAnalyser = new CensusAnalyser(); 
	   }
	   
	   @Test
		public void givenStateCodeCSVFile_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
			int noOfEntries = censusAnalyser.loadStateCode(STATE_CODE_FILE_PATH);
			Assert.assertEquals(37, noOfEntries);
		}
	   
	   @Test
		public void givenStateCode_WrongCSVFile_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCode(STATE_CODE_WRONG_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_CSV, e.type);
		   }
		}
	   
	   @Test
		public void givenStateCode_WrongHeader_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCode(STATE_CODE_WRONG_HEADER_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUE, e.type);
		   }
		}
	   
	   @Test
		public void givenStateCode_WrongDelimiter_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCode(STATE_CODE_WRONG_DELIMITER_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUE, e.type);
		   }
		}
	   
	   @Test
		public void givenStateCode_WrongType_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCode(STATE_CODE_WRONG_TYPE_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_TYPE, e.type);
		   }
		}
}