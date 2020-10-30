package bo.com.bienvenido18.android.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bienvenido18.android.R;

public class ViewHolderCocha extends RecyclerView.ViewHolder {
    public ImageView coverImageViewCocha;
    public TextView nameTextViewCocha;

    public ViewHolderCocha(@NonNull View itemView) {
        super(itemView);
        coverImageViewCocha = itemView.findViewById(R.id.idImagecocha);
        nameTextViewCocha = itemView.findViewById(R.id.nombreucocha);
    }
}
