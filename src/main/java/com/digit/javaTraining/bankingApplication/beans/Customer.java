package com.digit.javaTraining.bankingApplication.beans;

public class Customer {

	int bankID;
	String bankName;
	String ifscCode;
	int accountNumber;
	int pin;
	int customerID;
	String customerName;
	int balance;
	String email;
	Long phoneNumber;

	public Customer(int bankID, String bankName, String ifscCode, int accountNumber, int pin, int customerID,
			String customerName, int balance, String email, Long phoneNumber) {
		this.bankID = bankID;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.customerID = customerID;
		this.customerName = customerName;
		this.balance = balance;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getBankID() {
		return bankID;
	}

	public void setBankID(int bankID) {
		this.bankID = bankID;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
