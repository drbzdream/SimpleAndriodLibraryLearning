package com.fragment.navigation.jabed.apraisesend;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;


import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarouselPreviewActivity extends AppCompatActivity {

    public static int INVALID_POSITION = -1;

    public static String temp = "";
    //private TextView showPosition;
    public static  ImageView line_img;

    // Todo: update this
    ImageView[] imageViews = new ImageView[3];

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_carousel_preview);

        //final ActivityCarouselPreviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_carousel_preview);

        Toolbar tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);

        final HorizontalAdaptar adapter = new HorizontalAdaptar(this, imageViews);

        // dream
        //final VerticalAdaptar verticalAdaptar= new VerticalAdaptar(this);

        RecyclerView rh = (RecyclerView) findViewById(R.id.list_horizontal);
        //RecyclerView rv = (RecyclerView) findViewById(R.id.list_vertical);
        // create layout manager with needed params: vertical, cycle
        initRecyclerView(rh, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), adapter);



        line_img = (ImageView) findViewById(R.id.image_line);

        //line_img.setImageResource(R.drawable.line_ali);


        //initVerRecyclerView(rv, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), verticalAdaptar);

    }

    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final HorizontalAdaptar adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (INVALID_POSITION != adapterPosition) {
                    final int value = adapter.mPosition[adapterPosition];
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
                }

                //ImageView test = (ImageView) findViewById(R.id.profilePicture);
                //test.setAlpha(1.0f);
                Log.i("check","" + adapterPosition);

                // Test
                for (int i=0; i<imageViews.length; ++i) {

                    if (i == adapterPosition)
                        imageViews[i].setAlpha(1.0f);
                    else
                        imageViews[i].setAlpha(0.5f);
                }
            }
        });
    }

//    }

    private static final class HorizontalAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @SuppressWarnings("UnsecureRandomNumberGeneration")
       // private final Random mRandom = new Random();
        //private final int[] mColors;
        private final int[] mPosition;
        private Context context;

        private final int[] image = {
                R.drawable.kplus,
                R.drawable.alipay,
                R.drawable.wechatpay,


        };
        private final String[] title = {
                "K PLUS",
                "Alipay",
                "WeChat Pay",

        };

        private  final int[] imageLine = {
                R.drawable.line_kplus,
                R.drawable.line_ali,
                R.drawable.line_wechatpay,
            };

        private int mItemsCount = 3;
        LayoutInflater inflater;
        ImageView[] imageViews;

        HorizontalAdaptar(Context context, ImageView[] ImageViews) {
            this.context = context;
            this.imageViews = ImageViews;

            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //mColors = new int[3];
            mPosition = new int[3];

            for (int i = 0; i < 3; ++i) {
                //noinspection MagicNumber
               // mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
                mPosition[i] = i;

            }


        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = inflater.inflate(R.layout.item_view, null);
            RecyclerView.ViewHolder holder = new RowNewsViewHolder(view);
            return holder;

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((RowNewsViewHolder) holder).cItem1.setText(title[position]);
            ((RowNewsViewHolder) holder).pp.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), image[position], null));

            // Assign image view index
            imageViews[position] = ((RowNewsViewHolder) holder).pp;

            //((RowNewsViewHolder) holder).pp.setAlpha(1.0f);
            //((RowNewsViewHolder) holder).pp.setBackgroundResource(image[position]);
            //((RowNewsViewHolder) holder).cItem2.setText(String.valueOf(mPosition[position]));

            //holder.itemView.setBackgroundColor(mColors[position]);


            temp = Integer.toString(position);
            line_img.setImageResource(imageLine[position]);
            //((RowNewsViewHolder) holder).showPosition.setText(temp);
            //((RowNewsViewHolder) holder).line_img.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), imageLine[position], null));
        }

        @Override
        public int getItemCount() {
            return mItemsCount;
        }
    }


    public static class RowNewsViewHolder extends RecyclerView.ViewHolder {
        //CircleImageView apraisorProfilePic;
        TextView cItem1;
        ImageView pp;
        //TextView showPosition;

        //ImageView line_img;

        public RowNewsViewHolder(View itemView) {
            super(itemView);

            cItem1 = (TextView) itemView.findViewById(R.id.c_item_1);
            pp = (ImageView) itemView.findViewById(R.id.profilePicture);
            // cItem2 = (TextView) itemView.findViewById(R.id.c_item_2);


            //dream
            //line_img = (ImageView) itemView.findViewById(R.id.image_line);
            //showPosition = (TextView) itemView.findViewById(R.id.showposition);

        }
    }

}