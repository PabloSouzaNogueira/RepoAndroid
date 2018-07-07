package br.pucminas.pablo.exercicio05.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.pucminas.pablo.exercicio05.Activity.AddActivity;
import br.pucminas.pablo.exercicio05.Activity.MainActivity;
import br.pucminas.pablo.exercicio05.Model.Nota;
import br.pucminas.pablo.exercicio05.NotasDatabase;
import br.pucminas.pablo.exercicio05.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    private List<Nota> notas;
    private Context context;

    public NotaAdapter(List<Nota> notas, Context context) {
        this.notas = notas;
        this.context = context;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nota, parent, false);
        return new NotaViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        final Nota nota = notas.get(position);

        try {
            if (nota != null) {
                holder.TV_tituloNota.setText(nota.getTitulo());
                holder.TV_descricaoNota.setText(nota.getTexto());

                holder.LL_nota.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, AddActivity.class);
                        intent.putExtra("nota", nota);
                        context.startActivity(intent);
                    }
                });

                holder.LL_nota.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

                        dialog.setTitle("Confirmar exclusão");
                        dialog.setMessage("Deseja realmente excluir a nota: " + nota.getTitulo() + " ?");
                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NotasDatabase notasDatabase = NotasDatabase.getDatabase(context);
                                notasDatabase.notaDAO().delete(nota);
                                Toast.makeText(context, "Exclusão realizada com sucesso!", Toast.LENGTH_SHORT).show();

                                MainActivity activity = (MainActivity) context;
                                activity.carregarNotas();
                            }
                        });

                        dialog.setNegativeButton("Não", null);
                        dialog.create();
                        dialog.show();

                        return true;
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (notas == null) {
            return 0;
        } else {
            return notas.size();
        }
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TV_tituloNota)
        TextView TV_tituloNota;
        @BindView(R.id.TV_descricaoNota)
        TextView TV_descricaoNota;
        @BindView(R.id.LL_nota)
        LinearLayout LL_nota;


        NotaViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
