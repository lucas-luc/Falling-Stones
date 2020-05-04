
import java.util.Random;

public class SPIELFELD implements ITuWas, ITastatur
{
    
    private Rechteck hintergrund; 
    private MOORHUHN huhn;
    private FADENKREUZ fadenk;
    private Random zzgenerator;
    private StaticTools st;
    private Taste NachLinksF;
    private Taste NachRechtsF;
    private Taste NachObenF;
    private Taste NachUntenF;
    private Taste SchussF;
    private Taste Reset;
    private Tastatur myTastatur;
    private Rechteck Anfang;
    private baum baum1;
    private baum baum2;
    private baum baum3;
    private baum baum4;
    private baum baum5;
    private baum baum6;
    private baum baum7;
    public SPIELFELD()
    {
        hintergrund = new Rechteck();
        hintergrund.setzeFarbe("gruen");
        hintergrund.setzeGroesse(580,300);
        hintergrund.setzePosition(0,0);
        
        baum1 = new baum();
        baum2 = new baum();
        baum3 = new baum();
        baum4 = new baum();
        baum5 = new baum();
        baum6 = new baum();
        baum7 = new baum();
        
        baum1.setzePosition( 258,370);
        baum2.setzePosition( 200,200);
        baum3.setzePosition( 300,100);
        baum4.setzePosition( 500,230);
        baum5.setzePosition( 470,80);
        baum6.setzePosition( 59,90);
        baum7.setzePosition( 143,142);
        
        huhn = new MOORHUHN();
        fadenk = new FADENKREUZ();
        zzgenerator = new Random();
        
        
        //Tasten
        // NachLinksF = new Taste("Links",100,350,95,40);
        // NachLinksF.setzeLink(this,1);
        // SchussF = new Taste("Schuss",195,350,95,40);
        // SchussF.setzeLink(this,2);
        // NachRechtsF = new Taste("Rechts",290,350,95,40);
        // NachRechtsF.setzeLink(this,3);
        // NachUntenF = new Taste("Runter",195,390,95,40);
        // NachUntenF.setzeLink(this,4);
        // NachObenF = new Taste("Hoch",195,310,95,40);
        // NachObenF.setzeLink(this,5);
         Reset = new Taste ("Aimbot", 350,430,95,40);
         Reset.setzeLink(this,6);
        
        //Anfangsbildschirm
        //Anfang = new Rechteck();
        //Anfang.setzeFarbe("schwarz");
        //Anfang.setzeGroesse(1000,1000);
        //Anfang.setzePosition(0,0);

        //Tastatur
        myTastatur = new Tastatur();
        myTastatur.setzeLink(this);
        myTastatur.meldeAnTaste("W","W");
        myTastatur.meldeAnTaste("A","A");
        myTastatur.meldeAnTaste("S","S");
        myTastatur.meldeAnTaste("D","D");
        myTastatur.meldeAnTaste("SPACE","SPACE");
        myTastatur.meldeAnTaste("ENTER","ENTER");
        
        
        
        
        bewegen();
    }

   
    public void Aimbot()
    {
        fadenk.FXPos = huhn.XPos;
        fadenk.FYPos = huhn.XPos;
       
        schuss(); 
    }
    
    public void NewGame()
    {
        fadenk.reset();
        huhn.NewGame();
        huhn= new MOORHUHN();
        bewegen();
    }
    public void bewegen()
    {
      while(huhn.istlebendig() == true)
      {int zz = zzgenerator.nextInt(4);
       st.warte(1000);
       switch(zz)
       {
         case 0:
              if (huhn.gibYPos() > 30)
              {
                huhn.nachOben();
              }
              else
              {
                huhn.nachUnten();
              }
            break;
         case 1:
              if (huhn.gibXPos() < 550)
              {
                huhn.nachRechts();
              }
              else 
              {
                 huhn.nachLinks(); 
              }  
              break;
         case 2:
              if (huhn.gibYPos() < 270)
              {
                 huhn.nachUnten();   
              }
              else
              {
                 huhn.nachOben();  
              }
                break;
         default:
              if (huhn.gibXPos() > 30)
              {
                  huhn.nachLinks();  
              }
              else
              {
                 huhn.nachRechts();  
              }
       }
          
      }
    }
    
    
    

    
    public void schuss()
    {
        if (huhn.gibXPos() == fadenk.gibFXPos() && huhn.gibYPos() == fadenk.gibFYPos())
        {
           
             huhn.getroffen();  
             
           
        }
    }
    
    public void tuWas(int ID)
    {
        if(ID==1)
        {
            fadenk.nachLinks(); 
        }
        if(ID==2)
        {
          schuss();  
        }
         if(ID==3)
        {
          fadenk.nachRechts();  
        }
         if(ID==4)
        {
          fadenk.nachUnten();
        }
           if(ID==5)
        {
          fadenk.nachOben();  
        }
        
        if(ID==6)
        {
         
         Aimbot();
           
    
        }
    }
    public static void main(String[] args)
    {
      new SPIELFELD(); 
    }
    
    public void tastenAktion(String rueckgabe)
    {
       if(rueckgabe == "W")
       {
         fadenk.nachOben();  
       }
       if(rueckgabe == "A")
       {
         fadenk.nachLinks();  
       }
       if(rueckgabe == "S")
       {
         fadenk.nachUnten();  
       }
       if(rueckgabe == "D")
       {
         fadenk.nachRechts();  
       }
       if(rueckgabe == "SPACE")
       {
         schuss();  
       }
       if(rueckgabe == "ENTER")
       {
          NewGame(); 
        }
    }
}
