package co.com.misistema.ui.sistema;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.misistema.R;
import co.com.misistema.adapter.AstroAdapter;
import co.com.misistema.model.Astro;

public class SistemaSolarFragment extends Fragment {

    @BindView(R.id.listViewPlanetas)
    ListView listViewPlanetas;
    @BindView(R.id.editText)
    EditText editText;

    AstroAdapter astroAdapter;

    private List<Astro> astros;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sistema_solar, container, false);
        ButterKnife.bind(this,root);
        loadInformation();
        astroAdapter = new AstroAdapter(getContext(), astros);
        listViewPlanetas.setAdapter(astroAdapter);
        editTextWatcher();
        return root;
    }

    private void editTextWatcher() {
       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  astroAdapter.getFilter().filter(charSequence);
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
    }

    private void loadInformation() {
        astros =new ArrayList<>();
        astros.add(new Astro(R.drawable.tierra,"Tierra","La Tierra (del latín Terra,17\\u200B deidad romana equivalente a Gea, diosa griega de la feminidad y la fecundidad) es un planeta del sistema solar que gira alrededor de su estrella —el Sol— en la tercera órbita más interna"));
        astros.add(new Astro(R.drawable.jupiter,"Júpiter","Júpiter es el quinto planeta del sistema solar. Forma parte de los denominados planetas exteriores o gaseosos. Recibe su nombre del dios romano Júpiter (Zeus en la mitología griega)."));
        astros.add(new Astro(R.drawable.marte,"Marte","Marte es el cuarto planeta en orden de distancia al Sol y el segundo más pequeño del sistema solar, después de Mercurio."));

    }
}