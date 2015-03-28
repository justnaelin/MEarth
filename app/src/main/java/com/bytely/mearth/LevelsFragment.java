package com.bytely.mearth;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class LevelsFragment extends Fragment {

    private CardView mLevelOneCard;
    private CardView mLevelTwoCard;
    private CardView mLevelThreeCard;

    private ImageView mLevelOneImage;
    private TextView mLevelOneTitle;

    private ImageView mLevelTwoImage;
    private TextView mLevelTwoTitle;

    private ImageView mLevelThreeImage;
    private TextView mLevelThreeTitle;


    private Communicator comm;


    public LevelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_levels, container, false);
        mLevelOneCard = (CardView) view.findViewById(R.id.level_one);
        mLevelTwoCard = (CardView) view.findViewById(R.id.level_two);
        mLevelThreeCard = (CardView) view.findViewById(R.id.level_three);

        comm = (Communicator) getActivity();

        mLevelOneImage = (ImageView) view.findViewById(R.id.level_one_image);
        mLevelOneTitle = (TextView) view.findViewById(R.id.level_one_title);
        mLevelTwoImage = (ImageView) view.findViewById(R.id.level_two_image);
        mLevelTwoTitle =(TextView) view.findViewById(R.id.level_two_title);
        mLevelThreeImage = (ImageView) view.findViewById(R.id.level_three_image);
        mLevelThreeTitle = (TextView) view.findViewById(R.id.level_three_title);

        Bitmap levelOneLogo = BitmapFactory.decodeResource(getResources(), R.drawable.individual_logo);
        Bitmap circleIconOne = getRoundedShape(levelOneLogo);

        Bitmap levelTwoLogo = BitmapFactory.decodeResource(getResources(), R.drawable.family_logo);
        Bitmap circleIconTwo = getRoundedShape(levelTwoLogo);

        Bitmap levelThreeLogo = BitmapFactory.decodeResource(getResources(), R.drawable.community_logo);
        Bitmap circleIconThree = getRoundedShape(levelThreeLogo);

        mLevelOneImage.setImageBitmap(circleIconOne);
        mLevelOneTitle.setText(R.string.level_one);

        mLevelTwoImage.setImageBitmap(circleIconTwo);
        mLevelTwoTitle.setText(R.string.level_two);

        mLevelThreeImage.setImageBitmap(circleIconThree);
        mLevelThreeTitle.setText(R.string.level_three);

        mLevelOneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               comm.runLevelOne();
            }
        });

        mLevelTwoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.runLevelTwo();
            }
        });

        mLevelThreeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.runLevelThree();
            }
        });


        return view;
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 200;
        int targetHeight = 200;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }
}
