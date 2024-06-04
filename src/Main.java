
import java.util.Scanner;

public class Main {

	public static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		LinkItem items = new LinkItem();
		ListofDataSet data = new ListofDataSet();
		// alaws pa pangcheck if empty outer boolean muna temp
		boolean notEmptyList = false;
		// temp interface lang to test
		while (true) {
			System.out.println("\nChoose an Option");
			System.out.println("1. Item List");
			System.out.println("2. Add Data Set");
			System.out.println("3. Delete Data Set");
			System.out.println("4. Display DataSet");
			System.out.println("5. Display Support");
			System.out.println("6. Determine Association");
			System.out.print("7. Terminate \n:");

			int response = sc.nextInt();
			sc.nextLine();

			switch (response) {
			case 1:
				if (!notEmptyList) {
					System.out.println(
							"Enter 10 items (Enter ng walang tinatype to break agad if ayaw ng saktong 10 items");
					for (int i = 0; i < 10; i++) {
						String validateInput = sc.nextLine();
						if (validateInput.isEmpty())
							break;
						items.addToList(validateInput);
					}
					notEmptyList = true;
				} else {
					System.out.println("May list na");
				}
				break;
			case 2:
				if (!notEmptyList) {
					System.out.println("Create First!! muna");
					break;
				}
				DataSetNode datasets = new DataSetNode();

				// may if contains kasi di ko prinint ung list ng items so need malaman if
				// kagaya sa list before mapunta sa datasets
				System.out.println(
						"Enter items to dataset (Enter ng walang tinatype to break agad if ayaw ng saktong 10 items");
				for (int i = 0; true; i++) {
					System.out.print((i + 1) + " Item>> ");
					String validateInput = sc.nextLine();
					if (validateInput.isEmpty())
						break;
					if (items.ifContains(validateInput))
						datasets.addItemToDataSet(validateInput);
					else
						System.out.println("Not found...");
				}

				data.addDataSet(datasets);
				break;
			case 3:
				if (!notEmptyList) {
					System.out.println("Create First!! muna");
					break;
				}
				System.out.print("Enter the postion you want to delete\n:");
				Scanner scan = new Scanner(System.in);
				int position = scan.nextInt();
				data.deleteDataSet(position);
				break;
			case 4:
				data.display();
				break;
			case 5:
				System.out.print("Choose an Option" + "\n1. Support { A }" + "\n2. Support { B, A } \n:");
				int choice = checkUserInputInteger(
						"Choose an Option" + "\n1. Support { A }" + "\n2. Support { B, A } \n:");
				sc = new Scanner(System.in);
				if (choice == 1) {
					System.out.print("Enter item A: ");
					String A = checkUserInputString("Enter item A: ");
					data.displaySupportOneValue(A);
				}
				if (choice == 2) {
					System.out.print("Enter item B: ");
					String B = sc.nextLine();
					System.out.print("Enter item A: ");
					String A = sc.nextLine();
					data.displaySupportValue(B, A);
				}
				break;
			case 6:
				System.out.print("Enter item B: ");
				String Asso_B = sc.nextLine();
				System.out.print("Enter item A: ");
				String Asso_A = sc.nextLine();
				data.determineAssociation(Asso_B, Asso_A);
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid response!!!");
				;

			}// end switch
		} // end while

	}

	/*
	 * The CheckUserInputMenu method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of printing.
	 */
	// TLDR - METHOD FOR DEALING WITH INTEGER INPUT
	public static int checkUserInputInteger(String prompt) {
		sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			int temp = sc.nextInt();
			if (temp > -1) {
				return temp;
			}
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
	 * The printCustomError is exclusively used by checkUserInput, and
	 * checkUserInputMenu for printing their errors, but this method can by used by
	 * other methods if needed. This method has a parameter called 'type' for
	 * specify what data is needed be inputed on a method that calls this.
	 */

	public static String printCustomError(String type) {
		// @formatter:off
		return "\n" +
		"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
		"┇ Warning: Input is not a "+ type +" value. \n" +
		"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
		"┇ Notice: Please only enter a "+ type +" value.\n" +
		"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n";
		// @formatter:on
	}// end method

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
}