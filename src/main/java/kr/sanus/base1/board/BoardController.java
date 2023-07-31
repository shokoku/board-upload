package kr.sanus.base1.board;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/add")
  public String saveForm(Model model) {
    model.addAttribute("board", new Board());
    return "board/saveForm";
  }

  @PostMapping("/add")
  public String save(Board board) {
    boardService.save(board);
    return "redirect:/board";
  }

  @GetMapping()
  public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
    System.out.println("page = " + page);
    Page<Board> paging = boardService.findAll(page-1);
    model.addAttribute("paging", paging);
    return "board/list";
  }
}
