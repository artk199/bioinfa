package bioinfa.tests.services;

import bioinfa.UGMAAlignerService;
import bioinfa.model.Multialigment;
import bioinfa.model.Sequence;
import org.junit.Test;

/**
 * Created by Artur on 18.01.2017.
 */
public class UGMAAlignerTest {

    @Test
    public void test(){
        UGMAAlignerService alignerService = new UGMAAlignerService();

        Multialigment multialigment = new Multialigment();
        multialigment.getSequences().add(new Sequence("TCCA"));
        multialigment.getSequences().add(new Sequence("AC-A"));
        multialigment.getSequences().add(new Sequence("ACC-"));
        Multialigment multialigment2 = new Multialigment();
        multialigment2.getSequences().add(new Sequence("TA-G"));
        multialigment2.getSequences().add(new Sequence("CAT-"));
        multialigment2.getSequences().add(new Sequence("-CCG"));

        System.out.println(alignerService.computeDifferenceBetweenSequences(multialigment,multialigment2));
    }
}
