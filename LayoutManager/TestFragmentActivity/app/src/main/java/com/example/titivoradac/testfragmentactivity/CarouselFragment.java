package com.example.titivoradac.testfragmentactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarouselFragment extends Fragment {

    public static int INVALID_POSITION = -1;
    public static int mCountData = 3;
    public static ImageView line_img;

    // Todo: update this
    ImageView[] imageViews = new ImageView[mCountData];
    TextView[] textViews = new TextView[mCountData];

    public CarouselFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carousel, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HorizontalAdapter adapter = new HorizontalAdapter(this.getActivity(), imageViews, textViews);

        RecyclerView rh = (RecyclerView) view.findViewById(R.id.list_horizontal);
        initRecyclerView(rh, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), adapter);
        line_img = view.findViewById(R.id.image_line);
    }


    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final HorizontalAdapter adapter) {
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

                Log.i("check","" + adapterPosition);

                // Test
                for (int i=0; i<imageViews.length; ++i) {
                    if (i == adapterPosition) {
                        imageViews[i].setAlpha(1.0f);
                        textViews[i].setAlpha(1.0f);
                    } else {
                        imageViews[i].setAlpha(0.3f);
                        textViews[i].setAlpha(0.3f);
                    }
                }
            }
        });
    }

    private static final class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @SuppressWarnings("UnsecureRandomNumberGeneration")
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

        private int mItemsCount = mCountData;
        LayoutInflater inflater;
        ImageView[] imageViews;
        TextView[] textViews;

        HorizontalAdapter(Context context, ImageView[] ImageViews, TextView[] TextView) {
            this.context = context;
            this.imageViews = ImageViews;
            this.textViews = TextView;

            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mPosition = new int[mCountData];

            for (int i = 0; i < mCountData; ++i) {
                //noinspection MagicNumber
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
            ((RowNewsViewHolder) holder).logo_name.setText(title[position]);
            ((RowNewsViewHolder) holder).logo_img.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), image[position], null));
            line_img.setImageResource(imageLine[position]);

            // Assign image view and text view index
            imageViews[position] = ((RowNewsViewHolder) holder).logo_img;
            textViews[position] = ((RowNewsViewHolder) holder).logo_name;
        }

        @Override
        public int getItemCount() {
            return mItemsCount;
        }
    }


    public static class RowNewsViewHolder extends RecyclerView.ViewHolder {
        TextView logo_name;
        ImageView logo_img;

        public RowNewsViewHolder(View itemView) {
            super(itemView);

            logo_name = (TextView) itemView.findViewById(R.id.name_item);
            logo_img = (ImageView) itemView.findViewById(R.id.profilePicture);

        }
    }

}
