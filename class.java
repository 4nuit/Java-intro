public class MaClasse
{

  private int n;

  private static int m;

  private static final int CONSTANTE = 0;


  public MaClasse()
  {
    this.n = 0;
    MaClasse.m = 0;
  }



  public int getN()
  {
    return this.n;
  }



  public static int getM()
  {
    return MaClasse.m;
  }
}
