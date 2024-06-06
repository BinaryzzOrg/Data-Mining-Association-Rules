public class LinkItem {
	private ItemNode headNode, tailPointer;

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
			return;
		}

		setHeadNode(insertNode);
		setTailPointer(insertNode);
		return;
	}// end method

}// end class
