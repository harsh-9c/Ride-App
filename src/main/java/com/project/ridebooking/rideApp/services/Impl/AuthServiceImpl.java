package com.project.ridebooking.rideApp.services.Impl;

import com.project.ridebooking.rideApp.dto.DriverDto;
import com.project.ridebooking.rideApp.dto.SignupDto;
import com.project.ridebooking.rideApp.dto.UserDto;
import com.project.ridebooking.rideApp.entities.User;
import com.project.ridebooking.rideApp.entities.enums.Role;
import com.project.ridebooking.rideApp.exceptions.RuntimeConflictException;
import com.project.ridebooking.rideApp.repositories.UserRepository;
import com.project.ridebooking.rideApp.services.AuthService;
import com.project.ridebooking.rideApp.services.RiderService;
import com.project.ridebooking.rideApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null){
            throw new RuntimeConflictException("User already exists! "+signupDto.getEmail());
        }

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);


        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
