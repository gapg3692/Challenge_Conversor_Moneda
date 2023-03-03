package methods;

public class TemperatureRequest {
	
	private static String[] untitString = {"celsius", "kelvin", "fahrenheit"};

	public static double convertTemperature(double temperature, String fromUnit, String toUnit) {
	    if (fromUnit.equalsIgnoreCase("celsius")) {
	        if (toUnit.equalsIgnoreCase("kelvin")) {
	            return temperature + 273.15;
	        } else if (toUnit.equalsIgnoreCase("fahrenheit")) {
	            return (temperature * 1.8) + 32;
	        }
	    } else if (fromUnit.equalsIgnoreCase("kelvin")) {
	        if (toUnit.equalsIgnoreCase("celsius")) {
	            return temperature - 273.15;
	        } else if (toUnit.equalsIgnoreCase("fahrenheit")) {
	            return (temperature - 273.15) * 1.8 + 32;
	        }
	    } else if (fromUnit.equalsIgnoreCase("fahrenheit")) {
	        if (toUnit.equalsIgnoreCase("celsius")) {
	            return (temperature - 32) / 1.8;
	        } else if (toUnit.equalsIgnoreCase("kelvin")) {
	            return (temperature - 32) / 1.8 + 273.15;
	        }
	    }
	    return temperature; // return original value if no conversion was made
	}

	public static String[] getUntitString() {
		return untitString;
	}


}
