package gameState;

import model.Box;
import enums.GameStateEnum;

public class SetStartBox extends GameState {

	public SetStartBox(GameStateEnum gameStateEnum) {
		super(gameStateEnum);
	}

	@Override
	public void handleGameStateChange() {

	}

	@Override
	public void handleBoxPressed(Box box) {

		super.controller.boxController().setStart(box);

		super.controller.flowController().proceedToNextPhase();

	}

}
