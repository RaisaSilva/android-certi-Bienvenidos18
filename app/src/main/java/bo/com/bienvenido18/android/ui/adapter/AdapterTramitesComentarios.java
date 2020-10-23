package bo.com.bienvenido18.android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.users.Comentarios;

import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.ui.callBack.ComentariosCallback;
import bo.com.bienvenido18.android.ui.callBack.TramitesCallback;
import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderComentarios;


public class AdapterTramitesComentarios extends RecyclerView.Adapter<ViewHolderComentarios>{

    private List<Comentarios> comentarios;
    private LayoutInflater inflater;
    private TramitesCallback callbacktramites;
    private ComentariosCallback callBackComentarios;


    public AdapterTramitesComentarios(List<Comentarios> comentarios, Context context) {
        this.comentarios = comentarios;
        this.inflater= LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public ViewHolderComentarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_comentario, parent, false);
        return new ViewHolderComentarios(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderComentarios holder, int position) {

        Comentarios comentario = comentarios.get(position);

        holder.alias.setText(comentario.getAlias());
        holder.date.setText(comentario.getDate());
        holder.comentario.setText(comentario.getComentario());

        holder.itemView.setOnClickListener(view -> {
            if (callbacktramites != null) {
                callBackComentarios.OnComentariosClicked(comentario);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
    public void updateItems(List<Comentarios> comentarios) {
        this.comentarios = comentarios;
        notifyDataSetChanged();

    }
    public void setCallBackComentarios(ComentariosCallback comentariosCallback){
        this.callBackComentarios = comentariosCallback;

    }
}


