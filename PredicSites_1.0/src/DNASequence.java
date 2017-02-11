/** 
 * Class DNASequence
 * @author Mohamed Elati
 * @version 1.0
 * @date Mars 2009
 */

import java.io.*;
import java.lang.*;
import java.lang.String.*; 

/**
 * Class DNASequence
 */
class DNASequence {

	/**
	 * The name of the DNA sequence.
	 */
	String name;

	/**
	 * The raw sequence (alphabet 'A', 'C', 'G', 'T').
	 */
	String rawSequence;

	/**
	 * Constructor.
	 * @param rawSequence A DNA raw sequence (alphabet 'A', 'C', 'G', 'T').
	 */
	DNASequence(String rawSequence)  throws BadDNACharacterException {
		this.name     = new String("no name");
		this.rawSequence = rawSequence.toUpperCase();
		DNAAlphabet.checkRawSequence(this.rawSequence);
	}

	/**
	 * Constructor.
	 * @param name The name of the DNA sequence.
	 * @param rawSequence A DNA raw sequence (alphabet 'A', 'C', 'G', 'T').
	 */
	DNASequence(String name, String rawSequence) throws BadDNACharacterException {
		this.name     = new String(name);
		this.rawSequence = rawSequence.toUpperCase();
		DNAAlphabet.checkRawSequence(this.rawSequence);
	}

	/**
	 * Constructor.
	 * @param sequence A DNA sequence.
	 */
	DNASequence(DNASequence sequence) {
		name = new String(sequence.name);
		rawSequence = new String(sequence.rawSequence);
	}

	/**
	 * Return a copy of the name.
	 * @return A copy of the name of the DNA sequence.
	 */
	String getName() {
		return(new String(name));
	}

	/**
	 * Return a copy of the DNA sequence.
	 * @return A copu of the raw sequence of the DNA sequence.
	 */
	String getRawSequence() {
		return(new String(rawSequence));
	}

	/**
	 * Return the length of the raw sequence.
	 * @return The length of the raw sequence of the DNA sequence.
	 */
	int length() {
		return(rawSequence.length());
	}

	/**
	 * Return the character at position i in the DNA raw sequence.
	 * @return The character at position i.
	 */
	char charAt(int i) {
		return(rawSequence.charAt(i));
	}

	/**
	 * Return a substring of the raw sequence.
	 */
	String substring(int beginIndex, int endIndex) {
		return(rawSequence.substring(beginIndex, endIndex));
	}

	/**
	 * Stringify the DNA sequence in FASTA-like format.
	 */
	public String toString() {
		int size = name.length() + rawSequence.length();
		StringBuffer buffer = new StringBuffer(size + 5);

		// Write the prefix FASTA
		buffer.append("> ");
		buffer.append(name);
		buffer.append("\n");

		// Write the DNA rawSequence in 80 columns format.
		int beginIndex = 0;
		while (beginIndex < rawSequence.length()) {
			int endIndex = beginIndex + 80;
			if (endIndex >= rawSequence.length()) {
				endIndex = rawSequence.length();
			}
			buffer.append(rawSequence.substring(beginIndex, endIndex));
			buffer.append("\n");
			beginIndex = endIndex + 1;
		}
		buffer.append("\n");

		// That's all !!!
		return(buffer.toString());
	}
}
