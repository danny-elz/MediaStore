package ca.sheridancollege.elzeind.MediaShop.security;

import ca.sheridancollege.elzeind.MediaShop.beans.User;
import ca.sheridancollege.elzeind.MediaShop.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    @Lazy
    private DatabaseAccess da;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = da.findUserAccount(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        List<String> roles = da.getRolesById(user.getUserId());
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            System.out.println("Authority being granted: " + role);
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getEncryptedPassword(),
                authorities);
    }
}
