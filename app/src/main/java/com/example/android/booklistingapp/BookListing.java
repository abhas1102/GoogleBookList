package com.example.android.booklistingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookListing extends AppCompatActivity {

    private final static String LOG_TAG = BookListing.class.getSimpleName();
    static final String BOOK_LIST_VALUES = "bookListValues";


    private ListView mListView;
    private BooksAdapter mBooksAdapter;
    Context context = this;

    ArrayList<Books> books = new ArrayList<>();

    private String mKeyWord = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        if (savedInstanceState != null) {
            books = savedInstanceState.getParcelableArrayList(BOOK_LIST_VALUES);
        }

        mBooksAdapter = new BooksAdapter(this, books);

        // Now, we will get information of ListViews and attach adapter into it

        mListView = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_books_list);
        mListView.setEmptyView(emptyView);
        mListView.setAdapter(mBooksAdapter);

        final EditText searchBox = (EditText) findViewById(R.id.search_box);
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (InternetConnection.checkConnection(context)) {

                    mKeyWord = searchBox.getText().toString();
                    searchBooks();
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "No Internet Connection!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    private void searchBooks() {
        GetBooksData booksData = new GetBooksData(this, this);
        booksData.execute(mKeyWord);
    }

    public void refreshBookList(ArrayList<Books> result) {
        mBooksAdapter.clear();
        for (Books book : result) {
            mBooksAdapter.add(book);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the book list values
        savedInstanceState.putParcelableArrayList(BOOK_LIST_VALUES, books);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}
