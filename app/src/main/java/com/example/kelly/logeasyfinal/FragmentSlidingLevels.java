package com.example.kelly.logeasyfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kelly.logeasyfinal.modelo.Aluno;
import com.example.kelly.logeasyfinal.modelo.Curso;
import com.example.kelly.logeasyfinal.modelo.CursoAluno;
import com.example.kelly.logeasyfinal.persistencia.MySQLiteHelper;
import com.example.kelly.logeasyfinal.modelo.Conteudo;
import com.example.kelly.logeasyfinal.modelo.User;

public class FragmentSlidingLevels extends Fragment {
    private ViewPager mViewPager;
    private CursoAluno userScore;
    private Conteudo chosenLevel;
    private int pointsU;
    private Aluno aluno;
    private Curso curso;

    MySQLiteHelper db;
    Intent intent = new Intent();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        aluno = (Aluno) getArguments().getParcelable("chosenUser");

        db = new MySQLiteHelper(getActivity());

        userScore = db.getCursoAluno(aluno.getId());
        pointsU = userScore.getPontuacao();

        // acha o layout da onde vem a page
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return Integer.toString(position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = getActivity().getLayoutInflater().inflate(R.layout.level_page, container, false);
            container.addView(view);

            if(position==0)
                setDisplay(view,View.VISIBLE, View.GONE,position+1);
            else
                setDisplay(view,View.GONE, View.VISIBLE,position+1);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    public void setIntent(int a) {

        if (pointsU>=(chosenLevel.getNivel().getQtdPontosFinal())){
            intent = new Intent(getActivity(), ActivityQuiz.class);
        } else {
            intent = new Intent(getActivity(), ActivityLesson.class);
        }

        chosenLevel = userScore.getConteudo();

        intent.putExtra("chosenUser", (Parcelable) aluno);
        intent.putExtra("chosenLevel", (Parcelable) chosenLevel);
        intent.putExtra("userScore", (Parcelable) userScore);
        startActivity(intent);
    }

    public void setDisplay(View vw,int part1, int part2,int position){
        ImageButton btn;
        RelativeLayout layout = (RelativeLayout) vw.findViewById(R.id.boardLevel);

        for(int i=0;i<5;i++){
            btn = (ImageButton) vw.findViewById(getResources().getIdentifier("imbLevel"+String.valueOf(i+1),"id", getActivity().getPackageName()));
            btn.setOnClickListener(new viewT(i));
        }

        for(int i=5;i>userScore.getConteudo().getNivel().getOrdem();i--){
            btn = (ImageButton) vw.findViewById(getResources().getIdentifier("imbLevel"+String.valueOf(i),"id", getActivity().getPackageName()));
            btn.setImageResource(getResources().getIdentifier("level" + String.valueOf(i) + "d", "drawable", getActivity().getPackageName()));
        }

        for(int i=1; i<=3; i++){
            btn = (ImageButton) vw.findViewById(getResources().getIdentifier("imbLevel"+String.valueOf(i),"id", getActivity().getPackageName()));
            btn.setVisibility(part1);
        }
        for(int i=4; i<=5; i++){
            btn = (ImageButton) vw.findViewById(getResources().getIdentifier("imbLevel"+String.valueOf(i),"id", getActivity().getPackageName()));
            btn.setVisibility(part2);
        }

        layout.setBackgroundResource(getResources().getIdentifier("pagina"+String.valueOf(position),"drawable", getActivity().getPackageName()));
    }

    public void setToast() {
        Toast.makeText(getActivity(), "Desculpe, mas você não tem pontos suficientes para acessar este nível!", Toast.LENGTH_SHORT).show();
    }

    class viewT implements View.OnClickListener {
        private int i;

        public viewT(int valor){
            i = valor;
        }

        @Override
        public void onClick(View v) {
            if (pointsU >= userScore.getConteudo().getNivel().getQtdPontosFinal()) {
                //if (i < 2) {
                    setIntent(userScore.getConteudo().getNivel().getOrdem() + 1);
                //}
                //else{
                //    setToast();
                //}
            }
            else
                setToast();
        }
    }


}
