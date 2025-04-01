package com.basic.boot.service;

import com.basic.boot.domain.UserEntity;
import com.basic.boot.domain.request.UserRequest;
import com.basic.boot.domain.response.UserResponse;
import com.basic.boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public List<UserResponse> findAllUser(){
        List<UserEntity> userList = userRepository.findAll();
//        List<UserResponse> list = new ArrayList<>();
//        for (UserEntity user : userList) {
//            list.add(new UserResponse(user));
//        }
//        return list;
        return userList.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public UserEntity findById(String username){
        return userRepository.findById(username).orElse(null);
    }


    @Transactional
    public UserEntity saveUser( UserRequest userRequest) throws Exception{

        //UserEntity userEntity = ;

        if(userRepository.findByEmail(userRequest.getEmail())!= null){
            throw new Exception("UserEmail already exists");
        }

        if(findById(userRequest.getUsername()) != null){
           throw new Exception("Username already exists");
        }
       return  userRepository.save(userRequest.toEntity());
    }


}
