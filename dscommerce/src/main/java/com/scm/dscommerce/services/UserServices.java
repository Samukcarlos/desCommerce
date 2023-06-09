package com.scm.dscommerce.services;

package com.devsuperior.asdemo.services;

import com.devsuperior.asdemo.entities.User;
import com.devsuperior.asdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }

    public User authenticated(){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return repository.findByEmail(username);
        }
        catch (Exception e){
            throw new UsernameNotFoundException("Invalid user");
        }
    }
    @Transational
    public UserDTO getMe(reandOnly = true){
        User entity =  authenticated();
        return new UserDTO(entity);
    }

}