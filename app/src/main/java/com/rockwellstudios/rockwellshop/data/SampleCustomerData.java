package com.rockwellstudios.rockwellshop.data;

import com.rockwellstudios.rockwellshop.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 18.06.2017.
 */

public class SampleCustomerData {

    public static List<Customer> getSampleCustomers() {
        List<Customer> customers = new ArrayList<>();

        Customer customer = new Customer();
        customer.setCustomerName("John Smith");
        customer.setEmailAddress("john.smith@gmail.com");
        customer.setProfileImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVXg9kMZojNgEOl-5LdAQJTwbt58twBWbD008-wHNq4IxdTi5N");
        customers.add(customer);

        customer = new Customer();
        customer.setCustomerName("Jack Daniels");
        customer.setEmailAddress("jack.daniels@gmail.com");
        customer.setProfileImagePath("https://www.newschool.edu/uploadedImages/Parsons/Profiles/jamer_hunt_profile.jpg?n=4468");
        customers.add(customer);

        customer = new Customer();
        customer.setCustomerName("Petya Vasechkin");
        customer.setEmailAddress("petya.vasechkin@gmail.com");
        customer.setProfileImagePath("http://cdn.cfo.com/content/uploads/2014/07/mulford_chuck_profile.jpg");
        customers.add(customer);

        return customers;

    }
}
