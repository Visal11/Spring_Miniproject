package com.example.demo.Controller;

import com.example.demo.Model.Book;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class BookController {

   private BookService bookService;

   @Autowired
   public BookController(BookService bookService)
   {
     this.bookService=bookService;
   }

   @GetMapping({"/index","/","/home"})
    public String getIndex(ModelMap model)
   {
     List<Book> bookList= this.bookService.getAll();
     model.addAttribute("books",bookList);
     return "index";
   }

   @GetMapping("view/{id}")
    public String view(@PathVariable Integer id,Model model)
   {
       Book book=this.bookService.findOne(id);
       model.addAttribute("book",book);
//       System.out.println("ID:"+ id);
       return "view-detail";
   }

   @GetMapping ("update/{book_id}")
    public String update(@PathVariable Integer book_id,ModelMap modelMap)
   {
       System.out.println("Update ID:"+ book_id);
       Book book=this.bookService.findOne(book_id);
       modelMap.addAttribute("book",book);
       return "update";
   }

   @PostMapping("update/submit")
   public String updateSubmit(@ModelAttribute Book book, ModelMap modelMap,MultipartFile file)
   {
       if(file==null){
           return null;
       }
       File path=new File("/btb");

       if(!path.exists())
           path.mkdirs();

       String filename= file.getOriginalFilename();
       String extension= filename.substring(filename.lastIndexOf('.')+1);
       filename= UUID.randomUUID()+"."+extension;
       try{
           Files.copy(file.getInputStream(), Paths.get("/btb",filename));
       }catch (IOException e){

       }

       if(!file.isEmpty()){
           book.setThumbnail("/images-btb/"+filename);
       }

//     System.out.println(book);
       bookService.update(book);
       modelMap.addAttribute("book",book);
       return "redirect:/index";
   }

   @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
   {
       System.out.println(id);
       this.bookService.delete(id);
       return "redirect:/index";
   }

   @GetMapping("/create")
    public String create(Model model)
   {
     model.addAttribute("book",new Book());
     return "createbook";
   }

   @PostMapping("/create/submit")
    public String create(@Valid Book book, BindingResult bindingResult, MultipartFile file)
    //BindingResult= use for check it correct or not
   {
       if(file==null){
           return null;
       }

       File path=new File("/btb");

       if(!path.exists())
        path.mkdirs();

       String filename= file.getOriginalFilename();
       String extension= filename.substring(filename.lastIndexOf('.')+1);
       filename= UUID.randomUUID()+"."+extension;
       try{
           Files.copy(file.getInputStream(), Paths.get("/btb",filename));
       }catch (IOException e){

       }

       book.setThumbnail("/images-btb/"+filename);

       if(bindingResult.hasErrors())
       {
         return "createbook";
       }

       System.out.println(book);
       this.bookService.create(book);
       return "redirect:/index";
   }
 }

