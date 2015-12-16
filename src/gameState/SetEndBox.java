package gameState;

import model.Box;
import enums.GameStateEnum;

public class SetEndBox extends GameState {

	public SetEndBox(GameStateEnum gameStateEnum) {
		super(gameStateEnum);
	}

	@Override
	public void handleGameStateChange() {

	}

	@Override
	public void handleBoxPressed(Box box) {

		if (super.controller.boxController().isStartEndBlock(box))
			return;

		super.controller.boxController().setEnd(box);

		super.controller.flowController().proceedToNextPhase();

	}

}
