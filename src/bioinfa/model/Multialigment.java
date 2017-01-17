package bioinfa.model;

import java.util.LinkedList;
import java.util.List;

public class Multialigment {
	private List<Sequence> sequences = new LinkedList<>();
	private Sequence result;
	private SimilarityMatrix similarityMatrix;

	public List<Sequence> getSequences() {
		return sequences;
	}
	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}
	public Sequence getResult() {
		return result;
	}
	public void setResult(Sequence result) {
		this.result = result;
	}
	public SimilarityMatrix getSimilarityMatrix() {
		return similarityMatrix;
	}
	public void setSimilarityMatrix(SimilarityMatrix similarityMatrix) {
		this.similarityMatrix = similarityMatrix;
	}

	@Override
	public String toString() {
		String ret = "";
		for (Sequence s : sequences){
			ret+=s.toString()+"\n";
		}
		return ret;
	}

	public int getLength() {
		return sequences.get(0).getLength();
	}
}
