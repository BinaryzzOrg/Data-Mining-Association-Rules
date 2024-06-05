
import java.util.Scanner;

public class Main {
	// === FIELD VARIABLES === //
	static LinkItem items = new LinkItem();
	static ListofDataSet data = new ListofDataSet();
	// alaws pa pangcheck if empty outer boolean muna temp
	static boolean isListEmpty = true;

	// === MAIN METHOD === //
	public static void main(String[] args) {
		Menu();
	}// end class

	/*
	 * The PrintMenuChoices method returns a formatted string for MainMenu. This
	 * formatted string is displayed to the user when they run the code for the
	 * first time. The method is also passed as a parameter to other methods that
	 * use the 'prompt' String.
	 */
	public static String printMenuChoices() {
		String MenuChoicesAsString = """
				\n
				+=============================+
				|       --  MainMenu  --      |
				+=============================+
				| (1) : Create Item List      |
				| (2) : Create Data Set       |
				| (3) : Delete Data Set       |
				| (4) : Display Data Set      |
				| (5) : Display Support Value |
				| (6) : Display Association   |
				| (7) : Exit                  |
				+=============================+
				Select an operation>\s""";
		return MenuChoicesAsString;
	}// end method

	/*
	 * The MenuChoices method contains the following operations: Append, Delete,
	 * Change Value, Display, Node History, List History,and Exit. This method calls
	 * the PrintMenuChoices that prints out the choices for modifying the Persistent
	 * Singly LinkedList that is chosen by the user. MenuChoices method also handles
	 * miss inputs of the user and loops if it detects one.
	 */

	public static void Menu() {
		// @formatter:off
		int ItemLimit = 10;
		System.out.print(printMenuChoices());

		switch (checkUserInputInteger(printMenuChoices())) {
		case 1: {// Item List
			// enter only input not working yet
			System.out.println("Enter 10 item names (Enter ng walang tinatype to break agad if ayaw ng saktong 10 items");
			for (int i = 1; i < ItemLimit + 1; i++) {
				System.out.print(i+ ") ");
				String validateInput = checkUserInputString("Enter 10 Items:");
				if (validateInput.isEmpty()) {
					System.out.println("test on menu method");
					break;
				}//end if
				items.addToList(validateInput);
			}//end for
			isListEmpty = false;
			break;
		}
		case 2: {//
			if (isListEmpty) {
				System.out.println("Item List is currently empty, Please add items first");
				break;
			}
			DataSetNode datasets = new DataSetNode();
			
			//may if contains kasi di ko prinint ung list ng items so need malaman if kagaya sa list before mapunta sa datasets
			System.out.println("Enter items to dataset (Enter ng walang tinatype to break agad if ayaw ng saktong 10 items");
			for (int i = 0; true; i++) {
				System.out.print( (i + 1)  + " Item>> ");
				String validateInput = checkUserInputString("Select items on ItemList to enter ");
				if(validateInput.isEmpty()) break;
				if (!items.ifContains(validateInput)) {
					System.out.println("Not Found");
				}//end if
					datasets.addItemToDataSet(validateInput);
			}//end for
			data.addDataSet(datasets);
			break;
		}
		case 3: {//
			break;
		}
		case 4: {//
			break;
		}
		case 5: {//
			break;
		}
		case 6: {//
			break;
		}
		case 7: {//
			break;
		}
		default:
			// @formatter:off
			System.out.println("""
					\n
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃ 
					┇ Error:                                    ┇
					┇ Input is not a valid Menu choice.         ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					┇ Msg:                                      ┇
					┇ Please enter only 1 to 7 as input         ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					""");
			// @formatter:on
			break;
		}// end method
		System.out.println("\n");
		Menu();
		// @formatter:on
	}// end method

	/*
	 * The CheckUserInputMenu method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of printing.
	 */
	// TLDR - METHOD FOR DEALING WITH INTEGER INPUT
	static Scanner sc;

	public static int checkUserInputInteger(String prompt) {
		sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			return sc.nextInt();
		} // end if
		System.out.println(printCustomError("integer"));
		System.out.print(prompt);
		return checkUserInputInteger(prompt);
	}// end if

	/*
	 * The CheckUserInput is an overload of CheckUserMenu method, This method scans
	 * the user's input and checks if it is a string. If the input is a string, it
	 * is stored in the 'input' variable and returns it. If the input is not a
	 * string, an error message is displayed, and the user is prompted to enter a
	 * string value. The 'prompt' parameter is used for different scenarios of
	 * printing
	 */
	// TLDR - METHOD FOR DEALING WITH STRING INPUT
	public static String checkUserInputString(String prompt) {
		sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			String input = sc.nextLine();
			return removeWhiteSpace(input);
		} // end if

		System.out.println(printCustomError("string"));

		System.out.print(prompt);
		return checkUserInputString(prompt);
	}// end if

	/*
	 * The removeWhiteSpace method, simply removes any extra white spaces on a
	 * string that is passed on to the parameter. Once the method is done with the
	 * operations, it returns a more 'clean' string for the other methods to use.
	 */
	public static String removeWhiteSpace(String str) {
		String fixed = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
				fixed += str.charAt(i);
			}
			if (str.charAt(i) != ' ') {
				fixed += str.charAt(i);
			}

		} // end for

		return fixed;
	}// end method

	public static String checkEmail() {
		String tempEmail = checkUserInputString("Email Address: ");
		boolean isEmailValid = false;
		int indexOfAt = -1;
		int indexOfDot = -1;
		boolean consecDots = false;
		for (int indexAt = 0; indexAt < tempEmail.length(); indexAt++) {
			if (tempEmail.charAt(indexAt) == '@') { // checks if there's @
				indexOfAt = indexAt; // store the index
				break; // if found stop the looping
			}

		} // end for

		// find the index '.'
		for (int indexDot = 0; indexDot < tempEmail.length(); indexDot++) {
			if (tempEmail.charAt(indexDot) == '.') {
				indexOfDot = indexDot;
				break;
			} // end if
		} // end for

		if (!tempEmail.isEmpty() && tempEmail != null && indexOfDot > indexOfAt && indexOfAt > 0
				&& indexOfDot < tempEmail.length() - 1) {
			for (int indexConsec = indexOfAt + 1; indexConsec < tempEmail.length() - 1; indexConsec++) {
				if (tempEmail.charAt(indexConsec) == '.' && tempEmail.charAt(indexConsec - 1) == '.') {
					consecDots = true;
					break;
				} // end if
			} // end for

			if (!consecDots) { // if there's no another dot
				isEmailValid = true; // it's a valid email
			} // end if
		} // end if

		if (tempEmail == null) {
			System.out.println("[Incomplete Entry]");
			System.out.print("Email Address: ");
			checkEmail();
		} else if (!isEmailValid) {
			System.out.println("[This Email is Invalid.]");
			System.out.print("Email Address: ");
			checkEmail();
		} // end if else

		return tempEmail;
	}// end method

	/*
	 * The printCustomError is exclusively used by checkUserInput, and
	 * checkUserInputMenu for printing their errors, but this method can by used by
	 * other methods if needed. This method has a parameter called 'type' for
	 * specify what data is needed be inputed on a method that calls this.
	 */
	public static String printCustomError(String type) {
		// @formatter:off
					return "\n" +
								"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
								"┇ Warning: Input is not a "+ type +" value.    ┇\n" +
								"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
								"┇ Notice: Please only enter a "+ type +" value.┇\n" +
								"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n";
				// @formatter:on
	}// end method

}// end class