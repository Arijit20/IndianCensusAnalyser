package com.cg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateCensusTest {

	private CensusAnalyser censusAnalyser;
	
	   public static final String STATE_CENSUS_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/IndiaStateCensusData.csv";
	   public static final String STATE_CENSUS_WRONG_DELIMITER_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/IndianStateCensusDelimiter.csv";
	   public static final String STATE_CENSUS_WRONG_HEADER_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/IndiaStateCode.csv";
	   public static final String STATE_CENSUS_WRONG_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
	   public static final String STATE_CENSUS_WRONG_TYPE_FILE_PATH = "D:/javaworkspace/IndianCensusAnalyser/Data.txt";
	   
	   @Before
	   public void initialize() {
		    censusAnalyser = new CensusAnalyser(); 
	   }
	   
	   @Test
		public void givenStateCensusCSVFile_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
			int noOfEntries = censusAnalyser.loadStateCsvData(STATE_CENSUS_FILE_PATH);
			Assert.assertEquals(29, noOfEntries);
		}
}
