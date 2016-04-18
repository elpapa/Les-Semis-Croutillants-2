package edu.imag.miage.frigo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.imag.miage.frigo.data.ArticleContract.ArticleEntry;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleFragment extends Fragment {

    private ArrayAdapter<String> articleAdapter;

    public ArticleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.article_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        articleAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_article,
                R.id.list_item_article_textview,
                new ArrayList<String>());

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_article);
        listView.setAdapter(articleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String article = articleAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, article);
                startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        displayArticle();
    }

    private void displayArticle() {
        ArrayList<String> mArrayList = new ArrayList<>();
        Cursor mCursor = getContext().getContentResolver().query(
                ArticleEntry.CONTENT_URI,
                new String[]{ArticleEntry.COLUMN_ARTICLE_NAME},
                null,null,null,null);

        while(mCursor.moveToNext()){
            mArrayList.add(mCursor.getString(0));
        }
        mCursor.close();
        updateAdapter(mArrayList);

    }

    protected void updateAdapter(List<String> articleList){
        articleAdapter.clear();
        articleAdapter.addAll(articleList);
        articleAdapter.notifyDataSetChanged();
    }
}
