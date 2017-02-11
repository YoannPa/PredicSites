/**
 * Abstract class AbstractMatrix.
 */
abstract class AbstractMatrix {

	/**
	 * Number of rows.
	 */
	protected int numberOfRows;

	/**
	 * Number of columns.
	 */
	protected int numberOfColumns;

	/** 
	 * Return the number of rows of the matrix.
	 */
	public int getNumberOfRows() {
		return this.numberOfRows;
	}

	/**
	 * Return the number of columns of the matrix.
	 */
	public int getNumberOfColumns() {
		return this.numberOfColumns;
	}

	/**
	 * Throw IndexOutOfBoundsException in case of incorrect row index.
	 */
	public void checkRowIndex(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= numberOfRows) {
			throw new IndexOutOfBoundsException("Bad row index: " + i);
		}
	}

	/**
	 * Throw IndexOutOfBoundsException in case of incorrect column index.
	 */
	public void checkColumnIndex(int j) throws IndexOutOfBoundsException {
		if (j < 0 || j >= numberOfColumns) {
			throw new IndexOutOfBoundsException("Bad column index: " + j);
		}
	}
}
