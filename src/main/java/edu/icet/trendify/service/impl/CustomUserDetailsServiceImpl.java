package edu.icet.trendify.service.impl;

import edu.icet.trendify.entity.user.RoleEntity;
import edu.icet.trendify.entity.user.UserEntity;
import edu.icet.trendify.entity.user.UserRoleEntity;
import edu.icet.trendify.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository
                            .findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(user==null || !user.getIsActive()){
            throw new UsernameNotFoundException("User not found");
        }

        return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getUserRole()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<UserRoleEntity> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getRole().name()))
                .collect(Collectors.toList());
    }
}
