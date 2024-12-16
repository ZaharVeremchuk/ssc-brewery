package guru.sfg.brewery.web.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class PasswordEncoderTest {

    static final String PASSWORD = "password";

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
