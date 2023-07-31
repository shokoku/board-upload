package kr.sanus.base1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return "redirect:/";

  }
}
