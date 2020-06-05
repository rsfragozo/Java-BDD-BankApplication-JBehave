package training.javabdd.bank.offer.credit;

import training.javabdd.bank.customer.Customer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class CreditOffer {

	private String id;
	Set<Customer> customersSet = new HashSet<>();

	public CreditOffer(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Set<Customer> getCustomersSet() {
		return Collections.unmodifiableSet(customersSet);
	}

	public abstract boolean addCustomer(Customer customer);

	public abstract boolean removeCustomer(Customer customer);

}
