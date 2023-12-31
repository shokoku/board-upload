package kr.sanus.base1.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

  Page<Board> findAll(Pageable pageable);
  Page<Board> findByTitleContaining(String title, Pageable pageable);
  Page<Board> findByContentContaining(String content, Pageable pageable);
}
