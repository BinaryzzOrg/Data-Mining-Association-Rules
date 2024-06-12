public class LinkItem {
	private ItemNode headNode, tailPointer;// Pointers to the head and tail of the linked list
	private int length = 0;// Length of the linked list

	public LinkItem() {
		this.headNode = tailPointer = null;
	}

	private boolean emptyList() {
		return this.headNode == null;
	}

	public int count() {
		return countList(this.headNode);
	}

	private int countList(ItemNode head) {
		if (head != null)
			return countList(head.getNextPointer()) + 1;
		return 0;
	}

	public ItemNode getHeadNode() {
		return headNode;
	}

	public void setHeadNode(ItemNode headNode) {
		this.headNode = headNode;
	}

	public ItemNode getTailPointer() {
		return tailPointer;
	}

	public void setTailPointer(ItemNode tailPointer) {
		this.tailPointer = tailPointer;
	}

	public int getLength() {
		return this.length;
	}

	public boolean ifContains(String name) {
		for (ItemNode start = this.headNode; start != null; start = start.getNextPointer()) {
			if (start.getItemName().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * This method adds a new item to the list. If the list already contains 10
	 * items, it exits without adding the new item. Otherwise, it creates a new
	 * ItemNode with the given typeName. If the list is not empty, it appends the
	 * new item to the end of the list by updating the next pointer of the current
	 * tail node and setting the new item as the new tail. If the list is empty, it
	 * sets both the head and tail pointers to the new item. After adding the item,
	 * it increments the length of the list.
	 */
	public void addToList(String typeName) {
		// Check if the list already contains 10 items, if so, exit the method
		if (count() >= 10)
			return;
		// Create a new ItemNode with the given typeName
		ItemNode insertNode = new ItemNode(typeName);

		// If the list is not empty, append the new item to the end of the list
		if (!emptyList()) {
			tailPointer.setNextPointer(insertNode);
			setTailPointer(tailPointer.getNextPointer());
			length++;
			return;
		}
		// If the list is empty, set both the head and tail pointers to the new item
		length++;
		setHeadNode(insertNode);
		setTailPointer(insertNode);
		return;
	}// end method

	// print ung datasets
	/**
	 * This method displays the list of items in the dataset. It iterates through
	 * each item in the list, printing its name along with a sequential number. If
	 * an item has a successor, it appends a comma after the name. After printing
	 * all items, it adds a line break for clarity.
	 */
	public void display() {
		System.out.println("\n+===========[Item List]===========+");
		int numberFormat = 1;
		for (ItemNode byItem = this.getHeadNode(); byItem != null; byItem = byItem.getNextPointer()) {
			System.out.print(
					"| " + numberFormat + ") " + byItem.getItemName() + (byItem.getNextPointer() != null ? "," : ""));
			System.out.println();
			numberFormat++;
		}
		System.out.println("+=================================+\n");
	}// end method

	public String getItem(int position) {

		int counter = 1;
		for (ItemNode byItem = this.getHeadNode(); byItem != null; byItem = byItem.getNextPointer()) {

			if (counter == position) {
				return byItem.toString();
			} // end if

			counter++;
		} // end for

		return null;
	}// end method

}// end class
