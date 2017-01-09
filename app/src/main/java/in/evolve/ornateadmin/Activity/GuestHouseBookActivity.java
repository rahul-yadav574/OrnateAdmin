package in.evolve.ornateadmin.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import in.evolve.ornateadmin.R;

public class GuestHouseBookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_house_book);
        recyclerView= (RecyclerView) findViewById(R.id.gh_book_recyclerView);
    }
}
