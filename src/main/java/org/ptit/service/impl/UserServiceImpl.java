package org.ptit.service.impl;

import liquibase.pro.packaged.D;
import liquibase.pro.packaged.I;
import lombok.RequiredArgsConstructor;
import org.ptit.dto.UserDTO;
import org.ptit.exception.UserNotFoundException;
import org.ptit.mapper.UserMapper;
import org.ptit.model.User;
import org.ptit.repository.UserRepository;
import org.ptit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(int id) {
        User user = userRepository.getById(id);
        if(!Objects.nonNull(user)) throw  new UserNotFoundException();
        UserDTO userDTO = userMapper.convertToDTO(user);
        return userDTO;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        if(!Objects.nonNull(user)) throw  new UserNotFoundException();
        userRepository.save(user);
        return userMapper.convertToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        User user = userRepository.getById(id);
        if (!Objects.nonNull(user)) throw new UserNotFoundException();
        else {
            user.setName(userDTO.getName());
            user.setAddress(userDTO.getAddress());
            user.setAge(user.getAge());
            user.setIdNumber(user.getIdNumber());
            userRepository.save(user);
        }
        UserDTO userUpdated = userMapper.convertToDTO(user);
        return userUpdated;
    }

    @Override
    public UserDTO updateUserByIdNumber(String idNumber, int id) {
        User user = userRepository.getById(id);
        if (!Objects.nonNull(user)) throw new UserNotFoundException();
        else {
            user.setIdNumber(idNumber);
            userRepository.save(user);
        }
        UserDTO userDTO = userMapper.convertToDTO(user);
        return userDTO;
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUsers() {
        userRepository.deleteAll();
    }

    @Override
    public Map<String, Object> getUsers(int page, int size) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserDTO> userDTOPage = userMapper.mapPage(userPage, UserDTO.class);
        List<UserDTO> userDTOS = userDTOPage.getContent();
        response.put("User", userDTOS);
        response.put("currentPage", userDTOPage.getNumber());
        response.put("totalItems", userDTOPage.getTotalElements());
        response.put("totalPages", userDTOPage.getTotalPages());
        return response;
    }

    @Override
    public Map<String, Object> findUserByAge(int page, int size, int age) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findUserByAge(pageable, age);
        Page<UserDTO> userDTOPage = userMapper.mapPage(userPage, UserDTO.class);
        List<UserDTO> userDTOS = userDTOPage.getContent();
        response.put("User", userDTOS);
        response.put("currentPage", userDTOPage.getNumber());
        response.put("totalItems", userDTOPage.getTotalElements());
        response.put("totalPages", userDTOPage.getTotalPages());
        return response;
    }

    @Override
    public List<UserDTO> searchUserByName(String name, String address, Integer age) {
            List<User> users = userRepository.findAll(Specification.where(nameLike(name).and(filterUser(address, age))));
            List<UserDTO> userDTOS = userMapper.mapList(users, UserDTO.class);
            return userDTOS;

    }

    private Specification<User> nameLike(String name){
        return (userRoot, query, criteriaBuilder)
                -> criteriaBuilder.like(userRoot.get("name"), "%"+name+"%");
    }

    private Specification<User> filterUser(String address, Integer age){
        if(!address.isEmpty()){
            return (userRoot,query,criteriaBuilder)->criteriaBuilder.like(userRoot.get("address"),address);
        }
        if(!(age==null)){
            return (userRoot,query,criteriaBuilder)->criteriaBuilder.equal(userRoot.get("age"),age);
        }
        return null;
    }

}
