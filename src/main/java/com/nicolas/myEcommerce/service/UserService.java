package com.nicolas.myEcommerce.service;

import com.nicolas.myEcommerce.dto.UserDTO;
import com.nicolas.myEcommerce.exception.IdNotFoundException;
import com.nicolas.myEcommerce.model.user.User;
import com.nicolas.myEcommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public UserDTO register(UserDTO userToRegister){
        String hashPwd = passwordEncoder.encode(userToRegister.getPassword());
        userToRegister.setPassword(hashPwd);
        userToRegister.setCreateDate(String.valueOf(new Date(System.currentTimeMillis())));
        User savedUser = modelMapper.map(userToRegister, User.class);
        repository.save(savedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }
    User findById(long id){
        return repository.findById(id).orElseThrow(() -> new BadCredentialsException("User not found"));
    }

    public List<UserDTO> findAll(int pageIndex) {
        final int pageSize = 10;
        Page<User> users = repository.findAll(PageRequest.of(pageIndex, pageSize));
        return users.map(user -> modelMapper.map(user,  UserDTO.class)).toList();
    }

    public UserDTO findByEmail(String email) {
        return modelMapper.map(repository.findByEmail(email).orElseThrow(() -> new BadCredentialsException("User not found")), UserDTO.class);
    }
    @Transactional
    public UserDTO update(UserDTO userToUpdate, long id){
        if (getById(id) == null){
            throw new IdNotFoundException("User with ID " + id + " not found");
        }
        User user = modelMapper.map(userToUpdate, User.class);
        user.setId(userToUpdate.getId());
        user = repository.save(user);
        return  modelMapper.map(user, UserDTO.class);
    }

    @Transactional
    public UserDTO delete(long id){
        User user = modelMapper.map(getById(id), User.class);
        repository.delete(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getById(long id){
        User user = findById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    public String getAddressByUser(long userId) {
        User user = findById(userId);
        return user.getAddressList().toString();
    }

    public String getPhoneByUser(long userId) {
        User user = findById(userId);
        return user.getPhoneNumber().toString();
    }

}
