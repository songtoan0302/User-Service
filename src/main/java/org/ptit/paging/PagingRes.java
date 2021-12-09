package org.ptit.paging;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagingRes<T> {//PagingResult
    private List<T> pageData;
    private int pageNo;
    private int pageSize;
    private long total;

    private PagingRes(Page<T> page) {
        this.pageData = page.getContent();
        this.total = page.getTotalElements();
        this.pageNo = page.getNumber() + 1;
        this.pageSize = page.getSize();
    }

    public static <H> PagingRes<H> of(Page<H> page) {
        return new PagingRes<>(page);
    }
}
