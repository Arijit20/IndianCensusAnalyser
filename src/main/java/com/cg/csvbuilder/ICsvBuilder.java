package com.cg.csvbuilder;

import java.io.Reader;

import java.util.Iterator;
import java.util.List;


public interface ICsvBuilder {
   public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CsvException;
   public <E> List<E> getCsvFileIList(Reader reader, Class csvClass) throws CsvException;
}
