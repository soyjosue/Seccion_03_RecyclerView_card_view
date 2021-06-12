package com.example.seccion_03_recyclerview_card_view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.seccion_03_recyclerview_card_view.models.Movie;
import com.example.seccion_03_recyclerview_card_view.adapters.MyAdapter;
import com.example.seccion_03_recyclerview_card_view.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;
    // Puede ser declarado como 'RecyclerView.Adapter' o como nuestra clase adaptador 'MyAdapter'
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mLayoutManager = new LinearLayoutManager(this);

        // Implementamos nuestro OnItemClickListener propio, sobreescribiendo el metodo que nosotros
        // definimos en el adaptador, y recibiendo los parametros que necesitamos
        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                removeMovie(position);
            }
        });

        // Lo usamos en caso de que sepamos que el layout no va a cambiar de tamaño, mejorando el performance
        mRecyclerView.setHasFixedSize(true);
        // Añade un efecto por defecto, si le pasamos null lo desactiva por completo
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Enlazamos el layout manager y adapator directamente al recycler view
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_name:
                addMovie(0);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private List<Movie> getAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Ben Hur", R.drawable.benhur));
            add(new Movie("DeadPool", R.drawable.deadpool));
            add(new Movie("Guardans of Galaxy", R.drawable.guardians));
            add(new Movie("Warcraft", R.drawable.warcraft));
        }};
    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New Movie " + (++counter), R.drawable.new_movie));
        // Notificamos de un nuevo item insertado en nuestra colección
        mAdapter.notifyItemInserted(position);
        // Hacemos scroll hacia la posicion donde el nuevo elemento se alojo
        mLayoutManager.scrollToPosition(position);
    }

    private void removeMovie(int position) {
        movies.remove(position);
        // Notificamos de un item borrado en nuestra colección
        mAdapter.notifyItemRemoved(position);
    }
}