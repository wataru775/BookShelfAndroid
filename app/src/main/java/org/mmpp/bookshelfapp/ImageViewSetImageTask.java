package org.mmpp.bookshelfapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ImageViewにURLで指定した画像データを流し込むタスククラス
 * Created by wataru on 2016/10/21.
 */

public class ImageViewSetImageTask extends AbstractWebAccessTask<Object, Object, Bitmap> {
    /** ログ用 */
    private final static String TAG = "ImageViewSetImageTask";
    /**
     * 接続するURL
     */
    private String url;
    /**
     * 画像を流し込むImageView
     */
    private ImageView view;

    /**
     * コンストラクタ
     * @param view イメージビュー
     * @param url 画像URL
     */
    public ImageViewSetImageTask(ImageView view, String url) {
        super();
        this.view = view;
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(Object... objects) {
        try {
            if(url == null)return null;

            // Webアクセスして画像データを取り出します
            return webaccess(new URL(url));
        } catch (MalformedURLException e) {
            // URLではない
            Log.d(TAG , this.url + " Not URL");
        } catch (IOException e) {
            Log.d(TAG , " Exception Web access url :" + this.url,e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if( bitmap != null) {

            // 画像をセットします
            view.setImageBitmap(bitmap);
        }
        super.onPostExecute(bitmap);
    }

    @Override
    protected Bitmap castResult(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }
}
