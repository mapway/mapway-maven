package cn.mapway.maven;

import java.io.File;

/**
 * The type File parameter.
 */
public class FileParameter {
  @SuppressWarnings("unused")
  private File path;

  @SuppressWarnings("unused")
  private String[] lines;

    /**
     * Gets path.
     *
     * @return the path
     */
    public File getPath() {
    return path;
  }

    /**
     * Get lines string [ ].
     *
     * @return the string [ ]
     */
    public String[] getLines() {
    return lines;
  }
}
