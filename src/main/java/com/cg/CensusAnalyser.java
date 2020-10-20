package com.cg;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
  
	public int loadStateCsvData(String csvFilePath) throws CensusAnalyserException{
		if(! csvFilePath.contains(".csv")){
	throw new CensusAnalyserException("Not .csv file", CensusAnalyserException.ExceptionType.WRONG_TYPE);
	}

		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
			Iterator<CsvStateCensus> csvStateCensusIterator = csvBuilder.getCsvFileIterator(reader, CsvStateCensus.class);
			return this.getCount(csvStateCensusIterator );
		} catch (IOException e) {
			throw new CensusAnalyserException("File not found", CensusAnalyserException.ExceptionType.WRONG_CSV);
		}catch(NullPointerException e) {
			throw new CensusAnalyserException("File is empty", CensusAnalyserException.ExceptionType.NO_DATA);
		}
		catch(RuntimeException e) {
			throw new CensusAnalyserException("File internal data not proper", CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		} catch (CsvException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	public int loadStateCode(String csvFilePath) throws CensusAnalyserException{
		if(! csvFilePath.contains(".csv")){
	throw new CensusAnalyserException("Not .csv file", CensusAnalyserException.ExceptionType.WRONG_TYPE);
	}

		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
			Iterator<CsvStateCode> csvStateCensusIterator = csvBuilder.getCsvFileIterator(reader, CsvStateCode.class);
			return this.getCount(csvStateCensusIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("File not found", CensusAnalyserException.ExceptionType.WRONG_CSV);
		}catch(NullPointerException e) {
			throw new CensusAnalyserException("File is empty", CensusAnalyserException.ExceptionType.NO_DATA);
		}
		catch(RuntimeException e) {
			throw new CensusAnalyserException("File internal data not proper", CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		} catch (CsvException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
	
	private<E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int numOfEntries = (int)StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}
}
