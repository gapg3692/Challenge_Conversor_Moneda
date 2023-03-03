package methods;

import java.util.HashMap;

public class MeasurementUnitsConverter {

	private static String[] unitString = { "m", "cm", "mm", "km", "in", "ft", "yd", "mi" };

	private final static HashMap<String, Double> UNIT_FACTORS = new HashMap<String, Double>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			put("m", 1.0); // meter
			put("cm", 0.01); // centimeter
			put("mm", 0.001); // millimeter
			put("km", 1000.0); // kilometer
			put("in", 0.0254); // inch
			put("ft", 0.3048); // foot
			put("yd", 0.9144); // yard
			put("mi", 1609.344); // mile
		}
	};

	public static double convert(double value, String fromUnit, String toUnit) {
		if (!UNIT_FACTORS.containsKey(fromUnit) || !UNIT_FACTORS.containsKey(toUnit)) {
			throw new IllegalArgumentException("Invalid unit specified");
		}

		double factor = UNIT_FACTORS.get(fromUnit) / UNIT_FACTORS.get(toUnit);
		return value * factor;
	}

	public static String[] getUnitString() {
		return unitString;
	}

}
