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

		if (!super.controller.boxController().isStartEndBlock(box))
			super.controller.boxController().setBlock(box);

		else {

			Box start = super.controller.boxController().getStart();

			if (super.controller.boxController().pathFound(start)) {

				super.controller.boxController().setEndParent(start);
				super.controller.flowController().addGameStateFirst(
						GameStateEnum.PATH_FOUND);

			} else
				super.controller.boxController().openStartAdjacentBoxes();

			super.controller.flowController().proceedToNextPhase();

		}

	}

}
