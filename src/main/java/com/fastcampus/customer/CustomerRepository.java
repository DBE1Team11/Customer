package com.fastcampus.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
	private static final Map<String, Customer> USER = new HashMap<>();

	public void addCustomer(Customer customer) {
		if (USER.containsKey(customer.getId())) {
			return;
		}
		String id = customer.getId();
		USER.put(id, customer);
	}

	public Customer getCustomer(String id){
		return USER.get(id);
	}

	public boolean idCheck(String id){
		return USER.containsKey(id);
	}


}
