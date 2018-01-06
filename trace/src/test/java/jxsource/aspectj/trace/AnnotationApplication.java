package jxsource.aspectj.trace;

public class AnnotationApplication {
    public static void main(String[] args) {
        new AnnotationApplication().doSomething(11);
        new AnnotationApplication().doSomething(-22);
        new AnnotationApplication().doSomething(333);
    }

    public void doSomething(int number) {
        System.out.println("Doing something with number " + number);
    }
}
