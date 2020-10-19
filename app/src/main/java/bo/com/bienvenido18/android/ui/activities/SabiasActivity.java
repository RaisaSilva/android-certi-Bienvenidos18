package bo.com.bienvenido18.android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer;

import java.util.ArrayList;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.ui.fragments.sabiasCardFragment;
import me.relex.circleindicator.CircleIndicator;

public class SabiasActivity extends AppCompatActivity {

    ViewPager sabiasViewPager;
    private sabiasPagerAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabias);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

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
                    /*
                    sabiasCardFragment frag =  sabiasCardFragment.newInstance("Hola dd", "Benjamin dd", R.color.design_default_color_background);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.cardViewPager, frag);
                    transaction.commit();

                     */
                }
            });




            String[] sides = {"https://i.imgur.com/BPDdsjx.png","https://i.imgur.com/ycTTTjw.png","https://i.imgur.com/HmXO88E.png"};
            Glide.with(context).load(sides[position]).into(imgCardSide);

            container.addView(rootView);
            return rootView;

        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}