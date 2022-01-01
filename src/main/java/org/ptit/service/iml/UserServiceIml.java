package org.ptit.service.iml;


import lombok.RequiredArgsConstructor;
import org.ptit.dto.UserDTO;
//import org.ptit.exception.UserNotFoundException;
import org.ptit.exception.UserNotFoundException;
import org.ptit.mapper.MappingHelper;
import org.ptit.model.User;
import org.ptit.repository.UserRepository;
import org.ptit.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {
    private final UserRepository userRepository;
    private final MappingHelper mappingHelper;

    @Override
    public UserDTO getUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        UserDTO userDTO = mappingHelper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = mappingHelper.map(userDTO, User.class);
        userRepository.save(user);
        return mappingHelper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setAge(user.getAge());
        user.setIdNumber(user.getIdNumber());
        User userUpdated = userRepository.save(user);
        UserDTO userDTOUpdated = mappingHelper.map(userUpdated, UserDTO.class);
        return userDTOUpdated;
    }

    @Override
    public UserDTO updateByIdNumber(String idNumber, int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        user.setIdNumber(idNumber);
        userRepository.save(user);
        UserDTO userDTO = mappingHelper.map(user, UserDTO.class);
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
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserDTO> userDTOPage = mappingHelper.mapPage(userPage, UserDTO.class);
        return userDTOPage;
    }

    @Override
    public Page<UserDTO> listUsersByAge(Pageable pageable, int age) {
        Page<User> userPage = userRepository.findUserByAge(pageable, age);
        Page<UserDTO> userDTOPage = mappingHelper.mapPage(userPage, UserDTO.class);
        return userDTOPage;
    }

    @Override
    public List<UserDTO> findByName(String name, String address, Integer age) {
        List<User> users = userRepository.findAll(where(nameLike(name).and(filterByAge(age)).and(filterByAddress(address))));
        List<UserDTO> userDTOS = mappingHelper.mapList(users, UserDTO.class);
        return userDTOS;

    }

    private Specification<User> nameLike(String name) {
        return (userRoot, query, criteriaBuilder)
                -> criteriaBuilder.like(userRoot.get("name"), "%" + name + "%");
    }

    private Specification<User> filterByAge(Integer age) {
        if (!(age == null)) {
            return (userRoot, query, criteriaBuilder) -> criteriaBuilder.equal(userRoot.get("age"), age);
        } else {
            return null;
        }
    }

    private Specification<User> filterByAddress(String address) {
        if (!address.isEmpty()) {
            return (userRoot, query, criteriaBuilder) -> criteriaBuilder.like(userRoot.get("address"), address);
        } else {
            return null;
        }
    }
}
