package ly.generalassemb.drewmahrt.shoppinglistwithsearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import ly.generalassemb.drewmahrt.shoppinglistwithsearch.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    private ListView mShoppingListView;
    private CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        mShoppingListView = (ListView)findViewById(R.id.shopping_list_view);

        ShoppingSQLiteOpenHelper helper = new ShoppingSQLiteOpenHelper(MainActivity.this);
        Cursor cursor = helper.getShoppingList();

        mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                new String[]{ShoppingSQLiteOpenHelper.COL_ITEM_NAME},
                new int[]{android.R.id.text1}, 0);
        mShoppingListView.setAdapter(mCursorAdapter);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

//        Log.d("SEVTEST ", "intent generated");
//        Log.d("SEVTEST ", "ActionSearch: " + Intent.ACTION_SEARCH);
//        Log.d("SEVTEST ", "ActionSearch: " + intent.getAction());
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            Toast.makeText(MainActivity.this, "Searching for " + query, Toast.LENGTH_SHORT).show();
//
//            Cursor cursor = NumbersSQLiteHelper.getInstance(this).searchNumbers(query);
//            cursor.moveToFirst();
//
//            int n = 0;
//            while (!cursor.isAfterLast()) {
//                cursor.moveToNext();
//                n++;
//            }
//            cursor.close();
//
//            String message = "There were " + n + " " + query + "'s.";
//            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//
//            TextView textView = (TextView) findViewById(R.id.count_text_view);
//            textView.setText(message);
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
