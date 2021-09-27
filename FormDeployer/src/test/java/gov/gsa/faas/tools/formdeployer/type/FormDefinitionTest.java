package gov.gsa.faas.tools.formdeployer.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.lang.EquivalentFactory;
import org.meanbean.test.BeanTester;
import org.meanbean.test.HashCodeMethodTester;

public class FormDefinitionTest {

    @Test
    public void testConstructorWithParams(){

        Path path = FileSystems.getDefault().getPath("src/test/resources/formDefinitionOutputExample.json");
        JsonNode rootNode = null;
        try {
            String formDefinitionContent = Files.readString(path, StandardCharsets.US_ASCII);

            // read json into JsonNode using Jackson ObjectMapper
            rootNode = (new ObjectMapper()).readTree(formDefinitionContent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // create object with params
        FormDefinition formDefWithParams = new FormDefinition(rootNode);

        // create object with no params and set values
        FormDefinition formDefNoParams = new FormDefinition();
        formDefNoParams.setFormDefinitionNode(rootNode);

        // assert equality
        assertEquals(formDefNoParams, formDefWithParams);
    }

    @Test
    @Ignore("INTENTIONALLY IGNORED: tested by testConstructorWithParams")
    public void testEqualsMethod(){
        //INTENTIONALLY IGNORED
        //tested by testConstructorWithParams
        assertTrue(true);
    }

    @Test
    public void testHashCodeMethod() {
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(new FormDefinitionFactory());
    }

}

class FormDefinitionFactory implements EquivalentFactory<FormDefinition> {
    @Override
    public FormDefinition create() {

        Path path = FileSystems.getDefault().getPath("src/test/resources/formDefinitionOutputExample.json");
        JsonNode rootNode = null;
        try {
            String formDefinitionContent = Files.readString(path, StandardCharsets.US_ASCII);

            // read json into JsonNode using Jackson ObjectMapper
            rootNode = (new ObjectMapper()).readTree(formDefinitionContent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        FormDefinition formDef = new FormDefinition();
        formDef.setFormDefinitionNode(rootNode);
        return formDef;
    }
}
