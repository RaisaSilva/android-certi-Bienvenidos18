package bo.com.bienvenido18.android.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bienvenido18.android.R;

public class ViewHolderSanta extends RecyclerView.ViewHolder {
    public ImageView coverImageViewSanta;
    public TextView nameTextViewSanta;


    public ViewHolderSanta(@NonNull View itemView) {
        super(itemView);
        coverImageViewSanta = itemView.findViewById(R.id.idImagesanta);
        nameTextViewSanta = itemView.findViewById(R.id.nombreusanta);
    }
}
