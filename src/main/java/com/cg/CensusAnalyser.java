package com.cg;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {
  
	public int loadStateCsvData(String csvFilePath) throws CensusAnalyserException{
		if(! csvFilePath.contains(".csv")){
	throw new CensusAnalyserException("Not .csv file", CensusAnalyserException.ExceptionType.WRONG_TYPE);
	}

		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			CsvToBeanBuilder<CsvStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<CsvStateCensus> csvToBean = csvToBeanBuilder.withType(CsvStateCensus.class)
					                             .withIgnoreLeadingWhiteSpace(true).build();
			Iterator<CsvStateCensus> csvStateCensusIterator = csvToBean.iterator();
			List<CsvStateCensus> stateList = new ArrayList<CsvStateCensus>();
			while(csvStateCensusIterator.hasNext()) {
				stateList.add(csvStateCensusIterator.next());
			}
			return stateList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException("File not found", CensusAnalyserException.ExceptionType.WRONG_CSV);
		}catch(NullPointerException e) {
			throw new CensusAnalyserException("File is empty", CensusAnalyserException.ExceptionType.NO_DATA);
		}
		catch(RuntimeException e) {
			throw new CensusAnalyserException("File internal data not proper", CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		}
	}
	
	public int loadStateCode(String csvFilePath) throws CensusAnalyserException{
		if(! csvFilePath.contains(".csv")){
	throw new CensusAnalyserException("Not .csv file", CensusAnalyserException.ExceptionType.WRONG_TYPE);
	}

		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			CsvToBeanBuilder<CsvStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<CsvStateCensus> csvToBean = csvToBeanBuilder.withType(CsvStateCensus.class)
					                             .withIgnoreLeadingWhiteSpace(true).build();
			Iterator<CsvStateCensus> csvStateCensusIterator = csvToBean.iterator();
			List<CsvStateCensus> stateList = new ArrayList<CsvStateCensus>();
			while(csvStateCensusIterator.hasNext()) {
				stateList.add(csvStateCensusIterator.next());
			}
			return stateList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException("File not found", CensusAnalyserException.ExceptionType.WRONG_CSV);
		}catch(NullPointerException e) {
			throw new CensusAnalyserException("File is empty", CensusAnalyserException.ExceptionType.NO_DATA);
		}
		catch(RuntimeException e) {
			throw new CensusAnalyserException("File internal data not proper", CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		}
	}
}
