package hello.hellospring.mapper;

import hello.hellospring.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    //책 전체 조회하기
    @Select("select * from book")
    public List<Book> getBookListAll();

    // 관련 bookid 의 책만 조회하여 책 제목 가져오기.
    @Select("select bookid,title,writer,price from book where bookID=#{bookid} ")
    public Book getmyBookList(@Param("bookid") int bookid);

    //책 추가 등록하기
    @Insert("insert into book(bookid,title,writer,price) values(0,#{title},#{writer},#{price})")
    public void insertBook(@Param("title") String title, @Param("writer") String writer,@Param("price") int price);

    //삭제
    @Delete("delete from book where bookid=#{bookId}")
    public void deleteBook( int bookId);
}
