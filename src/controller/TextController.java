package controller;

import enums.Coordinates;
import enums.Credentials;
import enums.TextEnum;
import gui.TextGame;
import utils.ArrayList;

public class TextController {

	private ArrayList<TextGame> textGame = new ArrayList<>();
	private ArrayList<TextGame> textGameShowing = new ArrayList<>();
	private Coordinates coordinates = Coordinates.TEXT_PANEL;

	public TextController() {
		createTexts();
	}

	private void createTexts() {

		for (TextEnum textEnum : TextEnum.values())
			this.textGame.add(new TextGame(textEnum));

	}

	public void showText(TextEnum textEnum) {

		for (TextGame textGame : this.textGame) {

			if (!textGame.getTextEnum().equals(textEnum))
				continue;

			this.textGameShowing.add(textGame);
			break;

		}

		showText();

	}

	private void showText() {

		double x = this.coordinates.x();
		double y = this.coordinates.y();

		for (TextGame textGame : this.textGameShowing) {

			textGame.toFront();

			textGame.setVisible(true);
			textGame.relocate(x, y);

			y += Credentials.TEXT_HEIGHT.credential();

			if (textGame.getTextEnum().string().contains("\n"))
				y += Credentials.TEXT_HEIGHT.credential();

		}

	}

	public void concealText() {

		for (TextGame textGame : this.textGameShowing)
			textGame.setVisible(false);

		this.textGameShowing.clear();

	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

}
