package org.ptit.service;

import liquibase.pro.packaged.I;
import liquibase.pro.packaged.S;
import liquibase.pro.packaged.T;
import org.ptit.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO getUser(int id);

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, int id);

    UserDTO updateByIdNumber(String idNumber, int id);

    void deleteUserById(int id);

    void deleteUsers();

    Page<UserDTO> getUsers(Pageable pageable);

    Page<UserDTO> findByAge(Pageable pageable, int age);

    List<UserDTO> findByName(String name, String address, Integer age);

}
