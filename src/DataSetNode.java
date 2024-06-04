public class DataSetNode {
	private ItemNode headPointer, tailPointer;
	private DataSetNode nextPointer;

	public DataSetNode() {
		this.headPointer = tailPointer = null;
	}

	private boolean emptyList() {
		return this.headPointer == null;
	}

	public ItemNode getHeadPointer() {
		return headPointer;
	}

	public void setHeadPointer(ItemNode headPointer) {
		this.headPointer = headPointer;
	}

	public ItemNode getTailPointer() {
		return tailPointer;
	}

	public void setTailPointer(ItemNode tailPointer) {
		this.tailPointer = tailPointer;
	}

	public DataSetNode getNextPointer() {
		return nextPointer;
	}

	public void setNextPointer(DataSetNode nextPointer) {
		this.nextPointer = nextPointer;
	}

	public void addItemToDataSet(String typeName) {
		ItemNode insertItem = new ItemNode(typeName);
		if (!emptyList()) {
			tailPointer.setNextPointer(insertItem);
			setTailPointer(tailPointer.getNextPointer());
			return;
		}
		setHeadPointer(insertItem);
		setTailPointer(insertItem);
		return;
	}

}// end class
