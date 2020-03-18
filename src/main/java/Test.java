import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Test {

    @Timer()
    public void printName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Name printer");
    }

    @Timer
    public void printLastName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("LastName printer");
    }

    @Timer
    public void printAge() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Age printer");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Test test = new Test();
        Method[] methods = test.getClass().getDeclaredMethods();

        for (Method method : methods) {
            Annotation[] methodAnnotation = method.getAnnotations();
            for (Annotation annotation : methodAnnotation) {
                if (annotation instanceof Timer) {
                    long startTime = Instant.now().toEpochMilli();
                    method.invoke(test);
                    long endTime = Instant.now().toEpochMilli();
                    System.out.println(endTime - startTime);
                }
            }
        }
    }
}
