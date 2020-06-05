package training.javabdd.bank.story;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import training.javabdd.bank.customer.Customer;
import training.javabdd.bank.offer.credit.BusinessCreditOffer;
import training.javabdd.bank.offer.credit.CreditOffer;
import training.javabdd.bank.offer.credit.EconomyCreditOffer;
import training.javabdd.bank.offer.credit.PremiumCreditOffer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomersPolicy {
    private CreditOffer creditOffer;
    private Customer mike;
    private Customer john;

    @Given("there is an economy credit offer")
    public void there_is_an_economy_credit_offer() {
        creditOffer = new EconomyCreditOffer("1");
    }

    @When("we have a usual customer")
    public void we_have_a_usual_customer() {
        mike = new Customer("Mike", false);
    }

    @Then("you can add and remove him from an economy credit offer")
    public void you_can_add_and_remove_him_from_an_economy_credit_offer() {
        assertAll("Verify all conditions for a usual passenger and an economy credit offer",
                () -> assertEquals("1", creditOffer.getId()),
                () -> assertTrue(creditOffer.addCustomer(mike)),
                () -> assertEquals(1, creditOffer.getCustomersSet().size()),
                () -> assertTrue(creditOffer.getCustomersSet().contains(mike)),
                () -> assertTrue(creditOffer.removeCustomer(mike)),
                () -> assertEquals(0, creditOffer.getCustomersSet().size())
        );
    }

    @When("we have a VIP customer")
    public void we_have_a_VIP_customer() {
        john = new Customer("John", true);
    }

    @Then("you can add him but cannot remove him from an economy credit offer")
    public void you_can_add_him_but_cannot_remove_him_from_an_economy_credit_offer() {
        assertAll("Verify all conditions for a VIP passenger and an economy credit offer",
                () -> assertEquals("1", creditOffer.getId()),
                () -> assertTrue(creditOffer.addCustomer(john)),
                () -> assertEquals(1, creditOffer.getCustomersSet().size()),
                () -> assertTrue(creditOffer.getCustomersSet().contains(john)),
                () -> assertFalse(creditOffer.removeCustomer(john)),
                () -> assertEquals(1, creditOffer.getCustomersSet().size())
        );
    }

    @Given("there is an business credit offer")
    public void there_is_an_business_credit_offer() {
        creditOffer = new BusinessCreditOffer("2");
    }

    @Then("you cannot add or remove him from a business credit offer")
    public void you_cannot_add_or_remove_him_from_a_business_credit_offer() {
        assertAll("Verify all conditions for a usual customer and a business credit offer",
                () -> assertFalse(creditOffer.addCustomer(mike)),
                () -> assertEquals(0, creditOffer.getCustomersSet().size()),
                () -> assertFalse(creditOffer.removeCustomer(mike)),
                () -> assertEquals(0, creditOffer.getCustomersSet().size())
        );
    }

    @Then("you can add him but cannot remove him from a business credit offer")
    public void you_can_add_him_but_cannot_remove_him_from_a_business_credit_offer() {
        assertAll("Verify all conditions for a VIP customer and a business credit offer",
                () -> assertTrue(creditOffer.addCustomer(john)),
                () -> assertEquals(1, creditOffer.getCustomersSet().size()),
                () -> assertFalse(creditOffer.removeCustomer(john)),
                () -> assertEquals(1, creditOffer.getCustomersSet().size())
        );
    }

    @Given("there is an premium credit offer")
    public void there_is_an_premium_credit_offer() {
        creditOffer = new PremiumCreditOffer("3");
    }

    @Then("you cannot add or remove him from a premium credit offer")
    public void you_cannot_add_or_remove_him_from_a_premium_credit_offer() {
        assertAll("Verify all conditions for a usual customer and a premium credit offer",
                () -> assertFalse(creditOffer.addCustomer(mike)),
                () -> assertEquals(0, creditOffer.getCustomersSet().size()),
                () -> assertFalse(creditOffer.removeCustomer(mike)),
                () -> assertEquals(0, creditOffer.getCustomersSet().size())
        );
    }

    @Then("you can add and remove him from a premium credit offer")
    public void then_you_can_add_and_remove_him_from_a_premium_credit_offer() {
        assertAll("Verify all conditions for a VIP customer and a premium credit offer",
                () -> assertTrue(creditOffer.addCustomer(john)),
                () -> assertEquals(1, creditOffer.getCustomersSet().size()),
                () -> assertTrue(creditOffer.removeCustomer(john)),
                () -> assertEquals(0, creditOffer.getCustomersSet().size())
        );
    }

    @Then("you cannot add a usual customer to a credit offer more than once")
    public void you_cannot_add_him_to_an_economy_credit_offer_more_than_once() {
        for (int i=0; i<10; i++) {
            creditOffer.addCustomer(mike);
        }
        assertAll("Verify a usual customer can be added to an economy credit offer only once",
                () -> assertEquals(1, creditOffer.getCustomersSet().size()),
                () -> assertTrue(creditOffer.getCustomersSet().contains(mike)),
                () -> assertEquals(new ArrayList<>(creditOffer.getCustomersSet()).get(0).getName(), "Mike")
        );
    }

    @Then("you cannot add a VIP customer to a credit offer more than once")
    public void you_cannot_add_a_VIP_customer_to_a_credit_offer_more_than_once() {
        for (int i=0; i<10; i++) {
            creditOffer.addCustomer(john);
        }
        assertAll("Verify a usual customer can be added to an economy credit offer only once",
                () -> assertEquals(1, creditOffer.getCustomersSet().size()),
                () -> assertTrue(creditOffer.getCustomersSet().contains(john)),
                () -> assertEquals(new ArrayList<>(creditOffer.getCustomersSet()).get(0).getName(), "John")
        );
    }
}
