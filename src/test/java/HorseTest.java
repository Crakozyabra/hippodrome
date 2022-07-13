import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullFirstParamIllegalArgumentExceptionInConstructorAndFailMessageException(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{new Horse(null,1.0,1.0);});
        assertEquals("Name cannot be null.",exception.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = { "", " ", "\t","\n" })
    void blankNameIllegalArgumentExceptionInConstructorAndFailMessageException(String str) {
        Exception exception = assertThrows(IllegalArgumentException.class,()->{new Horse(str,1.0,1.0);});
        assertEquals("Name cannot be blank.",exception.getMessage());
    }


    @Test
    public void negativeSecondParamIllegalArgumentExceptionInConstructorAndFailMessageException(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{new Horse("name",-1.0,1.0);});
        assertEquals("Speed cannot be negative.",exception.getMessage());
    }


    @Test
    public void negativeThirdParamIllegalArgumentExceptionInConstructorAndFailMessageException(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{new Horse("name",1.0,-1.0);});
        assertEquals("Distance cannot be negative.",exception.getMessage());
    }


    @Test
    public void getName(){
        String expectedString = "Bob";
        Horse horse = new Horse(expectedString,1.0,1.0);
        assertEquals(expectedString, horse.getName());
    }


    @Test
    public void getSpeed(){
        double expectedSpeed = 7.0;
        Horse horse = new Horse("Bob",expectedSpeed,1.0);
        assertEquals(expectedSpeed, horse.getSpeed());
    }


    @Test
    public void getDistance(){
        double expectedDistance = 7.0;
        Horse horse = new Horse("Bob",1.0,expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }


    @Test
    public void getDistanceByDefault(){
        Horse horse = new Horse("Bob",1.0);
        assertEquals(0, horse.getDistance());
    }


    @Test
    public void move(){
        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)){
            new Horse("Bob",5,7).move();
            horseMockedStatic.verify(()->Horse.getRandomDouble(eq(0.2),anyDouble()));
        }
    }

}
