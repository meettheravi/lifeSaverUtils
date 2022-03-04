package utils.file.splitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This java program is to split a given csv file to smaller chunks of csv files with given number of lines.
 * File is expected to have a header and same header will be present in smaller chunks.
 * This code can be changed to include any extension of file.
 */
public class CsvSplit {

  private static final String FILE_PATH = "/Users/ravi_mishra1/Downloads/Compare Shipping Rates/New Rates 2022/Updated/Splitted/Parts/Automate/FedEx Standard Overnight US";
  private static final String CSV_EXT = ".csv";
  private static final String PART = " Part ";

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH + CSV_EXT))) {
      String header = br.readLine();
      String line;
      int linesRead = 1;
      int partFileToWrite = 1;
      BufferedWriter bw = new BufferedWriter(
          new FileWriter(FILE_PATH + PART + partFileToWrite + CSV_EXT));
      bw.write(header);
      while ((line = br.readLine()) != null) {
        if (linesRead == 200) {
          bw.flush();
          bw.close();
          partFileToWrite++;
          bw = new BufferedWriter(new FileWriter(FILE_PATH + PART + partFileToWrite + CSV_EXT));
          linesRead = 1;
          bw.write(header);
        }
        bw.newLine();
        bw.write(line);
        linesRead++;
      }
      bw.close();
    }
  }
}
