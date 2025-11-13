package com.java.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by kienng on 20200213
 * Custom page by format Example:
 * "content": [
 * {
 * "id": 511,
 * "label": "label666"
 * }
 * ],
 * "page": {
 * "totalElements": 11,
 * "last": false,
 * "totalPages": 2,
 * "size": 10,
 * "number": 0,
 * "first": true,
 * "numberOfElements": 10
 * }
 *
 * @param <T> : list element
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageCustomDto<T> {

    private List<T> content;
    private PageDto page;
    private String url; //For paging : url

    /**
     * @param elements     : List DTO
     * @param page
     * @param size
     * @param totalElement : Total record
     */
    public PageCustomDto(List<T> elements, Integer page, Integer size, Long totalElement) {
        this.content = elements;
        this.page = new PageDto(page, size, totalElement, elements.size());
    }

    public PageCustomDto(List<T> elements, Pageable pageable, Long totalElement) {
        this.content = elements;
        this.page = new PageDto(pageable.getPageNumber() + 1, pageable.getPageSize(), totalElement, elements.size());
    }

    public PageCustomDto(Page<T> page) {
        //TODO check page null ?
        this.content = page.get().collect(Collectors.toList());
        this.page = new PageDto(page.getPageable().getPageNumber() + 1, page.getPageable().getPageSize(), page.getTotalElements(), this.content != null ? this.content.size() : 0);
    }
}
