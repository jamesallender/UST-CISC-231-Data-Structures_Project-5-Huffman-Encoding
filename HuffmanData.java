
/**
 * 
 * @author James Allender & Adam Abrams, CISC 231 Data Structures, Assignment 5,
 *         Professor Jason Sawin, Class HuffmanData
 * 
 *         This Class models the data to be used in the node of a HuffmanTree.
 *         The data of a HuffmanTree consists of a symbol (a string of
 *         characters) and occurrences (the number of times the characters of
 *         the symbol have occurred)
 *
 */
public class HuffmanData {
	// Instance Variables
	private String symbol; // The string of characters contained by the data
	private int occurrences; // The number of times a character has occurred

	/**
	 * Constructor constructs a new HuffmanData instance. The number of occurrences
	 * is automatically set to 1
	 * 
	 * @param symbol
	 *            the character the data will hold, can't be null
	 */
	public HuffmanData(String symbol) {
		// If symbol is null
		if (symbol == null) {
			throw new IllegalArgumentException(this.getClass().getName()
					+ " Constructor threw an exception, Given symbol was null, symbol cant be null");
		}

		this.symbol = symbol;
		this.occurrences = 1; // Default
	}

	/**
	 * Constructor constructs a new HuffmanData instance. The number of occurrences
	 * is set by the user
	 * 
	 * @param symbol
	 *            the character the data will hold, can't be null
	 * @param occurrences
	 *            the number of occurrences of the characters of the symbol, can't
	 *            be less than 1
	 */
	public HuffmanData(String symbol, int occurrences) {
		// If symbol is null
		if (symbol == null) {
			throw new IllegalArgumentException(this.getClass().getName()
					+ " Constructor threw an exception, Given symbol was null, symbol cant be null");
		}
		// if occurrences is less than 1
		if (occurrences < 1) {
			throw new IllegalArgumentException(this.getClass().getName()
					+ " threw an exception, occurrences cannot be less than 1 occurrences was: " + occurrences);
		}

		this.symbol = symbol;
		this.occurrences = occurrences;
	}

	/**
	 * incrementOccurrences method increments the number of occurrences by 1
	 */
	public void incrementOccurrences() {
		this.occurrences++;
	}

	/**
	 * occurrences mutator
	 * 
	 * @param newOccurrences
	 *            new number of occurrences
	 */
	public void setOccurrences(int newOccurrences) {
		// if newOccurrences is less than 1
		if (newOccurrences < 1) {
			throw new IllegalArgumentException(this.getClass().getName()
					+ " threw an exception, occurrences cannot be less than 1 occurrences was: " + newOccurrences);
		}

		this.occurrences = newOccurrences;
	}

	/**
	 * occurrences accessor
	 * 
	 * @return number of occurrences
	 */
	public int getOccurrences() {
		return this.occurrences;
	}

	/**
	 * symbol accessor
	 * 
	 * @return this data's symbol
	 */
	public String getSymbol() {
		return this.symbol;
	}

	@Override
	/**
	 * Overridden hashCode uses Strings hashCode
	 */
	public int hashCode() {
		return getSymbol().hashCode();
	}

	@Override
	/**
	 * Overridden equal method compares two HuffmanTrees based on their symbol
	 * alone, not on their occurrences
	 */
	public boolean equals(Object other) {
		// if this is other
		if (this == other)
			return true;
		// If other is null
		if (other == null)
			return false;
		// if class mismatch
		if (getClass() != other.getClass())
			return false;
		// Cast other
		HuffmanData otherData = (HuffmanData) other;
		// Handles null symbol
		if (symbol == null) {
			if (otherData.symbol != null)
				return false;
		}
		// Otherwise check is symbols are the same
		else if (!getSymbol().equals(otherData.getSymbol()))
			return false;
		// If made it all the way through, then equal
		return true;
	}

}
