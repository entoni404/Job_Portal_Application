package org.spring_web.jobportalapplication.service;

import jakarta.transaction.Transactional;
import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.enums.UserRole;
import org.spring_web.jobportalapplication.model.mapper.UserMapper;
import org.spring_web.jobportalapplication.model.resource.UserResource;
import org.spring_web.jobportalapplication.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserResource getUserById(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }else{
            return userMapper.toDTO(userRepository.findById(id).get());
        }
    }

    public Page<UserResource> getAllUsersByRole(Optional<UserRole> userRole, Pageable pageable){
        Page<User> userPage;
        if(userRole.isPresent()){
            userPage = userRepository.findAllByUserRole(userRole.get(), pageable);
        }else{
            userPage = userRepository.findAll(pageable);
        }
        return userPage.map(userMapper::toDTO);
    }


    @Transactional
    public void deleteUserById(Long userId){
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("User not found");
        }else{
            userRepository.deleteById(userId);
        }
    }



}
