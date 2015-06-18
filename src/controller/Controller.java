package controller;

import utils.Executor;
import enums.GameStateEnum;
import instances.Instances;

public class Controller {

	private GameStateController gameStateController = null;

	public Controller() {

		createInstances();
		Executor.runLater(() -> this.gameStateController
				.setGameState(GameStateEnum.START_GAME));

	}

	private void createInstances() {

		Instances.setControllerInstance(this);
		this.gameStateController = new GameStateController();

	}

	public GameStateController gameStateController() {
		return this.gameStateController;
	}

}
