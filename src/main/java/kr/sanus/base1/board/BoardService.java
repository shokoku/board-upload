package kr.sanus.base1.board;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import kr.sanus.base1.common.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final FileStore fileStore;

  public void save(BoardSaveForm form) {
    Board board = new Board();
    board.setTitle(form.getTitle());
    board.setContent(form.getContent());
    try {
      board.setAttachFile(fileStore.storeFile(form.getAttachFile()));
      board.setImageFiles(fileStore.storeFiles(form.getImageFiles()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    boardRepository.save(board);
  }

  public Page<Board> findAll(int page, String type, String kw) {
    Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());

    if (kw != null && !kw.isEmpty()) {
      if (type.equals("title")) {
        return boardRepository.findByTitleContaining(kw, pageable);
      } else if (type.equals("content")) {
        return boardRepository.findByContentContaining(kw, pageable);
      }
    }
    return boardRepository.findAll(pageable);
  }

  public Board findById(Long id) {
    Optional<Board> optionalBoard = boardRepository.findById(id);
    if (optionalBoard.isPresent()) {
      return optionalBoard.get();
    }
    throw new RuntimeException();
  }

  public void edit(Long id, Board board) {
    Optional<Board> optionalBoard = boardRepository.findById(id);
    if (optionalBoard.isPresent()) {
      optionalBoard.get().setTitle(board.getTitle());
      optionalBoard.get().setContent(board.getContent());
      boardRepository.save(optionalBoard.get());
    }
  }

  public void delete(Long id) {
    boardRepository.deleteById(id);

  }

  public ResponseEntity<Resource> getAttach(Long id) {
    Board board = findById(id);
    String storeFileName = board.getAttachFile().getStoreFileName();
    String uploadFileName = board.getAttachFile().getUploadFileName();

    UrlResource resource = null;
    try {
      resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }

    String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
    String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
        .body(resource);
  }
}
