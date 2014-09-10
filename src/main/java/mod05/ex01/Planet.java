package mod05.ex01;
public enum Planet {
	Mercury(0.0553, 4879), 
	Venus(0.815, 12104), 
	Earth(1.000, 12742), 
	Mars(0.107, 6779), 
	Jupiter(317.83, 139822), 
	Saturn(95.159, 116464), 
	Uranus(14.536, 50724), 
	Neptune(17.147, 49244), 
	Pluto(0.0021, 2390);
	
	double mass; // relative to Earth
	double diameter; // km
	private final static double EARTH_MASS = 5.9736E24; // kg

	Planet(double mass, double diameter) {
		this.mass = mass;
		this.diameter = diameter;
	}

	public double getMass() {
		return mass * EARTH_MASS; // kg
	}

	public double getRadius() {
		return diameter / 2.0; // km
	}

	public double getDensity() {
		double radiusInCm = getRadius() * 1E5;
		double massInG = getMass() * 1E3; 
		double volume = 4.0 / 3.0 * Math.PI * radiusInCm * radiusInCm * radiusInCm;
		return massInG / volume;
	}

}
