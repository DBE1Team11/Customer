package com.fastcampus.customer.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model model, HttpServletRequest request) {
        System.out.println("밖 page: "+page+" pageSize: "+pageSize); //page랑 pageSize가 null이 들어온다.
        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
        if (page == null) page = 0;
        if (pageSize == null) pageSize = 10;

        try {
            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page) * pageSize);// + 되어있었음 * 로 바꿈
            map.put("pageSize", pageSize);
            List<BoardDto> list = boardService.getPage(map);
            model.addAttribute("list", list);
            model.addAttribute("ph", pageHandler);
            model.addAttribute("page",page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }
    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model model) {
        try{
            BoardDto boardDto = boardService.read(bno);
            System.out.println("bno = " + bno);
            System.out.println("content = " + boardDto.getContent());
            model.addAttribute(boardDto);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "board";
    }
    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("mode", "new");
        return "board";
    }
    @GetMapping("/modify")
    public String modify(Integer bno, Model model) {
        model.addAttribute("mode", "new");
        return "board";
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model model, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        try{
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(bno, writer);

            if(rowCnt != 1)
                throw  new Exception("board remove fail");
            rattr.addFlashAttribute("msg", "DEL_OK");
        }catch (Exception e){
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session, Model model, RedirectAttributes rattr) {
        System.out.println(boardDto.getBno());
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.write(boardDto);

            if(rowCnt != 1)
                throw  new Exception("board write fail");
            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(boardDto);
            rattr.addFlashAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, HttpSession session, Model model, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        System.out.println(boardDto.getBno() + " " + boardDto.getTitle() + " " + boardDto.getContent() + "변경전");

        boardDto.setTitle(boardDto.getTitle());
        boardDto.setContent(boardDto.getContent());

        System.out.println(boardDto.getBno() + " " + boardDto.getTitle() + " " + boardDto.getContent() + "변경후");
        try {
            int rowCnt = boardService.modify(boardDto);
            if(rowCnt != 1)
                throw  new Exception("board modify fail");
            rattr.addFlashAttribute("msg", "MIF_OK");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(boardDto);
            rattr.addFlashAttribute("msg", "MIF_ERR");
        }
        return "redirect:/board/list";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id") != null;
    }
}