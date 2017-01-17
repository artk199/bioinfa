package bioinfa.util;

import bioinfa.Main;
import bioinfa.model.DNASymbol;
import bioinfa.model.Multialigment;
import bioinfa.model.ProfileMatrix;
import bioinfa.model.Sequence;

import java.util.List;

/**
 * Created by Artur on 15.01.2017.
 */
public class BioUtils {

    private static final String PROFILE_SYMBOL_FORMAT = "[%s]\t- %s";

    public static void printProfileMatrix(ProfileMatrix matrix){
        List<DNASymbol> symbols = matrix.getSymbols();
        for(DNASymbol symbol : symbols){
            String symbolValues = matrix.getValuesForSymbol(symbol).toString();
            System.out.println(String.format(PROFILE_SYMBOL_FORMAT, symbol, symbolValues));
        }
    }

    public static void printNeedlemanWunschGrid(Multialigment firstSequence, Multialigment secondSequence, double[][] matrix) {
        System.out.println("\n\n Neddleman-Wunsch Grid\n\n");
        /*System.out.println("  "+firstSequence.toString());
        System.out.print(" ");
        for (int i = 0; i < firstSequence.getLength(); i++) {
            System.out.print(matrix[0][i]);
        }
        System.out.print("\n");
        for (int i = 0; i < secondSequence.getLength(); i++) {
            System.out.print(secondSequence.getSymbol(i).getSymbol());
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }*/
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%4.4f ", matrix[row][col]);
            }
            System.out.println();
        }
    }
}
