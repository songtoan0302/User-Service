package org.ptit.controller;

import liquibase.pro.packaged.*;
import lombok.RequiredArgsConstructor;
import org.ptit.dto.UserDTO;
import org.ptit.model.User;
import org.ptit.service.UserService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<UserDTO>> listUsers(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "1") int size) {
       Page<UserDTO> listUserPage = userService.getUserPage(page, size);
        return new ResponseEntity<>(listUserPage, HttpStatus.OK);
    }
    @GetMapping("/age")
    public ResponseEntity<Page<UserDTO>> findByAge(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "1") int size,
                                             @RequestParam(defaultValue = "0") int age) {
        Page<UserDTO> listUserPage = userService.findByAge(age,page, size);
        return new ResponseEntity<>(listUserPage, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userDTOCreated=userService.addUser(userDTO);
        return new ResponseEntity<>(userDTOCreated,HttpStatus.CREATED);
    }
     @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,
                                              @PathVariable("id") int id){
        UserDTO userDTOUpdated=userService.updateUser(userDTO,id);
        return new ResponseEntity<>(userDTOUpdated,HttpStatus.OK);
     }

     @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateIdNumber(@PathVariable("id") int id,
                                                  @RequestBody String idNumber){
        UserDTO userDTO =userService.updateByIdNumber(idNumber,id);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> findByNameFilterAddressAndAge(String name,
                                                          @RequestParam(defaultValue = "") String address,
                                                          @RequestParam(defaultValue = "") Integer age){
        List<UserDTO> userDTOS=userService.findByName(name,address, age);
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
