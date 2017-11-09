import java.util.Scanner;

public class Validator {
	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.next(); // read user entry
		sc.nextLine(); // discard any other data entered on the line
		return s;
	}
	
	public static String getStringSelection(Scanner sc, String prompt) {
		String s = null;

		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNext()) {
				s = sc.next();
				sc.nextLine(); // discard any other data entered on the line
				if (s.equalsIgnoreCase("SuperMario") || s.equalsIgnoreCase("StarWars")) {
					isValid = true;
				} else {
					System.out.println("Error! Invalid answer. Please enter 'SuperMario' or 'StarWars'.");
				}

			} else {
				System.out.println("Error! Invalid answer. Please enter 'SuperMario' or 'StarWars'.");
			}
		}
		return s.toLowerCase();
	}
	

	public static String getStringYN(Scanner sc, String prompt) {
		String s = null;

		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNext()) {
				s = sc.next();
				sc.nextLine(); // discard any other data entered on the line
				if (s.length() == 1 && s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) {
					isValid = true;
				} else {
					System.out.println("Error! Invalid answer. Please enter 'y' or 'n'.");
				}

			} else {
				System.out.println("Error! Invalid answer. Please enter 'y' or 'n'.");
			}
		}
		return s.toLowerCase();
	}

	public static int getInt(Scanner sc, String prompt) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}

	public static int getInt(Scanner sc, String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i < min)
				System.out.println("Error! Number must be " + min + " or greater.");
			else if (i > max)
				System.out.println("Error! Number must be " + max + " or less.");
			else
				isValid = true;
		}
		return i;
	}

	public static double getDouble(Scanner sc, String prompt) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid decimal value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return d;
	}

	public static double getDouble(Scanner sc, String prompt, double min, double max) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			d = getDouble(sc, prompt);
			if (d < min)
				System.out.println("Error! Number must be " + min + " or greater.");
			else if (d > max)
				System.out.println("Error! Number must be " + max + " or less.");
			else
				isValid = true;
		}
		return d;
	}

	public static String getStringLine(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine();

		return s.toLowerCase();

	}
}
