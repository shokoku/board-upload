package kr.sanus.base1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
  public String save(@ModelAttribute("board") BoardSaveForm form) {
    boardService.save(form);
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

  @GetMapping("/{id}")
  public String detail(@PathVariable Long id, Model model) {
    model.addAttribute("board", boardService.findById(id));
    return "board/detail";
  }

  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable Long id, Model model) {
    model.addAttribute("board", boardService.findById(id));
    return "board/editForm";
  }

  @PostMapping("/{id}/edit")
  public String edit(@PathVariable Long id, Board board) {
    boardService.edit(id, board);
    return "redirect:/board/" + id;
  }

  @GetMapping("/{id}/delete")
  public String delete(@PathVariable Long id) {
    boardService.delete(id);
    return "redirect:/board";
  }

  @GetMapping("/attach/{id}")
  public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) {
    return boardService.getAttach(id);
  }


}
