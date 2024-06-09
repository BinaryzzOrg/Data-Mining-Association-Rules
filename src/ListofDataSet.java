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
		if (position < 1 || position > countLength) {
			System.out.println("Invalid Position!");
			return;
		}
		if (position == 1) {
			setHeadPointer(this.headPointer.getNextPointer());
		}
		if (position == countLength) {
			for (DataSetNode start = this.headPointer; start != null; start = start.getNextPointer()) {
				if (counter == position - 1) {
					start.setNextPointer(null);
					setTailPointer(start);
				}
				counter++;
			}
		} else if (position > 1) {
			for (DataSetNode start = this.headPointer; start != null; start = start.getNextPointer()) {
				if (counter == position - 1) {
					start.setNextPointer(start.getNextPointer().getNextPointer());
				}
				counter++;
			}
		}
		countLength--;
		if (countLength == 0)
			Main.isEmptyDataSetList = true;
		System.out.println("Successfuly deleted!");

	}

	// print ung datasets
	public void display() {
		System.out.println("\n+=========[Data Set Lists]========+");
		int numberFormat = 1;
		for (DataSetNode start = this.headPointer; start != null; start = start.getNextPointer()) {
			System.out.print(numberFormat + ": [");
			for (ItemNode byItem = start.getHeadPointer(); byItem != null; byItem = byItem.getNextPointer()) {
				System.out.print(byItem.getItemName() + (byItem.getNextPointer() != null ? "," : ""));
			}
			System.out.println("]");
			numberFormat++;
		}
		System.out.print("+=================================+");
	}

	public void displaySupportValue() {
		LinkItem itemsAvailable = Main.getItems();
		LinkItem items = new LinkItem();
		
		itemsAvailable.display();
		String itemNumberPrompt = """
				+=========================================================================+
				| --  Select the number that corresponds to the item in the item list  -- |
				|     --  Once done, Press -1 to create the data set and go back  --      |
				+=========================================================================+
			    """;

		System.out.print(itemNumberPrompt);
		for (int i = 0; true; i++) {
			System.out.print("| "+ (i + 1) + ") ");
			int itemPosition = Main.checkUserInputInteger(itemNumberPrompt);
			if (itemPosition == -1) break;

			String temp = itemsAvailable.getItem(itemPosition);
			System.out.println(temp);
			items.addToList(temp);
		} // end for
		
		displaySupportValueHelper(items);
	}
	
	public void displaySupportValueHelper(LinkItem items) {
		
		double computedValue = 0;
		double numberOfOccurence = 0;
		double computedValuePercent = 0;

		DataSetNode currentDataSetNode = getHeadPointer();
		ItemNode byItem = currentDataSetNode.getHeadPointer();

		ItemNode currentItem = items.getHeadNode();
		int existingCounter = 0;
		
		while (currentDataSetNode != null) {
			byItem = currentDataSetNode.getHeadPointer();

			while(currentItem != null) {
				if (!ifContains(currentItem.getItemName(), byItem))
					break;
				existingCounter++;
				currentItem = currentItem.getNextPointer();
			}
			
			if(existingCounter == items.getLength())
				numberOfOccurence++;

			existingCounter = 0;
			currentItem = items.getHeadNode();
			byItem = byItem.getNextPointer();

			currentDataSetNode = currentDataSetNode.getNextPointer();
		}

		computedValue = numberOfOccurence / countLength;
		computedValuePercent = computedValue * 100;
		//@formatter:off
		System.out.print("\n+========[SUPPORT VALUE]=======+\n"
						 + "Total # of Occurence: " + numberOfOccurence
						 + "\nTotal # of Data Set: " + countLength
						 + "\n\n>Support { ");
		
		String supportItem = "";
		while(currentItem != null) {
			supportItem += (currentItem.getNextPointer() != null) ? currentItem.getItemName() + ", " : currentItem.getItemName() + " }";
			currentItem = currentItem.getNextPointer();
		}
		
		
		System.out.println(supportItem
					   + " = " + numberOfOccurence + " / " + countLength + " = " + computedValue
					   + "\n>Support { " + supportItem + " = " + computedValuePercent + "%"
					   + "\n+==============================+\n");
		//@formatter:on
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

			if (firstItemPresent && secondItemPresent) 
				numberOfBothOccurence += 1;
			
			firstItemPresent = false;
			secondItemPresent = false;
			
			currentDataSetNode = currentDataSetNode.getNextPointer();
		}

		// singular occurrence B
		currentDataSetNode = getHeadPointer();
		while (currentDataSetNode != null) {
			byItem = currentDataSetNode.getHeadPointer();
			if (ifContains(itemOne, byItem))
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
		//@formatter:off
			System.out.print("\n+========[CONFIDENCE VALUE]========+\n"
							 + "Total # of Occurence of { " + itemOne + "," + itemTwo +" }: " + numberOfBothOccurence
							 + "\nTotal # of Occurence of { " + itemOne + " }: " + numberOfOccurenceOfB
							 + "\n\n>Condifence { " + itemTwo + "-> " + itemOne + " }" 
							 + " = " + numberOfBothOccurence + " / " + numberOfOccurenceOfB 
							 + "\n>Condifence { " + itemTwo + "-> " + itemOne + " }" 
							 + " = " + String.format("%.2f", computedConfidenceValue)
							 + "\n"
							 + "\n+===========[LIFT VALUE]===========+\n"
							 + "Total # of Occurence of { " + itemOne + "," + itemTwo +" }: " + numberOfBothOccurence
							 + "\nTotal # of Occurence of { " + itemTwo + " }: " + numberOfOccurenceOfB
							 + "\nTotal # of Occurence of { " + itemOne + " }: " + numberOfOccurenceOfA
							 + "\n\n>Lift { " + itemTwo + "-> " + itemOne + " }" 
							 + " = (" + numberOfBothOccurence + " / " + countLength + ") / "  
							 + " ((" + numberOfOccurenceOfB + " / " + countLength + ") *" 
							 + " (" + numberOfOccurenceOfA + " / " + countLength + ")) " 
							 + "\n>Lift { " + itemTwo + "-> " + itemOne + " }" 
							 + " = " + String.format("%.2f", supportValueOfBoth) + " / "  
							 + " (" + String.format("%.2f", supportValueOfB) + " *" 
							 + " " + String.format("%.2f", supportValueOfA) + ") " 
							 + "\n>Lift { " + itemTwo + "-> " + itemOne + " }" 
							 + " = " + String.format("%.2f", computedLiftValue));
			if(computedLiftValue > 1) {
				System.out.println("\n+=================================+\n"
								 + "There is an association {" + itemTwo + " -> " + itemOne + "}"
								 + "\n+=================================+\n");
			} else if (computedLiftValue == 1) {
           	   System.out.println("\n+=================================+\n"
								 + "There is no association! \nLift Value: " + String.format("%.2f", computedLiftValue) + " = 1\n" 
								 + "+=================================+\n");
			
            } else {
				System.out.println("\n+=================================+\n"
								 + "There is no association! \nLift Value: " + String.format("%.2f", computedLiftValue) + " < 1\n" 
								 + "+=================================+");
			}
			
			//@formatter:on
	}// end method

	public boolean ifContains(String name, ItemNode headNode) {
		for (ItemNode start = headNode; start != null; start = start.getNextPointer()) {
			if (start.getItemName().equalsIgnoreCase(name)) 
				return true;
		}
		return false;
	}// end method

}// end class
