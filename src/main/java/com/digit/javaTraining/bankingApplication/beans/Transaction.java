package com.digit.javaTraining.bankingApplication.beans;

public class Transaction {

	int customerID;
	String senderBankName;
	String senderIFSC;
	int senderAccountNumber;
	String receiverIFSC;
	int receiverAccountNumber;
	int amountOfTransfer;
	Long transactionID;

	public Transaction(int customerID, String senderBankName, String senderIFSC, int senderAccountNumber,
			String receiverIFSC, int receiverAccountNumber, int amountOfTransfer, Long transactionID) {
		super();
		this.customerID = customerID;
		this.senderBankName = senderBankName;
		this.senderIFSC = senderIFSC;
		this.senderAccountNumber = senderAccountNumber;
		this.receiverIFSC = receiverIFSC;
		this.receiverAccountNumber = receiverAccountNumber;
		this.amountOfTransfer = amountOfTransfer;
		this.transactionID = transactionID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getSenderBankName() {
		return senderBankName;
	}

	public void setSenderBankName(String senderBankName) {
		this.senderBankName = senderBankName;
	}

	public String getSenderIFSC() {
		return senderIFSC;
	}

	public void setSenderIFSC(String senderIFSC) {
		this.senderIFSC = senderIFSC;
	}

	public int getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(int senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public String getReceiverIFSC() {
		return receiverIFSC;
	}

	public void setReceiverIFSC(String receiverIFSC) {
		this.receiverIFSC = receiverIFSC;
	}

	public int getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(int receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public int getAmountOfTransfer() {
		return amountOfTransfer;
	}

	public void setAmountOfTransfer(int amountOfTransfer) {
		this.amountOfTransfer = amountOfTransfer;
	}

	public Long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}
}
