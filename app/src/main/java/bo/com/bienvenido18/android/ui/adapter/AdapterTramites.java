package bo.com.bienvenido18.android.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.ui.callBack.TramitesCallback;
import bo.com.bienvenido18.android.ui.callBack.UniversidadesCallback;
import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderTramites;
import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderU;

public class AdapterTramites extends RecyclerView.Adapter<ViewHolderTramites>{

    private List<Tramites> tramites;
    private LayoutInflater inflater;
    private TramitesCallback callback;

    public AdapterTramites(List<Tramites> tramites, Context context) {
        this.tramites = tramites;
        this.inflater= LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public ViewHolderTramites onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.sigle_procedure, parent, false);
        return new ViewHolderTramites(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderTramites holder, int position) {
        Tramites tramite = tramites.get(position);
        holder.nameTextView.setText(tramite.getTitlePhoto());
        //holder.listaTramites.setText(tramite.getListaTramitesInfo());


        Picasso.get().load(tramite.getCoverPhoto()).into(holder.coverImageView);

        holder.itemView.setOnClickListener(view -> {
            if (callback != null) {
                callback.OnTramitesClicked(tramite);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tramites.size();
    }
    public void updateItems(List<Tramites> tramites) {
        this.tramites = tramites;
        notifyDataSetChanged();
    }

    public void setCallback(TramitesCallback callback) {
        this.callback = callback;
    }
}

