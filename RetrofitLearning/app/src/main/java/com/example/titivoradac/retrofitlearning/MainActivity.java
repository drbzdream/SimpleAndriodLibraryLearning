package com.example.titivoradac.retrofitlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.dribbble.com")
                .build();

        SimpleRetrofit retrofit = restAdapter.create(SimpleRetrofit.class);
        Shot shot = retrofit.getShot();

        Toast.makeText(this, "Name : " + shot.getTitle() + " URL : " + shot.getUrl(),
                Toast.LENGTH_LONG).show();

        new HttpAsyncTask().execute();

    }



    public class HttpAsyncTask extends AsyncTask<Void, Void, Shot> {

        @Override
        protected Shot doInBackground(Void... params) {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.dribbble.com")
                    .build();

            SimpleRetrofit retrofit = restAdapter.create(SimpleRetrofit.class);
            Shot shot = retrofit.getShot();

            return shot;
        }

        @Override
        protected void onPostExecute(Shot shot) {

            Toast.makeText(getApplicationContext(),
                    "Name : " + shot.getTitle() + " URL : " + shot.getUrl(),
                    Toast.LENGTH_LONG).show();
            super.onPostExecute(shot);
        }
    }

}
