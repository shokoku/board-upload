package kr.sanus.base1.common.dto;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UploadFile {
  private String uploadFileName;
  private String storeFileName;

  public UploadFile(String uploadFileName, String storeFileName) {
    this.uploadFileName = uploadFileName;
    this.storeFileName = storeFileName;
  }
  public UploadFile() {

  }
}