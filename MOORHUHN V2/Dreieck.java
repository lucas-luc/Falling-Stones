import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

/**
 * <h1>Dreieck</h1>
 * 
 * @author Hans Witt
 * 
 * @version
 * Version: 1.3 (9.8.2008) <br/>
 *        Dreiecksaurichtung: N,O,S,W,NO, SO, SW , NW<br/>  
 *        erzeugt rechtwinkeliges Dreieck mit einem Eck laut Himmesrichtung<br/>
 *        Fehler beim Setzen der Himmelsrichtung beseitigt<br/>
 */
public class Dreieck implements IComponente {
	
	private CDreieck				obj;
	protected int					breite		= 0;
	protected int					hoehe		= 0;
	protected int					xPos		= 0;
	protected int					yPos		= 0;
	protected boolean				sichtbar	= true;
	protected boolean				gefuellt	= true;
	protected String				farbe		= StaticTools.leseNormalfarbe();
	
	private StaticTools.Richtung	ausrichtung	= StaticTools.Richtung.N;
	
	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public Dreieck() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Dreieck(int neueBreite, int neueHoehe) {
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
	public Dreieck(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Dreieck(IContainer behaelter) {
		this(behaelter, 0, 0, 50, 50);
	}
	
	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Dreieck(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CDreieck();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
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
	
	/**
	 * Richtung der Dreiecksspitze
	 * 
	 * @param richtung
	 *            Moegliche Werte: "N","O","S","W","NO","SO","SW","NW"
	 */
	public void setzeAusrichtung(String richtung) {
		ausrichtung = StaticTools.getRichtung(richtung);
		obj.setzeAusrichtung(ausrichtung);
		obj.repaint();
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
	
	public static void main(String[] args) {
		Dreieck d = new Dreieck(0, 0, 300, 50);
		d.setzeAusrichtung("W");
		d.fuellen();
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

class CDreieck extends BasisComponente {
	
	int[]	xpoints	= new int[3];
	int[]	ypoints	= new int[3];
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CDreieck() {
		// Anfangskoordinaten fuer Dreieck setzen
		setzeAusrichtung(StaticTools.Richtung.N);
	}
	
	private StaticTools.Richtung	ausrichtung	= StaticTools.Richtung.N;
	
	public void setzeAusrichtung(StaticTools.Richtung richtung) {
		ausrichtung = richtung;
		repaint();
	}
	
	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width - 1;
		hoehe = getSize().height - 1;
		g2.setColor(farbe);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));
		
		switch (ausrichtung) {
		case N:
			xpoints[0] = 0;
			xpoints[1] = (breite / 2);
			xpoints[2] = breite;
			ypoints[0] = hoehe;
			ypoints[1] = 0;
			ypoints[2] = hoehe;
			break;
		case S:
			xpoints[0] = 0;
			xpoints[1] = (breite / 2);
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = 0;
			break;
		case O:
			xpoints[0] = 0;
			xpoints[1] = breite;
			xpoints[2] = 0;
			ypoints[0] = 0;
			ypoints[1] = hoehe / 2;
			ypoints[2] = hoehe;
			break;
		case W:
			xpoints[0] = breite;
			xpoints[1] = 0;
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = hoehe / 2;
			ypoints[2] = hoehe;
			break;
		case NO:
			xpoints[0] = 0;
			xpoints[1] = breite;
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = 0;
			ypoints[2] = hoehe;
			xpoints[0] = 1;
			xpoints[1] = breite - 1;
			xpoints[2] = breite - 1;
			ypoints[0] = 1;
			ypoints[1] = 1;
			ypoints[2] = hoehe - 1;
			break;
		case SW:
			xpoints[0] = 0;
			xpoints[1] = 0;
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = hoehe;
			xpoints[0] = 1;
			xpoints[1] = 1;
			xpoints[2] = breite - 1;
			ypoints[0] = 1;
			ypoints[1] = hoehe - 1;
			ypoints[2] = hoehe - 1;
			break;
		case SO:
			xpoints[0] = breite;
			xpoints[1] = breite;
			xpoints[2] = 0;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = hoehe;
			xpoints[0] = breite - 1;
			xpoints[1] = breite - 1;
			xpoints[2] = 1;
			ypoints[0] = 1;
			ypoints[1] = hoehe - 1;
			ypoints[2] = hoehe - 1;
			break;
		case NW:
			xpoints[0] = breite;
			xpoints[1] = 0;
			xpoints[2] = 0;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = 0;
			xpoints[0] = breite - 1;
			xpoints[1] = 1;
			xpoints[2] = 1;
			ypoints[0] = 1;
			ypoints[1] = 1;
			ypoints[2] = hoehe - 1;
			break;
		default:

		}
		
		if (gefuellt) {
			g2.fillPolygon(new Polygon(xpoints, ypoints, 3));
		} else {
			g2.drawPolygon(new Polygon(xpoints, ypoints, 3));
		}
	}
}
