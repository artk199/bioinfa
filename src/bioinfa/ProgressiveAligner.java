package bioinfa;

import bioinfa.model.*;
import bioinfa.util.BioUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 17.01.2017.
 */
public class ProgressiveAligner {

    public Multialigment alignByProfiles(Multialigment malign1, Multialigment malign2){
        ProfileService profileService = new ProfileService();

        //Wyliczamy profile wielodopasowan
        ProfileMatrix profile1 = profileService.computeProfile(malign1.getSequences());
        ProfileMatrix profile2 = profileService.computeProfile(malign2.getSequences());

        //Algorytm Needleman–Wunsch dostosowany do laczenia wielodopasowan przy pomocy ich profilii
        int firstSequenceLength = malign1.getLength();
        int secondSequenceLength = malign2.getLength();


        double[][] matrix = new double[secondSequenceLength+1][firstSequenceLength+1];

        //Uzupelnienie pierwszego wiersza
        for (int i = 1; i <= firstSequenceLength; i++) {
            matrix[0][i] = matrix[0][i-1] + upIndelCost(profile1, i);
        }

        //Uzupelnienie pierwszej kolumny
        for (int i = 1; i <= secondSequenceLength; i++) {
            matrix[i][0] = matrix[i-1][0] + leftIndelCost(profile2, i);
        }

        //Uzupełnienie srodka macierzy
        for (int i = 1; i <= secondSequenceLength; i++) {
            for (int j = 1; j <= firstSequenceLength; j++) {
                //Koszt przejscie do lewego gornego rogu
                double diagonalCost = getDiagonalCost(profile2, profile1, i, j);
                //Koszt przejscie w lewo
                double leftCost = leftIndelCost(profile1,j);
                //Koszt przejscia w gore
                double upCost = upIndelCost(profile2,i);
                matrix[i][j] = getMax(matrix[i-1][j-1]+diagonalCost,matrix[i][j-1]+leftCost,matrix[i-1][j]+upCost);
            }
        }

        //BioUtils.printNeedlemanWunschGrid(null,null,matrix);

        //Faza wyboru optymalnego dopasowania z tabeli
        //Startujemy z prawego dolengo rogu
        int i = secondSequenceLength,j = firstSequenceLength;
        while(i != 0 || j!= 0){
            int direction = 0;
            if(i > 0 && j > 0) {
                direction = getDirection(matrix[i - 1][j - 1], matrix[i][j - 1], matrix[i - 1][j]);
            }
            if(i == 0){
                direction = 1;
            }
            if(j == 0){
                direction = 2;
            }
            switch (direction){
                case 0:
                    //Idziemy do górnego lewego rogu
                    i--;
                    j--;
                    break;
                case 1:
                    //Idziemy w lewo
                    addGapToMultialigment(malign2,i);
                    j--;
                    break;
                case 2:
                    //Idziemy w górę
                    addGapToMultialigment(malign1,j);
                    i--;
                    break;
            }
        }

        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().addAll(malign1.getSequences());
        multialigment.getSequences().addAll(malign2.getSequences());

        removeAdditionalGaps(multialigment);

        return multialigment;

    }

    private void removeAdditionalGaps(Multialigment multialigment) {
        //Usuwa zbedne gapy
        List<Sequence> sequences = multialigment.getSequences();
        for (int i = multialigment.getLength()-1; i > 0 ; i--) {
            boolean toRemove = true;
            for (Sequence s: sequences){
                if(s.getSymbol(i) != DNASymbol.EMPTY){
                    toRemove = false;
                    break;
                }
            }
            if(toRemove){
                for (Sequence s: sequences){
                    s.getSymbols().remove(i);
                }
            }
        }
    }


    private void addGapToMultialigment(Multialigment malign, int i) {
        if(i > malign.getLength())
            i = malign.getLength();
        List<Sequence> sequences = malign.getSequences();
        for(Sequence s: sequences){
            s.getSymbols().add(i,DNASymbol.EMPTY);
        }
    }

    private double getDiagonalCost(ProfileMatrix profile1, ProfileMatrix profile2, int i, int j) {
        double diagonalCost = 0;
        List<DNASymbol> symbols2 = profile2.getSymbols();
        List<DNASymbol> symbols1 = profile1.getSymbols();
        for (DNASymbol s2 : symbols2){
            for (DNASymbol s1 : symbols1) {
                Double valueForFirstSymbol = profile1.getValuesForSymbol(s1).get(i - 1);
                Double valueForSecondSymbol = profile2.getValuesForSymbol(s2).get(j - 1);
                diagonalCost += SimilarityMatrix.get(s2,s1) * valueForFirstSymbol * valueForSecondSymbol;
            }
        }
        return diagonalCost;
    }

    private double leftIndelCost(ProfileMatrix profile, int i) {
        double sum = 0;
        List<DNASymbol> symbols = profile.getSymbols();
        for (DNASymbol symbol : symbols){
            sum += profile.getValuesForSymbol(symbol).get(i-1) * SimilarityMatrix.get(symbol,DNASymbol.EMPTY);
        }
        return sum;
    }

    private double upIndelCost(ProfileMatrix profile, int i) {
        double sum = 0;
        List<DNASymbol> symbols = profile.getSymbols();
        for (DNASymbol symbol : symbols){
            sum += profile.getValuesForSymbol(symbol).get(i-1) * SimilarityMatrix.get(symbol,DNASymbol.EMPTY);
        }
        return sum;
    }

    private int getDirection(double i, double i1, double i2) {
        if(i >= i1 && i >= i2)
            return 0;
        if(i1 >= i2)
            return 1;
        return 2;
    }

    private double getMax(double i, double i1, double i2) {
        if(i >= i1 && i >= i2)
            return i;
        if(i1 >= i2)
            return i1;
        return i2;
    }

}
