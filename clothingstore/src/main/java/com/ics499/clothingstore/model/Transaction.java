package com.ics499.clothingstore.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Transaction model. Extension of Payment, Transaction is the confirmation of
 * payment when a order is placed.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private long transactionId;

	private String creditCardNumber;
	private String cardName;
	private int creditCardCV;
	private Date creditCardExpirationDate;
	private double total;
	private Date transactionDate;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	private Order order;

	public Transaction(String creditCardNumber, String cardName, int creditCardCV, Date creditCardExpirationDate,
			double total, Date transactionDate) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.cardName = cardName;
		this.creditCardCV = creditCardCV;
		this.creditCardExpirationDate = creditCardExpirationDate;
		this.total = total;
		this.transactionDate = transactionDate;
	}

	public Transaction() {

	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public int getCreditCardCV() {
		return creditCardCV;
	}

	public void setCreditCardCV(int creditCardCV) {
		this.creditCardCV = creditCardCV;
	}

	public Date getCreditCardExpirationDate() {
		return creditCardExpirationDate;
	}

	public void setCreditCardExpirationDate(Date creditCardExpirationDate) {
		this.creditCardExpirationDate = creditCardExpirationDate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}