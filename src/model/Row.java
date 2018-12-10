package model;

public class Row {

	private Slot[] slots;
	
	
	public Row(Slot[] slots) {
		super();
		this.slots = slots;
	}

	
	public Slot[] getSlots() {
		return slots;
	}

	public void setSlots(Slot[] slots) {
		this.slots = slots;
	}
	
	
}
