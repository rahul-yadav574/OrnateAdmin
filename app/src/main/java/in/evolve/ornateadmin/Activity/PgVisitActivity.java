package in.evolve.ornateadmin.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import in.evolve.ornateadmin.R;

public class PgVisitActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_visit);
        recyclerView= (RecyclerView) findViewById(R.id.pg_visit_recyclerView);
    }
}
