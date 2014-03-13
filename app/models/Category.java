package models;

public class Category {
	final private int id;
	private final String name;
	private final String icon;

	public Category(int id, String name, String icon) {
		this.id = id;
		this.name = name;
		this.icon = icon;	
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}
}
