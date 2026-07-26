package bank.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import bank.model.bl.CustomerBl;
import bank.model.entity.Customer;
import bank.model.entity.Response;
import bank.model.entity.ResponseStatus;

import java.time.LocalDate;

@Slf4j

public class CustomerController {
    @Getter
    private final static CustomerController instance = new CustomerController();
    private CustomerController() {}

    public Response save(String username, String password, String firstName, String lastName,
                         String nationllid, String phone, String email,
                         String address, LocalDate dateOfBirth,LocalDate registrationDate) {
        try {
            Customer customer =
                    Customer
                            .builder()
                            .username(username)
                            .password(password)
                            .firstName(firstName)
                            .lastName(lastName)
                            .nationllid(nationllid)
                            .phone(phone)
                            .email(email)
                            .address(address)
                            .dateOfBirth(dateOfBirth)
                            .registrationDate(registrationDate)
                            .build();
            CustomerBl.getInstance().save(customer);
            log.debug("customer saved");
            return new Response(ResponseStatus.Success,"Customer saved successfully",customer);
        } catch (Exception e) {
            log.error("Customer save failed");
            return new Response(ResponseStatus.Failure,e.getMessage());
        }
    }
    public Response update(long id,String username, String password, String firstName, String lastName,
                           String nationllid, String phone, String email,
                           String address, LocalDate dateOfBirth,LocalDate registrationDate) {
        try {
            Customer customer =
                    Customer
                            .builder()
                            .id(id)
                            .username(username)
                            .password(password)
                            .firstName(firstName)
                            .lastName(lastName)
                            .nationllid(nationllid)
                            .phone(phone)
                            .email(email)
                            .address(address)
                            .dateOfBirth(dateOfBirth)
                            .registrationDate(registrationDate)
                            .build();
            CustomerBl.getInstance().update(customer);
            log.debug("customer edited");
            return new Response(ResponseStatus.Success,"Customer edited successfully",customer);
        } catch (Exception e) {
            log.error("Customer edited failed");
            return new Response(ResponseStatus.Failure,e.getMessage());
        }
    }
    public Response delete(int id) {
        try {
            Customer customer = CustomerBl.getInstance().delete(id);
            log.debug("Customer deleted");
            return new Response(ResponseStatus.Success, "Customer has been deleted", customer);
        } catch (Exception e) {
            return new Response(ResponseStatus.Failure, e.getMessage());
        }
    }
    public Response findAll() {
        try {
            log.debug("select all Customer");
            return new Response(ResponseStatus.Success,"FindAll", CustomerBl.getInstance().findAll());
        }catch (Exception e) {
            log.error("FindAll failed");
            return new Response(ResponseStatus.Failure, e.getMessage());
        }
    }
    public Response findById(int id) {
        try {
            log.debug("select Customer by id");
            return new Response(ResponseStatus.Success,"FindById : "+id, CustomerBl.getInstance().findById(id));
        } catch (Exception e) {
            log.error("FindById failed");
            return new Response(ResponseStatus.Failure, e.getMessage());
        }
    }
    public Response findByUsername(String username)
    {
        try {
            log.debug("select Customer by username");
            return new Response(ResponseStatus.Success,"FindByUsername : "
                    +username,CustomerBl.getInstance().findByUserName(username));
        } catch (Exception e) {
            log.error("FindByUsername failed user not found");
            return new Response(ResponseStatus.Failure, e.getMessage());
        }

    }
}

