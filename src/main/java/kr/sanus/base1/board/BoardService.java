package kr.sanus.base1.board;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public void save(Board board) {
    boardRepository.save(board);
  }

  public List<Board> findAll() {
    return boardRepository.findAll();
  }
}
