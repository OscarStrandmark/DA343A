package f01;

public class StringBuilderEx {
	public void action() {
		String name = "Gustav";
		String street = "Föreningsgatan 11";
		String town = "Malmö";
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(", ").append(street).append(", ").append(town);
		
		System.out.println(sb.toString());
		
		sb.insert(6, " Hansson");
		sb.delete(26, 30);
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		new StringBuilderEx().action();
	}
}
