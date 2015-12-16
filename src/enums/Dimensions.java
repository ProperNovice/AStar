package enums;

public enum Dimensions {

	INSETS(7, 29),
	GAP_BETWEEN_BORDERS(50, 50),
	GAP_BETWEEN_BOXES(1, 1),
	BOX(80, 80),
	LABYRINTH(10, 10),
	FRAME(BOX.x() * LABYRINTH.x() + GAP_BETWEEN_BOXES.x() * (LABYRINTH.x() - 1) + 2 * GAP_BETWEEN_BORDERS.x(), BOX.y() * LABYRINTH.y() + GAP_BETWEEN_BOXES.y() * (LABYRINTH.y() - 1) + 2 * GAP_BETWEEN_BORDERS.y()),
	
	;

	private double x = -1, y = -1;

	private Dimensions(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

}
