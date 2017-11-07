package com.example.customerservice;

import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class CustomerTest {

    private Validator validator;


    @Before
    public void before()  throws Throwable{

        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        Validator validator = localValidatorFactoryBean.getValidator();
        this.validator = validator;

    }

    @Test
    public void newInstanceWithValidValuesShouldReturnARecord() {
        Customer customer = new Customer(1L, "firstname", "lastname", "email@email.com");
//        Assert.assertEquals( customer.getId(), (Long) 1L);
        BDDAssertions.assertThat(customer.getId()).isEqualTo(1L);
        BDDAssertions.then(customer.getFirstName()).isEqualTo("firstname");
    }

    @Test
    public void newInstanceWithInvalidConstraintsShouldProduceConstraintViolations() {

        Customer customer = new Customer(null, null, null, null);
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
        BDDAssertions.then(constraintViolations.size()).isEqualTo(3);


    }
}
