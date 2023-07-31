package kr.sanus.base1.board;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardSaveForm {

  private String title;
  private String content;
  private MultipartFile attachFile;
  private List<MultipartFile> imageFiles;
}
