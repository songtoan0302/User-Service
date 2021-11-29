package org.ptit.service;

import liquibase.pro.packaged.I;
import liquibase.pro.packaged.S;
import org.ptit.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO getUser(int id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO,int id);
    UserDTO updateUserByIdNumber(String idNumber,int id);
    void deleteUserById(int id);
    void deleteUsers();
    Map<String,Object> getUsers(int page, int size);
    Map<String , Object> findUserByAge(int age,int page,int size);
    List<UserDTO> searchUserByName(String name, String address, Integer age);

}
