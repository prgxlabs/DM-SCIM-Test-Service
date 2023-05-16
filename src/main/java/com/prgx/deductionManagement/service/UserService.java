package com.prgx.deductionManagement.service;

import com.prgx.deductionManagement.model.Address;
import com.prgx.deductionManagement.model.UserResource;
import com.prgx.deductionManagement.model.UserResourceRef;
import com.prgx.deductionManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserResourceRef> listUsers(Pageable pageable){
        return userRepository.findAll(pageable).stream()
                .map((userResource)-> {
                    return UserResourceRef.builder()
                            .username(userResource.getUsername())
                            .id(userResource.getId())
                            .build();
                }).toList();
    }

    public Optional<UserResource> getUser(String id){
        return userRepository.findById(id);
    }
    public UserResource createUser(UserResource user){
        return userRepository.save(user);
    }

    public UserResource updateUser(UserResource user){
        return userRepository.save(user);
    }
}
