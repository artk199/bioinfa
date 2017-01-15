package bioinfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bioinfa.model.DNASymbol;
import bioinfa.model.Sequence;

public class ProfileService {
	public Map<DNASymbol, List<Double>> computeProfile(List<Sequence> sequences){
		int sequencesLength = sequences.get(0).getSymbols().size();
		Map<DNASymbol, List<Double>> matrix = initProfileMatrix(sequencesLength);
		
		for(Sequence sequence : sequences){
			List<DNASymbol> symbols = sequence.getSymbols();
			int i = 0;
			for(DNASymbol symbol : symbols){
				increaseSymbolAtIndex(symbol, i, matrix);
				i++;
			}
		}
		
		return computeFrequencies(matrix, sequences.size());
	}
	
	private Map<DNASymbol, List<Double>> computeFrequencies(Map<DNASymbol, List<Double>> matrix, int sequencesSize){
		Map<DNASymbol, List<Double>> result = new HashMap<>();
		for(Entry<DNASymbol, List<Double>> mapEntry : matrix.entrySet()){
			int n = mapEntry.getValue().size();
			for(int i = 0; i < n; i++){
				mapEntry.getValue().set(i, mapEntry.getValue().get(i) / sequencesSize);
			}
			result.put(mapEntry.getKey(), mapEntry.getValue());
		}
		return result;
	}
	
	private void increaseSymbolAtIndex(DNASymbol symbol, int index, Map<DNASymbol, List<Double>> matrix){
		List<Double> positions = matrix.get(symbol);
		positions.set(index, positions.get(index) + 1);
		matrix.put(symbol, positions);
	}
	
	private Map<DNASymbol, List<Double>> initProfileMatrix(int arraySize){
		Map<DNASymbol, List<Double>> result = new HashMap<>();
		for(DNASymbol symbol : DNASymbol.getBasicSymbols()){
			List<Double> emptyList = new ArrayList<>(arraySize);
			
			for(int i = 0; i < arraySize; i++){
				emptyList.add(0.0);
			}
			
			result.put(symbol, emptyList);
		}
		return result;
	}
}
