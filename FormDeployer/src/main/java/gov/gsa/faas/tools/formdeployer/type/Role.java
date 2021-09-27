package gov.gsa.faas.tools.formdeployer.type;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Role {
    @JsonAlias({"_id"})
    private String id;
    private String description;
    @JsonAlias({"default"})
    private String roleDefault;
    private String admin;
    private String title;
    private String machineName;
    private String project;
    private String created;
    private String modified;

    public Role() {
        //Nothing to do
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleDefault() {
        return this.roleDefault;
    }

    public void setRoleDefault(String roleDefault) {
        this.roleDefault = roleDefault;
    }

    public String getAdmin() {
        return this.admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMachineName() {
        return this.machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return this.modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(description, role.description) && Objects.equals(roleDefault, role.roleDefault) && Objects.equals(admin, role.admin) && Objects.equals(title, role.title) && Objects.equals(machineName, role.machineName) && Objects.equals(project, role.project) && Objects.equals(created, role.created) && Objects.equals(modified, role.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, roleDefault, admin, title, machineName, project, created, modified);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", roleDefault='" + getRoleDefault() + "'" +
            ", admin='" + getAdmin() + "'" +
            ", title='" + getTitle() + "'" +
            ", machineName='" + getMachineName() + "'" +
            ", project='" + getProject() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }

}
