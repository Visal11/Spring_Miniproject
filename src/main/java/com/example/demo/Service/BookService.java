package com.example.demo.Service;

import com.example.demo.Model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    public Book findOne(Integer id);

    public boolean update(Book book);

    public boolean delete(Integer id);

    public boolean create(Book book);

}
