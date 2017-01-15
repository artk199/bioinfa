package bioinfa.model;

import bioinfa.util.BioUtils;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
	private List<DNASymbol> symbols;

	public Sequence(){}
	
	public Sequence(String symbols){
		setSymbols(symbols);
	}

	public Sequence(List<DNASymbol> symbols){
		this.symbols = symbols;
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

	public int getLength() {
		return symbols.size();
	}

	public DNASymbol getSymbol(int position){
		return symbols.get(position);
	}

	@Override
	public String toString() {
		String result = "";
		for (DNASymbol symbol : symbols){
			result += symbol.getSymbol();
		}
		return result;
	}
}
