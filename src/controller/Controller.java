package controller;

import instances.Instances;

public class Controller {

	private GameStateController gameStateController = null;
	private TextController textController = null;
	private FlowController flowController = null;
	private BoxController boxController = null;

	public Controller() {
		createInstances();
	}

	private void createInstances() {

		Instances.setControllerInstance(this);

		this.gameStateController = new GameStateController();
		this.textController = new TextController();
		this.flowController = new FlowController();
		this.boxController = new BoxController();

	}

	public GameStateController gameStateController() {
		return this.gameStateController;
	}

	public TextController textController() {
		return this.textController;
	}

	public FlowController flowController() {
		return this.flowController;
	}

	public BoxController boxController() {
		return this.boxController;
	}

}
