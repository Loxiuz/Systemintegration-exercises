package two.in_class.txt_file_parsing;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TxtService {

    private final char rowChar = '~';
    private final String lastChars = "#EOF#";
    private final char columnChar = '|';

    public List<Company> parseTxtFile(String filePath) throws FileNotFoundException {
        String companiesFromFile;
        try{
            FileInputStream inputStream = new FileInputStream(filePath);
            companiesFromFile = new String(inputStream.readAllBytes());
            IOUtils.closeQuietly(inputStream);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found at path: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        companiesFromFile = companiesFromFile.replace(lastChars, "");

        String[] rows = companiesFromFile.split(String.valueOf(rowChar));

        return Arrays.stream(rows)
                .map(row -> row.split("\\" + columnChar))
                .map(columns -> new Company(
                        columns[0],
                        columns[1],
                        columns[2],
                        columns[3],
                        columns[4],
                        columns[5],
                        columns[6]
                ))
                .toList();
    }
}
