package com.higradius;


public class model2 {
    
    protected long invoice_id;
    protected int total_open_amount;
    protected String Notes;
    
    
	public model2(long invoice_id, int total_open_amount, String notes) {
		super();
		this.invoice_id = invoice_id;
		this.total_open_amount = total_open_amount;
		Notes = notes;
	}
	

	public long getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(long invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getTotal_open_amount() {
		return total_open_amount;
	}
	public void setTotal_open_amount(int total_open_amount) {
		this.total_open_amount = total_open_amount;
	}

	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
}