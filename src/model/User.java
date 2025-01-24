package model;

import java.util.ArrayList;

public class User {

    private int id;
    private String nume;
    private String prenumel;
    private int varsta;
    private ArrayList<Post> postari;

    public User(int id, String nume, String prenumel, int varsta) {
        this.id = id;
        this.nume = nume;
        this.prenumel = prenumel;
        this.varsta = varsta;
        postari = new ArrayList<>();
    }

    public ArrayList<Post> getPostari() {
        return postari;
    }

    public void setPostari(ArrayList<Post> postari) {
        this.postari = postari;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenumel() {
        return prenumel;
    }

    public void setPrenumel(String prenumel) {
        this.prenumel = prenumel;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenumel='" + prenumel + '\'' +
                ", varsta=" + varsta +
                ", postari=" + postari +
                '}';
    }
}
