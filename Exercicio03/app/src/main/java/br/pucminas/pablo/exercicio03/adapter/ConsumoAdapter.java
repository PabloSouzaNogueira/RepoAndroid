package br.pucminas.pablo.exercicio03.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import br.pucminas.pablo.exercicio03.R;
import br.pucminas.pablo.exercicio03.model.Consumo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsumoAdapter extends RecyclerView.Adapter<ConsumoAdapter.ConsumoViewHolder> {

    private List<Consumo> consumos;
    private Context context;

    public ConsumoAdapter(final List<Consumo> consumos, Context context) {
        this.consumos = consumos;
        this.context = context;
    }

    @NonNull
    @Override
    public ConsumoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consumo, parent, false);
        return new ConsumoAdapter.ConsumoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumoViewHolder holder, int position) {
        try {
            Locale brasil = new Locale("pt", "BR");

            Consumo consumo = consumos.get(position);

            holder.TV_nome.setText(consumo.getNome());
            holder.TV_quantidade.setText(String.valueOf(consumo.getQuantidade()));
            holder.TV_valorUnitario.setText(String.format(brasil,"R$ %.2f",consumo.getValorUnitario()));
            holder.TV_valorTotal.setText(String.format(brasil,"R$ %.2f",consumo.getValorTotal()));

        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        if (consumos == null)
            return 0;
        else
            return consumos.size();
    }

    class ConsumoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TV_nome)
        TextView TV_nome;
        @BindView(R.id.TV_valorUnitario)
        TextView TV_valorUnitario;
        @BindView(R.id.TV_quantidade)
        TextView TV_quantidade;
        @BindView(R.id.TV_valorTotal)
        TextView TV_valorTotal;

        ConsumoViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }
}
