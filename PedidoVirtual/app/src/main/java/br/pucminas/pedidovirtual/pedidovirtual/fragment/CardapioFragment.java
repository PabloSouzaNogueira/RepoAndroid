package br.pucminas.pedidovirtual.pedidovirtual.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import br.pucminas.pedidovirtual.pedidovirtual.activity.EstabelecimentoActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CardapioFragment extends Fragment {

    @BindView(R.id.RV_prato)
    RecyclerView RV_prato;

    private EstabelecimentoActivity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        ButterKnife.bind(this, view);

        activity = (EstabelecimentoActivity) getActivity();


        return view;
    }

}
