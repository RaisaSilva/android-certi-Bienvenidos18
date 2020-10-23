package bo.com.bienvenido18.android.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bienvenido18.android.R;

public class ViewHolderComentarios extends RecyclerView.ViewHolder {

    public TextView alias;
    public TextView date;
    public TextView comentario;

    public ViewHolderComentarios(@NonNull View itemView) {
        super(itemView);
        alias = itemView.findViewById(R.id.alias);
        date = itemView.findViewById(R.id.date);
        comentario = itemView.findViewById(R.id.comentario);
    }
}
