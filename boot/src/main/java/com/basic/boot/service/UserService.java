package com.basic.boot.service;

import com.basic.boot.domain.UserEntity;
import com.basic.boot.domain.request.UserRequest;
import com.basic.boot.domain.response.UserResponse;
import com.basic.boot.repository.UserRepository;
import com.basic.boot.repository.UserSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
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

    public UserResponse findUserById(String username) throws NoSuchElementException{
        UserEntity entity = userRepository.findById(username).orElseThrow();
        return new UserResponse(entity);
    }

    @Transactional
    public UserEntity saveUser( UserRequest userRequest) throws Exception{

        //UserEntity userEntity = ;

        if(userRepository.findByEmail(userRequest.getEmail())!= null){
            throw new Exception("UserEmail already exists");
        }

        if(userRepository.findById(userRequest.getUsername()).isPresent()){
           throw new Exception("Username already exists");
        }
       return  userRepository.save(userRequest.toEntity());
    }

    @Transactional
         //유저 네임과 페이스워드가 불일치할때 에러
    public void updateUser(UserRequest userRequest) throws Exception{

        if(!userRepository.existsByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword())){
            throw new Exception("Username or password does not exist");
        }
        UserEntity entity = userRepository.findById(userRequest.getUsername()).orElseThrow(); // 기존 entity객체
       // log.info("Updating entity: " + entity.getEmail());
       // log.info("Updating request: " + userRequest.getEmail());
        entity.update(userRequest); // email 만 사용자가 입력한 값으로 변경
       // log.info("Updating entity: " + entity.getEmail());
        userRepository.save(entity); // 기존 객체 email 달라지면 jpa 자동으로 update query 날림

    }
    @Transactional
    public void deleteUser(UserRequest userRequest) throws Exception{
        if(!userRepository.existsByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword())){
            throw new Exception("Username or password does not exist");
        }
        UserEntity entity = userRepository.findById(userRequest.getUsername()).orElseThrow(); // 기존 entity객체
        userRepository.delete(entity);

    }

    public List<UserSummary> findAllUserSummary(){
        return userRepository.findBy();
    }

    public UserSummary findUserSummaryById(String username) throws NoSuchElementException{
        return userRepository.findByUsername(username);
    }

}
