package com.nicolas.myEcommerce.service;

import com.nicolas.myEcommerce.dto.UserDTO;
import com.nicolas.myEcommerce.model.user.User;
import com.nicolas.myEcommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        String hashpwd = passwordEncoder.encode(userToRegister.getPassword());
        userToRegister.setPassword(hashpwd);
        userToRegister.setCreateDate(String.valueOf(new Date(System.currentTimeMillis())));
        User savedUser = modelMapper.map(userToRegister, User.class);
        repository.save(savedUser);
        return modelMapper.map(savedUser, UserDTO.class);
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
        User user = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("id non valido"));
        user.setId(userToUpdate.getId());
        user = repository.save(user);
        return  modelMapper.map(user, UserDTO.class);
    }

    @Transactional
    public UserDTO delete(long id){
        User user = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("id non valido"));
        repository.delete(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getById(long id){
        User user = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("id non valido"));
        return modelMapper.map(user, UserDTO.class);
    }

}
