import java.util.Scanner; 

public class TipCalcAppTester {

	public static void main(String[]args) {
		
		
		/* Declare Variables */
		String appInfo;
		String studentName;
		String studentId;
		
		String billAmountEnterPrompt;
		String tipPercentEnterPrompt;
		String billAmountInput;
		String tipPercentInput;
		
		double billAmount;
		double tipPercentage;
		double tipAmount;
		double totalAmount;
		
		boolean isParseable;
		boolean isReadyForCalculation;
		
		/* Define Variables */
		appInfo = "Tip Calculator Java Application" 
				+ "\n" 
				+ "Calculte tip amounts and total from the bill amount and tip percentage";
		studentName = "By: Omie Walls";
		studentId = "Student No.: 99-121-7982-80";
		
		billAmountEnterPrompt = "Enter Bill Amount [must be greater than or equal to 1]: ";
		tipPercentEnterPrompt = "Enter Tip Amount [must be less than or equal to 1]: ";
		
		billAmountInput = "";
		tipPercentInput = "";
		
		billAmount = 0;
		tipPercentage = 0;
		tipAmount = 0;
		
		isReadyForCalculation = false;
		
		/* Run Application */
		printAppInfo(appInfo, studentName, studentId);
		do {
			billAmountInput = getUserInputForBillAmount(billAmountEnterPrompt);
			tipPercentInput = getUserInputForTipPercent(tipPercentEnterPrompt);
			
			isParseable = validateUserInput(billAmountInput, tipPercentInput);
			if(isParseable) {
				billAmount = parseUserInput(billAmountInput);
				tipPercentage = parseUserInput(tipPercentInput);
				isReadyForCalculation = hasNoCalculationErrors(billAmount, tipPercentage);
			
				if (isReadyForCalculation) {
					tipAmount = calculateTipAmount(billAmount, tipPercentage);
					printResults(billAmount, tipPercentage, tipAmount);
				}
			}
		} while(!(isParseable && isReadyForCalculation));
		
	}
	
	/* Print out application and student information in a readable format */
	public static void printAppInfo(String app, String name, String id) {
		Scanner stdIn = new Scanner(System.in);
		System.out.println("---------------------------------------------------------");
		System.out.println(app + "\n" + name + ", " + id + "\n");
	}
	
	/* Prompt and gather user input for the bill amount and tip percentage */
	public static String getUserInputForBillAmount(String billPrompt) {
		String billInput;
		/* Instantiate Scanner Class */
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print(billPrompt);
		billInput = stdIn.next();
		
		return billInput;
	}
	
		/* Prompt and gather user input for the bill amount and tip percentage */
	public static String getUserInputForTipPercent(String tipPrompt) {
		String tipInput;
		/* Instantiate Scanner Class */
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print(tipPrompt);
		tipInput = stdIn.next();
		
		return tipInput;
	}
	
