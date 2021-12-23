package org.ptit.controller;

import lombok.RequiredArgsConstructor;
import org.ptit.dto.UserDTO;
import org.ptit.paging.PagingReq;
import org.ptit.paging.PagingRes;
import org.ptit.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int id) {
        UserDTO userDTO = userService.getUser(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagingRes<UserDTO>> listUsers(@Validated PagingReq pagingReq) {
        Page<UserDTO> listUserPage = userService.getUsers(pagingReq.makePageable());

        return new ResponseEntity<>(PagingRes.of(listUserPage), HttpStatus.OK);
    }

    @GetMapping("/search/age")
    public ResponseEntity<PagingRes<UserDTO>> listUsersByAge(@Validated PagingReq pagingReq,
                                                             @RequestParam int age) {
        Page<UserDTO> listUserPage = userService.listUsersByAge(pagingReq.makePageable(), age);
        return new ResponseEntity<>(PagingRes.of(listUserPage), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO userDTO) {
        UserDTO userDTOCreated = userService.addUser(userDTO);
        return new ResponseEntity<>(userDTOCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Validated @RequestBody UserDTO userDTO,
                                              @PathVariable("id") int id) {
        UserDTO userDTOUpdated = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(userDTOUpdated, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateIdNumber(@PathVariable("id") int id,
                                                  @RequestBody String idNumber) {
        UserDTO userDTO = userService.updateByIdNumber(idNumber, id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<UserDTO>> findByNameFilterAddressAndAge(@RequestParam String name,
                                                                       @RequestParam(defaultValue = "") String address,
                                                                       @RequestParam(defaultValue = "") Integer age) {
        List<UserDTO> userDTOS = userService.findByName(name, address, age);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("Deleted User", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUsers() {
        userService.deleteUsers();
        return new ResponseEntity<>("Deleted Users", HttpStatus.OK);
    }

}
