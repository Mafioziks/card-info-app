package lv.teteris.toms.cardinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lv.teteris.toms.cardinfo.adapters.SimpleUserAdapter;
import lv.teteris.toms.cardinfo.models.User;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    ArrayList<User> users = new ArrayList<User>();
    SimpleUserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userAdapter = new SimpleUserAdapter(this, this.users);
        ListView lview = (ListView) findViewById(R.id.result_items);
        lview.setAdapter(userAdapter);
        CardInfoApi api = new CardInfoApi() {
            @Override
            public void onPostExecute(String s) {
                userAdapter.addAll(this.toUserArray(s));
                userAdapter.notifyDataSetChanged();
            }
        };
        api.getUsers();
    }

    public void toaster(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
