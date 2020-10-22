package com.cg.commoncsv;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CommonCsvBuilder implements ICsvBuilder{

	@Override
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CsvException {
		
		CSVParser csvParser;
		try {
			csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeaderComments(csvClass));
			return (Iterator<E>) csvParser.iterator();
		} catch (IOException e) {
			throw new CsvException(e.getMessage(), CsvException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	@Override
	public <E> List<E> getCsvFileIList(Reader reader, Class csvClass) throws CsvException {
		
		
			Iterator<E> iterator = getCsvFileIterator(reader, csvClass);
			List<E> list = new ArrayList<>();
			iterator.forEachRemaining(list::add);
			return list;
		
	}

}
