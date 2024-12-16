package guru.sfg.brewery.web.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import static org.junit.Assert.assertTrue;

public class PasswordEncoderTest {

    static final String PASSWORD = "password";

    @Test
    void testLDAP() {
        /*
         * LDAP using random salt.
         * 1 and 2 are different.
         * Encrypted password example : {{SSHA}}hash-code
         * {SSHA}+Okj/GoJOkShTQu5FiQb5nVpZyn5WYc+E4mlwQ==
         */
        PasswordEncoder ldap = new LdapShaPasswordEncoder();
        System.out.println(ldap.encode(PASSWORD)); //1
        System.out.println(ldap.encode(PASSWORD)); //2

        String encodedPwd = ldap.encode(PASSWORD);

        assertTrue(ldap.matches(PASSWORD, encodedPwd)); //true
    }

    @Test
    void testNoOp() throws Exception {
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

        System.out.println(passwordEncoder.encode(PASSWORD)); // password not encrypted
    }
    @Test
    void hashingExample() throws Exception {
        // Hash without salt, random salt. 1 and 2 are same
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes())); //1
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes())); //2

        String salted = PASSWORD + "ThisIsMySaltValue";
        System.out.println(DigestUtils.md5DigestAsHex(salted.getBytes())); //different hash
    }
}
