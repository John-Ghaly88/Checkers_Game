package entity.pieces;

public class Location {
	private int row;
	private int col;

	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public String toString() {
		return this.row + " " + this.col + "";
	}

	public void setRow(int x) {
		this.row = x;
	}

	public void setCol(int x) {
		this.col = x;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public boolean equals(Object obj) {
		Location x = (Location) obj;
		return (this.row == x.row && this.col == x.col);
	}
}
