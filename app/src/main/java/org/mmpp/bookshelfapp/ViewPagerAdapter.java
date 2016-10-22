package org.mmpp.bookshelfapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import org.mmpp.bookshelf.util.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * パラパラスクロールページのアダプタ
 * Created by wataru on 2016/10/19.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    /** ログ用 */
    private final static String TAG = "ViewPagerAdapter";

    /**
     * 表示データ格納変数
     */
    private List<Book> books = new LinkedList<Book>();

    /**
     * 1ページに表示するデータ数
     */
    private int viewCount = 12;

    /**
     * デフォルトコントラクタ
     *
     * @param fragmentManager
     */
    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    public void setBooks(List<Book> books) {
        this.books = books;
    }
    /**
     * 1ページでの表示本を指定します<br />
     * default : 12
     *
     * @param viewCount 本の数
     */
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


    /**
     * ページ単位のデータを取得します
     *
     * @param page_no ページ番号
     * @return 表示データ
     */
    private List<Book> getPageItems(int page_no) {
        int item_start = page_no * viewCount;
        int item_end = (page_no + 1) * viewCount;
        if (item_start < 0) {
            item_start = 0;
        }
        if (item_end > books.size()) {
            item_end = books.size();
        }
        // 前後逆転する場合 (最終ページ以降をスクロールされるなど)
        if (item_end < item_start) {
            // 範囲外になる場合は空を返却する
            return new LinkedList<Book>();
        }
        return books.subList(item_start, item_end);
    }

    @Override
    public Fragment getItem(int position) {
        ViewPagerFragment viewPageFragment = ViewPagerFragment.getInstance(getPageItems(position));
        return viewPageFragment;
    }

    /**
     * 表示する最後のページ番号を取得します
     *
     * @return 最大ページ件数
     */
    @Override
    public int getCount() {
        int count = (int) Math.ceil((float) books.size() / viewCount);
        return count;
    }

}
