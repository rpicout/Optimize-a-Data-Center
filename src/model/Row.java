package model;

public class Row {

	private Slot[] slot;
	
	
	public Row(int nbSlot) {
		super();
		this.slot = new Slot[nbSlot];
		
		for (int i = 0; i < nbSlot; i++) {
			this.slot[i] = new Slot(null, false);
		}
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
