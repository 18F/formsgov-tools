package gov.gsa.faas.tools.formdeployer.type;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

public class SingleAccessTest {

    @Test
    public void testConstructorWithParams(){

        List<String> roleList = new ArrayList<String>();
        roleList.add("RoleId");
        String accessType = "create_own";

        // create object with params
        SingleAccess saWithParams = new SingleAccess(roleList, accessType);

        // create object with no params and set values
        SingleAccess saNoParams = new SingleAccess();
        saNoParams.setRoles(roleList);
        saNoParams.setType(accessType);

        // assert equality
        assertEquals(saNoParams, saWithParams);
    }

    @Test
    public void testGettersAndSetters(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Role.class);
    }

    @Test
    public void testEqualsMethod(){
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(SingleAccess.class);
    }

    @Test
    public void testHashCodeMethod(){
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(SingleAccess.class);
    }
    
}
