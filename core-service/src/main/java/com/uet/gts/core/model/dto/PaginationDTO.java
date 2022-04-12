package com.uet.gts.core.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO {
    private int totalPages;
    private int totalElements;
    private int size;
    private int pageNumber;
    private boolean first;
    private boolean last;
}
