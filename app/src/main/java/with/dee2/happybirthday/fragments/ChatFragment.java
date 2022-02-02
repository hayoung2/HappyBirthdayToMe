package with.dee2.happybirthday.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import with.dee2.happybirthday.CustomBaseAdapter;
import with.dee2.happybirthday.MessageActivity;
import with.dee2.happybirthday.R;


public class ChatFragment extends Fragment {

    String[] names={"이재용", "제니", "카카오사장","스티븐잡스","비트코인","김정은","카디비"};
    int[] imges={R.drawable.samsung,R.drawable.je,R.drawable.kakao,R.drawable.iphone,R.drawable.bitcoin,R.drawable.kim_j,R.drawable.ka};
    String[] times ={"오후 4:25","오후 12:11","오전 7:42","오전 1:38","오전 12:42","오전 12:00","오전 12:00"};
    String[] messages={"ㅎㅇ 나 이재용ㅋ 생일 축하합니다","I'm 제니 Happy Birthday~~","\uD83C\uDF89 카카오주식 선물 드릴게요 \uD83C\uDF89 ","아이폰 당첨되셨습니다"
            ,"하영아 비트코인이다 생일축하한다","나 김정은 요즘 다이어트한다 생일 축하해","HI ㅅ..ㅐㅇ.이..ㄹ..추카..ㅎㅔ ㅎㅘ용!"};

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_chat, container, false);
        listView=v.findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter=new CustomBaseAdapter(getContext(),names,imges,times,messages);
        listView.setAdapter(customBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}