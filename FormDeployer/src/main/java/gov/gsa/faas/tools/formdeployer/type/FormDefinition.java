package gov.gsa.faas.tools.formdeployer.type;

import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;

public class FormDefinition {
    private String formName;
    private JsonNode formDefinitionNode;

    public FormDefinition() {
    }

    public FormDefinition(JsonNode formDefinitionNode) {
        setFormDefinitionNode(formDefinitionNode);
    }

    public String getFormName() {
        return this.formName;
    }

    public JsonNode getFormDefinitionNode() {
        return this.formDefinitionNode;
    }

    public void setFormDefinitionNode(JsonNode formDefinitionNode) {
        this.formName = formDefinitionNode.at("/name").asText();

        this.formDefinitionNode = formDefinitionNode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FormDefinition)) {
            return false;
        }
        FormDefinition formDefinition = (FormDefinition) o;
        return Objects.equals(formName, formDefinition.formName) && Objects.equals(formDefinitionNode, formDefinition.formDefinitionNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formName, formDefinitionNode);
    }

    @Override
    public String toString() {
        return "{" +
            " formName='" + getFormName() + "'" +
            ", formDefinitionNode='" + getFormDefinitionNode().toPrettyString() + "'" +
            "}";
    }

}
