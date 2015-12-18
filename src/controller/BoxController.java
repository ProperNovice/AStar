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
	private ArrayList<Box> lowestFCost = new ArrayList<>();
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
		closeBox(this.start);
		this.start.setStartTextColor();
		openAdjacencies(this.start);
	}

	public void closeBox(Box box) {

		this.toOpen.remove(box);
		this.closed.add(box);
		box.setClosedColor();

	}

	public void openAdjacencies(Box boxOriginal) {

		ArrayList<Box> boxes = getAdjacenciesActive(boxOriginal);

		for (Box boxTemp : boxes) {

			int gCost = getGCost(boxOriginal, boxTemp);

			if (this.toOpen.contains(boxTemp))
				if (boxTemp.getGCost() < gCost)
					continue;

			if (!this.toOpen.contains(boxTemp))
				this.toOpen.add(boxTemp);

			boxTemp.setToOpenColor();

			boxTemp.setGCostUpdateTexts(gCost);

			int hCost = getHCost(boxTemp);
			boxTemp.setHCostUpdateTexts(hCost);

			boxTemp.setParent(boxOriginal);

		}

	}

	private int getGCost(Box boxOriginal, Box boxTemp) {

		int gCost = boxOriginal.getGCost();

		int rowOriginal = boxOriginal.getRow();
		int columnOriginal = boxOriginal.getColumn();
		int boxRow = boxTemp.getRow();
		int boxColumn = boxTemp.getColumn();

		int boxRowDif = (int) Math.abs(boxRow - rowOriginal);
		int boxColumnDif = (int) Math.abs(boxColumn - columnOriginal);

		if (boxRowDif + boxColumnDif == 1)
			gCost += Credentials.CARTESIAN.credential();
		else
			gCost += Credentials.DIAGONAL.credential();

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

	private ArrayList<Box> getAdjacenciesActive(Box box) {

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

		for (Box boxTemp : boxes.clone())
			if (boxTemp == null)
				boxes.remove(boxTemp);
			else if (this.blocks.contains(boxTemp))
				boxes.remove(boxTemp);
			else if (this.closed.contains(boxTemp))
				boxes.remove(boxTemp);

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

	public void createLowestFCostList() {

		this.lowestFCost.clear();
		this.lowestFCost.addAll(this.toOpen);

		int fCostLowest = this.lowestFCost.getFirst().getFCost();

		for (Box box : this.lowestFCost)
			if (box.getFCost() < fCostLowest)
				fCostLowest = box.getFCost();

		for (Box box : this.lowestFCost.clone())
			if (box.getFCost() > fCostLowest)
				this.lowestFCost.remove(box);

		for (Box box : this.lowestFCost)
			box.setLowestFCostColor();

	}

	public boolean isLowestFCostList(Box box) {
		return this.lowestFCost.contains(box);
	}

	public boolean pathFound(Box box) {
		return getAdjacenciesActive(box).contains(this.end);
	}

	public void setEndParent(Box box) {
		this.end.setParent(box);
	}

	public void setBoxesToOpenColor() {
		for (Box box : this.lowestFCost)
			box.setToOpenColor();
	}

	public void showPath() {
		showPath(this.end);
	}

	private void showPath(Box box) {

		box.setPathColor();

		Box parent = box.getParent();

		if (parent == null)
			return;

		showPath(parent);

	}

	public Box getStart() {
		return this.start;
	}

}
