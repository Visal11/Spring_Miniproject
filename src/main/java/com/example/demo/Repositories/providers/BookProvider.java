package com.example.demo.Repositories.providers;

import com.example.demo.Model.Book;
import org.apache.ibatis.jdbc.SQL;

public class BookProvider {
    public String getAllProvider(){
        return new SQL(){{
            SELECT("*");
            FROM("tb_book");
            ORDER_BY("id desc");
        }}.toString();
    }
    public String createProvider(Book book){
      return new SQL(){{
          INSERT_INTO("tb_book");
          VALUES("title", "#{tile}");
          VALUES("author", "#{author}");
          VALUES("publisher", "#{publisher}");
          VALUES("thumbnail", "#{thumbnail}");
      }}.toString();
    }
}
