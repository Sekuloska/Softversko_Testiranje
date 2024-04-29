package org.example;

public class Triangle {
    boolean clauseA=false;
    boolean clauseB=false;
    boolean clauseC=false;

    public Triangle() {
    }

    public void makeClauseATrue(){
        this.clauseA=true;
    }

    public void makeClauseBTrue(){
        this.clauseB=true;
    }

    public void makeClauseCTrue(){
        this.clauseC=true;
    }

    public boolean isTriangle(){
        if(this.clauseA && this.clauseB && this.clauseC){
            return true;
        }
        return false;
    }
}
