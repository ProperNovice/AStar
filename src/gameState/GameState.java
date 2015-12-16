package gameState;

import model.Box;
import instances.Instances;
import javafx.scene.input.KeyCode;
import controller.Controller;
import enums.GameStateEnum;
import enums.TextEnum;

public abstract class GameState {

	protected Controller controller = Instances.getControllerInstance();
	private GameStateEnum gameStateEnum = null;

	public GameState(GameStateEnum gameStateEnum) {
		this.gameStateEnum = gameStateEnum;
	}

	public abstract void handleGameStateChange();

	public void handleTextOptionPressed(TextEnum textEnum) {

	}

	public void handleKeyPressed(KeyCode keyCode) {

	}

	public void handleBoxPressed(Box box) {

	}

	public final GameStateEnum getGameStateEnum() {
		return this.gameStateEnum;
	}

}
