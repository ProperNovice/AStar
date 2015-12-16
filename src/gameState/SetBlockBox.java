package gameState;

import model.Box;
import enums.GameStateEnum;

public class SetBlockBox extends GameState {

	public SetBlockBox(GameStateEnum gameStateEnum) {
		super(gameStateEnum);
	}

	@Override
	public void handleGameStateChange() {

	}

	@Override
	public void handleBoxPressed(Box box) {
		
		if (super.controller.boxController().isStartEndBlock(box))
			return;

		super.controller.boxController().setBlock(box);

	}

}
