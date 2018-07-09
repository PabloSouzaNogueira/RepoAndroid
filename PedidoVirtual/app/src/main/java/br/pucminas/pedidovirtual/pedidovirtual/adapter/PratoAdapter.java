package br.pucminas.pedidovirtual.pedidovirtual.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import br.pucminas.pedidovirtual.pedidovirtual.model.Prato;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.MaskTransformation;

public class PratoAdapter extends RecyclerView.Adapter<PratoAdapter.PratoViewHolder> {

    private List<Prato> pratos;
    private Context context;
    private Locale brasil;

    public PratoAdapter(List<Prato> pratos, Context context) {
        this.pratos = pratos;
        this.context = context;
        brasil = new Locale("pt", "BR");
    }

    @NonNull
    @Override
    public PratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_prato, parent, false);
        return new PratoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PratoViewHolder holder, int position) {
        try {
            final Prato prato = pratos.get(position);

            if (prato != null) {
                holder.TV_nome.setText(prato.getNome());

                holder.TV_preco.setText(String.format(brasil, "Valor: R$ %02.2f", prato.getPreco()));

                Picasso.get().load(prato.getFoto())
                        .fit()
                        .centerCrop()
                        .transform(new MaskTransformation(context, R.drawable.ic_shape_rounded))
                        .into(holder.IV_foto);

                holder.LL_prato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, R.string.comming_soon, Toast.LENGTH_LONG).show();
                    }
                });
            }
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        if (pratos == null)
            return 0;
        else
            return pratos.size();
    }

    class PratoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.IV_foto)
        ImageView IV_foto;
        @BindView(R.id.LL_prato)
        LinearLayout LL_prato;
        @BindView(R.id.TV_nome)
        TextView TV_nome;
        @BindView(R.id.TV_preco)
        TextView TV_preco;

        PratoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
