package bo.com.bienvenido18.android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer;

import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Sabias;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.api.ApiRepository;
import bo.com.bienvenido18.android.repository.api.SabiasApi;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.ui.fragments.sabiasCardFragment;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.viewModel.SabiasViewModel;
import bo.com.bienvenido18.android.viewModel.UniversidadesViewModel;
import me.relex.circleindicator.CircleIndicator;

import static bo.com.bienvenido18.android.ui.activities.SabiasActivity.sabiasPagerAdapter.*;

public class SabiasActivity extends AppCompatActivity {

    ViewPager sabiasViewPager;
    private sabiasPagerAdapter pagerAdapter;
    private SabiasViewModel viewModel;
    private List<Sabias> sabias = new ArrayList<>();
    Context con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabias);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }


        //Injectando el viewModel
        viewModel = new ViewModelProvider(this).get(SabiasViewModel.class);

        setContentView(R.layout.activity_sabias);


        sabiasViewPager = findViewById(R.id.cardViewPager);
        pagerAdapter = new sabiasPagerAdapter(this);
        sabiasViewPager.setAdapter(pagerAdapter);

        CardFlipPageTransformer cardFlipPageTransformer = new CardFlipPageTransformer();
        cardFlipPageTransformer.isScalable();
        cardFlipPageTransformer.setFlipOrientation(CardFlipPageTransformer.VERTICAL);
        sabiasViewPager.setPageTransformer(true, cardFlipPageTransformer);
        CircleIndicator indicator;

        indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(sabiasViewPager);
        //subscribeToData();

    }

    public class sabiasPagerAdapter extends PagerAdapter {
        Context context;
        LayoutInflater mLayoutInflater;
        ArrayList pages = new ArrayList<>();


        public sabiasPagerAdapter(Context context) {
            this.context = context;
            mLayoutInflater = LayoutInflater.from(context);

            pages.add(new Object());
            pages.add(new Object());
            pages.add(new Object());


        }



        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // This method should create the page for the given position passed to it as an argument.
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View rootView = mLayoutInflater.inflate(R.layout.card_image_layout, container, false);
            AppCompatImageView imgCardSide = rootView.findViewById(R.id.imgCardSide);
            imgCardSide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        sabiasViewPager.setCurrentItem(1, true);
                    } else {
                        sabiasViewPager.setCurrentItem(0, true);
                    }

                }
            });




            String[] sides = {"https://i.imgur.com/BPDdsjx.png", "https://i.imgur.com/ycTTTjw.png", "https://i.imgur.com/HmXO88E.png"};

            Glide.with(context).load(sides[position]).into(imgCardSide);

            container.addView(rootView);
            return rootView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }

    private void subscribeToData() {
        viewModel.getSabias("").observe(this, listBase -> {
            //onChanged(Base<List<Posts>> listBase)
            //T1, Tn: Firebase
            if (listBase.isSuccess()) {
                sabias = listBase.getData();

            } else {
                Toast.makeText(con, ErrorMapper.getError(con, listBase.getErrorCode()),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}




