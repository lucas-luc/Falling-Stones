

public class FADENKREUZ
{
    private Kreis kreis;
    private Kreis kaussen;
    private MOORHUHN huhn;
    private SPIELFELD feld;
    public int FXPos;
    public int FYPos;
    public FADENKREUZ()
    {
      kreis = new Kreis();
      kreis.setzeFarbe("schwarz");
      kreis.setzeGroesse(3);
      kreis.setzeMittelpunkt(290,150);
      kaussen = new Kreis();
      kaussen.setzeFarbe("schwarz");
      kaussen.setzeRadius(22);
      kaussen.rand();
      kaussen.setzeMittelpunkt(290,150);

      
      FXPos = 290;
      FYPos = 150;
    }
    
    
    
    public void reset()
    {
        kaussen.setzeMittelpunkt(290,150);
        kreis.setzeMittelpunkt(290,150);
        FXPos = 290;
        FYPos = 150;
    }
    public void nachRechts ()
    {
      kreis.verschieben(5,0);  
      kaussen.verschieben(5,0);
      FXPos = FXPos + 5;
    }
    public void nachLinks()
    {
      kreis.verschieben(-5,0); 
      kaussen.verschieben(-5,0);
      FXPos = FXPos - 5;
    }
    public void nachOben ()
    {
      kreis.verschieben(0,-5);
      kaussen.verschieben(0,-5);
      FYPos = FYPos - 5;
    }
    public void nachUnten()
    {
      kreis.verschieben(0,5);
      kaussen.verschieben(0,5);
      FYPos = FYPos + 5;
    }
    
    public int gibFXPos()
    {
       return FXPos; 
    }
    
    public int gibFYPos()
    {
        return FYPos;
    }
}
