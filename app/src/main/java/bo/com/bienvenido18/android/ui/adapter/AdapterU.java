package bo.com.bienvenido18.android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderU;

public class AdapterU  extends RecyclerView.Adapter<ViewHolderU> {
    ArrayList<Universidades> listaUni;




    public AdapterU(ArrayList<Universidades> listaUni) {
        this.listaUni = listaUni;

    }


    @NonNull
    @Override
    public ViewHolderU onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,false);
        /*View view1 = this.inflater.inflate(R.layout.item_list,parent,false);*/
        return new ViewHolderU(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderU holder, int position) {

        holder.nombre.setText(listaUni.get(position).getNombre());
        holder.foto.setImageResource(listaUni.get(position).getFoto());


    }

    @Override
    public int getItemCount() {
        return listaUni.size();
    }

}
