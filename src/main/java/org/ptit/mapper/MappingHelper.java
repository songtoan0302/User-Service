package org.ptit.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MappingHelper {
    private final ModelMapper modelMapper;



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

    public <T,H> T map(H source,Class<T> destination){
        return modelMapper.map(source,destination);
    }

}
