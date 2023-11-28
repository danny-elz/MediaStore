package ca.sheridancollege.elzeind.MediaShop.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String originalPassword = "123";
        String encodedPassword = encoder.encode(originalPassword);

        System.out.println("Original Password: " + originalPassword);
        System.out.println("Hashed Password: " + encodedPassword);
    }
}
