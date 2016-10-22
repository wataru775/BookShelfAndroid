package org.mmpp.bookshelfapp;

import android.util.Log;

import net.arnx.jsonic.JSON;

import org.mmpp.bookshelf.util.Book;
import org.mmpp.bookshelf.util.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * JSONに通信してアダプタに結果を入れるタスククラス
 */
public class JSONConnectTask extends AbstractWebAccessTask<Object, Object, Response> {
    /** ログタグ */
    private final static String TAG = "JSONConnectTask";
    /** ViewPagerのアダプタ */
    private ViewPagerAdapter viewPagerAdapter;
    /** JSONの存在するurl */
    private URL url;

    /**
     * デフォルトコンストラクタ
     *
     * @param url JSONのアドレス
     * @param adapter 結果格納アダプタ
     */
    public JSONConnectTask(URL url, ViewPagerAdapter adapter){
        super();
        this.viewPagerAdapter = adapter;
        this.url = url;
    }
    @Override
    protected Response doInBackground(Object[] objects) {

        try{
            // JSONにアクセスします
            Response response = webaccess(this.url);
            // 結果を返却
            return response;
        } catch (IOException e) {
            Log.d(TAG, " : " + e.getMessage(), e);
            return null;
        }finally {

        }

    }
    @Override
    protected void onPostExecute(Response response) {
        if(response != null) {
            // JSON結果を変換します
            List<Book> books = castBooks(response);
            // アダプタに書籍情報を登録します
            viewPagerAdapter.setBooks(books);
            // アダプタ更新
            viewPagerAdapter.notifyDataSetChanged();
        }
        super.onPostExecute(response);
    }

    /**
     * JSON結果から書籍情報一覧を変換します
     * @param response JSON検索結果
     * @return 書籍情報一覧
     */
    private List<Book> castBooks(Response response){
        List<Book> books = new ArrayList<Book>();

        // 個別取得
        for (Book book : response.getResult()) {
            books.add(book);
        }
        return books;

    }

    @Override
    protected Response castResult(InputStream inputStream) {
        Response response = null;
        try {
            response = JSON.decode(inputStream, Response.class);
        } catch (IOException e) {
            Log.d(TAG,"JSON Decode Exception : ",e);
        }
        return response;
    }

}
