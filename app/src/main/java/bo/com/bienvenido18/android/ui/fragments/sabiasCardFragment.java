package bo.com.bienvenido18.android.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bo.com.bienvenido18.android.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sabiasCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sabiasCardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private  String title = "";
    private  String content = "";
    private int color;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sabiasCardFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static sabiasCardFragment newInstance(String title, String content, int color) {
        sabiasCardFragment fragment = new sabiasCardFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        args.putInt("imageId", color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        color = getArguments().getInt("color", R.color.colorPrimary);
        title = getArguments().getString("title", "");
        content = getArguments().getString("content", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sabias_card, container, false);


        TextView txtTitle = rootView.findViewById(R.id.textView2);
        TextView txtSubTitle = rootView.findViewById(R.id.textView3);
        txtTitle.setText(title);
        txtSubTitle.setText(content);


        return rootView;
    }

}