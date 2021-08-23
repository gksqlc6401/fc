package org.zerock.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageDTO {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    public int getSkip() {
        //1페이지 -0개를 건너뜀(skip 할게 없음)
        //2페이지 - 10개를 건너뜀
        //3페이지 - 20개를 건너뜀
        return (this.page -1) * size;
    }

}
