package com.project.ridebooking.rideApp.services;

import com.project.ridebooking.rideApp.entities.User;
import com.project.ridebooking.rideApp.exceptions.ResourceNotFoundException;
import com.project.ridebooking.rideApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Cacheable(cacheNames = "users", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElse(null);
    }

    @Cacheable(cacheNames = "users", key = "#userId")
    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: "+userId));
    }

    // Cache eviction methods for when user data is updated
    @CacheEvict(cacheNames = "users", key = "#userId")
    public void evictUserCache(Long userId) {
        // This method can be called when user data is updated
    }

    @CacheEvict(cacheNames = "users", key = "#email")
    public void evictUserCacheByEmail(String email) {
        // This method can be called when user data is updated
    }
}