package in.evolve.ornateadmin.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import in.evolve.ornateadmin.R;

public class AddNewPg extends AppCompatActivity {

    private Toolbar toolbar;
    private final int PICK_IMAGE_REQUEST = 574;
    private Bitmap bitmap;
    private EditText pgName,pgAddress,pgCity,pgLocality,pgTerms,singleSharePrice,doubleSharePrice,tripleSharePrice;
    private ProgressDialog progressDialog;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pg);

        pgName = (EditText) findViewById(R.id.pgName);
        pgAddress = (EditText) findViewById(R.id.pgAddress);
        pgLocality = (EditText) findViewById(R.id.locality);
        pgCity = (EditText) findViewById(R.id.city);
        pgAddress = (EditText) findViewById(R.id.pgAddress);
        pgTerms = (EditText) findViewById(R.id.pg_conditions);
        singleSharePrice = (EditText) findViewById(R.id.single_price);
        doubleSharePrice = (EditText) findViewById(R.id.double_price);
        tripleSharePrice = (EditText) findViewById(R.id.triple_price);
        submitButton = (Button) findViewById(R.id.submit_information);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPg();
            }
        });


        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_pg_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            AddNewPg.this.finish();
        }else if (item.getItemId() == R.id.addImage){

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);



        }
        return true;
    }

    private void addNewPg(){

        showProgressDialog("Submitting...");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormEncodingBuilder()
                .add("name",pgName.getText().toString())
                .add("address",pgAddress.getText().toString())
                .add("terms",pgTerms.getText().toString())
                .add("city",pgCity.getText().toString())
                .add("locality",pgLocality.getText().toString())
                .add("latitude","12.34")
                .add("longitude","12.33")
                .add("image",getStringImage(bitmap))
                .add("singleshare",singleSharePrice.getText().toString())
                .add("doubleshare",doubleSharePrice.getText().toString())
                .add("tripleshare",tripleSharePrice.getText().toString())
                .build();

        Request request = new Request.Builder()
                .post(body)
                .url("http://ornateresidency.com/adminappapi/addpg.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        Toast.makeText(AddNewPg.this,"Unable To Connect To Server",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String res = response.body().string();
                Log.d("an is",res);

                try{
                    JSONObject jsonObject = new JSONObject(res);

                    if (jsonObject.getBoolean("status")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideProgressDialog();
                                Toast.makeText(AddNewPg.this,"Pg Added",Toast.LENGTH_LONG).show();
                                AddNewPg.this.finish();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideProgressDialog();

                                Toast.makeText(AddNewPg.this,"Unable To Connect To Server  ....Try Again..",Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {

            Uri imagePath = data.getData();

            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);

                //Scaling the bitmap as it might cause issues OPENGL RENDERING
                Bitmap bitmap1 = new BitmapDrawable(getResources(), bitmap2).getBitmap();
                int nh = (int) (bitmap1.getHeight() * (512.0 / bitmap1.getWidth()));
                bitmap = Bitmap.createScaledBitmap(bitmap1, 512, nh, true);
                // Log.d(TAG, String.valueOf(bitmap));

                Toast.makeText(AddNewPg.this, "Image Added", Toast.LENGTH_LONG).show();


                //setImageToQuestionList(bitmap);
                //Setting the Bitmap to ImageView
                // imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        public String getStringImage(Bitmap bmp){
            if (bmp!=null){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                return Base64.encodeToString(imageBytes, Base64.DEFAULT);}

            return null;
        }

    private void showProgressDialog(String msg){

        progressDialog   = new ProgressDialog(AddNewPg.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    private void hideProgressDialog(){

        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }
}

