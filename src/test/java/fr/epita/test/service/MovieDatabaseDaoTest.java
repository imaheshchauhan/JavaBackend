package fr.epita.test.service;

import fr.epita.model.Address;
import fr.epita.model.Contact;
import fr.epita.model.Role;
import fr.epita.model.User;
import fr.epita.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JPATestContextConfiguration.class)
public class MovieDatabaseDaoTest {

    @Inject
    @Named("user-jpadao")
    IDao userDAO;
    @Inject
    @Named("role-jpadao")
    IDao roleDAO;
    @Inject
    @Named("contact-jpadao")
    IDao contactDAO;
    @Inject
    @Named("address-jpadao")
    IDao addressDAO;

    @Test
    public void testUserCRUD() {
        Role role = new Role();
        role.setName("admin");
        Contact contact = new Contact();
        contact.setName("John Doe");
        contact.setEmail("johndoe@doe.com");
        contact.setBirthdate(new Date());
        contact.setGender("M");

        // Create a new user
        User user = new User();
        user.setUsername("testuser");
        user.setRole(role);
        user.setContact(contact);
        contact.setUser(user);

        Address address = new Address();
        address.setArea("NY");
        address.setCountry("USA");
        address.setCity("New York");
        address.setNumber("10001");
        address.setStreet("abc");
        address.setContact(contact);
        contact.setAddress(address);

        // Save the user to the database
        roleDAO.save(role);
        addressDAO.save(address);
        contactDAO.save(contact);
        userDAO.save(user);



        // Retrieve the user from the database and verify that it was saved
        User savedUser = (User) userDAO.findById(user.getId());
        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());

        // Retrieve the updated user from the database and verify that the changes were saved
        User updatedUser = (User) userDAO.findById(savedUser.getId());
        assertNotNull(updatedUser);
        assertEquals("testuser", updatedUser.getUsername());

        // Delete the user from the database
        userDAO.delete(updatedUser);

        // Verify that the user was deleted
        User deletedUser = (User) userDAO.findById(updatedUser.getId());
        assertNull(deletedUser);
    }

    @Test
    public void testRoleCRUD() {
        Role role = new Role(2,"User");
        roleDAO.save(role);
        assertNotNull(role.getId());

        role.setName("Super Admin");
        roleDAO.update(role);
        Role updatedRole = (Role) roleDAO.findById(role.getId());
        assertEquals("Super Admin", updatedRole.getName());

        roleDAO.delete(role);
        assertNull(roleDAO.findById(role.getId()));
    }

    @Test
    public void testContactCRUD() {
        Address address = new Address(1,"USA", "abc","New York","NY","10001",new Contact());
        address.setCountry("USA");
        address.setCity("New York");
        addressDAO.save(address);
        assertNotNull(address.getId());

        Contact contact = new Contact();
        contact.setName("John Doe");
        contact.setEmail("johndoe@example.com");
        contact.setAddress(address);
        contactDAO.save(contact);
        assertNotNull(contact.getId());

        contact.setName("Jane Doe");
        contactDAO.update(contact);
        Contact updatedContact = (Contact) contactDAO.findById(contact.getId());
        assertEquals("Jane Doe", updatedContact.getName());

        contactDAO.delete(contact);
        assertNull(contactDAO.findById(contact.getId()));
        assertNotNull(addressDAO.findById(address.getId()));
    }

    @Test
    public void testAddressCRUD() {
        Address address = new Address();
        address.setCountry("USA");
        address.setCity("Los Angeles");
        addressDAO.save(address);
        assertNotNull(address.getId());

        address.setCity("San Francisco");
        addressDAO.update(address);
        Address updatedAddress = (Address) addressDAO.findById(address.getId());
        assertEquals("San Francisco", updatedAddress.getCity());

        addressDAO.delete(address);
        assertNull(addressDAO.findById(address.getId()));
    }
}


