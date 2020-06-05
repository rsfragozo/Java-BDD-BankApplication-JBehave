package training.javabdd.bank.story;

import training.javabdd.bank.bonus.Bonus;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import training.javabdd.bank.customer.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusPolicy {
    private Customer mike;
    private Customer john;
    private Bonus bonus = new Bonus();

    @Given("we have a usual customer with credit offers")
    public void we_have_a_usual_customer_with_credit_offers() {
        mike = new Customer("Mike", false);
    }

    @When("the usual customer has credit offers with amounts $amount1 and $amount2 and $amount3")
    public void the_usual_customer_has_credit_offers_with_amounts_and_and(int amount1, int amount2, int amount3) {
        bonus.addAmount(mike, amount1);
        bonus.addAmount(mike, amount2);
        bonus.addAmount(mike, amount3);
    }

    @Then("the bonus points of the usual customer should be $points")
    public void the_bonus_points_of_the_usual_customer_should_be(int points) {
        bonus.calculateGivenPoints();
        assertEquals(points, bonus.getCustomersPointsMap().get(mike).intValue());
    }

    @Given("we have a VIP customer with credit offers")
    public void we_have_a_VIP_customer_with_credit_offers() {
        john = new Customer("John", true);
    }

    @When("the VIP customer has credit offers with amounts $amount1 and $amount2 and $amount3")
    public void the_VIP_customer_has_credit_offers_with_amounts_and_and(int amount1, int amount2, int amount3) {
        bonus.addAmount(john, amount1);
        bonus.addAmount(john, amount2);
        bonus.addAmount(john, amount3);
    }

    @Then("the bonus points of the VIP customer should be $points")
    public void the_bonus_points_of_the_VIP_customer_should_be(int points) {
        bonus.calculateGivenPoints();
        assertEquals(points, bonus.getCustomersPointsMap().get(john).intValue());
    }

}
