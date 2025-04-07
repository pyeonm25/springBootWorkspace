package com.my.security2.service;


import com.my.security2.domain.UserEntity;
import com.my.security2.dto.JoinLoginRequest;
import com.my.security2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void addUser(JoinLoginRequest request) throws Exception{
        boolean existUsername = userRepository.existsByUsername(request.getUsername());
        if(existUsername){
            throw new Exception("username alreay exist");
        }
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
       // user.setRole("ROLE_ADMIN");
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
