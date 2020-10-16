
package bo.com.bienvenido18.android.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

import bo.com.bienvenido18.android.R;

public class ViewHolderU extends RecyclerView.ViewHolder {
    public ImageView coverImageView;
    public TextView nameTextView;

    public ViewHolderU(@NonNull View itemView) {
        super(itemView);
        coverImageView = itemView.findViewById(R.id.idImage);
        nameTextView = itemView.findViewById(R.id.nombre);


    }
}