package br.pucminas.pedidovirtual.pedidovirtual.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.pucminas.pedidovirtual.pedidovirtual.R;
import br.pucminas.pedidovirtual.pedidovirtual.activity.EstabelecimentoActivity;
import butterknife.ButterKnife;

public class CardapioFragment extends Fragment {

    private EstabelecimentoActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        ButterKnife.bind(this, view);

        activity = (EstabelecimentoActivity) getActivity();


        return view;
    }

}
