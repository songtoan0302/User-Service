package org.ptit.service;

import liquibase.pro.packaged.I;
import liquibase.pro.packaged.S;
import liquibase.pro.packaged.T;
import org.ptit.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO getUser(int id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO,int id);
    UserDTO updateByIdNumber(String idNumber,int id);
    void deleteUserById(int id);
    void deleteUsers();
    Page<UserDTO> getUserPage(int page, int size);
    Page<UserDTO> findByAge(int age,int page,int size);
    List<UserDTO>findByName(String name, String address, Integer age);

}
