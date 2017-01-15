package bioinfa.consensus;

import bioinfa.model.DNASymbol;
import bioinfa.model.Sequence;

import java.util.*;
import java.util.logging.Logger;

public class SimpleConsensusWordResolver implements ConsensusWordResolver {

    private final static Logger Log = Logger.getLogger(SimpleConsensusWordResolver.class.getName());

    private final int SYMBOL_WEIGHT = 100;
    private final int THRESHOLD = 99;

    public Sequence resolve(List<Sequence> sequences) {
        int length = sequences.get(0).getLength();
        List<DNASymbol> result = new ArrayList<DNASymbol>();
        for (int i = 0; i < length; i++) {
            List<DNASymbol> symbolsInColumn = new ArrayList<DNASymbol>();
            for(Sequence sequence : sequences) {
                symbolsInColumn.add(sequence.getSymbol(i));
            }
            DNASymbol symbol = getConsensusForColumn(symbolsInColumn);
            result.add(symbol);
        }

        return new Sequence(result);
    }

    private DNASymbol getConsensusForColumn(List<DNASymbol> column) {
        Map<DNASymbol,Integer> weights = new EnumMap<DNASymbol, Integer>(DNASymbol.class);
        int symbolCount = 0;
        for(DNASymbol current : column) {
            if (DNASymbol.EMPTY != current) {
                int newWeight = SYMBOL_WEIGHT;
                if (weights.containsKey(current)) {
                    newWeight += weights.get(current);
                }
                weights.put(current, newWeight);
                symbolCount++;
            }
        }
        final int finalSymbolCount = symbolCount;
        weights.replaceAll((k, v) -> v/finalSymbolCount);
        DNASymbol symbol = getSymbolFromWeights(weights);
        return symbol;
    }

    private DNASymbol getSymbolFromWeights(Map<DNASymbol, Integer> weights) {
        List<DNASymbol> dnaSymbols = new LinkedList<>();
        int maxValue = -1;
        for (Map.Entry<DNASymbol, Integer> entry : weights.entrySet()) {
            if(entry.getValue() == maxValue){
                dnaSymbols.add(entry.getKey());
            }
            if(entry.getValue() > maxValue){
                dnaSymbols.clear();
                dnaSymbols.add(entry.getKey());
                maxValue = entry.getValue();
            }
        };
        if(maxValue >= THRESHOLD){
            return translateToSingleSymbol(dnaSymbols);
        }else{
            return translateToSingleSymbol(new LinkedList<DNASymbol>(weights.keySet()));
        }
    }

    private DNASymbol translateToSingleSymbol(List<DNASymbol> dnaSymbols) {
        if(dnaSymbols.size() == 1)
            return dnaSymbols.get(0);
        if(dnaSymbols.size() == 0)
            return DNASymbol.EMPTY;
        return DNASymbol.getSymbolForArray(dnaSymbols.toArray(new DNASymbol[]{}));
    }

}
