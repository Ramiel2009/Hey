package ua.hey.hey;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RestTest extends ListActivity implements Callback<StackOverflowQuestions> {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        ArrayAdapter<Question> arrayAdapter =
                new ArrayAdapter<Question>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new ArrayList<Question>());
        setListAdapter(arrayAdapter);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);

        setProgressBarIndeterminateVisibility(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://94.244.34.33:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepare call in Retrofit 2.0
        API api = retrofit.create(API.class);

        Call<StackOverflowQuestions> call = api.load("u2");
        //asynchronous call
        call.enqueue(this);

        // synchronous call would be with execute, in this case you
        // would have to perform this outside the main thread
        // call.execute()

        // to cancel a running request
        // call.cancel();
        // calls can only be used once but you can easily clone them
        //Call<StackOverflowQuestions> c = call.clone();
        //c.enqueue(this);

    }

    @Override
    public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
        setProgressBarIndeterminateVisibility(false);
        ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) getListAdapter();
        adapter.clear();
        adapter.addAll(response.body().items);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(RestTest.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
