package br.org.davi.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.org.davi.data.vo.v1.BookVO;
import br.org.davi.model.Books;

public class MockBook {


    public Books mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public Books mockEntity(Integer number) {
        Books book = new Books();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

}