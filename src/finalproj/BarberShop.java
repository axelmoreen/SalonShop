package finalproj;

public class BarberShop {
	
	
	
	
	private enum Location{
		NYC("New York City"),
		LA("Los Angeles"),
		CHICAGO("Chicago"),
		HOUSTON("Houston"),
		PHOENIX("Phoenix");
		
		private String name;
		
		private Location(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
}
