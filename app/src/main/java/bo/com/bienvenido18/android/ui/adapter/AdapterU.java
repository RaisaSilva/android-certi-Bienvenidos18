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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.ui.callBack.UniversidadesCallback;
import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderU;

public class AdapterU  extends RecyclerView.Adapter<ViewHolderU> {
    private List<Universidades> universidades;
    private LayoutInflater inflater;

    private UniversidadesCallback callback;




    public AdapterU(List<Universidades> universidades, Context context) {
        this.universidades = universidades;
        this.inflater= LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public ViewHolderU onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolderU(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderU holder, int position) {
        Universidades universidad = universidades.get(position);
        holder.nameTextView.setText(universidad.getDisplayName());

        Picasso.get().load(universidad.getCoverPhoto()).into(holder.coverImageView);
        holder.itemView.setOnClickListener(view -> {
            if (callback != null) {
                callback.onUniversidadClicked(universidad);
            }
        });


    }

    @Override
    public int getItemCount() {
        return universidades.size();
    }
    public void updateItems(List<Universidades> universidades) {
        this.universidades = universidades;
        notifyDataSetChanged();
    }

    public void setCallback(UniversidadesCallback callback) {
        this.callback = callback;
    }
}




