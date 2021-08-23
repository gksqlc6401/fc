package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.dto.AttachDTO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;

@Log4j2
public class BoardDAOTest {

    @Test
    public void testInsertWithAttach() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Test")
                .writer("user1")
                .build();

        //bno를 따지 않으면 어떤 게시물인지 알 수 없음. 따서 DB에 넣어야함
        //Integer bno = BoardDAO.INSTANCE.insert(boardDTO);

        //첨부파일 여러장 불러서 저장하기 (가짜 데이터 생성)
        for (int i = 0; i < 3; i++) {
            AttachDTO attachDTO = AttachDTO.builder()
                    .fname("file" + i + ".jpg")
                    .savename(System.currentTimeMillis() + "_file" + i + ".jpg")
                    .imgyn(true)
                    .build();
            boardDTO.addAttach(attachDTO);
        } //db에 바로 insert 가능

        //------------------Controller 역할 여기까지 ~ ------------------------

        log.info("===========================");
        log.info(boardDTO);

        //bno가 없는 상태에서 데이터 수집 -> bno는 insert된 다음에 생성 -> 첨부파일에 bno 설정 -> 그다음 insert 또 해주고 데이터 넣기

        BoardDAO.INSTANCE.insert(boardDTO);
        //insert가 몇 번 일어났는지 확인 해보기

    }

    @Test
    public void testInsert() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Test")
                .writer("user1")
                .build();

        BoardDAO.INSTANCE.insert(boardDTO);

        log.info("================");
        log.info(boardDTO);
    }

    @Test
    public void testSelect() {
        log.info(BoardDAO.INSTANCE.select(3));
    }

    @Test
    public void testList() {
        PageDTO pageDTO = PageDTO.builder().page(3).build();

        BoardDAO.INSTANCE.list(pageDTO).forEach(boardDTO -> log.info(boardDTO));
    }

    @Test
    public void testDelete() {
        BoardDAO.INSTANCE.delete(1);
    }

    @Test
    public void testUpdate() {
        BoardDTO boardDTO = BoardDTO.builder().bno(3).title("update").content("content").build();

        BoardDAO.INSTANCE.update(boardDTO);
    }
}
