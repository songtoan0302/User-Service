package org.ptit.controller;

import liquibase.pro.packaged.H;
import liquibase.pro.packaged.N;
import liquibase.pro.packaged.O;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
import org.ptit.dto.UserDTO;
import org.ptit.model.User;
import org.ptit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int id) {
        UserDTO userDTO = userService.getUser(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsersPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        Map<String, Object> response = userService.getUsers(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/age")
    public ResponseEntity<Map<String, Object>> getUsersPageFilterAge(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size,@RequestParam(defaultValue = "0") int age) {
        Map<String, Object> response = userService.findUserByAge(page, size,age);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userDTOCreated=userService.addUser(userDTO);
        return new ResponseEntity<>(userDTOCreated,HttpStatus.CREATED);
    }
     @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable int id){
        UserDTO userDTOUpdated=userService.updateUser(userDTO,id);
        return new ResponseEntity<>(userDTOUpdated,HttpStatus.OK);
     }

     @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateIdNumber(@PathVariable int id, @RequestBody String idNumber){
        UserDTO userDTO =userService.updateUserByIdNumber(idNumber,id);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping("/name/{age}")
    public ResponseEntity<List<UserDTO>> searchUserByName(@RequestBody String name,@PathVariable int age){
        List<UserDTO> userDTOS=userService.searchUserByName(name,age);
        return new ResponseEntity<>(userDTOS,HttpStatus.OK);
    }

    @GetMapping("/names/address")
    public ResponseEntity<List<UserDTO>> searchUserByName(@RequestBody String name,@RequestBody String address){
        List<UserDTO> userDTOS=userService.searchUserByName(name,address);
        return new ResponseEntity<>(userDTOS,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable("id") int id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("Deleted User",HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String>  deleteUsers(){
        userService.deleteUsers();
        return new ResponseEntity<>("Deleted Users",HttpStatus.OK);
    }

}
