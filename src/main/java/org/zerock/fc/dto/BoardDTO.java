package org.zerock.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Integer bno;
    private String title,content,writer;
    private int viewcount;
    private Timestamp updatedate,regdate;

    private List<AttachDTO> attachDTOList;//List 방을 만들어준다 ,이미지를 같이 사용하기 때문에 선언하는거같다.

    public void addAttach(AttachDTO attachDTO) {
        if(attachDTOList==null){//List방이 비어 있으면
            attachDTOList = new ArrayList<>();//ArrayList를 만들어줌
        }
        attachDTOList.add(attachDTO);//값을 더해준다.
    }
}
