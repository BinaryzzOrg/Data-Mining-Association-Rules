
import java.util.Scanner;

public class Main {
	// === FIELD VARIABLES === //
	static LinkItem items = new LinkItem();
	static ListofDataSet data = new ListofDataSet();

	public static boolean isEmptyItemList = true;
	public static boolean isEmptyDataSetList = true;

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
		String isListEmptyError = "\n[Item List is currently empty, Please add items first]";
		int ItemLimit = 10;
		
		System.out.print(printMenuChoices());
		switch (checkUserInputInteger(printMenuChoices())) {
		case 1: {// Item List
			if (!isEmptyItemList) {
				System.out.println("Item List has already been created.");
			}//end if
			
			// enter only input not working yet
			System.out.println("Enter 10 item names (Or Press Enter To Go Back)");
			for (int i = 1; i < ItemLimit + 1; i++) {
				System.out.print(i+ ") ");				
				String validateInput = checkUserInputString("Enter 10 item names (Or Press Enter To Go Back)");
				if (validateInput.isEmpty()) {
					System.out.println("d nagana for somereason, pinasok kolang sa checkUserInput eh");
					break;
				}//end if
				
				items.addToList(validateInput);
			}//end for
			isEmptyItemList = false;
			break;
		}
		case 2: {//
			if (isEmptyItemList) {
				System.out.print(isListEmptyError);
				break;
			}//end if
			
			DataSetNode datasets = new DataSetNode();
			
			//may if contains kasi di ko prinint ung list ng items so need malaman if kagaya sa list before mapunta sa datasets
			System.out.println("Enter items to dataset (Or Press Enter To Go Back");
			for (int i = 0; true; i++) {
				System.out.print(i+ ") ");
				String validateInput = checkUserInputString("Select items on ItemList to enter ");
				if(validateInput.isEmpty()) break;
				if (items.ifContains(validateInput)) {
					datasets.addItemToDataSet(validateInput);
				}else {
					System.out.print("[That item is not available in the Item List]");
					i--;
				}//end if else
			}//end for
			data.addDataSet(datasets);
			isEmptyDataSetList = false;
			break;
		}
		case 3: {// delete data set
				if (isEmptyItemList || isEmptyDataSetList) {
					System.out.println((isEmptyDataSetList && !isEmptyItemList) ? "\n[Please Create a Data Set List First.]"
							: "\n[Please Create an Item List First.]");
					break;
				}//end if
				
				System.out.print("Enter the position of the data set you want to delete: (1-?) need position shit here\nposition>");
				int position = checkUserInputInteger("Position>");
				data.deleteDataSet(position);
			break;
		}
		case 4: {// display data set
			if (isEmptyItemList) {
				System.out.print(isListEmptyError);
				break;
			}//end if
				data.display();
			break;
		}
		case 5: {// display support value
				if (isEmptyItemList || isEmptyDataSetList) {
					System.out.println((isEmptyDataSetList && !isEmptyItemList) ? "\n[Please Create a Data Set List First.]"
							: "\n[Please Create an Item List First.]");
					break;
				}//end if
				
				supportValueMenu();
				break;
		}
		case 6: {// determine association
			if (isEmptyItemList || isEmptyDataSetList) {
				System.out.println((isEmptyDataSetList && !isEmptyItemList) ? "\n[Please Create a Data Set List First.]"
						: "\n[Please Create an Item List First.]");
				break;
			} // end if
			System.out.print("Enter item B: ");
			String Asso_B = checkUserInputString("Enter item B: ");
			System.out.print("Enter item A: ");
			String Asso_A = checkUserInputString("Enter item A: ");
			data.determineAssociation(Asso_B, Asso_A);
			break;
		}
		case 7: {// exit
			System.out.println(":: Exiting now...");
			System.exit(0);
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

	public static void supportValueMenu() {
		String supportValueMenu = """
				\n
				+==============================+
				|   --  Choose on Option  --   |
				+==============================+
				| (1) : Support {A}            |
				| (2) : Support {B,A}          |
				| (3) : Go Back                |
				+==============================+
				Select an option>\s""";
		System.out.print(supportValueMenu);

		int choice = checkUserInputInteger(supportValueMenu);
		switch (choice) {
		case 1: {
			System.out.print("Enter item A: ");
			String A = checkUserInputString("Enter item A: ");
			data.displaySupportOneValue(A);
			break;
		}
		case 2: {
			System.out.print("Enter item B: ");
			String B = checkUserInputString("Enter item B: ");
			System.out.print("Enter item A: ");
			String A = checkUserInputString("Enter item A: ");
			data.displaySupportValue(B, A);
			break;
		}
		default:
			// @formatter:off
			System.out.println("""
					\n
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					┇ Error:                                   ┇
					┇ Input is not a valid Menu choice.        ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					┇ Msg:                                     ┇
					┇ Please enter only 1 to 7 as input        ┇
					⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃
					""");
			// @formatter:on
		}// end switch
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
			} // end if
			if (str.charAt(i) != ' ') {
				fixed += str.charAt(i);
			} // end if
		} // end for
		return fixed;
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