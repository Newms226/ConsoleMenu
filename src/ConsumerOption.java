package menu;

import java.util.function.Consumer;

public class ConsumerOption<T> extends AbstractMenuOption {
	private static final long serialVersionUID = -4218012884696906663L;

	Consumer<T> consumer;
	T toConsume;
	
	ConsumerOption(String title, Runnable prior, Consumer<T> consumer, Runnable after) {
		super(prior, title, after);
		this.consumer = consumer;
	}
	
	public void setObjectToConsume(T toConsume) {
		this.toConsume = toConsume;
	}

	@Override
	void runInternal() {
		if (consumer == null) {
			System.out.println("Called " + TITLE);
		} else if (toConsume == null) {
			System.out.println("Called " + TITLE + " without settint objectToConsume");
		} else {
			consumer.accept(toConsume);
		}
		
	}

}
