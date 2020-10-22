package com.cg;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;
import com.cg.csvbuilder.*;
import com.google.gson.Gson;

public class CensusAnalyser {
	
List<CsvStateCensus> csvStateCensusList;
	
	public CensusAnalyser() {
		this.csvStateCensusList = new ArrayList<CsvStateCensus>();
	}
	 
  
	public int loadStateCsvData(String csvFilePath) throws CensusAnalyserException{
		if(! csvFilePath.contains(".csv")){
	throw new CensusAnalyserException("Not .csv file", CensusAnalyserException.ExceptionType.WRONG_TYPE);
	}

		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			com.cg.csvbuilder.ICsvBuilder csvBuilder = com.cg.csvbuilder.CsvBuilderFactory.createCsvBuilder();
			csvStateCensusList = csvBuilder.getCsvFileIList(reader, CsvStateCensus.class);
			return csvStateCensusList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException("File not found", CensusAnalyserException.ExceptionType.WRONG_CSV);
		}catch(NullPointerException e) {
			throw new CensusAnalyserException("File is empty", CensusAnalyserException.ExceptionType.NO_DATA);
		}
		catch(RuntimeException e) {
			throw new CensusAnalyserException("File internal data not proper", CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		} catch (com.cg.csvbuilder.CsvException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	public int loadStateCode(String csvFilePath) throws CensusAnalyserException{
		if(! csvFilePath.contains(".csv")){
	throw new CensusAnalyserException("Not .csv file", CensusAnalyserException.ExceptionType.WRONG_TYPE);
	}

		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			com.cg.csvbuilder.ICsvBuilder csvBuilder = com.cg.csvbuilder.CsvBuilderFactory.createCsvBuilder();
			csvStateCensusList = csvBuilder.getCsvFileIList(reader, CsvStateCode.class);
			return csvStateCensusList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException("File not found", CensusAnalyserException.ExceptionType.WRONG_CSV);
		}catch(NullPointerException e) {
			throw new CensusAnalyserException("File is empty", CensusAnalyserException.ExceptionType.NO_DATA);
		}
		catch(RuntimeException e) {
			throw new CensusAnalyserException("File internal data not proper", CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		} catch (com.cg.csvbuilder.CsvException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	private<E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int numOfEntries = (int)StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}
	
	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		if(csvStateCensusList == null || csvStateCensusList.size() == 0) {
			throw new CensusAnalyserException("File is empty", CensusAnalyserException.ExceptionType.NO_DATA);
		}
			Comparator<CsvStateCensus> censusComparator = Comparator.comparing(census -> census.state);
			this.sort( censusComparator);
			String sortedStateCensus = new Gson().toJson(csvStateCensusList);
			return sortedStateCensus;
	}

	private void sort(Comparator<CsvStateCensus> censusComparator) {
		for(int i = 0;i < csvStateCensusList.size()-1; i++) {
			for(int j = 0; j < csvStateCensusList.size()-1-i; j++) {
				CsvStateCensus census1 = csvStateCensusList.get(j);
				CsvStateCensus census2 = csvStateCensusList.get(j+1);
				if(censusComparator.compare(census1, census2) > 0) {
					csvStateCensusList.set(j, census2);
					csvStateCensusList.set(j+1, census1);
				}
			}
		}
	}
}
