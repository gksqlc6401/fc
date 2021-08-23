package org.zerock.fc.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.zerock.fc.dto.AttachDTO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardDAO {
    INSTANCE;



    private static final String PREFIX ="org.zerock.fc.dao.BoardMapper";

    public Integer insert(BoardDTO boardDTO)throws RuntimeException{

        Integer bno= null;//bno초기화

        try(SqlSession session = MyBatiesLoader.INSTANCE.getFactory().openSession(true)) {
            session.insert(PREFIX+".insert", boardDTO);//파라미터 값 받아서 insert 해준다.
            bno = boardDTO.getBno();//BoardDTO에서 bno값 빨아온다

            List<AttachDTO> attachDTOList = boardDTO.getAttachDTOList();//BoardDTO에 있는 attachDTOList를 불러와서 attachList에 담아준다

            if(attachDTOList != null && attachDTOList.size() > 0) {//첨부파일이 있는지 없는지 체크
                //forEach는 파이널이여서 안됨
                for (AttachDTO attachDTO : attachDTOList) {//하위에있는 변수를 넣으면 거기에있는 변수를 다 출력한다.
                    attachDTO.setBno(bno);//26번줄에서 뽑은 bno값을 attachDTO에 넣어준다
                    session.insert(PREFIX + ".insertAttach", attachDTO);//반복해서 추가한다
                }
            }
            session.commit();//저장

        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return bno;
    }

    public BoardDTO select(Integer bno)throws RuntimeException {
        BoardDTO dto = null;
        try(SqlSession session = MyBatiesLoader.INSTANCE.getFactory().openSession(true)) {
            dto=session.selectOne(PREFIX+".select", bno);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return dto;
    }

    public List<BoardDTO> list(PageDTO pageDTO)throws RuntimeException {
        List<BoardDTO> list = null;

        try(SqlSession session = MyBatiesLoader.INSTANCE.getFactory().openSession(true)) {
            list = session.selectList(PREFIX+".list", pageDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public void delete(Integer bno)throws RuntimeException {

        try(SqlSession session = MyBatiesLoader.INSTANCE.getFactory().openSession(true)) {
            session.delete(PREFIX+".delete",bno);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(BoardDTO dto)throws RuntimeException{

        try(SqlSession session = MyBatiesLoader.INSTANCE.getFactory().openSession(true)) {
            session.update(PREFIX+".update",dto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
