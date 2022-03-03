package hello.hellospring.mapper;

import hello.hellospring.domain.Book;
import hello.hellospring.domain.Readinglist;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReadingMapper {

    // 사용자 id를 통해 사용자가 읽은 책 id 찾기
    @Select("select bookID from readinglist where userID=#{userid}")
    public List<Integer> getMyBookId(@Param("userid") int userid);

    //책 id가 reading테이블에 있는지 확인 하기
    @Select("select * from readinglist where bookID=#{bookid}")
    public Optional<Readinglist> checkPresent(@Param("bookid") int bookid);

    //해당 책id를 reading 테이블에 추가하기
    @Insert("insert into readinglist(readingID,userID,bookID) values(0,1,#{bookid}) ")
    public void addReadingList(@Param("bookid") int bookid);

    //삭제
    @Delete("delete from readinglist where bookID=#{bookid}")
    void deleteReadingList(int bookid);
}
