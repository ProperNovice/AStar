package gameState;

import model.Box;
import enums.GameStateEnum;

public class ChooseLowestFCostBox extends GameState {

	public ChooseLowestFCostBox(GameStateEnum gameStateEnum) {
		super(gameStateEnum);
	}

	@Override
	public void handleGameStateChange() {

		super.controller.boxController().createLowestFCostList();

	}

	@Override
	public void handleBoxPressed(Box box) {

		if (!super.controller.boxController().isLowestFCostList(box))
			return;

		super.controller.boxController().setBoxesToOpenColor();
		super.controller.boxController().closeBox(box);

		if (super.controller.boxController().pathFound(box)) {

			super.controller.boxController().setEndParent(box);
			super.controller.flowController().addGameStateFirst(
					GameStateEnum.PATH_FOUND);

		} else
			super.controller.boxController().openAdjacencies(box);

		super.controller.flowController().proceedToNextPhase();

	}

}
