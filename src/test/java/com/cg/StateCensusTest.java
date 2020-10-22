package com.cg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

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
	   
	   @Test
		public void givenStateCensus_WrongCSVFile_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCsvData(STATE_CENSUS_WRONG_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_CSV, e.type);
		   }
		}
	   
	   @Test
		public void givenStateCensus_WrongType_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCsvData(STATE_CENSUS_WRONG_TYPE_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_TYPE, e.type);
		   }
		}
	   
	   @Test
		public void givenStateCensus_WrongHeader_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCsvData(STATE_CENSUS_WRONG_HEADER_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUE, e.type);
		   }
		}
	   
	   @Test
		public void givenStateCensus_WrongDelimiter_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCsvData(STATE_CENSUS_WRONG_DELIMITER_FILE_PATH);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUE, e.type);
		   }
		}
	   
	   @Test
	   public void censusSortedOnState() throws CensusAnalyserException {
		   censusAnalyser.loadStateCsvData(STATE_CENSUS_FILE_PATH);
		   String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
		  CsvStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CsvStateCensus[].class);
		  Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
	   }
	   
	   @Test
	   public void censusSortedOnStatePopulation() throws CensusAnalyserException {
		   censusAnalyser.loadStateCsvData(STATE_CENSUS_FILE_PATH);
		   String sortedCensusData = censusAnalyser.getStatePopulationWiseSortedCensusData();
		  CsvStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CsvStateCensus[].class);
		  Assert.assertEquals("Sikkim", censusCsv[28].state);
	   }
	   
	   @Test
	   public void censusSortedOnStatePopulationDensity() throws CensusAnalyserException {
		   censusAnalyser.loadStateCsvData(STATE_CENSUS_FILE_PATH);
		   String sortedCensusData = censusAnalyser.getStatePopulationDensityWiseSortedCensusData();
		  CsvStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CsvStateCensus[].class);
		  Assert.assertEquals("Arunachal Pradesh", censusCsv[28].state);
	   }
}
