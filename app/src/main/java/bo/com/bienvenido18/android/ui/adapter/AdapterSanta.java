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
import bo.com.bienvenido18.android.model.users.UniversidadSantaCruz;
import bo.com.bienvenido18.android.ui.callBack.USantaCruzCallback;
import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderSanta;

public class AdapterSanta extends RecyclerView.Adapter<ViewHolderSanta> {
    private List<UniversidadSantaCruz> universidadSantaCruz;
    private LayoutInflater inflater;
    private USantaCruzCallback callback;

    public AdapterSanta(List<UniversidadSantaCruz> universidadSantaCruz, Context context) {
        this.universidadSantaCruz = universidadSantaCruz;
        this.inflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolderSanta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_usanta, parent, false);
        return new ViewHolderSanta(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSanta holder, int position) {
        UniversidadSantaCruz uscz = universidadSantaCruz.get(position);
        holder.nameTextViewSanta.setText(uscz.getDisplayNameS());
        Picasso.get().load(uscz.getCoverPhoto()).into(holder.coverImageViewSanta);
        holder.itemView.setOnClickListener(view -> {
            if (callback != null) {
                callback.onUniversidadSantaClicked(uscz);
            }
        });

    }

    @Override
    public int getItemCount() {
        return universidadSantaCruz.size();
    }

    public void updateItems(List<UniversidadSantaCruz> universidadSantaCruz) {
        this.universidadSantaCruz = universidadSantaCruz;
        notifyDataSetChanged();
    }

    public void setCallback(USantaCruzCallback callback) {
        this.callback = callback;

    }


}
