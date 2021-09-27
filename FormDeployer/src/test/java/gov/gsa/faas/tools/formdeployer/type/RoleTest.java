package gov.gsa.faas.tools.formdeployer.type;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

public class RoleTest {

    @Test
    public void testGettersAndSetters(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Role.class);
    }

    @Test
    public void testEqualsMethod(){
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(Role.class);
    }

    @Test
    public void testHashCodeMethod(){
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(Role.class);
    }

}
