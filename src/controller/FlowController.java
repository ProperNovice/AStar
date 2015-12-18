package controller;

import instances.Instances;
import utils.ArrayList;
import enums.GameStateEnum;

public class FlowController {

	private ArrayList<GameStateEnum> gameStateNormalTurn = new ArrayList<>();
	private ArrayList<GameStateEnum> gameStateResolving = new ArrayList<>();

	public FlowController() {

		createGameStateNormalTurn();

		this.gameStateResolving.add(GameStateEnum.SET_START_BOX);
		this.gameStateResolving.add(GameStateEnum.SET_END_BOX);
		this.gameStateResolving.add(GameStateEnum.SET_BLOCK_BOX);

	}

	public void proceedToNextPhase() {

		if (this.gameStateResolving.isEmpty())
			this.gameStateResolving.addAll(this.gameStateNormalTurn);

		Controller controller = Instances.getControllerInstance();

		GameStateEnum gameStateEnum = this.gameStateResolving.removeFirst();
		controller.gameStateController().setGameState(gameStateEnum);

	}

	public void addGameStateFirst(GameStateEnum gameStateEnum) {
		this.gameStateResolving.addFirst(gameStateEnum);
	}

	public void createGameStateNormalTurn() {

		this.gameStateNormalTurn.add(GameStateEnum.CHOOSE_LOWEST_F_COST_BOX);

	}

}
