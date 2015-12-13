package utils;

public class ShutDown {

	public static void execute() {
		Logger.logNewLine("System.exit(0)");
		System.exit(0);
	}

	public static void execute(String log) {
		Logger.logNewLine(log);
		execute();
	}

}
