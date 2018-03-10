package com.example.kelly.logeasyfinal;

import android.os.Parcel;
import android.os.Parcelable;

public class ClassCurso implements Parcelable {

    private int curso_id;
    private String cursoName;
    private String descricao;

    public ClassCurso(){
        curso_id = 0;
        cursoName="";
        descricao="";
    }

    public ClassCurso(Parcel in) {
        readFromParcel(in);
    }

    public ClassCurso(int c_id, String c_name, String desc){
        curso_id=c_id;
        cursoName=c_name;
        descricao=desc;
    }

    public String getCursoname() {
        return cursoName;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCurso_id(int c_id) {
        this.curso_id = c_id;
    }

    public void setCursoname(String cursoname) {
        this.cursoName = cursoname;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(curso_id);
        out.writeString(cursoName);
        out.writeString(descricao);
    }

    private void readFromParcel(Parcel in) {
        curso_id = in.readInt();
        cursoName = in.readString();
        descricao = in.readString();
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        public ClassCurso createFromParcel(Parcel in) {
            return new ClassCurso(in);
        }
        public ClassCurso[] newArray(int size) {
            return new ClassCurso[size];
        }
    };
}
