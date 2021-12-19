package org.ptit.paging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Min;

@Data
public class PagingReq {//PagingRequest
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUM = 1;

    @Min(1)
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    @Min(1)
    private Integer pageNum = DEFAULT_PAGE_NUM;


    @JsonIgnore
    public Pageable makePageable() {
        return PageRequest.of(getPageNum() - 1, getPageSize());
    }
    @JsonIgnore
    public Pageable nextPage(int page){
        return  PageRequest.of(getPageNum()+page-2,getPageSize());
    }
}
