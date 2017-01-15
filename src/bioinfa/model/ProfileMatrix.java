package bioinfa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileMatrix {
	private Map<DNASymbol, List<Integer>> matrix;
	private int sequences;
	
	public ProfileMatrix(){
		this.matrix = new HashMap<>();
		this.sequences = 0;
	}
	
	public void addSequence(Sequence sequence){
		sequences++;
		List<DNASymbol> symbols = sequence.getSymbols();
		int i = 0;
		for(DNASymbol symbol : symbols){
			increaseSymbolAtIndex(symbol, i);
			i++;
		}
	}
	
	private void increaseSymbolAtIndex(DNASymbol symbol, int index){
		List<Integer> positions = this.matrix.get(symbol);
		positions.set(index, positions.get(index) + 1);
		this.matrix.put(symbol, positions);
	}
	
	public void initMatrix(int sequenceLength){
		for(DNASymbol symbol : DNASymbol.getBasicSymbols()){
			List<Integer> emptyList = new ArrayList<>(sequenceLength);
			
			for(int i = 0; i < sequenceLength; i++){
				emptyList.add(0);
			}
			
			this.matrix.put(symbol, emptyList);
		}
	}	
	
	public List<DNASymbol> getSymbols(){
		return new ArrayList<DNASymbol>(this.matrix.keySet());
	}
	
	public List<Double> getValuesForSymbol(DNASymbol symbol){
		List<Double> values =  new ArrayList<>();
		for(Integer value : this.matrix.get(symbol)){
			values.add((1.0 * value) / sequences);
		}
		return values;
	}
}
