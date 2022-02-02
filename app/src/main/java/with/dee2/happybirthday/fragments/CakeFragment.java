package with.dee2.happybirthday.fragments;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.util.Calendar;
import java.util.HashMap;

import androidx.recyclerview.widget.RecyclerView;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import with.dee2.happybirthday.R;

public class CakeFragment extends Fragment {

    private TextView monthYearText;
    private RecyclerView calenderRecyclerView;
    private CustomCalendar customCalendar;
    private LottieAnimationView lottieAnimationView;
    View v;
    private KonfettiView konfettiView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_cake, container, false);


        customCalendar=v.findViewById(R.id.custom_calendar);
        konfettiView= v.findViewById(R.id.viewKonfetti);

        HashMap<Object, Property> descHashMap =new HashMap<>();
        Property defaultProperty =new Property();
        defaultProperty.layoutResource =R.layout.default_view;
        defaultProperty.dateTextViewResource=R.id.text_view;
        descHashMap.put("default",defaultProperty);

        Property currentProperty =new Property();
        currentProperty.layoutResource=R.layout.current_view;
        currentProperty.dateTextViewResource=R.id.current_text_view;
        descHashMap.put("current",currentProperty);


        Property presentProperty =new Property();
        presentProperty.layoutResource=R.layout.present_view;
        presentProperty.dateTextViewResource=R.id.present_text_view;
        descHashMap.put("present",presentProperty);

        Property absentProperty =new Property();
        absentProperty.layoutResource=R.layout.absent_view;
        absentProperty.dateTextViewResource=R.id.absent_text_view;
        descHashMap.put("absent",absentProperty);

        customCalendar.setMapDescToProp(descHashMap);

        HashMap<Integer,Object> dateHashMap =new HashMap<>();
        Calendar calender = Calendar.getInstance();
        dateHashMap.put(calender.get(Calendar.DAY_OF_MONTH),"current");
        dateHashMap.put(2,"absent");

        customCalendar.setDate(calender,dateHashMap);

        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                if(selectedDate.getTime().toString().contains("Feb 02")){
                    Log.d("맞아요 ","하하핳");

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
                }else{
                    Toast.makeText(getContext(),"2월 2일 내 생일 눌러",Toast.LENGTH_SHORT).show();
                }

            }
        });



        return v;
    }



}