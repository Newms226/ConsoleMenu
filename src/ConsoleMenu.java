package menu;

import java.util.ArrayList;

import tools.NumberTools;

public class ConsoleMenu implements Menu {

	private ArrayList<AbstractMenuOption> options;
	public final String TITLE;
	private final Runnable defaultAfterOperation, defaultPrior;
	private boolean optionsModified;
	private String summary;
	
	public ConsoleMenu(String title) {
		this(title, null, null);
	}
	
	public ConsoleMenu(String title, Runnable defaultAfter) {
		this(title, null, defaultAfter);
	}
	
	public ConsoleMenu(String title, Runnable defaultPrior, Runnable defaultAfter) {
		this.TITLE = title;
		options = new ArrayList<>();
		this.defaultPrior = defaultPrior;
		
		if (defaultAfter == null) {
			defaultAfterOperation = () -> selection();
		} else {
			this.defaultAfterOperation = defaultAfter;
		}
	}
	
//	public <OPTION extends AbstractMenuOption> void add( option) {
//		options.add(option);
//		optionsModified = true;
//	}
	
	public void add(AbstractMenuOption option) {
		option.conditionalSetActions(defaultPrior, defaultAfterOperation);
		options.add(option);
		optionsModified = true;
	}

	@Override
	public void selection() {
		System.out.println(this);
		options.get((NumberTools.generateInt(false, 1, size())) - 1).run();
	}

	@Override
	public void buildString() {
		StringBuffer buffer = new StringBuffer("\n" + TITLE + "\n");
		for (int i = 0; i < size(); i++) {
			buffer.append("  " + (i + 1) + ". " + options.get(i) + "\n");
		} 
		summary = buffer.toString();
		optionsModified = false;
	}

	@Override
	public int size() {
		return options.size();
	}
	
	public String toString() {
		if (optionsModified) buildString();
		return summary;
	}
	
	@SuppressWarnings("unused")
	private static void test() {
		ConsoleMenu menu = new ConsoleMenu("Hello!");
		menu.add(new RunnableOption("Run test", () -> System.out.println("Called run test")));
		ConsumerOption<Integer> divisionByTwo = new ConsumerOption<>("Division by two", () -> System.out.println("Testing prior..."), (x) -> System.out.println(x / 2 + ""), null);
		menu.add(divisionByTwo);
		divisionByTwo.setObjectToConsume(new Integer(6));
		menu.add(RunnableOption.RETURN);
		menu.selection();
	}
	
	public static void main(String[] args) {
		test();
	}

}
