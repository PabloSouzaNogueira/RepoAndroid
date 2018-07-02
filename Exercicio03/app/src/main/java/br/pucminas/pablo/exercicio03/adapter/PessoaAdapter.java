package br.pucminas.pablo.exercicio03.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.pucminas.pablo.exercicio03.R;
import br.pucminas.pablo.exercicio03.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;


public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {

    private List<Pessoa> pessoas;
    private Context context;

    public PessoaAdapter(final List<Pessoa> pessoas, Context context) {
        this.pessoas = pessoas;
        this.context = context;
    }

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pessoa, parent, false);
        return new PessoaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder holder, int position) {
        try {
            Pessoa pessoa = pessoas.get(position);
            holder.TV_nome.setText(pessoa.getNome());

            if (pessoa.getFoto() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(pessoa.getFoto(), 0, pessoa.getFoto().length);
                holder.IV_foto.setImageBitmap(bitmap);
            }
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        if (pessoas == null)
            return 0;
        else
            return pessoas.size();
    }


    class PessoaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.IV_foto)
        ImageView IV_foto;
        @BindView(R.id.TV_nome)
        TextView TV_nome;

        PessoaViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }


}
