package cn.mapway.maven;

import java.io.File;

public class FileParameter {
  @SuppressWarnings("unused")
  private File path;

  @SuppressWarnings("unused")
  private String[] lines;

  public File getPath() {
    return path;
  }

  public String[] getLines() {
    return lines;
  }
}
