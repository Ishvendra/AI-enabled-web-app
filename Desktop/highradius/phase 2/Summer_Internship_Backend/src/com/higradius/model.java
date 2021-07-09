package com.higradius;

import java.util.Date;

public class model {
    
    protected String name_customer;
    protected String cust_number;
    protected long invoice_id;
    protected int total_open_amount;
    protected String due_in_date;
    protected Date predicted_clear_date;
    protected String Notes;
    
    
	public model(String name_customer, String cust_number, long invoice_id, int total_open_amount,
			String due_in_date, Date predicted_clear_date, String notes) {
		super();
		this.name_customer = name_customer;
		this.cust_number = cust_number;
		this.invoice_id = invoice_id;
		this.total_open_amount = total_open_amount;
		this.due_in_date = due_in_date;
		this.predicted_clear_date = predicted_clear_date;
		Notes = notes;
	}
	
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
	public String getCust_number() {
		return cust_number;
	}
	public void setCust_number(String cust_number) {
		this.cust_number = cust_number;
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
	public String getDue_in_date() {
		return due_in_date;
	}
	public void setDue_in_date(String due_in_date) {
		this.due_in_date = due_in_date;
	}
	public Date getPredicted_clear_date() {
		return predicted_clear_date;
	}
	public void setPredicted_clear_date(Date predicted_clear_date) {
		this.predicted_clear_date = predicted_clear_date;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
}