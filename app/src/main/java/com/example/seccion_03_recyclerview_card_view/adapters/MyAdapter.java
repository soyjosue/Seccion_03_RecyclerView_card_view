package com.example.seccion_03_recyclerview_card_view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seccion_03_recyclerview_card_view.R;
import com.example.seccion_03_recyclerview_card_view.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    public OnItemClickListener itemClickListener;

    private Context context;

    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener) {
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos el layout y se lo pasamos al constructor del ViewHolder, donde manejaremos
        // Toda la logica como extraer los datos, referencias...
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        // Llamaos al meodo Bind del ViewHolder pasandole objeto y listener
        holder.bind(movies.get(position), itemClickListener);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Elementos UI a rellenar
        public TextView textViewName;
        public ImageView imageViewPoster;

        public ViewHolder(View v) {
            // Recibe la View completa. La pasa al constructor padre y enlazamos referencias UI
            // Con nuestras propiedades ViewHolder declaradas justo arriba.
            super(v);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {

            // Procesamos los datos a renderizar
            textViewName.setText(movie.getName());
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
            // imageViewPoster.setImageResource(movie.getPoster());

            // Definimos que por cada elemento de nuestro recyclerView, tenemos un click listener
            // que se comporta de la siguiente manera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });
        }
    }

    // Declaramos nuestra interfaz con el/los metodo/s a implementar
    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }

}
