package com.example.kelly.logeasyfinal.util;

import com.example.kelly.logeasyfinal.modelo.CursoAluno;

import java.util.Comparator;

/**
 * Created by Kelly on 05/04/2018.
 */

public class ComparatorCursoAluno implements Comparator<CursoAluno>{
    public int compare(CursoAluno c1, CursoAluno c2) {
        if (c1.getPontuacao() > c2.getPontuacao()) return -1;
        else if (c1.getPontuacao() < c2.getPontuacao()) return +1;
        else {
            if(c1.getPercentualErro() < c2.getPercentualErro()) return -1;
            else if(c1.getPercentualErro() > c2.getPercentualErro()) return +1;
        }
        return 0;
    }
}
