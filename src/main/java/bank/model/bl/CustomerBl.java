package bank.model.bl;

import lombok.Getter;
import bank.model.da.CustomerDa;
import bank.model.entity.Customer;
import java.util.List;

public class CustomerBl implements BusinessLogic<Customer, Integer>{
    @Getter
    private final static CustomerBl instance = new CustomerBl();

    private CustomerBl() {
    }


    @Override
    public Customer save(Customer customer) throws Exception {
        try (CustomerDa customerDa = new CustomerDa())
        {
            if (customerDa.findCustomerById(customer.getId()) != null)
            {
                throw new Exception("Duplicate Account Number !!!!!");
            }
            customerDa.saveCustomer(customer);
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer) throws Exception{
        try (CustomerDa customerDa = new CustomerDa())
        {
            if (customerDa.findCustomerById(customer.getId()) == null) {
                throw new Exception("Account with id = " + customer.getId() + " Not exists !!!");
            }
            if (customerDa.findCustomerById(customer.getId()) != null)
            {
                throw new Exception("Duplicate account number !!!");
            }
            customerDa.updateCustomer(customer);
        }
        return customer;
    }

    @Override
    public Customer delete(Integer id) throws Exception
    {
        try (CustomerDa customerDa = new CustomerDa())
        {
            if (customerDa.findCustomerById(id) == null)
            {
                throw new Exception("Account with id = " + id + " Not exists !!!");
            }
            customerDa.deleteCustomer(id);
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (CustomerDa customerDa = new CustomerDa())
        {
            return customerDa.findAllCustomers();
        }
    }

    @Override
    public Customer findById(Integer id) throws Exception{
        try (CustomerDa customerDa = new CustomerDa()) {
            return customerDa.findCustomerById(id);
        }
    }

}
