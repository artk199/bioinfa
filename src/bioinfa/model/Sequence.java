package bioinfa.model;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
	private List<DNASymbol> symbols;

	public Sequence(){}
	
	public Sequence(String symbols){
		setSymbols(symbols);
	}
	
	public List<DNASymbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<DNASymbol> symbols) {
		this.symbols = symbols;
	}	
	
	public void setSymbols(String symbols){
		this.symbols = new ArrayList<>(symbols.length());
		for(int i = 0; i < symbols.length(); i++){
			this.symbols.add(DNASymbol.convert(String.valueOf(symbols.charAt(i))));
		}
	}
	
	@Override
	public String toString(){
		return symbols.toString();
	}
}
