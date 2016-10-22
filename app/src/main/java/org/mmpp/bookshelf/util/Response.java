package org.mmpp.bookshelf.util;

import org.mmpp.bookshelf.util.Book;

import java.util.List;
/**
 * JSON通信の結果を格納する変数
 * Created by wataru on 2016/10/18.
 */
public class Response {
    // 検索結果格納変数 (TRUE / FALSE)
    public String isValid;

    public List<Book> RESULT;
    public String ERROR;
    /**
     * 通信結果
     * @return 通信結果(TRUE / FALSE)
     */
    public String getIsValid(){
        return isValid;
    }

    public List<Book> getResult() {
        return RESULT;
    }
}
