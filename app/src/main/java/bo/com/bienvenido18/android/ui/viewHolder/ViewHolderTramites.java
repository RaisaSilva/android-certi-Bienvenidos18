package bo.com.bienvenido18.android.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bienvenido18.android.R;

public class ViewHolderTramites extends RecyclerView.ViewHolder {
    public ImageView coverImageView;
    public TextView nameTextView;
    public TextView plataformaTextView;
    public TextView sucursalesTextView;

    public ViewHolderTramites(@NonNull View itemView) {
        super(itemView);
        coverImageView = itemView.findViewById(R.id.procedure_view);
        nameTextView = itemView.findViewById(R.id.title_view);
        plataformaTextView = itemView.findViewById(R.id.plataformaTextView);
        sucursalesTextView = itemView.findViewById(R.id.sucursalesTextView);
    }
}
