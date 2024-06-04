public class ItemNode {
	private String itemName;
	private ItemNode nextPointer;

	public ItemNode(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ItemNode getNextPointer() {
		return nextPointer;
	}

	public void setNextPointer(ItemNode nextPointer) {
		this.nextPointer = nextPointer;
	}

}// end class
