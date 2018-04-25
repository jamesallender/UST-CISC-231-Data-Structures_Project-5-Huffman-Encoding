
public class HuffmanData {
	public String symbol;
	public int occurrences;
	
	public HuffmanData (String symbol, int occurrences) {
		this.symbol = symbol;
		this.occurrences = occurrences;
	}
	
	public void incOccurrences() {
		this.occurrences++;
	}
	public void setOccurrences(int newOccurrences) {
		this.occurrences = newOccurrences;
	}
	public String getSymbol() {
		return this.symbol;
	}
	public int getOccurrences() {
		return this.occurrences;
	}
}
