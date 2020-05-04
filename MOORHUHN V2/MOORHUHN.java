

public class MOORHUHN
{
    private Kreis kopf;
    private Kreis lauge;
    private Kreis rauge;
    // l= linkes r= rechtes
    private Kreis lpupille;
    private Kreis rpupille;
    private Dreieck schnabel;
    private boolean lebendig;
    public int XPos;
    public int YPos;
    
    public MOORHUHN()
    {
        
      kopf = new Kreis();
      kopf.setzeFarbe("braun");
      kopf.setzeRadius(21);
      kopf.setzeMittelpunkt(100,100);
      
      lauge = new Kreis();
      lauge.setzeFarbe("orange");
      lauge.setzeRadius(6);
      lauge.setzeMittelpunkt(92,93);
      
      rauge = new Kreis();
      rauge.setzeFarbe("orange");
      rauge.setzeRadius(6);
      rauge.setzeMittelpunkt(108,93);
      
      lpupille = new Kreis();
      lpupille.setzeFarbe("schwarz");
      lpupille.setzeRadius(2);
      lpupille.setzeMittelpunkt(92,93);
      
      rpupille = new Kreis();
      rpupille.setzeFarbe("schwarz");
      rpupille.setzeRadius(2);
      rpupille.setzeMittelpunkt(108,93);
      
      schnabel = new Dreieck();
      schnabel.setzeAusrichtung("S");
      schnabel.setzePosition(95,102);
      schnabel.setzeGroesse(11,15);
      schnabel.setzeFarbe("orange");
      
      
      lebendig = true;
      
      XPos = 100;
      YPos = 100;
    }
    
    public void NewGame()
    {
        XPos = 100;
        YPos = 100;
       ;
     
        
    }
    
    public void nachRechts ()
    {
      kopf.verschieben(5,0);
      lauge.verschieben(5,0);
      rauge.verschieben(5,0);
      lpupille.verschieben(5,0);
      rpupille.verschieben(5,0);
      schnabel.verschieben(5,0);
      XPos = XPos + 5;
    }
    public void nachLinks()
    {
      kopf.verschieben(-5,0);  
      lauge.verschieben(-5,0);
      rauge.verschieben(-5,0);
      lpupille.verschieben(-5,0);
      rpupille.verschieben(-5,0);
      schnabel.verschieben(-5,0);
      XPos = XPos - 5;
    }
    public void nachOben ()
    {
      kopf.verschieben(0,-5);  
      lauge.verschieben(0,-5);
      rauge.verschieben(0,-5);
      lpupille.verschieben(0,-5);
      rpupille.verschieben(0,-5);
      schnabel.verschieben(0,-5);
      YPos = YPos - 5;
    }
    public void nachUnten()
    {
      kopf.verschieben(0,5);
      lauge.verschieben(0,5);
      rauge.verschieben(0,5);
      lpupille.verschieben(0,5);
      rpupille.verschieben(0,5);
      schnabel.verschieben(0,5);
      YPos = YPos + 5;
    }
    
    public void getroffen()
    {
      kopf.setzeFarbe("grau"); 
      kopf.setzeMittelpunkt(50,500);
      lauge.setzeMittelpunkt(42, 493);
      rauge.setzeMittelpunkt(58, 493);
      lpupille.setzeMittelpunkt(42, 493);
      rpupille.setzeMittelpunkt(58, 493);
      schnabel.setzePosition(45, 502);
      lebendig = false;
    }
    
    public boolean istlebendig()
    {
        return lebendig;
    }
    
    public int gibXPos()
    {
       return XPos; 
    }
    
    public int gibYPos()
    {
        return YPos;
    }
}
