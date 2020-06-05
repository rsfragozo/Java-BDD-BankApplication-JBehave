package training.javabdd.bank.offer.credit;

import training.javabdd.bank.customer.Customer;

public class BusinessCreditOffer extends CreditOffer {
	
	public BusinessCreditOffer(String id) {
		super(id);
	}

	@Override
	public boolean addCustomer(Customer customer) {
		if (customer.isVip()) {
			return customersSet.add(customer);
		}
		return false;
	}

	@Override
	public boolean removeCustomer(Customer customer) {
		return false;
	}

}
