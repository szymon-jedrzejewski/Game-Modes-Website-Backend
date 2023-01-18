package com.gmw.services.user.impl;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.testutilities.ServiceType;
import com.gmw.services.testutilities.TestDbUtilities;
import com.gmw.services.user.DBUserService;
import com.gmw.user.enums.RoleEnum;
import com.gmw.user.tos.ExistingUserTO;
import com.gmw.user.tos.NewUserTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DBUserServiceImplTest {
    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        TestDbUtilities.initializeDatabase(ServiceType.USER);
        this.serviceManager = new SqlServiceManager(new SqlRepositoryManager(TestDbUtilities.getConnection()));
    }

    @Test
    public void findUserByEmail() throws ResourceNotFoundException {
        String email = "email@org.com";
        ExistingUserTO existingUserTO = serviceManager.getDbUserService().obtainUserByEmail(email);
        assertNotNull(existingUserTO);
        assertEquals(Long.valueOf(1), existingUserTO.getId());
        assertEquals(email, existingUserTO.getEmail());
        assertEquals("test_name", existingUserTO.getName());
        assertEquals("secret_password", existingUserTO.getPassword());
    }

    @Test
    public void findUsersByIds() throws ResourceNotFoundException {
        List<ExistingUserTO> existingUserTOS = serviceManager.getDbUserService().obtainUsersByIds(List.of(1L));
        ExistingUserTO existingUserTO = existingUserTOS.get(0);
        assertNotNull(existingUserTO);
        assertEquals(Long.valueOf(1), existingUserTO.getId());
        assertEquals("email@org.com", existingUserTO.getEmail());
        assertEquals("test_name", existingUserTO.getName());
        assertEquals("secret_password", existingUserTO.getPassword());
    }

    @Test
    public void findUserById() throws ResourceNotFoundException {
        ExistingUserTO existingUserTO = serviceManager.getDbUserService().obtainUserById(1L);
        assertNotNull(existingUserTO);
        assertEquals(Long.valueOf(1), existingUserTO.getId());
        assertEquals("email@org.com", existingUserTO.getEmail());
        assertEquals("test_name", existingUserTO.getName());
        assertEquals("secret_password", existingUserTO.getPassword());
    }

    @Test
    public void findUserRoleByEmail() throws ResourceNotFoundException {
        String role = serviceManager.getDbUserService().obtainUserRoleByUserEmail("email@org.com");
        assertNotNull(role);
        assertEquals("USER", role);
    }

    @Test
    public void createUser() throws ResourceNotCreatedException, ResourceNotFoundException {
        NewUserTO userTO = new NewUserTO();
        userTO.setAvatar(null);
        userTO.setName("name");
        userTO.setEmail("email");
        userTO.setPassword("password");
        DBUserService service = serviceManager.getDbUserService();
        service.createUser(userTO);

        long id = service.obtainUserById(2L).getId();
        assertEquals(2L, id);
    }

    @Test
    public void updateUser() throws ResourceNotUpdatedException, ResourceNotFoundException {
        ExistingUserTO existingUserTO = prepareExistingUser();

        DBUserService service = serviceManager.getDbUserService();

        service.updateUser(existingUserTO);

        ExistingUserTO actual = service.obtainUserById(1L);

        assertEquals(existingUserTO, actual);
    }

    @Test
    public void deleteUser() throws ResourceNotDeletedException, ResourceNotFoundException {
        DBUserService service = serviceManager.getDbUserService();
        service.deleteUser(1L);
        ExistingUserTO user = service.obtainUserById(1L);

        assertEquals("deleted_user", user.getName());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getAvatar());
    }

    @Test
    public void promoteToAdmin() throws ResourceNotUpdatedException, ResourceNotFoundException {
        DBUserService service = serviceManager.getDbUserService();
        service.promoteToAdmin(prepareExistingUser());

        assertEquals(RoleEnum.ADMIN, RoleEnum.valueOf(service.obtainUserRoleByUserEmail("test@email.com")));
    }

    @Test
    public void demoteFromAdmin() throws ResourceNotUpdatedException, ResourceNotFoundException {
        DBUserService service = serviceManager.getDbUserService();
        service.promoteToAdmin(prepareExistingUser());

        ExistingUserTO actual = service.obtainUserById(1L);
        assertEquals(RoleEnum.ADMIN, RoleEnum.valueOf(service.obtainUserRoleByUserEmail("test@email.com")));

        service.demoteFromAdmin(actual);
        assertEquals(RoleEnum.USER, RoleEnum.valueOf(service.obtainUserRoleByUserEmail("test@email.com")));
    }

    private static ExistingUserTO prepareExistingUser() {
        return ExistingUserTO
                .builder()
                .id(1L)
                .email("test@email.com")
                .avatar(null)
                .name("some_random_name")
                .password("4321")
                .build();
    }
}