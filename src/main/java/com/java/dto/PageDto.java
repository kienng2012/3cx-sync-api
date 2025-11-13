package com.java.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kienng 2019/02/13
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto {
    private Long totalElements;
    private Boolean last;
    private Integer totalPages;
    private Integer size;
    private Integer number;
    private Boolean first;
    private Integer numberOfElements;
    private List<Integer> relatedPages;

    /**
     * @param page
     * @param size
     * @param count            = totalElements
     * @param numberOfElements = dtoList.size()
     */
    public PageDto(Integer page, Integer size, Long count, Integer numberOfElements) {
        if (count == 0) {
            //If count= null ==> Get default;
            this.size = size;
            this.totalElements = 0L;
            this.totalPages = 0;
            this.numberOfElements = 0;
            this.number = 0;
            this.first = false; //number = 0 ==> first page
            this.last = false;
        } else {
            this.size = size;
            this.totalElements = count;
            this.totalPages = Math.toIntExact((count % size) == 0 ? (count / size) : ((count / size) + 1));
            this.numberOfElements = numberOfElements;
            this.number = page - 1;
            this.first = this.number == 0; //number = 0 ==> first page
            this.last = count <= page * size; // count <= (page * size) ==> last page

            //Add relatedPage to display paging
            this.relatedPages = combineRelatedPage(number + 1, totalPages);
        }
    }

    /**
     * currentPage = number+1
     *
     * @param currentPage
     * @return
     */
    private List<Integer> get2PreviousByCurrentPage(int currentPage) {
        List<Integer> previousPages = new ArrayList<>();
        if (currentPage > 1) {
            if (currentPage == 2) {
                previousPages.add(1);
            } else {
                previousPages.add(currentPage - 2);
                previousPages.add(currentPage - 1);
            }
        }
        return previousPages;
    }

    /**
     * currentPage = number+1
     *
     * @param currentPage
     * @return
     */
    private List<Integer> get2NextByCurrentPage(int currentPage, int totalPages) {
        List<Integer> nextPages = new ArrayList<>();
        if (currentPage < totalPages) {
            if (currentPage == totalPages - 1) {
                nextPages.add(totalPages);
            } else {
                nextPages.add(currentPage + 1);
                nextPages.add(currentPage + 2);
            }
        }
        return nextPages;
    }

    private List<Integer> combineRelatedPage(int currentPage, int totalPages) {
        List<Integer> currentPages = new ArrayList<>();
        currentPages.add(currentPage);
        Stream<Integer> combineRelatePage = Stream.concat(Stream.concat(get2PreviousByCurrentPage(currentPage).stream(), currentPages.stream()), get2NextByCurrentPage(currentPage, totalPages).stream());
        return combineRelatePage.collect(Collectors.toList());
    }

}
