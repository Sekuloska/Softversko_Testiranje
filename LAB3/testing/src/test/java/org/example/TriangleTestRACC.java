package org.example;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class TriangleTestRACC {
    Triangle triangle=new Triangle();

    public TriangleTestRACC() {
    }

   // @Before
    public void setUp(){
        this.triangle=new Triangle();
    }

    @Test
    public void test1(){
        //1: T T T -> T
        triangle.makeClauseATrue();
        triangle.makeClauseBTrue();
        triangle.makeClauseCTrue();
        assertTrue(this.triangle.isTriangle());

    }

    @Test
    public void test5(){
        //5: F T T -> F
        triangle.makeClauseBTrue();
        triangle.makeClauseCTrue();
        assertFalse(this.triangle.isTriangle());

    }

    @Test
    public void test3(){
        //3: T F T -> F
        triangle.makeClauseATrue();
        triangle.makeClauseCTrue();
        assertFalse(this.triangle.isTriangle());

    }

    @Test
    public void test2(){
        //2: T T F -> F
        triangle.makeClauseATrue();
        triangle.makeClauseBTrue();
        assertFalse(this.triangle.isTriangle());

    }

}
