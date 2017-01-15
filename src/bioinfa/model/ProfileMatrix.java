package bioinfa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileMatrix {
	private Map<DNASymbol, List<Double>> matrix;
	private int sequencesCount;
	private int sequencesLength;
	
	public ProfileMatrix(){
		this.matrix = new HashMap<>();
		this.sequencesCount = 0;
		this.sequencesLength = 0;
	}
	
	public void setValue(DNASymbol symbol, int index, double value){
		List<Double> positions = this.matrix.get(symbol);
		positions.set(index, value);
	}
	
	public void initMatrix(int sequenceLength){
		this.matrix = new HashMap<>();
		this.sequencesLength = sequenceLength;
		for(DNASymbol symbol : DNASymbol.getBasicSymbols()){
			List<Double> emptyList = new ArrayList<>(sequenceLength);
			
			for(int i = 0; i < sequenceLength; i++){
				emptyList.add(0.0);
			}
			
			this.matrix.put(symbol, emptyList);
		}
	}	
	
	public List<DNASymbol> getSymbols(){
		return new ArrayList<DNASymbol>(this.matrix.keySet());
	}
	
	public List<Double> getValuesForSymbol(DNASymbol symbol){
		return this.matrix.get(symbol);
	}

	public int getSequencesCount() {
		return sequencesCount;
	}

	public void setSequencesCount(int sequencesCount) {
		this.sequencesCount = sequencesCount;
	}

	public int getSequencesLength() {
		return sequencesLength;
	}
}
