package org.ptit.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.ptit.dto.UserDTO;
import org.ptit.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public <T, H> Page<T> mapPage(Page<H> inputData, Class<T> clazz) {
        return inputData.map(i -> modelMapper.map(i, clazz));
    }

    public <T, H> List<T> mapList(List<H> inputData, Class<T> clazz) {
        return CollectionUtils.isEmpty(inputData) ?
                Collections.emptyList() :
                inputData.stream()
                        .map(i -> modelMapper.map(i, clazz))
                        .collect(Collectors.toList());
    }

}
