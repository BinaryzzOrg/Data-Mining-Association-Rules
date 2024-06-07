public class LinkItem {
	private ItemNode headNode, tailPointer;
	private int length = 0;

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

	public void addToList(String typeName) {
		if (count() >= 10)
			return;

		ItemNode insertNode = new ItemNode(typeName);
		if (!emptyList()) {
			tailPointer.setNextPointer(insertNode);
			setTailPointer(tailPointer.getNextPointer());
			length++;
			return;
		}

		length++;
		setHeadNode(insertNode);
		setTailPointer(insertNode);
		return;
	}// end method

	// print ung datasets
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
