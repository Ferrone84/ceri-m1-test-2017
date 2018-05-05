package fr.univavignon.rodeo.api;

public class NamedObject implements INamedObject {

	protected String name;
	
	public NamedObject(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
