package controller;

import model.Box;
import enums.GameStateEnum;
import enums.TextEnum;
import gameState.GameState;
import gameState.SetBlockBox;
import gameState.SetEndBox;
import gameState.SetStartBox;
import gameState.StartGame;
import javafx.scene.input.KeyCode;
import utils.ArrayList;
import utils.Logger;

public class GameStateController {

	private GameState currentGameState = null;
	private ArrayList<GameState> gameStates = new ArrayList<>();

	public GameStateController() {

		this.gameStates.add(new StartGame(GameStateEnum.START_GAME));
		this.gameStates.add(new SetStartBox(GameStateEnum.SET_START_BOX));
		this.gameStates.add(new SetEndBox(GameStateEnum.SET_END_BOX));
		this.gameStates.add(new SetBlockBox(GameStateEnum.SET_BLOCK_BOX));

	}

	public void setGameState(GameStateEnum gameStateEnum) {

		for (GameState gameState : this.gameStates) {

			GameStateEnum gameStateEnumTemp = gameState.getGameStateEnum();

			if (!gameStateEnumTemp.equals(gameStateEnum))
				continue;

			this.currentGameState = gameState;
			break;

		}

		Logger.log("changing gameState");
		Logger.logNewLine(gameStateEnum);

		this.currentGameState.handleGameStateChange();

	}

	public void handleTextOptionPressed(TextEnum textEnum) {
		this.currentGameState.handleTextOptionPressed(textEnum);
	}

	public void handleKeyPressed(KeyCode keyCode) {
		this.currentGameState.handleKeyPressed(keyCode);
	}

	public void handleBoxPressed(Box box) {
		this.currentGameState.handleBoxPressed(box);
	}

}
