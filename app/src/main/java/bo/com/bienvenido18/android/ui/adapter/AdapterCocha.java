package bo.com.bienvenido18.android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.users.UniversidadCocha;
import bo.com.bienvenido18.android.ui.callBack.UCochaCallback;

import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderCocha;


public class AdapterCocha  extends RecyclerView.Adapter<ViewHolderCocha>{
    private List<UniversidadCocha> universidadCocha;
    private LayoutInflater inflater;
    private UCochaCallback callback;

    public AdapterCocha(List<UniversidadCocha> universidadCocha, Context context) {
        this.universidadCocha = universidadCocha;
        this.inflater= LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolderCocha onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_ucocha, parent, false);
        return new ViewHolderCocha(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCocha holder, int position) {
        UniversidadCocha ucocha = universidadCocha.get(position);
        holder.nameTextViewCocha.setText(ucocha.getDisplayNameCocha());
        Picasso.get().load(ucocha.getCoverPhoto()).into(holder.coverImageViewCocha);
        holder.itemView.setOnClickListener(view -> {
            if (callback != null) {
                callback.onUniversidadCochaClicked(ucocha);
            }
        });

    }

    @Override
    public int getItemCount() {
        return universidadCocha.size();
    }

    public void updateItems(List<UniversidadCocha> universidadCocha) {
        this.universidadCocha = universidadCocha;
        notifyDataSetChanged();
    }

    public void setCallback(UCochaCallback callback) {
        this.callback = callback;

    }
}

