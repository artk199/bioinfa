package bioinfa.util;

import bioinfa.model.DNASymbol;

/**
 * Created by Artur on 15.01.2017.
 */
public class BioUtils {
    public static DNASymbol convertCharToDNASymbol(char c) {
        switch (c){
            case 'A': return  DNASymbol.A;
            case 'G': return  DNASymbol.G;
            case 'C': return  DNASymbol.C;
            case 'T': return  DNASymbol.T;
            case '-': return  DNASymbol.EMPTY;
            default: throw new IllegalArgumentException("Invalid DNA Symbol" + c);
        }
    }
}
