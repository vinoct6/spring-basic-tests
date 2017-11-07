package com.example.customerservice;


import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = CustomerServiceApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    //https://stackoverflow.com/questions/13489388/how-does-junit-rule-work
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void saveShouldMapCorrectly() throws Exception {
        Customer customer = new Customer(null, "first", "last", "email@email.com");
        Customer saved = this.testEntityManager.persistFlushFind(customer);
        then(saved.getId()).isNotNull();
        then(saved.getFirstName()).isEqualTo("first");
    }

    @Test
    public void repositorySaveShouldMapCorrectly() throws Exception {
        Customer customer = new Customer(null, "firstName", "lastName", "email@email.com");
        Customer saved = customerRepository.save(customer);
        then(saved.getId()).isNotNull();
        then(saved.getFirstName()).isEqualTo("firstName");
    }

    @Test
    public void newInstanceWithInvalidParametersShouldResultInConstraintViolation() {
        expectedException.expect(ConstraintViolationException.class);
        customerRepository.save(new Customer(null, null, null, null));
    }


}
