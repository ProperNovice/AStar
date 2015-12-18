package controller;

import model.Box;
import utils.ArrayList;
import enums.Dimensions;

public class BoxController {

	private ArrayList<ArrayList<Box>> boxes = new ArrayList<>();
	private ArrayList<Box> blocks = new ArrayList<>();
	private ArrayList<Box> closed = new ArrayList<>();
	private ArrayList<Box> toOpen = new ArrayList<>();
	private Box start = null;
	private Box end = null;

	public BoxController() {

		createBoxes();

	}

	private void createBoxes() {

		int rowsMax = (int) Dimensions.LABYRINTH.x();
		int columnsMax = (int) Dimensions.LABYRINTH.y();

		for (int row = 1; row <= rowsMax; row++) {

			this.boxes.add(new ArrayList<Box>());

			for (int column = 1; column <= columnsMax; column++)
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

	public void setClosed(Box box) {
		this.closed.add(box);
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

}
