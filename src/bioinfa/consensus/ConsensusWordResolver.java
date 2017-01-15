package bioinfa.consensus;

import bioinfa.model.Sequence;

import java.util.List;

public interface ConsensusWordResolver {

    Sequence resolve(List<Sequence> sequences);

}
