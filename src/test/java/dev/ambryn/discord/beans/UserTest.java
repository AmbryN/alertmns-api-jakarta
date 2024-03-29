package dev.ambryn.discord.beans;

import dev.ambryn.discord.enums.ERole;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void shouldLowercaseEmail() {
        user.setEmail("TEST3@TEST.com");
        assertEquals("test3@test.com", user.getEmail());
    }

    @Test
    public void shouldTrimEmail() {
        user.setEmail("     test@test.com     ");
        assertEquals("test@test.com", user.getEmail());
    }

    @Test
    public void shouldHashPassword() {
        Pbkdf2PasswordHashImpl hasher = new Pbkdf2PasswordHashImpl();
        user.setPassword("test");

        assertTrue(hasher.verify("test".toCharArray(), user.getPassword()));
    }

    @Test
    public void shouldUppercaseLastname() {
        user.setLastname("test");
        assertEquals("TEST", user.getLastname());
    }

    @Test
    public void shouldTrimLastname() {
        user.setLastname("     TEST     ");
        assertEquals("TEST", user.getLastname());
    }

    @Test
    void setLastnameShouldEscapeHTMLChars() {
        user.setLastname("<>&\"");
        assertEquals("&lt;&gt;&amp;&quot;", user.getLastname());
    }

    @Test
    public void shouldTrimFirstname() {
        user.setFirstname("     Test     ");
        assertEquals("Test", user.getFirstname());
    }

    @Test
    public void shouldCapitalizeFirstname() {
        user.setFirstname("test");
        assertEquals("Test", user.getFirstname());
    }

    @Test
    void setFirstnameShouldEscapeHTMLChars() {
        user.setFirstname("<>&\"");
        assertEquals("&lt;&gt;&amp;&quot;", user.getFirstname());
    }

    @Test
    void shouldAddRole() {
        Role role = new Role(ERole.USER);
        user.addRole(role);
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    void shouldRemoveRole() {
        Role role = new Role(ERole.USER);
        user.addRole(role);
        user.removeRole(role);
        assertFalse(user.getRoles().contains(role));
    }

    @Test
    void shouldAddNotification() {
        Notification notification = new Notification();
        user.addNotification(notification);
        assertTrue(user.getNotifications().contains(notification));
    }
}