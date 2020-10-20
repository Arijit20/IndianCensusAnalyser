package com.cg;

import java.io.Reader;
import java.util.Iterator;

public interface ICsvBuilder {
   public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CsvException;
}
