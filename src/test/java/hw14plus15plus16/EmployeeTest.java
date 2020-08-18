package hw14plus15plus16;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EmployeeTest {

    @Test
    public void employeeHasIdProperty () {
        Employee employee = new Employee(1,"John","Smith","USA",25);
        assertThat(employee, hasProperty("id"));
    }

    @Test
    public void employeeHasCorrectCountryValue () {
        Employee employee = new Employee(1,"John","Smith","USA",25);
        assertThat(employee, hasProperty("country", equalTo("USA")));
    }

    @Test
    public void employeeHasSameProperties () {
        Employee employee = new Employee(1,"John","Smith","USA",25);
        Employee employee2 = new Employee(1,"John","Smith","USA",25);
        assertThat(employee, samePropertyValuesAs(employee2));
    }

    @Test
    public void employeeHasToString () {
        Employee employee = new Employee(1,"John","Smith","USA",25);
        hasToString(employee.toString());
    }

}
