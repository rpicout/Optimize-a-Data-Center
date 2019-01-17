package model;

public class Row {

	private Slot[] slot;
	
	
	public Row(Slot[] slot) {
		super();
		this.slot = slot;
	}

	
	public Slot[] getSlot() {
		return slot;
	}

	public Slot getSlot(int i){
		return slot[i];
	}
	
	public void setSlot(Slot[] slot) {
		this.slot = slot;
	}
	
	public void setSlot(int i, Slot slot){
		this.slot[i] = slot;
	}
}
