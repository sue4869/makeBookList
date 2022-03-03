package hello.hellospring.service;

import hello.hellospring.domain.Book;
import hello.hellospring.domain.Readinglist;
import hello.hellospring.mapper.BookMapper;
import hello.hellospring.mapper.ReadingMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    ReadingMapper readingMapper;

    /**책목록**/
    //목록 구성
    public List<Book> getAllBookList(){

        List<Book> bookList = bookMapper.getBookListAll();
        return bookList;
    }

    //책 추가
    public void insertBook(String title, String writer, int price) {
        bookMapper.insertBook(title,writer,price);
    }

    //책 삭제
    public Boolean deleteBookFromList(int bookid) {
        bookMapper.deleteBook(bookid);
        return true;
    }

    /**나의 책목록**/
    //목록 구성
    public List<Book> getMyBookList(int userid){

        //사용자가 읽은 책id 조회
        List<Integer> mybookid = readingMapper.getMyBookId(userid);

        //해당 id를 바탕으로 책 정보 가져오기.
        List<Book> mybookList = new ArrayList<>();
        for(int bookid : mybookid) {
            mybookList.add(bookMapper.getmyBookList(bookid));
        }
        return mybookList;
    }

    //책 삭제
    public Boolean deleteMyBook(int bookid){
        readingMapper.deleteReadingList(bookid);
        return true;
    }

    //책 추가
    public Boolean addMyBookList(int bookid){

        //책 목록에 이미 존재하는지 확인
        Optional<Readinglist> reading = readingMapper.checkPresent(bookid);
        if(reading.isPresent()){
            return true;
        }else{
            readingMapper.addReadingList(bookid);
            return false;
        }
    }

}
