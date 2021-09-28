package com.kstudio.quizapp;

public class usera {
    public String name,semester,branch,sect;


    public usera() {

    }

    public usera(String name, String semester, String branch, String sect) {
        this.name = name;
        this.semester = semester;
        this.branch = branch;
        this.sect = sect;
    }

    public usera(String toString, String toString1) {
        this.name = toString;
        this.semester = toString1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSect() {
        return sect;
    }

    public void setSect(String secti) {
        this.sect = secti;
    }
}
