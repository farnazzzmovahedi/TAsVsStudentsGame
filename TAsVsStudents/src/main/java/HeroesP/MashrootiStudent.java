package HeroesP;

public class MashrootiStudent extends StudentHeroes
{

    public MashrootiStudent()
    {
        super("mashrooti", 35, 35, 65, 15);
    }
    public static StudentHeroes failedStudent = new MashrootiStudent();
}
