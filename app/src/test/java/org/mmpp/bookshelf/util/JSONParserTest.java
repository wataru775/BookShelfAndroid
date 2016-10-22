package org.mmpp.bookshelf.util;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.arnx.jsonic.JSON;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * Created by wataru on 2016/10/18.
 */

public class JSONParserTest {
    /**
     * JSONNicの学習用
     * @throws FileNotFoundException
     */
    @Test
    public void testJSONICTest() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("JSONSample.json");
        File file = new File(url.getPath());

        InputStream ist = null;
        try {
            ist = (InputStream) (new FileInputStream(file));
            Response response = JSON.decode(ist, Response.class);

            assertEquals("結果が正しく取得できること","TRUE",response.getIsValid());

            // 個別取得
            Book book = response.getResult().get(0);
            assertEquals("ID取得","9",book.id);
            assertEquals("タイトルの取得","ぷにぷにぽえみぃ―小林伝説",book.title);
            assertEquals("著者情報の取得","黒田 洋介(著),大越 秀武(イラスト)",book.author);
            assertEquals("Barcodeの取得","9784044260019",book.barcode);
            assertEquals("表紙画像の取得","/service/bookdb/bookcover/9784044260019.jpg",book.amazon_image);
            assertEquals("シリーズの取得","角川スニーカー文庫",book.magazine);
            assertEquals("出版社の取得","角川書店",book.publisher);
            assertEquals("シリーズコードの取得","",book.m_code);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ist.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
