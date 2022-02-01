package with.dee2.happybirthday.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import with.dee2.happybirthday.R;

public class CakeFragment extends Fragment {

    KonfettiView konfettiView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_cake, container, false);

        konfettiView= v.findViewById(R.id.viewKonfetti);


        konfettiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                konfettiView.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA,Color.CYAN, Color.BLUE)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE,Shape.CIRCLE.INSTANCE)
                        .addSizes(new Size(12, 5f))
                        .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                        .streamFor(300, 50000L);
            }
        });

        return v;
    }
}