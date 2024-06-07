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
	
	//getting the items
	public static LinkItem getItems() {
		return items;
	}

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
		String isListEmptyError = "\n[Item List is Currently Empty, Please Add Items First.]";
		String isDataSetEmptyError = "\n[Data Set is Currenty Empty, Please Create a Data Set First.]";
		int ItemLimit = 10;

		System.out.print(printMenuChoices());
		switch (checkUserInputInteger(printMenuChoices())) {
		case 1: {// Item List
			if (!isEmptyItemList) {
				System.out.println("Item List has already been created.");
			} // end if

			// @formatter:off
			String itemItemNamePrompt = """
					\n
					+=================================================+
					|    Enter 10 item names (Enter -1 to go back)    |
					+=================================================+
				    """;
			// @formatter:on
			System.out.print(itemItemNamePrompt);
			for (int i = 1; i < ItemLimit + 1; i++) {
				System.out.print("| " + i + ") ");
				String validateInput = checkUserInputString(itemItemNamePrompt + "| " + i + ") ");
				if (validateInput.equals("-1"))
					break;

				isEmptyItemList = false;
				items.addToList(validateInput);
			} // end for
			System.out.print("+=================================================+");
			break;
		}
		case 2: {//
			if (isEmptyItemList) {
				System.out.print(isListEmptyError);
				break;
			} // end if

			DataSetNode datasets = new DataSetNode();
			items.display();
			// @formatter:off
			String itemNumberPrompt = """
					+=========================================================================+
					| --  Select the number that corresponds to the item in the item list  -- |
					|     --  Once done, Press -1 to create the data set and go back  --      |
					+=========================================================================+
				    """;

			System.out.print(itemNumberPrompt);
			
			for (int i = 0; true; i++) {
				ItemNode dataSetListHead = datasets.getHeadPointer();
				System.out.print("| "+ (i + 1) + ") ");
				int itemPosition = checkUserInputInteger(itemNumberPrompt + "| "+ (i + 1) + ") ");
				if (itemPosition == -1) {
					if (dataSetListHead == null) {
						System.out.println("Please enter at least one item.");
						i--;
						continue;
					}
					break;
				}

				String temp = items.getItem(itemPosition);
				if (temp == null) {
					System.out.println("Input is not a valid Item Choice.");
					i--;
					continue;
				}
				boolean inDataSet = false;
				if (dataSetListHead != null) {
					while (dataSetListHead != null) {
						if (dataSetListHead.getItemName() == temp) {
							inDataSet = true;
							break;
						}
						dataSetListHead = dataSetListHead.getNextPointer();
					}
				}
				if (inDataSet) {
					System.out.println("Item choice is already in the data set.");
					i--;
					continue;
				}
				datasets.addItemToDataSet(temp);
				isEmptyDataSetList = false;
			} // end for
			System.out.print("+======================================================================+");
			data.addDataSet(datasets);
			break;
			
			// @formatter:on
		}
		case 3: {// delete data set
			//@formatter:off
			if (isEmptyItemList || isEmptyDataSetList) {
				System.out.print((isEmptyDataSetList && !isEmptyItemList) ? "\n" + isDataSetEmptyError : "\n" + isListEmptyError);
				break;
			} // end if

			data.display();
			System.out.print("Enter the position of the data set you want to delete. \nposition> ");
			int position = checkUserInputInteger("Position> ");
			data.deleteDataSet(position);
			break;
		}
		case 4: {// display data set
			if (isEmptyItemList || isEmptyDataSetList) {
				System.out.print((isEmptyDataSetList && !isEmptyItemList) ? "\n" + isDataSetEmptyError : "\n" + isListEmptyError);
				break;
			} // end if
			data.display();
			break;
		}
		case 5: {// display support value
			if (isEmptyItemList || isEmptyDataSetList) {
				System.out.print((isEmptyDataSetList && !isEmptyItemList) ? "\n" + isDataSetEmptyError : "\n" + isListEmptyError);
				break;
			} // end if
			data.displaySupportValue();
			break;
		}
		case 6: {// determine association
			if (isEmptyItemList || isEmptyDataSetList) {
				System.out.print((isEmptyDataSetList && !isEmptyItemList) ? "\n" + isDataSetEmptyError : "\n" + isListEmptyError);
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
				    +============================================+
				    | Error :                                    |
				    | Input is not a valid Menu Choice.          |
				    +============================================+
				    | Msg:                                       |
				    | Please enter only 1 to 7 as input          |
				    +============================================+
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
	 * used for different scenarios of printing.s
	 */

	// TLDR - METHOD FOR DEALING WITH INTEGER INPUT
	private static Scanner sc;

	public static int checkUserInputInteger(String prompt) {
		sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			return sc.nextInt();
		} // end if
		System.out.println(printCustomError("integer"));
		System.out.print(prompt);
		return checkUserInputInteger(prompt);
	}// end method

	/*
	 * The CheckUserInput is an overload of CheckUserMenu method, This method scans
	 * the user's input and checks if it is a string. If the input is a string, it
	 * is stored in the 'input' variable and returns it. If the input is not a
	 * string, an error message is displayed, and the user is prompted to enter a
	 * string value. The 'prompt' parameter is used for different scenarios of
	 * printing
	 */
	// TLDR - METHOD FOR DEALING WITH STRING INPUT
	private static String checkUserInputString(String prompt) {
		sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			String input = sc.nextLine();
			return input;
		} else {
			if (sc.nextInt() == -1) {
				return "-1";
			} else {
				System.out.println(printCustomError("string"));

				System.out.print(prompt);
				return checkUserInputString(prompt);
			}//end if else 
		}//end if else
	}// end method

	/*
	 * The printCustomError is exclusively used by checkUserInput, and
	 * checkUserInputMenu for printing their errors, but this method can by used by
	 * other methods if needed. This method has a parameter called 'type' for
	 * specify what data is needed be inputed on a method that calls this.
	 */
	private static String printCustomError(String type) {
		// @formatter:off
		return "\n" 
				+ "+================================================+\n" 
				+ "| Warning: Input is not a " + type+ " value.         |\n" 
				+ "+================================================+\n"
				+ "| Notice: Please only enter a " + type + " value.     |\n"
				+ "+================================================+\n";
		// @formatter:on
	}// end method

}// end class
