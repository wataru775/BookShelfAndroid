package org.mmpp.bookshelf.util;

import java.io.IOException;

/**
 * JSONParserでの例外
 * Created by wataru on 2016/10/19.
 */
public class JSONParseException extends Exception{
    /**
     * メッセージを指定したJSONParserの例外コンストラクタ
     * @param message メッセージ
     */
    public JSONParseException(String message) {
        super(message);
    }

    /**
     * メッセージと例外を合わせたJSONParser例外コンストラクタ
     * @param message メッセージ
     * @param cause スルーサブル
     */
    public JSONParseException(String message, Throwable cause) {
        super(message,cause);
    }
}
