package bo.com.bienvenido18.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bienvenido18.android.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyviewHolder> {

    int []arr;

    public RecyclerViewAdapter(int[] arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sigle_procedure,parent,  false);
        MyviewHolder myviewHolder = new MyviewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.imageView.setImageResource(arr[position]);
        holder.textView.setText("Tramite 1"+position);

    }

    @Override
    public int getItemCount() {
        return arr.length;
    }
    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.procedure_view);
            textView = itemView.findViewById(R.id.title_view);
        }
    }
}
