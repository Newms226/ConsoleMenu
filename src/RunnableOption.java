package menu;

public class RunnableOption extends AbstractMenuOption {
	private static final long serialVersionUID = -5386582820068767038L;
	
	public static final RunnableOption RETURN = new RunnableOption("Return", 
			                                                       null,
			                                                       () -> System.out.println("Returning"),
			                                                       () -> {return;}),
									  EXIT_WITHOUT_SAFE = new RunnableOption("Exit without saving", 
												                   null,
												                   () -> System.out.println("Exiting...\nGoodbye!"),
												                   () -> System.exit(0));
	
	final Runnable method;
	
	public RunnableOption(String optionTitle, Runnable method) {
		this (optionTitle, null, method, null);
	}
	
	public RunnableOption(String optionTitle, Runnable method, Runnable afterAction) {
		this (optionTitle, null, method, afterAction);
	}
	
	public RunnableOption(String optionTitle, Runnable prior, Runnable method, Runnable afterAction) {
		super(prior, optionTitle, afterAction);
		this.method = method;
	}
	
	@Override
	void runInternal() {
		if (method == null) {
			System.out.println("Called " + TITLE);
		} else {
			method.run();
		}
	}
}