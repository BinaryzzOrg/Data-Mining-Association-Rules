public class ListofDataSet {
	private DataSetNode headPointer, tailPointer;
	private double countLength = 0;

	public ListofDataSet() {
		this.headPointer = tailPointer = null;
	}

	public DataSetNode getHeadPointer() {
		return headPointer;
	}

	public void setHeadPointer(DataSetNode headPointer) {
		this.headPointer = headPointer;
	}

	public DataSetNode getTailPointer() {
		return tailPointer;
	}

	public void setTailPointer(DataSetNode tailPointer) {
		this.tailPointer = tailPointer;
	}

	public void addDataSet(DataSetNode dataSet) {
		if (!(this.headPointer == null)) {
			countLength++;
			tailPointer.setNextPointer(dataSet);
			setTailPointer(tailPointer.getNextPointer());
			return;
		}

		countLength++;
		setHeadPointer(dataSet);
		setTailPointer(dataSet);
		return;
	}

	public void deleteDataSet(int position) {
		int counter = 1;
		if (position == 1) {
			setHeadPointer(this.headPointer.getNextPointer());
			System.out.println("Successfuly deleted!");
			return;
		}
		int DataListLength = countDataSetList(this.headPointer);
		if (position == DataListLength) {
			for (DataSetNode start = this.headPointer; start != null; start = start.getNextPointer()) {
				if (counter == position - 1) {
					System.out.println(counter);
					start.setNextPointer(null);
					setTailPointer(start);
				}
				counter++;
			}
		} else {
			for (DataSetNode start = this.headPointer; start != null; start = start.getNextPointer()) {
				if (counter == position - 1) {
					start.setNextPointer(start.getNextPointer().getNextPointer());
				}
				counter++;
			}
		}

		System.out.println("Successfuly deleted!");

	}

	private int countDataSetList(DataSetNode countDataSetList) {
		int countList = 0;
		DataSetNode head = this.headPointer;
		while (head != null) {
			countList++;
			head = head.getNextPointer();
		}
		return countList;
	}

	// print ung datasets
	public void display() {
		System.out.println("+=========[Data Set Lists]========+\n");
		int numberFormat = 1;
		for (DataSetNode start = this.headPointer; start != null; start = start.getNextPointer()) {
			System.out.print(numberFormat + ": (");
			for (ItemNode byItem = start.getHeadPointer(); byItem != null; byItem = byItem.getNextPointer()) {
				System.out.print(byItem.getItemName() + (byItem.getNextPointer() != null ? "," : ""));
			}
			System.out.println("]");
			numberFormat++;
		}
		System.out.println("+=================================+\n");

	}

	public double displaySupportOneValue(String item) {
		double computedValue = 0;
		double numberOfOccurence = 0;
		double computedValuePercent = 0;

		DataSetNode currentDataSetNode = getHeadPointer();
		ItemNode byItem = currentDataSetNode.getHeadPointer();

		while (currentDataSetNode != null) {
			byItem = currentDataSetNode.getHeadPointer();

			if (ifContains(item, byItem))
				numberOfOccurence += 1;
			byItem = byItem.getNextPointer();

			currentDataSetNode = currentDataSetNode.getNextPointer();
		}

		computedValue = numberOfOccurence / countLength;
		computedValuePercent = computedValue * 100;
		System.out.println("\n+========[SUPPORT VALUE]=======+\n" + "Total # of Occurence: " + numberOfOccurence
				+ "\nTotal # of Data Set: " + countLength + "\n\n>Support { " + item + " }" + " = " + numberOfOccurence
				+ " / " + countLength + " = " + String.format("%.4f", computedValue) + "\n>Support { " + item + " }"
				+ " = " + String.format("%.2f", computedValuePercent) + "% " + "\n+=============================+\n");
		return computedValue;
	}

	public double displaySupportValue(String itemOne, String itemTwo) {
		double computedValue = 0;
		double numberOfOccurence = 0;
		double computedValuePercent = 0;
		DataSetNode currentDataSetNode = getHeadPointer();
		ItemNode byItem = currentDataSetNode.getHeadPointer();

		while (currentDataSetNode != null) {
			byItem = currentDataSetNode.getHeadPointer();
			boolean firstItemPresent = false;
			boolean secondItemPresent = false;

			if (ifContains(itemOne, byItem))
				firstItemPresent = true;

			if (ifContains(itemTwo, byItem))
				secondItemPresent = true;

			if (firstItemPresent && secondItemPresent) {
				numberOfOccurence += 1;
				break;
			} else {
				firstItemPresent = false;
				secondItemPresent = false;
			}

			currentDataSetNode = currentDataSetNode.getNextPointer();
		}

		computedValue = numberOfOccurence / countLength;
		computedValuePercent = computedValue * 100;
		System.out.println("\n+========[SUPPORT VALUE]=======+\n" + "Total # of Occurence of { " + itemOne + ", "
				+ itemTwo + " }: " + numberOfOccurence + "\nTotal # of Data Set: " + countLength + "\n\n>Support { "
				+ itemOne + ", " + itemTwo + " }" + " = " + numberOfOccurence + " / " + countLength + " = "
				+ String.format("%.4f", computedValue) + "\n>Support { " + itemOne + ", " + itemTwo + " }" + " = "
				+ String.format("%.2f", computedValuePercent) + "% " + "\n+=============================+\n");
		return computedValue;
	}

	public void determineAssociation(String itemOne, String itemTwo) {

		double numberOfBothOccurence = 0;
		double numberOfOccurenceOfA = 0;
		double numberOfOccurenceOfB = 0;

		DataSetNode currentDataSetNode = getHeadPointer();
		ItemNode byItem = currentDataSetNode.getHeadPointer();
		// both occurrence
		while (currentDataSetNode != null) {
			boolean firstItemPresent = false;
			boolean secondItemPresent = false;

			byItem = currentDataSetNode.getHeadPointer();
			if (ifContains(itemOne, byItem))
				firstItemPresent = true;

			if (ifContains(itemTwo, byItem))
				secondItemPresent = true;

			if (firstItemPresent && secondItemPresent) {
				numberOfBothOccurence += 1;
				break;
			} else {
				firstItemPresent = false;
				secondItemPresent = false;
			}

			currentDataSetNode = currentDataSetNode.getNextPointer();
		}

		// singular occurrence B
		currentDataSetNode = getHeadPointer();
		while (currentDataSetNode != null) {
			byItem = currentDataSetNode.getHeadPointer();
			if (ifContains(itemTwo, byItem))
				numberOfOccurenceOfB += 1;
			currentDataSetNode = currentDataSetNode.getNextPointer();
		}

		// singular occurrence A
		currentDataSetNode = getHeadPointer();
		while (currentDataSetNode != null) {
			byItem = currentDataSetNode.getHeadPointer();
			if (ifContains(itemTwo, byItem))
				numberOfOccurenceOfA += 1;
			currentDataSetNode = currentDataSetNode.getNextPointer();
		}

		double computedConfidenceValue = 0;
		double computedLiftValue = 0;

		computedConfidenceValue = numberOfBothOccurence / numberOfOccurenceOfB;

		double supportValueOfBoth = (numberOfBothOccurence / countLength);
		double supportValueOfB = (numberOfOccurenceOfB / countLength);
		double supportValueOfA = (numberOfOccurenceOfA / countLength);

		computedLiftValue = supportValueOfBoth / (supportValueOfB * supportValueOfA);
		System.out.println("\n+========[CONFIDENCE VALUE]========+\n" + "Total # of Occurence of { " + itemOne + ","
				+ itemTwo + " }: " + numberOfBothOccurence + "\nTotal # of Occurence of { " + itemOne + " }: "
				+ numberOfOccurenceOfB + "\n\n>Condifence { " + itemTwo + "-> " + itemOne + " }" + " = "
				+ numberOfBothOccurence + " / " + numberOfOccurenceOfB + "\n>Condifence { " + itemTwo + "-> " + itemOne
				+ " }" + " = " + String.format("%.2f", computedConfidenceValue) + "\n"
				+ "\n+===========[LIFT VALUE]===========+\n" + "Total # of Occurence of { " + itemOne + "," + itemTwo
				+ " }: " + numberOfBothOccurence + "\nTotal # of Occurence of { " + itemTwo + " }: "
				+ numberOfOccurenceOfB + "\nTotal # of Occurence of { " + itemOne + " }: " + numberOfOccurenceOfA
				+ "\n\n>Lift { " + itemTwo + "-> " + itemOne + " }" + " = (" + numberOfBothOccurence + " / "
				+ countLength + ") / " + " ((" + numberOfOccurenceOfB + " / " + countLength + ") *" + " ("
				+ numberOfOccurenceOfA + " / " + countLength + ")) " + "\n>Lift { " + itemTwo + "-> " + itemOne + " }"
				+ " = " + String.format("%.2f", supportValueOfBoth) + " / " + " ("
				+ String.format("%.2f", supportValueOfB) + " *" + " " + String.format("%.2f", supportValueOfA) + ") "
				+ "\n>Lift { " + itemTwo + "-> " + itemOne + " }" + " = " + String.format("%.2f", computedLiftValue));
		if (computedLiftValue >= 1) {
			System.out.println("+=================================+\n" + "There is an association {" + itemTwo + " -> "
					+ itemOne + "}" + "\n+=================================+\n");
		} else {
			System.out.println("+=================================+\n" + "There is no association! \nLift Value: "
					+ String.format("%.2f", computedLiftValue) + " < 1\n" + "+=================================+\n");
		}
	}

	public boolean ifContains(String name, ItemNode headNode) {
		for (ItemNode start = headNode; start != null; start = start.getNextPointer()) {
			if (start.getItemName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
}// end class
