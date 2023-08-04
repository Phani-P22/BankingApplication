package com.digit.javaTraining.bankingApplication.beans;

public class Loan {
	int loanID;
	String loanType;
	int tenure;
	float intrest;
	String description;

	public Loan(int loanID, String loanType, int tenure, float intrest, String description) {
		super();
		this.loanID = loanID;
		this.loanType = loanType;
		this.tenure = tenure;
		this.intrest = intrest;
		this.description = description;
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public float getIntrest() {
		return intrest;
	}

	public void setIntrest(float intrest) {
		this.intrest = intrest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
