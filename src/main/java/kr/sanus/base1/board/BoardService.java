package kr.sanus.base1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public void save(Board board) {
    boardRepository.save(board);
  }

  public Page<Board> findAll(int page) {
    Pageable pageable = PageRequest.of(page, 10);
    return boardRepository.findAll(pageable);
  }
}
