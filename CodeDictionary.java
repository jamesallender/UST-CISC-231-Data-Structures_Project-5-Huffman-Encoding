/**
 * This class accepts a text file and builds a 
 * Huffman code dictionary based on character frequencies.
 * 
 * @author David
 * @version 12/29/16
 */
public interface CodeDictionary
{
	/**
	 * Searches through the dictionary and finds the 
	 * value mapped to the given key.
	 * 
	 * @param key
	 * @return the value mapped by the given parameter
	 */
	public String lookup(String key);


	/**
	 * Searches through the dictionary and finds the key 
	 * mapped to the given value. If multiple keys are mapped, 
	 * only the first occurrence is returned.
	 * 
	 * @param value
	 * @return the key mapped from the given parameter
	 */
	public String reverseLookup(String value);
}
