
public class baum
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private Kreis baumkrone;
    private Rechteck stamm;

    private StaticTools ST;

    public baum()
    {
       
        stamm = new Rechteck();
        stamm.setzeFarbe("braun");
        stamm.setzeGroesse(25,55);

        baumkrone = new Kreis();
        baumkrone.setzeFarbe("dunkelgruen");
        baumkrone.setzeRadius(23);

          
      
      
       
        }
    
    public void setzePosition(int X, int Y)
    {
        
        stamm. setzePosition(X,Y);
        baumkrone.setzeMittelpunkt(X+13,Y-10);
    }
    
    
    
    
    
    } 
   