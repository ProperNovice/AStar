package controller;

import model.Box;
import utils.ArrayList;
import enums.Credentials;
import enums.Dimensions;

public class BoxController {

	private ArrayList<ArrayList<Box>> boxes = new ArrayList<>();
	private ArrayList<Box> blocks = new ArrayList<>();
	private ArrayList<Box> toOpen = new ArrayList<>();
	private ArrayList<Box> closed = new ArrayList<>();
	private Box start = null;
	private Box end = null;

	public BoxController() {

		createBoxes();

	}

	private void createBoxes() {

		int rowsMax = (int) Dimensions.LABYRINTH.x();
		int columnsMax = (int) Dimensions.LABYRINTH.y();

		for (int row = 0; row < rowsMax; row++) {

			this.boxes.add(new ArrayList<Box>());

			for (int column = 0; column < columnsMax; column++)
				this.boxes.getLast().add(new Box(row, column));

		}

	}

	public void setStart(Box box) {
		this.start = box;
		this.toOpen.add(box);
		box.setStartTextColor();
	}

	public void setEnd(Box box) {
		this.end = box;
		this.closed.add(this.end);
		box.setEndTextColor();
	}

	public void setBlock(Box box) {
		this.blocks.add(box);
		box.setBlockColor();
	}

	public boolean isStartEndBlock(Box box) {

		if (box.equals(this.start))
			return true;
		else if (box.equals(this.end))
			return true;
		else if (this.blocks.contains(box))
			return true;

		return false;

	}

	public void openStartAdjacentBoxes() {

		this.closed.add(this.start);
		this.toOpen.remove(this.start);

		openAdjacencies(this.start);

	}

	public void openAdjacencies(Box boxOriginal) {

		ArrayList<Box> boxes = getAdjacencies(this.start);

		for (Box box : boxes.clone())
			if (box == null)
				boxes.remove(box);
			else if (this.blocks.contains(box))
				boxes.remove(box);

		for (Box boxTemp : boxes) {

			this.toOpen.add(boxTemp);
			boxTemp.setToOpenColor();

			int gCost = getGCost(boxOriginal, boxTemp);
			boxTemp.setGCostUpdateTexts(gCost);

			int hCost = getHCost(boxTemp);
			boxTemp.setHCostUpdateTexts(hCost);

		}

	}

	private int getGCost(Box boxOriginal, Box boxTemp) {

		int gCost;

		int rowOriginal = boxOriginal.getRow();
		int columnOriginal = boxOriginal.getColumn();
		int boxRow = boxTemp.getRow();
		int boxColumn = boxTemp.getColumn();

		int boxRowDif = (int) Math.abs(boxRow - rowOriginal);
		int boxColumnDif = (int) Math.abs(boxColumn - columnOriginal);

		if (boxRowDif + boxColumnDif == 1)
			gCost = Credentials.CARTESIAN.credential();
		else
			gCost = Credentials.DIAGONAL.credential();

		return gCost;

	}

	private int getHCost(Box boxOriginal) {

		int hCost = 0;

		int rowOriginal = boxOriginal.getRow();
		int columnOriginal = boxOriginal.getColumn();
		int rowEnd = this.end.getRow();
		int columnEnd = this.end.getColumn();

		int boxRowDif = (int) Math.abs(rowEnd - rowOriginal);
		int boxColumnDif = (int) Math.abs(columnEnd - columnOriginal);

		int minimum = (int) Math.min(boxRowDif, boxColumnDif);

		boxRowDif -= minimum;
		boxColumnDif -= minimum;

		hCost += minimum * Credentials.DIAGONAL.credential();
		hCost += boxRowDif * Credentials.CARTESIAN.credential();
		hCost += boxColumnDif * Credentials.CARTESIAN.credential();

		return hCost;

	}

	private ArrayList<Box> getAdjacencies(Box box) {

		int row = box.getRow();
		int column = box.getColumn();

		ArrayList<Box> boxes = new ArrayList<>();

		boxes.add(getBoxWithCoordinates(row - 1, column - 1));
		boxes.add(getBoxWithCoordinates(row - 1, column));
		boxes.add(getBoxWithCoordinates(row - 1, column + 1));
		boxes.add(getBoxWithCoordinates(row, column - 1));
		boxes.add(getBoxWithCoordinates(row, column + 1));
		boxes.add(getBoxWithCoordinates(row + 1, column - 1));
		boxes.add(getBoxWithCoordinates(row + 1, column));
		boxes.add(getBoxWithCoordinates(row + 1, column + 1));

		return boxes;

	}

	private Box getBoxWithCoordinates(int row, int column) {

		if (row < 0)
			return null;

		if (row == this.boxes.size())
			return null;

		if (column < 0)
			return null;

		if (column == this.boxes.getFirst().size())
			return null;

		return this.boxes.get(row).get(column);

	}

}
