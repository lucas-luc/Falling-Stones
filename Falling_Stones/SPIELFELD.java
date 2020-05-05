
/**
 * Beschreiben Sie hier die Klasse spielfeld.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SPIELFELD
{
   private Rechteck hintergrund; 
 
    
   public SPIELFELD()
    {
        hintergrund = new Rechteck();
        hintergrund.setzeFarbe("gruen");
        hintergrund.setzeGroesse(580,300);
        hintergrund.setzePosition(0,0);
       
       
   }
}
