//Class for inserting and retrieving rows in each table. As we know each row of all tables contains index, symbol
//and the address of that symbol

package com.sarthak;

public class TableRow {
	
	int index;
	String symbol;
	int address;
	
	public TableRow(String symbol, int address) {
		super();
		this.symbol = symbol;
		this.address = address;
	}
	
	public TableRow(String symbol, int address, int index) {
		super();
		this.symbol = symbol;
		this.address = address;
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	
	

}
