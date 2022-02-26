package finalproj;

public enum HairStyle {
	HIGH_FADE("High Fade", "Sharp fade for a clean and sharp appearance.",3),
	MID_FADE("Mid Fade", "An evenly distributed fade that's stylish and classy.", 4),
	LOW_FADE("Low Fade", "A subtle fade just above the ears with longer sides.", 4),
	BLOWOUT("Blowout", "Attractive, voluminous hair with short sides.", 4),
	COMB_OVER("Comb-Over", "Versatile look for influencers and hipsters!", 3),
	BRAIDS("Braids", "Cool, stylizable braids for any hair length and size!", 1),
	DREADLOCKS("Dreadlocks", "Classic hairstyle where hair is twisted into rope-like locks.", 3),
	CREW_CUT("Crew Cut", "Cleanly trimmed hair for a neat and tidy apperance.", 1),
	FRENCH_CROP("French Crop", "Short sides with a slightly longer length on top.", 2),
	BUZZ_CUT("Buzz Cut", "Bald is sexy!", 1),
	MULLET("Mullet", "Long in the back. Look like a rockstar!", 2),
	BOWL_CUT("Bowl Cut", "The Beatles did it first!", 1);
	
	
	
	private String name;
	private String description;
	private int difficulty;
	
	private HairStyle(String name, String description, int difficulty) {
		this.name = name;
		this.description = description;
		this.difficulty = difficulty;
	}
	
	public String getDisplayName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
}
