package in.evolve.ornateadmin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import in.evolve.ornateadmin.R;

public class Landing extends AppCompatActivity implements View.OnClickListener {

    private Button addGuestHouse,addPg,ghBookChck,pgBookChk,pgVisitChk,lisProCheck;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addGuestHouse = (Button) findViewById(R.id.add_new_gh);
        addPg = (Button) findViewById(R.id.add_new_pg);
        ghBookChck = (Button) findViewById(R.id.gh_book_check);
        pgBookChk = (Button) findViewById(R.id.pg_book_check);
        pgVisitChk = (Button) findViewById(R.id.pg_visit_check);
        lisProCheck = (Button) findViewById(R.id.list_house_check);

        addGuestHouse.setOnClickListener(this);
        addPg.setOnClickListener(this);
        ghBookChck.setOnClickListener(this);
        pgBookChk.setOnClickListener(this);
        pgVisitChk.setOnClickListener(this);
        lisProCheck.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.add_new_gh:
                Intent intent = new Intent(Landing.this,AddNewPg.class);
                startActivity(intent);
                break;
            case R.id.add_new_pg:
                Intent intent1 = new Intent(Landing.this,AddNewGuestHouse.class);
                startActivity(intent1);
                break;
            case R.id.gh_book_check:
                Intent intent2 = new Intent(Landing.this,GuestHouseBookActivity.class);
                startActivity(intent2);
                break;
            case R.id.pg_book_check:
                Intent intent3 = new Intent(Landing.this,PgBookActivity.class);
                startActivity(intent3);
                break;
            case R.id.pg_visit_check:
                Intent intent4 = new Intent(Landing.this,PgVisitActivity.class);
                startActivity(intent4);
                break;
            case R.id.list_house_check:
                Intent intent5 = new Intent(Landing.this,PgListRequest.class);
                startActivity(intent5);
                break;
        }

    }
}
