import java.util.Scanner; 

public class TipCalcApp {

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
		
		billAmountEnterPrompt = "Enter Bill Amount: ";
		tipPercentEnterPrompt = "Enter Tip Amount: ";
		
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
		
		System.out.println();
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
	public static boolean isThrowingNoExceptions(String input) {
		boolean valid = false;
		boolean stringHasDecimalPoint = false;
		boolean exception = false;

		int digitCount = 0;
		int decimalIndex;
		int extraDecimalIndex;
		int decimalCount = 0;
		
		/* Check if user input is null */
		if(input.length() >= 1) {
			/* Validate if each character of input string is a digit or decimal */
			for(int i = 0; i < input.length(); i++) {
				char inputChar = input.charAt(i);
				if(Character.isDigit(inputChar)) {
					valid = true;
					digitCount++;
				} else if(input.charAt(i) == '.') {
					valid = true;
					stringHasDecimalPoint = true;
					decimalCount++;
				} else {
					exception = true;
				}
			}
			/* Check if string contains digits */
			if(digitCount < 1) {
				exception = true;
			}
						
			/* Check if the string only has one decimal point if it has one */
			if(stringHasDecimalPoint) {				
				decimalIndex = input.indexOf('.');
				
				extraDecimalIndex = input.indexOf('.', ++decimalIndex);
				if (extraDecimalIndex != -1) {
					exception = true;
				} else {
					valid = true;
				}
			}
		
		  /* If the string is null, catch the null exception */	
		} else {
				exception = true;		
		}
		
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

		/* Check if bill amount is greater than 1 */
			if(amount >= 1) {
				isValidAmount = true;
			}
				/* Check if tip percentage is less than 1 */
			if(percentage <= 1) {
				isValidPercentage = true;
			}
			return isValidAmount && isValidPercentage;
	}
	
	public static boolean validateUserInput(String billInput, String tipInput) {
		/* Check if user input is formattable */
		boolean isBillDecimal = isThrowingNoExceptions(billInput);
		boolean isTipDecimal = isThrowingNoExceptions(tipInput);
		
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
		
		System.out.println();
		System.out.print(billAmountPrintPrompt + billAmount + "\n"
			+ tipPercentPrintPrompt + tipPercent + "\n"
			+ tipAmountPrintPrompt + tipAmount + "\n"
			+ totalAmountPrintPrompt + totalAmount + "\n");
	}
}
	