package bioinfa.model;

import java.util.Arrays;
import java.util.List;

public enum DNASymbol {
	A("A"), G("G"), C("C"), T("T"), EMPTY("-");
	
	private final String symbol;
	
	private DNASymbol(String symbol){
		this.symbol = symbol;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
	
	public static DNASymbol convert(String symbol){
		DNASymbol result = null;
		switch(symbol){
			case "A":
				result = DNASymbol.A;
			break;
			case "G":
				result = DNASymbol.G;
			break;
			case "C":
				result = DNASymbol.C;
			break;
			case "T":
				result = DNASymbol.T;
			break;
			default:
				result = DNASymbol.EMPTY;
		}
		
		return result;
	}
	
	public static List<DNASymbol> getBasicSymbols(){
		return Arrays.asList(DNASymbol.A, DNASymbol.G, 
				DNASymbol.C, DNASymbol.T, DNASymbol.EMPTY);
	}
}
