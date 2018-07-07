package br.pucminas.pablo.exercicio06.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import br.pucminas.pablo.exercicio06.R;
import br.pucminas.pablo.exercicio06.activity.VotoActivity;
import br.pucminas.pablo.exercicio06.model.Filme;
import br.pucminas.pablo.exercicio06.model.FilmesDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmeVotoAdapter extends RecyclerView.Adapter<FilmeVotoAdapter.FilmeVotoViewHolder> {

    private List<Filme> filmes;
    private Context context;

    public FilmeVotoAdapter(List<Filme> listaFilmes, Context context) {
        this.filmes = listaFilmes;
        this.context = context;
    }

    @NonNull
    @Override
    public FilmeVotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filme_voto, parent, false);
        return new FilmeVotoViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeVotoViewHolder holder, int position) {
        final Filme filme = filmes.get(position);

        if (filme != null) {
            holder.TV_filme.setText(filme.getNome());

            holder.CB_filme.setOnClickListener(v -> {
                filme.setVotos(filme.getVotos() + 1);

                DatabaseReference filmesFirebase = FilmesDatabase.getFirebaseDatabase();
                filmesFirebase.child(filme.getId()).setValue(filme);

                Toast.makeText(context, "Voto computado com sucesso!", Toast.LENGTH_SHORT).show();

                ((VotoActivity) context).finish();
            });
        }
    }


    @Override
    public int getItemCount() {
        if (filmes == null)
            return 0;
        else
            return filmes.size();
    }

    class FilmeVotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.TV_filme)
        TextView TV_filme;
        @BindView(R.id.CB_filme)
        CheckBox CB_filme;

        FilmeVotoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
