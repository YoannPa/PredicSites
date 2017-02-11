import java.lang.*;
import java.lang.String.*; 
import java.text.DecimalFormat;

/**
 * Abstract class DoubleMatrix.
 */
abstract class DoubleMatrix extends AbstractMatrix {

	/**
	 * 2DArray to store the elements.
	 */
	protected double element[][];

	/**
	 * Constructor.
	 */
	public DoubleMatrix(int numberOfRows, int numberOfColumns) {
		element = new double[numberOfRows][numberOfColumns];
		for (int col=0;col<numberOfColumns;col++){
			for (int ligne=0;ligne<numberOfRows;ligne++){
				this.element[ligne][col]=(double)0;
			}
		}
		this.numberOfColumns=numberOfColumns;
		this.numberOfRows=numberOfRows;
	}

	/**
	 * Constructor.
	 */
	DoubleMatrix(DoubleMatrix matrix) {
		this.element=matrix.element;
		this.numberOfColumns=matrix.numberOfColumns;
		this.numberOfRows=matrix.numberOfRows;
	}

	/**
	 * Constructor.
	 */
	DoubleMatrix(PositiveIntegerMatrix matrix) {
		this.element = new double[matrix.numberOfRows][matrix.numberOfColumns];
		for (int col=0;col<matrix.numberOfColumns;col++){
			for (int ligne=0;ligne<matrix.numberOfRows;ligne++){
				this.element[ligne][col]=(double)matrix.element[ligne][col];
			}
		}
		this.numberOfColumns=matrix.numberOfColumns;
		this.numberOfRows=matrix.numberOfRows;
	}

	/**
	 * Return the element at row i column j.
	 */
	public double getElement(int i, int j) {
		return this.element[i][j];
	}

	/**
	 * Convert the matrix to a String for pretty printing.
	 */
	public String toString() 
	{
		String s = new String();
		DecimalFormat df = new DecimalFormat ();
		df.setMaximumFractionDigits(2);
		for(int i=0;i<this.numberOfRows;i++){
			for(int j=0;j<this.numberOfColumns;j++){
				s+=df.format(this.element[i][j])+"\t";
			}
			s+="\n";
		}
		return s;
	}
}
