package com.urban.androidhomework;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CharactersAdapter adapter;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkClient.get()
                .getService()
                .getAllCharacters()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<Response<Character>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onSuccess(Response<Character> characterResponse) {
                                onSuccessResult(characterResponse);
                            }

                            @Override
                            public void onError(Throwable e) {
                            }
                        });
    }

    private void onSuccessResult(Response<Character> characterResponse) {
        listview = (ListView) findViewById(R.id.characters_list);
        adapter = new CharactersAdapter(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Utils.Companion.getName(characterResponse).toArray(new String[0])
        );
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, CharacterFragment.Companion.newInstance(name)).commit();
            }
        });
    }
}