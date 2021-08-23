package org.zerock.fc.controller.sub;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.annotations.Controller;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;
import org.zerock.fc.dto.PageMaker;
import org.zerock.fc.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@Controller(value = "/board")
public class BoardController {

    private Integer getInt(String str) {
        try {
            int values = Integer.parseInt(str);//문자열은 int값으로 형변환
            if( values <= 0){//페이지의 파라미터 값이 0보다 작으면 생기는 에러를 막아준다.
                return null;//0보다 작으면 null(빈값)로 리턴
            }
            return values;
        }catch (Exception e){
            return null;//Integer은 return값을 null로한다.
        }
    }
    @GetMapping(value = "/board/register.do")
    public String registerGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        return "board/register";
    }

    @PostMapping(value = "/board/register.do")
    public String registerPost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post....do");

        BoardDTO boardDTO = BoardDTO.builder()
                .title(request.getParameter("title"))//값을 빼서 넣어줌
                .content(request.getParameter("content"))
                .writer(request.getParameter("writer"))
                .build();

        Integer bno = BoardService.INSTANCE.register(boardDTO);


        return "re:/board/list.do";

    }

    @GetMapping(value = "/board/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("---------------------------");

        Integer page = getInt(request.getParameter("page"));//String값으로 가져와서 getInt메소드로 Integer로 형변환해줌.
        Integer size = getInt(request.getParameter("size"));//String값으로 가져와서 getInt메소드로 Integer로 형변환해줌.

        PageDTO pageDTO = PageDTO.builder().build();

        if(page != null) {pageDTO.setPage(page);};//만약 페이지가 null이 아니면 pageDTO에서 page를 넣어준다
        if(size != null) {pageDTO.setSize(size);};//만약 페이지가 null이 아니면 pageDTO에서 size를 넣어준다

        log.info("======================");
        log.info(pageDTO);

        List<BoardDTO> dtoList = BoardService.INSTANCE.getList(pageDTO);

        log.info("======================================");
        log.info(dtoList);

        request.setAttribute("dtoList",dtoList);

        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(),1230);

        request.setAttribute("pageMaker",pageMaker);

        return "board/list";
    }

    @GetMapping(value = "/board/read.do")
    public String read(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));

        //page, size 사용해서 BoardDTO에 담아주기
        PageDTO pageDTO = PageDTO.builder().build();

        if(page != null) { pageDTO.setPage(page); }
        if(size != null) { pageDTO.setSize(size); }

        //페이지 가져오기
        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("boardDTO", boardDTO);
        request.setAttribute("pageDTO", pageDTO);

        return "board/read";

    }

    @GetMapping(value = "/board/update.do")
    public String updateGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Integer bno = Integer.parseInt(request.getParameter("bno"));

        request.setAttribute("bno", bno);

        return "board/update";
    }

    @PostMapping(value = "/board/update.do")
    public String updatePost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Integer bno = Integer.parseInt(request.getParameter("bno"));//값을 빼서
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDTO dto = BoardDTO.builder()
                .bno(bno)//넣어줌
                .title(title)
                .content(content)
                .build();

        BoardService.INSTANCE.update(dto);//쿼리무 돌림

        return "re:/board/list.do";
    }

    @PostMapping(value = "/board/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response)throws Exception{

        Integer bno = Integer.parseInt(request.getParameter("bno"));

        BoardDTO dto = BoardDTO.builder().bno(bno).build();

        BoardService.INSTANCE.delete(bno);

        return "re:/board/list.do";
    }



}