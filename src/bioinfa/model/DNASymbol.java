package bioinfa.model;

import java.util.Arrays;
import java.util.List;

public enum DNASymbol {
	A("A"),
	G("G"),
	C("C"),
	T("T"),
	Y("Y",DNASymbol.C,DNASymbol.T),
	R("R",DNASymbol.A,DNASymbol.G),
	W("W",DNASymbol.A,DNASymbol.T),
	S("S",DNASymbol.G,DNASymbol.C),
	K("K",DNASymbol.G,DNASymbol.T),
	M("M",DNASymbol.A,DNASymbol.C),
	D("D",DNASymbol.A, DNASymbol.G,DNASymbol.T),
	V("D",DNASymbol.A, DNASymbol.C,DNASymbol.G),
	H("D",DNASymbol.A, DNASymbol.C,DNASymbol.T),
	B("D",DNASymbol.C, DNASymbol.G,DNASymbol.T),
	XN("X/N"),
	EMPTY("-");
	
	private final String symbol;
	private final DNASymbol[] components;

	private DNASymbol(String symbol){
		this.symbol = symbol;
		components = new DNASymbol[0];
	}

	DNASymbol(String symbol, DNASymbol... components) {
		this.symbol = symbol;
		this.components = components;
	}

	public String getSymbol() {
		return symbol;
	}

	public static DNASymbol convert(String symbol){
		DNASymbol[] allValues = DNASymbol.values();
		for (int i = 0; i < allValues.length; i++) {
			if(allValues[i].symbol.equals(symbol)){
				return allValues[i];
			}
		}
		return DNASymbol.EMPTY;
	}
	
	public static List<DNASymbol> getBasicSymbols(){
		return Arrays.asList(DNASymbol.A, DNASymbol.G, 
				DNASymbol.C, DNASymbol.T, DNASymbol.EMPTY);
	}

	public static DNASymbol getSymbolForArray(DNASymbol[] symbols) {
		DNASymbol[] allValues = DNASymbol.values();
		for (int i = 0; i < allValues.length; i++) {
			if(compareSymbolArrays(allValues[i].components,symbols)){
				return allValues[i];
			}
		}
		return DNASymbol.XN;
	}

	private static boolean compareSymbolArrays(DNASymbol[] symbols, DNASymbol[] symbols2) {
		Arrays.sort(symbols);
		Arrays.sort(symbols2);
		return Arrays.equals(symbols,symbols2);
	}


}
