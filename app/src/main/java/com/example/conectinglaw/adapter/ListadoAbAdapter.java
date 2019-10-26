package com.example.conectinglaw.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conectinglaw.R;
import com.example.conectinglaw.model.Lawyer;
import com.example.conectinglaw.model.User;

import java.util.ArrayList;

public class ListadoAbAdapter extends RecyclerView.Adapter<ListadoAbAdapter.ListadoViewHolder> {

    ArrayList<Lawyer> abFiltrados;
    int layout;
    onItemClickListener listener;

    public ListadoAbAdapter(ArrayList<Lawyer> abFiltrados,
                            int layout, onItemClickListener listener) {
        this.abFiltrados = abFiltrados;
        this.layout = layout;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new ListadoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoViewHolder holder, int position) {
        holder.onBind(abFiltrados.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return this.abFiltrados.size();
    }

    public class ListadoViewHolder extends RecyclerView.ViewHolder {

        TextView tipoAb, nombre, apellido,  telefono;


        public ListadoViewHolder(@NonNull View itemView) {
            super(itemView);

            tipoAb = (TextView)itemView.findViewById(R.id.txt_typeCase_cardview);
            nombre = (TextView)itemView.findViewById(R.id.txt_username_cardview);
            apellido=(TextView)itemView.findViewById(R.id.txt_username_apellido_cardview);
            telefono= (TextView)itemView.findViewById(R.id.txt_username_telefono_cardview);


        }
        public void onBind(final Lawyer abogado, final onItemClickListener listener){
            ArrayList<String> textoTipoAbogado = new ArrayList<>();

            if (abogado.isPenal()){
                textoTipoAbogado.add("Penal");
            }
            if (abogado.isMercantil()){
                textoTipoAbogado.add("Mercantil");
            }
            if (abogado.isCivil()){
                textoTipoAbogado.add("Civil");
            }
            tipoAb.setText("Tipo Abogado: " +  textoTipoAbogado);
            nombre.setText("Nombre: " + abogado.getName());
            apellido.setText("Apellido: "+ abogado.getLastname());
            telefono.setText("Telefono: "+ abogado.getTelephoneNumber());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(abogado, getAdapterPosition());
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(Lawyer lawyer, int position);
    }
}
