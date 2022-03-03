package hello.bookListForPractice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Readinglist {

    private int readingID;
    private int userID;
    private int bookID;

}
