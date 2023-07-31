package kr.sanus.base1.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import kr.sanus.base1.common.dto.UploadFile;
import lombok.Data;

@Data
@Entity
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;

  private UploadFile attachFile;

}
