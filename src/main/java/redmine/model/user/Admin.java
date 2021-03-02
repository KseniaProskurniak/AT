package redmine.model.user;

public class Admin extends User {
    public Admin() {
        this.setAdmin(true);
    }
}
