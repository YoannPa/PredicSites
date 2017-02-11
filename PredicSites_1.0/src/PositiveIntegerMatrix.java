import java.lang.*;
import java.lang.String.*; 
import java.text.DecimalFormat;

/**
 * Abstract class PositiveIntegerMatrix.
 */
abstract class PositiveIntegerMatrix extends AbstractMatrix {

	/**
	 * 2DArray to store the elements.
	 */
	protected int element[][];

	/**
	 * Constructor.
	 */
	public PositiveIntegerMatrix(int numberOfRows, int numberOfColumns) {
		element = new int[numberOfRows][numberOfColumns];
		for (int col=0;col<numberOfColumns;col++){
			for (int ligne=0;ligne<numberOfRows;ligne++){
				this.element[ligne][col]=(int)0;
			}
		}
		this.numberOfColumns=numberOfColumns;
		this.numberOfRows=numberOfRows;
	}

	/**
	 * Constructor
	 */
	public PositiveIntegerMatrix(PositiveIntegerMatrix matrix) {
		this.element=matrix.element;
		this.numberOfColumns=matrix.numberOfColumns;
		this.numberOfRows=matrix.numberOfRows;
	}

	/**
	 * Return the element at row i column j.
	 */
	public int getElement(int i, int j) {
		return element[i][j];
	}

	/**
	 * Raise IllegalArgumentException if value is negative.
	 */
	protected void checkValue(int value) throws IllegalArgumentException {
		if (value < 0) {
			throw new IllegalArgumentException("negative value: " + value);
		}
	}

	/**
	 * Convert the matrix to a String for pretty printing.
	 */
	public String toString() 
	{
		StringBuffer st = new StringBuffer(numberOfRows * numberOfColumns * 25);
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				DecimalFormat df = new DecimalFormat("###00");
				st.append(" " + df.format(element[i][j]));
			}
			st.append("\n");
		}
		return(st.toString());
	}
}
