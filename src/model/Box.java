package model;

import instances.Instances;
import javafx.scene.paint.Color;
import utils.EventHandler.EventHandlerAble;
import utils.Rectangle;
import utils.Text;
import enums.Coordinates;
import enums.Dimensions;

public class Box implements EventHandlerAble {

	private int row, column;
	private double xCoordinate, yCoordinate;
	private Rectangle rectangle = null;

	public Box(int row, int column) {

		this.row = row;
		this.column = column;
		createRectangle();

	}

	private void createRectangle() {

		double width = Dimensions.BOX.x();

		this.rectangle = new Rectangle(width);

		double x = Coordinates.BOX_FIRST.x();
		x += (this.column - 1) * width;
		x += (this.column - 1) * Dimensions.GAP_BETWEEN_BOXES.x();

		double y = Coordinates.BOX_FIRST.y();
		y += (this.row - 1) * width;
		y += (this.row - 1) * Dimensions.GAP_BETWEEN_BOXES.y();

		this.xCoordinate = x;
		this.yCoordinate = y;

		this.rectangle.relocate(this.xCoordinate, this.yCoordinate);
		this.rectangle.setFill(Color.GRAY);
		this.rectangle.setStroke(null);

		this.rectangle.setEventHandler(this);

	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Instances.getControllerInstance().gameStateController()
				.handleBoxPressed(this);
	}

	public void setStartTextColor() {
		setStartEndText("s");
		this.rectangle.setFill(Color.GREEN);
	}

	public void setEndTextColor() {
		setStartEndText("e");
		this.rectangle.setFill(Color.GREEN);
	}

	public void setBlockColor() {
		this.rectangle.setFill(Color.BLACK);
	}

	private void setStartEndText(String string) {

		Text text = new Text(string);

		double height = 2 * Dimensions.BOX.y() / 3;
		text.setHeight(height);

		double width = text.getWidth();

		double x = this.xCoordinate + (Dimensions.BOX.x() - width) / 2;
		double y = this.yCoordinate + (Dimensions.BOX.y() - height) / 2;

		text.relocate(x, y);

	}

}
