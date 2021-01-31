package sql_tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import redmain.db.request.RoleRequests;
import redmain.model.role.Role;

import java.util.List;

@Slf4j
public class DataBaseTest {

    @Test
    public void basicSqlTest() {
        List<Role> roles = RoleRequests.getAllRoles();
//        log.debug("roles: {}", roles);
        int rolesCountBefore = roles.size();
        Role role = new Role();
//        Assert.assertNotNull(role.getId());
        RoleRequests.addRole(role);
        Assert.assertNotNull(role.getId());
        int rolesCountAfter = RoleRequests.getAllRoles().size();
        Assert.assertEquals(rolesCountAfter, rolesCountBefore + 1);

    }

    @Test
    public void getRolesTest() {
        Role role = new Role();
        role.setName("Пользователь1");

        Role dataBaseRole = RoleRequests.getRole(role);
        Assert.assertEquals(dataBaseRole.getId().intValue(), 11);

        role.setId(15);
        Role dataBaseRole2 = RoleRequests.getRole(role);
        Assert.assertEquals(dataBaseRole2.getName(), "Новая автотестовая роль");
    }

    @Test
    public void updateRolesTest() {
        Role role = new Role();
        role.setName("Новая автотестовая роль");

        RoleRequests.updateRole(role);
    }
}

