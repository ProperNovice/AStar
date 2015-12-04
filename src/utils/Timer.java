package utils;

import java.util.concurrent.atomic.AtomicBoolean;

public class Timer {

	private long MILLIS_TO_FIRE_EVENT = -1;
	private AtomicBoolean isRunning = new AtomicBoolean(false);
	private TimerClass timerClass = null;
	private TimerInterface timerInterface = null;

	public Timer(long millisToFireEvent, TimerInterface timerInterface) {

		this.timerInterface = timerInterface;
		this.MILLIS_TO_FIRE_EVENT = millisToFireEvent;

		addShutDownHook();

	}

	public interface TimerInterface {
		public void fireEvent();
	}

	public void start() {

		this.isRunning.set(true);
		this.timerClass = new TimerClass();
		this.timerClass.start();

	}

	private class TimerClass extends java.lang.Thread {

		private long startTime = -1;
		private AtomicBoolean stopTimer = new AtomicBoolean(false);

		public TimerClass() {
			this.startTime = currentTimeMillis();
		}

		public void stopTimer() {
			this.stopTimer.set(true);
		}

		@Override
		public void run() {

			while (!this.stopTimer.get()) {

				sleepTime(0);

				long actualTimeSleeping = currentTimeMillis() - this.startTime;

				if (actualTimeSleeping < MILLIS_TO_FIRE_EVENT)
					continue;

				this.startTime += MILLIS_TO_FIRE_EVENT;

				fireEvent();

			}
		}
	}

	public void stop() {
		this.isRunning.set(false);

		if (this.timerClass == null)
			return;

		this.timerClass.stopTimer();
	}

	private long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	private void sleepTime(long duration) {
		Executor.sleep(duration);
	}

	private void fireEvent() {
		Executor.runLater(() -> this.timerInterface.fireEvent());
	}

	public boolean isRunning() {
		return this.isRunning.get();
	}

	private void addShutDownHook() {

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				stop();
			}
		}));
	}

}
