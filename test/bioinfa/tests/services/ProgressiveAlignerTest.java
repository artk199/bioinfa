package bioinfa.tests.services;

import bioinfa.ProgressiveAligner;
import bioinfa.model.Multialigment;
import bioinfa.model.Sequence;
import bioinfa.model.SimilarityMatrix;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Artur on 17.01.2017.
 */
public class ProgressiveAlignerTest {

    ProgressiveAligner aligner;

    @Before
    public void init(){
        aligner = new ProgressiveAligner();
    }

    @Test
    public void testAlign(){
        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("GCATGCU"));
        multialigment.setResult(new Sequence("GCATGCU"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("GCATGCU"));
        multialigment2.setResult(new Sequence("GCATGCU"));

        aligner.alignByProfiles(multialigment,multialigment2,new SimilarityMatrix());
    }
}