	/* Confirm that the user string can be parsed without 
	throwing a NullPointerException or NumberFormatException */
	public static boolean isThrowingNoExceptions(String input, String name) {
		boolean valid = false;
		boolean stringHasDecimalPoint = false;
		boolean exception = false;

		int digitCount = 0;
		int decimalIndex;
		int extraDecimalIndex;
		int decimalCount = 0;
		
		System.out.println("---------------------------------------------------------");
		System.out.println("                Debug Mode for " + name + "               ");
		System.out.println("_________________________________________________________");
		System.out.println(name + " input: " + input + "\n");
		/* Check if user input is null */
		if(input.length() >= 1) {
			System.out.println("Valid length! String is " + input.length() + " characters long.");
			/* Validate if each character of input string is a digit or decimal */
			for(int i = 0; i < input.length(); i++) {
				char inputChar = input.charAt(i);
				if(Character.isDigit(inputChar)) {
					System.out.println("Valid character! Character '" + inputChar + "' is a digit.");
					valid = true;
					digitCount++;
					System.out.println("String now has " + digitCount + " digit(s).");
				} else if(input.charAt(i) == '.') {
					System.out.println("Valid character! Character '" + inputChar + "' at substring " + i + " is a decimal point.");
					valid = true;
					stringHasDecimalPoint = true;
					decimalCount++;
					System.out.println("String now has " + decimalCount + " decimal point(s).");
				} else {
					System.out.println("Invalid Parsing String! There is a non-digit/decimal: " + inputChar);
					exception = true;
				}
				System.out.println();
			}
			/* Check if string contains digits */
			if(digitCount < 1) {
				System.out.println("Invalid Parsing String! There are no digits. Digit count: " + digitCount + "\n");
				exception = true;
			} else {
				System.out.println("Valid Digit Count! There are enough digits to parse this string. Digit count: " + digitCount + "\n");
			}
			
			/* Check if the string only has one decimal point if it has one */
			if(stringHasDecimalPoint) {
				System.out.println("---------------------");
				System.out.println("  Decimal Index Test ");
				System.out.println("---------------------");
				System.out.println("String has a decimal point.");
				
				decimalIndex = input.indexOf('.');
				System.out.println("Decimal point is has an index of: " + decimalIndex);
				
				extraDecimalIndex = input.indexOf('.', ++decimalIndex);
				if (extraDecimalIndex != -1) {
					System.out.println("Invalid Parsing String! There are too many decimal points! \nExtra decimal has an index of: " + extraDecimalIndex + " instead of -1.\n");
					exception = true;
				} else {
					System.out.println("Valid Decimal Count! String only has one decimal point. \n\nThe search for an extra decimal resulted in an index of: " + extraDecimalIndex + "\n");
					valid = true;
				}
			}
		
		  /* If the string is null, catch the null exception */	
		} else {
				System.out.println("Invalid Parsing String! This string is null. Input length: " + input.length());
				exception = true;		
		}
		
		if (valid && !exception) {
			System.out.println(input + " is a parseable " + name + " value.\n");
		} else {
			System.out.println(input + " is a not a parsable " + name + " value.\n");
		}
		System.out.println("_________________________________________________________");
		System.out.println("                 End Debug Mode for " + name + "           ");
		System.out.println("---------------------------------------------------------");
		return valid && !exception;
	}

	/* Parse user input string into a deciml data type */
	public static double parseUserInput(String input) {
		double parseStringToDouble = Double.parseDouble(input);
		return parseStringToDouble;
	}
	
	public static boolean hasNoCalculationErrors(double amount, double percentage) {
		boolean isValidAmount = false;
		boolean isValidPercentage = false;
		System.out.println("---------------------------------------------------------");
		System.out.println("             Checking for Calculation Errors             ");
		System.out.println("_________________________________________________________");
		/* Check if bill amount is greater than 1 */
			if(amount >= 1) {
				isValidAmount = true;
				System.out.println(amount + " is a valid input for the Bill Amount.");
			} else {
				System.out.println(amount + " is not a valid input for the Bill Amount. It is too low.\n");
			}
				/* Check if tip percentage is less than 1 */
			if(percentage <= 1) {
				isValidPercentage = true;
				System.out.println(percentage + " is a valid input for the Tip Percentage.");
			} else {
				System.out.println(percentage + " is not a valid input for the Tip Percentage. It is too high.\n");
			}
			System.out.print("---------------------------------------------------------\n");
			return isValidAmount && isValidPercentage;
	}
	
	public static boolean validateUserInput(String billInput, String tipInput) {
		/* Check if user input is formattable */
		boolean isBillDecimal = isThrowingNoExceptions(billInput, "Bill Amount");
		boolean isTipDecimal = isThrowingNoExceptions(tipInput, "Tip Percent");
		
		/* If user input is formattable, return true */
		if(isBillDecimal && isTipDecimal) {
			return true;
			
		} 
		return false;
	}
	
	/* Calculate the tip amount from the user input */
	public static double calculateTipAmount(double billAmount, double tipPercent) {
		double tipAmount = billAmount * tipPercent;
		return tipAmount;
	}
	
	/* Print totals and calculations */
	public static void printResults(double billAmount, double tipPercent, double tipAmount) {
		String billAmountPrintPrompt = "Bill Amount: ";
		String tipPercentPrintPrompt = "Tip Percent: ";
		String tipAmountPrintPrompt = "Tip Amount: ";
		String totalAmountPrintPrompt = "Total Amount: ";
		double totalAmount = billAmount + tipAmount;
		
		System.out.print(billAmountPrintPrompt + billAmount + "\n"
			+ tipPercentPrintPrompt + tipPercent + "\n"
			+ tipAmountPrintPrompt + tipAmount + "\n"
			+ totalAmountPrintPrompt + totalAmount + "\n");
	}
}
	