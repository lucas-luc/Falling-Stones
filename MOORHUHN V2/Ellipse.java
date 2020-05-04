
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * <h1>Ellipse, auch als Bogen einsetzbar</h1>
 * Startwinkel und Bogenlaenge im Gradmass
 * <hr>
 * @author Hans Witt
 * 
 * @version
 * Version: 1.0 (19.7.2008)<br />
 * 
 * Version: 3 (9.8.2008) 
 *        Containerklasse fuer GUI-Elemente<br />
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst<br />		
 *  
 * Version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt <br />
 * Version 5.0: (4.9.2010
 *  	   Entfernen fr Graphikkomponente eingefhrt<br />
 *  
 *   	   Destruktor entfernt Graphikkomponente automatisch bei gc()<br />
 *   
 */
public class Ellipse implements IComponente {
	
	private CEllipse	obj;
	protected int		breite		= 0;
	protected int		hoehe		= 0;
	protected int		xPos		= 0;
	protected int		yPos		= 0;
	
	protected int		startwinkel	= 0;
	protected int		bogenlaenge	= 360;
	
	protected boolean	sichtbar	= true;
	protected boolean	gefuellt	= true;
	protected String	farbe		= StaticTools.leseNormalfarbe();
	
	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Ellipse() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Ellipse(int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe);
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Ellipse(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Ellipse(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}
	
	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Ellipse(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(behaelter, neuesX, neuesY, neueBreite, neueHoehe, 0, 360);
	}
	
	public Ellipse(int neuesX, int neuesY, int neueBreite, int neueHoehe,
			int neuerStartwinkel, int neueBogenlaenge) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe, neuerStartwinkel, neueBogenlaenge);
		
	}
	
	public Ellipse(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe, int neuerStartwinkel,
			int neueBogenlaenge) {
		obj = new CEllipse();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		setzeBogen(neuerStartwinkel, neueBogenlaenge);
		behaelter.validate();
	}
	
	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	public BasisComponente getBasisComponente() {
		return obj;
	}
	
	public void setzeBogen(int neuerStartwinkel, int neueBogenlaenge) {
		startwinkel = neuerStartwinkel;
		bogenlaenge = neueBogenlaenge;
		obj.setzeBogen(startwinkel, bogenlaenge);
	}
	
	public void sichtbarMachen() {
		sichtbar = true;
		obj.sichtbarMachen();
	}
	
	/**
	 * Mache unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		obj.unsichtbarMachen();
	}
	
	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void nachRechtsBewegen() {
		horizontalBewegen(20);
	}
	
	/**
	 * Bewege einige Bildschirmpunkte nach links.
	 */
	public void nachLinksBewegen() {
		horizontalBewegen(-20);
	}
	
	/**
	 * Bewege einige Bildschirmpunkte nach oben.
	 */
	public void nachObenBewegen() {
		vertikalBewegen(-20);
	}
	
	/**
	 * Bewege einige Bildschirmpunkte nach unten.
	 */
	public void nachUntenBewegen() {
		vertikalBewegen(20);
	}
	
	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamVertikalBewegen(int entfernung) {
		int delta;
		
		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}
		
		for (int i = 0; i < entfernung; i++) {
			vertikalBewegen(delta);
			StaticTools.warte(10);
		}
	}
	
	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamHorizontalBewegen(int entfernung) {
		int delta;
		
		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}
		
		for (int i = 0; i < entfernung; i++) {
			horizontalBewegen(delta);
			StaticTools.warte(10);
		}
	}
	
	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeGroesse(breite, hoehe);
	}
	
	/**
	 * neue Position
	 * 
	 * @param neuesX
	 * @param neuesY
	 */
	public void setzePosition(int neuesX, int neuesY) {
		xPos = neuesX;
		yPos = neuesY;
		obj.setzePosition(xPos, yPos);
	}
	
	// Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx, int dy) {
		setzePosition(xPos + dx, yPos + dy);
	}
	
	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		xPos = neuesX;
		yPos = neuesY;
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeDimensionen(xPos, yPos, breite, hoehe);
	}
	
	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeBasisfarbe(neueFarbe);
	}
	
	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void horizontalBewegen(int entfernung) {
		xPos += entfernung;
		obj.setzePosition(xPos, yPos);
	}
	
	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void vertikalBewegen(int entfernung) {
		yPos += entfernung;
		obj.setzePosition(xPos, yPos);
	}
	
	public void fuellen() {
		gefuellt = true;
		obj.fuellen();
	}
	
	public void rand() {
		gefuellt = false;
		obj.rand();
	}
	
	/**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
		if (obj != null) {
			obj.ausContainerEntfernen();
			obj = null;
		}
	}
	
	/**
	 * Destruktor
	 */
	protected void finalize() {
	if (!Zeichnung.verweistesGUIElementEntfernen) return;
		if (obj != null) entfernen();
	}
	
}

class CEllipse extends BasisComponente {
	
	private int	startWinkel	= 0;
	private int	bogenLaenge	= 360;
	
	public void setzeBogen(int neuerStartwinkel, int neueBogenlaenge) {
		startWinkel = neuerStartwinkel;
		bogenLaenge = neueBogenlaenge;
		repaint();
	}
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CEllipse() {
		
	}
	
	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width - 1;
		hoehe = getSize().height - 1;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));
		
		g2.setColor(farbe);
		
		if (gefuellt) {
			g2.fillArc(0, 0, breite, hoehe, startWinkel, bogenLaenge);
		} else {
			g2.drawArc(0, 0, breite, hoehe, startWinkel, bogenLaenge);
			g2.drawArc(1, 1, breite - 2, hoehe - 2, startWinkel, bogenLaenge);
		}
	}
}
