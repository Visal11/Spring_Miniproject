package com.example.demo.Service.Impl;

import com.example.demo.Model.Book;
import com.example.demo.Repositories.BookRepositories;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories)
    {
      this.bookRepositories=bookRepositories;
    }

    @Override
    public List<Book> getAll() {
        return bookRepositories.getAll();
    }

    @Override
    public Book findOne(Integer id) {
        return this.bookRepositories.findOne(id);
    }

    @Override
    public boolean update(Book book) {
        return this.bookRepositories.update(book);
    }

    @Override
    public boolean delete(Integer id) {
        return this.bookRepositories.delete(id);
    }

    @Override
    public boolean create(Book book) {
        return this.bookRepositories.create(book);
    }
}
