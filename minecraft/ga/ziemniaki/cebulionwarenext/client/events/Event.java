package ga.ziemniaki.cebulionwarenext.client.events;

public abstract class Event {

	private boolean cancelled;

	public boolean isCancelled() {
		return cancelled;
	}

	public void cancel() {
		this.cancelled = true;
	}

}
