package OpenWeather;

public class Weather {

	private int id;
	private String main;
	private String description;
	private String icon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String description) {
		this.description = description;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon() {
		this.icon = icon;
	}

	public String toString() {
		return "The weather right now is " + description + ".";
	}

}
