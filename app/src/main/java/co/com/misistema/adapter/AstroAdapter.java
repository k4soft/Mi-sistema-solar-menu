package co.com.misistema.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.misistema.R;
import co.com.misistema.model.Astro;

public class AstroAdapter extends BaseAdapter implements Filterable {

    private final LayoutInflater inflater;
    private List<Astro> astrosIn;
    private List<Astro> astrosOut;

   public AstroAdapter(Context context, List<Astro> astros){
       astrosIn = astros;
       astrosOut = astros;
       inflater = LayoutInflater.from(context);
   }


    @Override
    public int getCount() {
        return astrosOut.size();
    }

    @Override
    public Astro getItem(int i) {
        return astrosOut.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder;
       if(view != null){
           viewHolder = (ViewHolder) view.getTag();
       }else {
           view = inflater.inflate(R.layout.astro_item, viewGroup,false);
           viewHolder = new ViewHolder(view);
           view.setTag(viewHolder);
       }
        viewHolder.imagen.setImageResource(astrosOut.get(i).getImagen());
        viewHolder.nombre.setText(astrosOut.get(i).getNombre());
        viewHolder.descripcion.setText(astrosOut.get(i).getDescripcion());
        return view;
    }

    @Override
    public Filter getFilter() {
       Filter filter = new Filter() {
           @Override
           protected FilterResults performFiltering(CharSequence charSequence) {
               FilterResults results = new FilterResults();
               List<Astro> filteredArrList = new ArrayList<>();
               if (astrosIn == null) {
                   astrosIn = new ArrayList<>(astrosOut);
               }
               if(charSequence == null){
                   results.count = astrosIn.size();
                   results.values = astrosIn;
               }else{
                   charSequence = charSequence.toString().toLowerCase();
                   for (int i = 0; i < astrosIn.size(); i++) {
                       String data = astrosIn.get(i).getNombre();
                       if (data.toLowerCase().contains(charSequence.toString())) {
                           filteredArrList.add(astrosIn.get(i));
                       }
                   }
                   results.count = filteredArrList.size();
                   results.values = filteredArrList;

               }
               return results;
           }

           @Override
           protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
               astrosOut = (List<Astro>) filterResults.values;
               notifyDataSetChanged();
           }
       };
        return filter;
    }


    class ViewHolder{
       @BindView(R.id.imagen)
        ImageView imagen;
        @BindView(R.id.nombre)
        TextView nombre;
        @BindView(R.id.descripcion)
        TextView descripcion;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
