package br.pucminas.pablo.exercicio06.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.pucminas.pablo.exercicio06.R;
import br.pucminas.pablo.exercicio06.model.Filme;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {

    private List<Filme> filmes;

    public FilmeAdapter(List<Filme> filmes) {
        this.filmes = filmes;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filme, parent, false);
        return new FilmeViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        final Filme filme = filmes.get(position);

        if (filme != null) {
            holder.TV_nomeFilme.setText(filme.getNome());
            holder.TV_votoFilme.setText(String.valueOf(filme.getVotos()));
        }
    }

    @Override
    public int getItemCount() {
        if (filmes == null) {
            return 0;
        } else {
            return filmes.size();
        }
    }

    class FilmeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TV_nomeFilme)
        TextView TV_nomeFilme;
        @BindView(R.id.TV_votoFilme)
        TextView TV_votoFilme;

        FilmeViewHolder(View viewItem) {
            super(viewItem);

            ButterKnife.bind(this, viewItem);
        }
    }
}
