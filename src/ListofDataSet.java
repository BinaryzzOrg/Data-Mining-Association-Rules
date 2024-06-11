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

	/*
 	 * addDataSet adds a data set that has been created using
   	 * the item list from the Menu. If the dataSet list is
         * not empty then it will add the new data set at the tail
	 * if it is not empty it will set it as the head and tail.
	 */
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

	/*
	 * This method return is used for deleting a data set specified by the user. It has a parameter of integer position
	 * where user specified for deletion. 
	 * 
	 * If the position is invalid it will notify the user that the input is invalid.
	 * 
	 * If the position is invalid it will show an error message. If the position to delete is on 
	 * the first node, the second node will be the new head. If the position to delete is on the last node, it will traverse through
	 * the list and the second to the last of the list will point its next node to null. If the position to delete is neither the 
	 * first and last node, we traverse the list and find the preceded node and make the next pointer to the next next node. 
	 * 
	 * if the data set list is zero make the the isEmptyDataSetList to true 
	 * 
	 * after executing make a notification that the deletion is successful;
	 */
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

	/*
 	 * display prints the data sets by travsering the data
         * set list then getting the pointer to the head of the
	 * item list stored within that data set node to print
   	 * all of the stored item in that data set.
	 */
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

	/*
 	 * In order to display the support value, it first displays
   	 * the item list to view all the items available then
     	 * stores all of the selected to a LinkItem node to keep
       	 * track of all the items that has been selected by the user
	 * Items stored in the LinkItem list are items that will be
  	 * considered as items that appear on a same data set and get
         * its support value using the helper method named displaySupportValueHelper()
	 */
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
			items.addToList(temp);
		} // end for
		
		displaySupportValueHelper(items);
	}

	/*
 	 * displaySupportValueHelper is for printing the support value
         * computed of the given items. Using a nested while loop,
	 * in the outer while loop it is used to traverse the DataSet
         * list and gets the items within it and storing it in the
	 * ItemNode list, afterwards using the inner while loop
  	 * it checks if the items in the given parameter contains
    	 * all of the items stored within it using ifContains. If
      	 * ifContains returns a false value it will break the inner
	 * while loop and move to the next one. Else if it finds a
  	 * true value meaning that the item exist it will increase
    	 * the existingCounter until it reaches the length of the
      	 * items list, if it reaches the length it means that all of
	 * the items within that item list exist in that data set and
  	 * it will increase the numberOfOccurence of those items.
    	 * then finally in the computedValue it divides the numberOfOccurence
      	 * to countLength to identify its support value. The display shows
	 * a percentage value and a decimal value along with its breakdown
  	 * of computation
	 */
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

	/*
 	 * determineAssociation is for getting the confidence and lift
   	 * value of the two given items. The first while loop if for checking
     	 * if itemOne and itemTwo exist within the list of Items in the currentDataSet.
       	 * If both of these items exist in the currentDataSet it will add 1 to
	 * numberOfBothOccurence, then the following two while loops if for checking
  	 * the occurence of each item in the data set list. It has the same logic
    	 * as the first while loop where it searchers for its occurence in the
      	 * ItemNode of the currentDataSet then adds one to its number of occurence
	 * if found. Finally using the formulas for confidence and life value
  	 * it prints the breakdown computation of the confidence and life along
    	 * with its computed value before printing it.
 	 */
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
							 + "\n>Condifence { " + itemTwo + "-> " + itemOne + " }" 
							 + " = " + String.format("%.2f", (computedConfidenceValue * 100)) + "%"
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

	/*
 	 * ifContains is used for checking if a specific
   	 * items exists within the ItemNode list of items.
     	 * If it exists return true. If the for loop ends
       	 * without finding it return false meaning it does not
	 * exist.
	 */
	public boolean ifContains(String name, ItemNode headNode) {
		for (ItemNode start = headNode; start != null; start = start.getNextPointer()) {
			if (start.getItemName().equalsIgnoreCase(name)) 
				return true;
		}
		return false;
	}// end method

}// end class
