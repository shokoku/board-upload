package kr.sanus.base1.board;

import lombok.RequiredArgsConstructor;
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
  public String list(Model model, @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "") String type,
                                  @RequestParam(defaultValue = "") String kw) {
    model.addAttribute("paging", boardService.findAll(page-1, type, kw));
    model.addAttribute("kw", kw);
    model.addAttribute("type", type);
    return "board/list";
  }

  @GetMapping("/test")
  public String test() {

    for (int i = 1; i < 100; i++) {
      Board board = new Board();
      board.setTitle("테스트 " + i + " 제목");
      board.setContent("테스트 " + i + " 내용");
      boardService.save(board);
    }
    return "redirect:/";
  }
}
