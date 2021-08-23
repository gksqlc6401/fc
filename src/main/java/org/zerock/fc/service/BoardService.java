package org.zerock.fc.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;


import java.util.List;

@Log4j2
public enum BoardService {
    INSTANCE;

    public List<BoardDTO> getList(PageDTO pageDTO) throws RuntimeException {

        log.info("BoardService getList............");
        log.info(pageDTO);

        return BoardDAO.INSTANCE.list(pageDTO);
    }

    public BoardDTO read(Integer bno) throws RuntimeException {
        log.info("BoardService read..................." + bno);

        return BoardDAO.INSTANCE.select(bno);
    }

    public Integer register(BoardDTO boardDTO) throws RuntimeException {
        return BoardDAO.INSTANCE.insert(boardDTO);
    }

    public void delete(Integer bno) throws RuntimeException{
        BoardDAO.INSTANCE.delete(bno);
    }

    public void update(BoardDTO dto) throws RuntimeException{

        BoardDAO.INSTANCE.update(dto);
    }

}
