package in.evolve.ornateadmin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.evolve.ornateadmin.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.login_email_input);
        final EditText pass = (EditText) findViewById(R.id.login_pass_input);

        Button button  = (Button) findViewById(R.id.login_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("jagdeepkumar01@gmail.com") && pass.getText().toString().equals("9050290015#"))
                {
                    Intent intent  = new Intent(LoginActivity.this,Landing.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }

            }
        });
    }
}
