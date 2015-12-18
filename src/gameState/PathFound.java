package gameState;

import enums.GameStateEnum;

public class PathFound extends GameState {

	public PathFound(GameStateEnum gameStateEnum) {
		super(gameStateEnum);
	}

	@Override
	public void handleGameStateChange() {
		
		super.controller.boxController().showPath();
		
	}

}
