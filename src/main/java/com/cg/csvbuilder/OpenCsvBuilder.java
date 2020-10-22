package com.cg.csvbuilder;

import java.io.Reader;

import java.util.Iterator;
import java.util.List;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder implements ICsvBuilder{

	public<E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CsvException{
		return (Iterator<E>) this.getCsvBean(reader, csvClass).iterator();
	}

	@Override
	public <E> List<E> getCsvFileIList(Reader reader, Class csvClass) throws CsvException {
		return (List<E>) this.getCsvBean(reader, csvClass).parse();
	}

	private<E> CsvToBean<E> getCsvBean(Reader reader, Class csvClass) throws CsvException{
		// TODO Auto-generated method stub
		try {
		CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		csvToBeanBuilder.withType(csvClass);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		return csvToBeanBuilder.build();
	}catch(IllegalStateException e) {
		throw new CsvException(e.getMessage(), CsvException.ExceptionType.UNABLE_TO_PARSE);
	}
	}
}