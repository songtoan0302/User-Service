package org.ptit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NonNull
    private String name;
    @NonNull
    private  String idNumber;
    @NotBlank
    private int age;
    @NonNull
    private  String address;
}
