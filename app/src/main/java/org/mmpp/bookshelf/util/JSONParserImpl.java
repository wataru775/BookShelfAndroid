package org.mmpp.bookshelf.util;

import net.arnx.jsonic.JSON;

import org.mmpp.bookshelf.*;
import org.mmpp.bookshelf.Book;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * JSON解析サービス実装
 * Created by wataru on 2016/10/18.
 */
public class JSONParserImpl implements JSONPaser {
    @Override
    public List<Book> parseBooks(InputStream inputStream) throws JSONParseException{
        if(inputStream == null){
            throw new JSONParseException("入力ストリームが存在しません");
        }
        // InputSteamからJSON取得用の書籍情報一覧を取得します
        Response response;
        try {
            response = JSON.decode(inputStream, Response.class);
        } catch (IOException e) {
            throw new JSONParseException("JSON解析中にエラーが発生しました",e);
        }
        // 実行結果の判断
        if(! response.getIsValid().equals("TRUE")){
            throw new JSONParseException("正しくサービスが実行されませんでした \n"+response.ERROR);
        }
        // JSON取得用の書籍情報一覧から書籍情報一覧を生成します
        java.util.List<Book> books = new java.util.LinkedList<Book>();
        for(org.mmpp.bookshelf.util.Book jsonBook : response.getResult()){
            books.add(castJSONBookToBook(jsonBook));
        }
        return null;
    }

    /**
     * JSON書籍情報を書籍情報に変換します
     * @param jsonBook JSON書籍情報
     * @return 書籍情報
     */
    public Book castJSONBookToBook(org.mmpp.bookshelf.util.Book jsonBook){
        Book book = new Book();
        book.id = jsonBook.id;
        book.title = jsonBook.title;
        book.author = jsonBook.author;
        book.barcode = jsonBook.barcode;
        book.publisher = jsonBook.publisher;
        book.publisher_code = jsonBook.publisher_code;
        book.magazine = jsonBook.magazine;
        book.m_code = jsonBook.m_code;
        book.release_date = jsonBook.release_date;
        book.amazon_id = jsonBook.amazon_id;
        book.amazon_image = jsonBook.amazon_image;
        return book;
    }
}
