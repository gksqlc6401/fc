package org.zerock.fc.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {

    private int start, end, page, size, total;
    private boolean prev, next;

    public PageMaker(int page, int size, int total){

        this.page = page;
        this.size = size;
        this.total = total;

        //마지막 페이지 계산
        end = (int)(Math.ceil(page/10.0)*10);
        //시작 페이지 계산
        start = end - 9;
        //이전 계산
        prev = start > 1;
        //다음 계산
        next = (total/(double)size) > end;

//        if(end * size > total) {
//            end = (int)(Math.ceil(total)/(double)size);//페이지가 10이 넘어가면 end값이 커져서 total값보다 커진다 그래서 next가 false로 나온다.
//        }

//        end = end * size > total) ? (int)(Math.ceil(total)/(double)size) : end;
    }
}
