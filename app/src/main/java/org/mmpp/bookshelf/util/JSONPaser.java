package org.mmpp.bookshelf.util;

import java.io.InputStream;
import java.util.List;

/**
 * JSON解析サービスインターフェイス
 * Created by wataru on 2016/10/18.
 */
public interface JSONPaser {

    /**
     * InputStreamから書籍情報一覧を取得します
     * @param inputStream JSON入力ストリーム
     * @return 書籍情報一覧
     */
    public List<org.mmpp.bookshelf.Book> parseBooks(InputStream inputStream) throws JSONParseException;
}
