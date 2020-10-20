package com.cg;

public class CensusAnalyserException extends Exception{

	 
		enum ExceptionType {
	       WRONG_CSV, WRONG_TYPE, INTERNAL_ISSUE, UNABLE_TO_PARSE, NO_DATA
	    }
		
		ExceptionType type;
	
		public CensusAnalyserException(String message, ExceptionType type) {
			super(message);
			this.type = type;
		}
}