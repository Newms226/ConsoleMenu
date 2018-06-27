package menu;

import java.io.Serializable;

abstract class AbstractMenuOption implements Serializable{
	private static final long serialVersionUID = -7013784446735598208L;
	
	final String TITLE;
	Runnable prior, after;
	
//	AbstractMenuOption(String title) {
//		this(null, title, null);
//	}
//	
//	AbstractMenuOption(String title, Runnable after) {
//		this(null, title, after);
//	}
	
	AbstractMenuOption(Runnable prior, String title, Runnable after) {
		this.TITLE = title;
		this.prior = prior;
		this.after = after;
	}

	final void run() {
		if (prior != null) prior.run();
		runInternal();
		if (after != null) after.run(); // TODO
	}
	
	void conditionalSetActions(Runnable defaultPriorAction, Runnable defaultAfterAction) {
		if (after == null) {
			after = defaultAfterAction;
		}
		if (prior == null) {
			prior = defaultPriorAction;
		}
	}
	
	abstract void runInternal();
	
	public String toString() {
		return TITLE;
	}

}
