
/**
 * Beschreiben Sie hier die Klasse person.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Person
{
    private Kreis kopf;
    private Kreis lauge;
    private Kreis rauge;
    // l= linkes r= rechtes
    private Kreis lpupille;
    private Kreis rpupille;
    private Linie haar1;
    private Linie haar2;
    private Linie haar3;
    private Linie haar4;
    private Linie haar5;
    private Linie haar6;
    private Linie haar7;
    private Linie haar8;
    private Linie oberarml;
    private Linie unterarml;
    private Linie oberarmr;
    private Linie unterarmr;
    private Rechteck rumpf;
    private Rechteck hals;
    private Rechteck hosel;
    private Rechteck hosem;
    private Rechteck hoser;
    private Rechteck tascher;
    private Rechteck taschel;
    private Rechteck beinr;
    private Rechteck beinl;
    private Dreieck nase;
    private Ellipse mund;
    private Linie mundlinie;
    private boolean lebendig;
    public int XPos;
    public int YPos;
    
    public Person()
    {
      XPos=100;
      YPos=100;
        
      hals = new Rechteck();
      hals.setzeFarbe("haut");
      hals.setzeDimensionen(92,128,16,10);
      
      rumpf = new Rechteck();
      rumpf.setzeFarbe("olivgruen");
      rumpf.setzeDimensionen(70,135,60,80);
      
      oberarml = new Linie();
      oberarml.setzeFarbe("haut");
      oberarml.setzeEndpunkte(71,142,53,160);
      oberarml.setzeLinienDicke(15);
      
      unterarml = new Linie();
      unterarml.setzeFarbe("haut");
      unterarml.setzeEndpunkte(51,160,53,200);
      unterarml.setzeLinienDicke(10);
      
      oberarmr = new Linie();
      oberarmr.setzeFarbe("haut");
      oberarmr.setzeEndpunkte(129,142,147,160);
      oberarmr.setzeLinienDicke(15);
      
      unterarmr = new Linie();
      unterarmr.setzeFarbe("haut");
      unterarmr.setzeEndpunkte(149,160,147,200);
      unterarmr.setzeLinienDicke(10);
      
      kopf = new Kreis();
      kopf.setzeFarbe("haut");
      kopf.setzeRadius(30);
      kopf.setzeMittelpunkt(100,100);
      
      lauge = new Kreis();
      lauge.setzeFarbe("blau");
      lauge.setzeRadius(6);
      lauge.setzeMittelpunkt(90,93);
      
      rauge = new Kreis();
      rauge.setzeFarbe("blau");
      rauge.setzeRadius(6);
      rauge.setzeMittelpunkt(110,93);
      
      lpupille = new Kreis();
      lpupille.setzeFarbe("schwarz");
      lpupille.setzeRadius(2);
      lpupille.setzeMittelpunkt(90,93);
      
      rpupille = new Kreis();
      rpupille.setzeFarbe("schwarz");
      rpupille.setzeRadius(2);
      rpupille.setzeMittelpunkt(110,93);
      
      nase = new Dreieck();
      nase.setzeFarbe("nasenhaut");
      nase.setzeDimensionen(95,93,11,20);
      
      mund = new Ellipse();
      mund.setzeFarbe("mundfarbe");
      mund.setzeDimensionen(85,115,30,10);
      
      mundlinie = new Linie();
      mundlinie.setzeFarbe("mundlinie");
      mundlinie.setzeEndpunkte(85,119,115,119);
      
      haar1 = new Linie();
      haar1.setzeFarbe("schwarz");
      haar1.setzeEndpunkte(111,72,116,63);
      
      haar2 = new Linie();
      haar2.setzeFarbe("schwarz");
      haar2.setzeEndpunkte(107,71,110,61);
      
      haar3 = new Linie();
      haar3.setzeFarbe("schwarz");
      haar3.setzeEndpunkte(103,70,105,60);
      
      haar4 = new Linie();
      haar4.setzeFarbe("schwarz");
      haar4.setzeEndpunkte(100,70,100,60);
      
      haar5 = new Linie();
      haar5.setzeFarbe("schwarz");
      haar5.setzeEndpunkte(97,70,96,60);
      
      haar6 = new Linie();
      haar6.setzeFarbe("schwarz");
      haar6.setzeEndpunkte(93,71,90,61);
      
      haar7 = new Linie();
      haar7.setzeFarbe("schwarz");
      haar7.setzeEndpunkte(89,72,84,63);
      
      beinr = new Rechteck();
      beinr.setzeFarbe("haut");
      beinr.setzeDimensionen(74,250,18,50);
      
      beinl = new Rechteck();
      beinl.setzeFarbe("haut");
      beinl.setzeDimensionen(109,250,18,50);
      
      hosem = new Rechteck();
      hosem.setzeFarbe("cargo");
      hosem.setzeDimensionen(70,210,60,20);
      
      hosel = new Rechteck();
      hosel.setzeFarbe("cargo");
      hosel.setzeDimensionen(70,210,25,50);
      
      hoser = new Rechteck();
      hoser.setzeFarbe("cargo");
      hoser.setzeDimensionen(105,210,25,50);
      
      
      
      taschel = new Rechteck();
      taschel.setzeFarbe("cargo");
      taschel.setzeDimensionen(70,210,15,20);
      
      tascher = new Rechteck();
      tascher.setzeFarbe("cargo");
      tascher.setzeDimensionen(115,210,15,20);
      
      
      
      lebendig = true;
      
      XPos = 100;
      YPos = 100;
    }
    public void nachRechts()
    {
        if(XPos<=990)
        {
            kopf.verschieben(10,0);
            lauge.verschieben(10,0);
            rauge.verschieben(10,0);
            lpupille.verschieben(10,0);
            rpupille.verschieben(10,0);
            haar1.verschieben(10,0);
            haar2.verschieben(10,0);
            haar3.verschieben(10,0);
            haar4.verschieben(10,0);
            haar5.verschieben(10,0);
            haar6.verschieben(10,0);
            haar7.verschieben(10,0);
            oberarml.verschieben(10,0);
            unterarml.verschieben(10,0);
            oberarmr.verschieben(10,0);
            unterarmr.verschieben(10,0);
            rumpf.verschieben(10,0);
            hals.verschieben(10,0);
            hosel.verschieben(10,0);
            hosem.verschieben(10,0);
            hoser.verschieben(10,0);
            tascher.verschieben(10,0);
            taschel.verschieben(10,0);
            beinr.verschieben(10,0);
            beinl.verschieben(10,0);
            nase.verschieben(10,0);
            mund.verschieben(10,0);
            mundlinie.verschieben(10,0);
            XPos = XPos + 10;
        }
    }
    public void nachLinks()
    {
        if(XPos>=65)
        {
            kopf.verschieben(-10,0);
            lauge.verschieben(-10,0);
            rauge.verschieben(-10,0);
            lpupille.verschieben(-10,0);
            rpupille.verschieben(-10,0);
            haar1.verschieben(-10,0);
            haar2.verschieben(-10,0);
            haar3.verschieben(-10,0);
            haar4.verschieben(-10,0);
            haar5.verschieben(-10,0);
            haar6.verschieben(-10,0);
            haar7.verschieben(-10,0);
            oberarml.verschieben(-10,0);
            unterarml.verschieben(-10,0);
            oberarmr.verschieben(-10,0);
            unterarmr.verschieben(-10,0);
            rumpf.verschieben(-10,0);
            hals.verschieben(-10,0);
            hosel.verschieben(-10,0);
            hosem.verschieben(-10,0);
            hoser.verschieben(-10,0);
            tascher.verschieben(-10,0);
            taschel.verschieben(-10,0);
            beinr.verschieben(-10,0);
            beinl.verschieben(-10,0);
            nase.verschieben(-10,0);
            mund.verschieben(-10,0);
            mundlinie.verschieben(-10,0);
            XPos = XPos - 10;
        }
    }
}
