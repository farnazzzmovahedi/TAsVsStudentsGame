package HeroesP;

public class NormalStudent extends StudentHeroes
{

    public NormalStudent()
    {
        super("normal", 50, 20, 50, 10);
    }
    public static StudentHeroes normalStudent = new NormalStudent();
}
