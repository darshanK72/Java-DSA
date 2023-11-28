public class j14Abstraction {

	public static void main(String[] args) {
		
		/*
		 * Abstract class is a class which contain abstract methods, abstract methods are methods declared in class without any body,
		 * they are always must be derived and declared in child class, else child class also becomes abstract class
		 * 
		 * we cannot make objects of abstract class
		 */
		
		Dog d = new Dog(4,"Dogs","RotWheeler",false);
		
		d.sound();
	}

}

abstract class Animal
{
	private int leggs;
	private String species;
	private String name;
	private boolean canFly;
	
	public Animal(int leggs, String species, String name, boolean canFly) {
		super();
		this.setLeggs(leggs);
		this.setSpecies(species);
		this.setName(name);
		this.setCanFly(canFly);
	}

	abstract void sound();

	public boolean isCanFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public int getLeggs() {
		return leggs;
	}

	public void setLeggs(int leggs) {
		this.leggs = leggs;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

class Dog extends Animal
{
	public Dog(int leggs, String species, String name, boolean canFly) {
		super(leggs,species,name,canFly);
	}
	void sound()
	{
		System.out.println("Boo Boo");
	}
	
}
