import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Graphics;

/**
 * <h1>Zeichnung</h1> Die Klasse Zeichnung ist ein JFrame zur Aufnahme von
 * Graphikobjekten<br />
 * <br />
 * 
 * Normalerweise braucht man sich um die Klasse Zeichnung nicht zu k&uuml;mmern. <br />
 * Sie wird von den Komponenten automatisch bearbeitet.<br />
 * <br />
 * 
 * Wichtig: <b>Die statische Methode setzeFenstergroesse(...)</b> aendert die
 * Breite und Hoehe des Fensters<br />
 * Wenn es das Objekt noch nicht gibt, wird es erzeugt.<br />
 * <br />
 * Hilfreich: <b>Die statische Methode setzeRasterEin() bzw. setzeRasterAus()</b> erzeugt/l&ouml;scht ein Raster auf der Zeichenfl&auml;che.<br />
 * setzeDeltaX/setzeDeltaY setzen das Rastermass<br />
 * Diese Methoden sind hilfreich beim Designen von Oberflchen.<br/>
 * <br />
 * 
 * Die sichbaren Attribute und Methoden wurden den BlueJ-Beispiel Shapes
 * nachempfunden.<br />
 * <br />
 * 
 * Die statische Methoden gibJFrame und gibPanel geben Referenzen auf das JPanel
 * in dem JFrame zurueck<br />
 * Wenn es den JFrame und das JPanel noch nicht gibt, werden sie erzeugt.<br />
 * <hr>
 * 
 * @author Hans Witt
 * @version Version 1.2: Raster bei der Zeichenflaeche eingefuehrt<br />
 * 
 *          Interface ITuWas geaendert s.u.<br />
 * 
 *          Version 2.0: <br />
 *          Zoom fuer Behaelter eingefuehrt<br />
 *          Anpassung bei BasisComponente fuer Wechseln zwischen Behaeltern<br />
 *          Interface IComponente. Alle Klassen, die zum einem Behaelter
 *          nachtraeglich hinzugefuegt werden koennen,<br />
 *          muessen das Interface IComponente haben <br />
 * 
 *          Version 3.0: ( 01.03.2009) Zeichenfenster in Scrollpane eingebettet
 * 
 *          Version 4.0: Experimentel: Destruktor f�r Graphikelemente
 *          eingefuehrt
 * 
 */
public class Zeichnung extends JFrame {

	public static Zeichenflaeche panel;
	public JPanel parentPannel = null;
	public static JScrollPane parentPane = null;

	public static boolean applet = false;
	// Zeiger auf ein JApplet zum Lesen von Bildern fuer das Applet
	public static HTMLZeichnung pApplet = null;

	/**
	 * Experimentell: Bei true werden mit dem GarbageCollector wird auch
	 * GUIElemente entfernt
	 */
	public static boolean verweistesGUIElementEntfernen = false;

	public static JFrame single;

	public static Zeichenflaeche gibZeichenflaeche() {
		if (panel == null) {
			single = new Zeichnung("BlueJ Graphik-Fenster");
		}
		return panel;
	}

	public static JFrame gibJFrame() {
		if (applet)
			return null;
		if (single == null) {
			single = new Zeichnung("BlueJ Graphik-Fenster");
		}
		return single;
	}

	public Zeichnung() {
		// Frame-Initialisierung
		super();
		setTitle("BlueJ-JGUIToolbox");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		int frameWidth = 600;
		int frameHeight = 600;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		// Zeichenflaeche zentriert
		// int x = (d.width - getSize().width) / 2;
		// int y = (d.height - getSize().height) / 2;

		// Zeichenflaeche rechtsbuendig
		int x = d.width - getSize().width;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		erzeugeFenster();
		System.gc();
	}

	public void erzeugeFenster() {
		panel = new Zeichenflaeche();

		parentPannel = new JPanel();
		parentPannel.setLayout(new BorderLayout());
		parentPane = new JScrollPane();

		parentPannel.add(parentPane, BorderLayout.CENTER);
		parentPane.setViewportView(panel);
		this.getContentPane().add(parentPannel);

		// this.getContentPane().add(panel);

		setExtendedState(Frame.NORMAL);
		setResizable(true);
		setVisible(true);
		// Damit immer der gleiche Frame angesprochen wird, unabhaengig vom der
		// Erzeugung ueber new oder gibJFrame
		single = this;
		setzeScrollbar(true);
	}

	public static void setzeScrollbar(boolean scrollbar) {
		if (applet)
			HTMLZeichnung.setzeScrollbar(scrollbar);
		if (single == null)
			Zeichnung.gibJFrame();
		if (scrollbar) {
			parentPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			parentPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		} else {
			parentPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			parentPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
		panel.setzeScrollbar(scrollbar);
	}

	public Zeichnung(String title) {
		this();
		setTitle(title);
	}

	public Zeichnung(String title, boolean mitRaster) {
		this();
		setTitle(title);
		if (mitRaster) {
			setzeRasterEin();
		} else {
			setzeRasterAus();
		}

	}

	public static void setzeFenstergroesse(int breite, int hoehe) {
		if (applet)
			return;
		JFrame frame = gibJFrame();
		frame.setSize(breite, hoehe);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		// Zeichenflaeche zentriert
		// int x = (d.width - getSize().width) / 2;
		// int y = (d.height - getSize().height) / 2;

		// Zeichenflaeche rechtsbuendig
		int x = d.width - frame.getSize().width;
		int y = (d.height - frame.getSize().height) / 2;
		frame.setLocation(x, y);
	}

	public static void setzeRasterEin() {
		Zeichenflaeche.mitRaster = true;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	public static void setzeRasterAus() {
		Zeichenflaeche.mitRaster = false;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	/**
	 * Setze Rasterweite horizontal
	 * 
	 * @param deltaX
	 */
	public static void setzeDeltaX(int deltaX) {
		Zeichenflaeche.deltaX = deltaX;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	/**
	 * Setze Rasterweite vertikal
	 * 
	 * @param deltaY
	 */
	public static void setzeDeltaY(int deltaY) {
		Zeichenflaeche.deltaY = deltaY;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	/**
	 * @param args
	 */
	// public static void main(String[] args) {
	// single = new Zeichnung("Graphik-Fenster-main");
	// }
}

class Zeichenflaeche extends JPanel implements IContainer {

	public int breite = 100;
	public int hoehe = 100;
	public boolean scrollable = false;
	public Zeichenflaeche parent = null; // Wenn scrollbar
	// eigentliche
	// Zeichenflaeche, die
	// als Kind eine
	// Zeichenflaeche enthaelt
	public JScrollPane parentPane = null;
	public JPanel parentPannel = null;

	public static boolean mitRaster = false;
	public static int deltaX = 100;
	public static int deltaY = 100;

	public static double zoomFaktor = 1.0;

	public static void setzeMitRaster(boolean mitRaster) {
		Zeichenflaeche.mitRaster = mitRaster;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	public static void setzeDeltaX(int deltaX) {
		Zeichenflaeche.deltaX = deltaX;
	}

	public static void setzeDeltaY(int deltaY) {
		Zeichenflaeche.deltaY = deltaY;
	}

	public Zeichenflaeche() {
		this.setLayout(null);
	}

	public void scrollenAnpassen(int x, int y, int width, int height) {
		boolean anpassen = false;
		if ((x + width) > breite) {
			breite = x + width;
			anpassen = true;
		}
		if ((y + height) > hoehe) {
			hoehe = y + height;
			anpassen = true;
		}
		if (anpassen) {
			setPreferredSize(new Dimension(breite, hoehe));
			((JScrollPane) this.getParent().getParent()).validate();
		}
	}

	public void setzeScrollbar(boolean scrollbar) {
		scrollable = scrollbar;
	}

	public void setzeSichtbarkeit(boolean sichtbar) {
		this.getParent().setVisible(sichtbar);
	}

	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height) {
		obj.setBounds(x, y, width, height);
		if (scrollable)
			scrollenAnpassen(x, y, width, height);
		repaint();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		obj.setSize(width, height);
		if (scrollable)
			scrollenAnpassen(obj.getX(), obj.getY(), width, height);
		obj.repaint();
		repaint();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		if (scrollable)
			scrollenAnpassen(x, y, obj.getWidth(), obj.getHeight());
		obj.setLocation(x, y);
		obj.repaint();
		repaint();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */

	public void paintComponentSpezial(Graphics g) {
		if (mitRaster) {
			Graphics2D g2 = (Graphics2D) g;
			// Graphik-Abmessungen
			int breite = getSize().width - 1;
			int hoehe = getSize().height - 1;
			Color farbe = StaticTools.getColor("schwarz");
			g.setColor(farbe);

			int hor = deltaX;
			while (hor < breite) {
				g2.drawLine(hor, 0, hor, hoehe);
				hor += deltaX;
			}

			int ver = deltaY;
			while (ver < hoehe) {
				g2.drawLine(0, ver, breite, ver);
				ver += deltaY;
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintComponentSpezial(g);
	}

	// Zoom fuer Zeichenflaeche
	public double getBehaelterZoom() {
		return zoomFaktor;
	}

	// Zoom fuer Zeichenflaeche
	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		return zoomFaktor;
	}
}

/**
 * Basisklasse, von der alle Komponenten im Framework abgeleitet sind. </br>
 * 
 * in der Methode paintComponent(Graphics g) wird die Methode
 * paintComponentSpezial(Graphics g) (absstrakt!) aufgerufen. Die von der
 * Basiskomponente abgeleiteten Komponenten koennen darin ihre Zeichnungen
 * unterbringen.
 */
abstract class BasisComponente extends JPanel {
	// Zustand der Komponente

	protected Color farbe = StaticTools.leseNormalZeichenfarbe();
	protected int breite;
	protected int hoehe;
	protected int xPos = 0;
	protected int yPos = 0;
	protected boolean gefuellt = true;
	protected boolean sichtbar = true;
	protected int fontGroesse = 20;

	protected int originalXPos = 0;
	protected int originalYPos = 0;
	protected int originalBreite = 100;
	protected int originalHoehe = 100;
	protected int originalFontGroesse = 20;

	protected int originalVX = 0;
	protected int originalVY = 0;

	protected double zoomFaktor = 1.0;
	protected double bzf = 1; // Zommfaktor

	// der
	// uebergeordneten
	// Behaelter

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public BasisComponente() {
		setOpaque(false); // Komponenten sind durchsichtig !
	}

	Font f = new Font("Dialog", Font.PLAIN, fontGroesse);

	protected void setFontsize(int i) {
		originalFontGroesse = i;
		zoomen();
		f = new Font("Dialog", Font.PLAIN, fontGroesse);
		setFont(f);
	}

	// Zur Kommunikation zwischen Objekten
	ITuWas linkObj; // Link auf das zu benachrichtigende Objekt
	int id = 0; // ID der Komponente. Fuer Callback wichtig

	public void setzeID(int ID) {
		id = ID;
	}

	public void setzeLink(ITuWas linkObj) {
		this.linkObj = linkObj;
	}

	public void setzeLink(ITuWas linkObj, int ID) {
		this.linkObj = linkObj;
		id = ID;
	}

	// zum Ueberschreiben in abgeleiteten Komponenten
	public void setzeSchriftgroesse(int i) {
		setFontsize(i);
		repaint();
	}

	/**
	 * Komponenten aus Behaelter entfernen
	 */
	public void ausContainerEntfernen() {
		JPanel p = (JPanel) this.getParent();
		if (p == null)
			return;
		p.remove(this);
		p.repaint();
		p.validate();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	public abstract void paintComponentSpezial(Graphics g);

	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		bzf = ((IContainer) this.getParent()).getBehaelterZoom();
		fontGroesse = (int) Math.round(originalFontGroesse * zoomFaktor * bzf);
		zoomen();
		setzeSchriftgroesse(fontGroesse);
		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, 0, 0);
		}
		return zoomFaktor;

	}

	public void zommfaktorAnpassen() {
		setzeZoomfaktor(zoomFaktor);
	}

	protected void zoomen() {
		breite = (int) Math.round(originalBreite * zoomFaktor * bzf);
		hoehe = (int) Math.round(originalHoehe * zoomFaktor * bzf);
		xPos = (int) Math.round((originalXPos + originalVX) * zoomFaktor * bzf);
		yPos = (int) Math.round((originalYPos + originalVY) * zoomFaktor * bzf);
		fontGroesse = (int) Math.round(originalFontGroesse * zoomFaktor * bzf);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintComponentSpezial(g);
	}

	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		repaint();
	}

	/**
	 * Mache sichtbar. Wenn es bereits sichtbar ist, tue nichts.
	 */
	public void sichtbarMachen() {
		sichtbar = true;
		((IContainer) this.getParent()).setzeKomponentenGroesse(this, breite,
				hoehe);
		repaint();
	}

	/**
	 * Mache diesen Kreis unsichtbar. Wenn es bereits unsichtbar ist, tue
	 * nichts.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		((IContainer) this.getParent()).setzeKomponentenGroesse(this, 0, 0);
		repaint();
	}

	public void fuellen() {
		gefuellt = true;
		repaint();
	}

	public void rand() {
		gefuellt = false;
		repaint();
	}

	public void setzeGroesse(int width, int height) {
		originalBreite = width;
		originalHoehe = height;
		zoomen();
		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenGroesse(this,
					breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenGroesse(this, 0, 0);
		}
		// Zeichnung.gibZeichenflaeche().setzeGroesse(this, width, height);
		// Zeichnung.gibJFrame().validate();
	}

	public void setzePosition(int x, int y) {
		originalXPos = x;
		originalYPos = y;
		zoomen();
		((IContainer) this.getParent()).setzeKomponentenPosition(this, xPos,
				yPos);
		// Zeichnung.gibZeichenflaeche().setzePosition(this, x, y);
		// Zeichnung.gibJFrame().validate();
	}

	public void verschieben(int dx, int dy) {
		originalVX = dx;
		originalVY = dy;
		zoomen();
		setzePosition(originalXPos, originalYPos);
	}

	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		originalXPos = neuesX;
		originalYPos = neuesY;
		originalBreite = neueBreite;
		originalHoehe = neueHoehe;
		zoomen();
		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, 0, 0);
		}
	}

}

/**
 * <h1>Interface f&uuml;r die Toolbox-Kommunikation</h1> 
 * 
 * Klassen die Ziel der Meldungen aktiver Komponenten (z.B. Taste) sind, m&uuml;ssen die<br/><br/>
 * <b>Methode public void setzeLink( ITuWas linkObj ) </b> implementieren.<br/><br>
 * 
 * Mit setzeLink(...) wird der aktiven Komponente das Zielobjekt �bergeben (h&auml;ufig <b>this</b> ).<br/>
 * 
 * Diese ruft dann die Funktion tuWas( int ID) des Zielobjekts auf.<br/>
 * <br/>
 * Jede aktive Komponente besitzt auch die Methode setzeID(int ID). 
 * Beim Aufruf von TuWas wird dann die ID &uuml;bertragen, <br/>
 * bei verschiedenen Ereignissen ID + Nr. Nr ist die interne ID der Komponente.
 * <hr>
 * @author Witt
 * @version : 2 ( 3.8.2008 )
 * 
 */
interface ITuWas {
	public void tuWas(int ID);
}

/**
 * Das Interface IComponente fordert eine Methode die eine BasisComponente
 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen Container
 * hinzuzufuegen
 */
interface IComponente {
	public BasisComponente getBasisComponente();
}

/**
 * Das Interface IContainer fordert Methoden zum Aendern der Groesse und
 * Position eingebetteter Komponenten 1. Beispiel: Der Container Zeichenflaeche
 * 
 * @version : 3 ( 4.8.2008 ):
 */
interface IContainer {

	public Component add(Component comp, int index);

	/**
	 * die folgenden Methoden weden von der Basiskomponente aufgerufen obj ist
	 * immer (IContainer) this.getParent() von der Basidkomponente von hier aus
	 * wird die Basiskomponente veraendert
	 */

	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height);

	public void setzeKomponentenGroesse(JComponent obj, int width, int height);

	public void setzeKomponentenPosition(JComponent obj, int x, int y);

	public void validate();

	/**
	 * liefert den Zoomfaktor fuer den Behaelter
	 * 
	 * @return Zoomfaktor
	 */
	public double getBehaelterZoom();

	public double setzeZoomfaktor(double zf);

}

abstract class HTMLZeichnung extends JApplet {

	public static Zeichenflaeche panel;
	public JPanel parentPannel = null;
	public static JScrollPane parentPane = null;

	public static JFrame single;

	public abstract void initHTML();

	public void init() {
		// Zeichenflaeche erstellen
		panel = new Zeichenflaeche();
		// Scroll-Pannel
		parentPannel = new JPanel();
		parentPannel.setLayout(new BorderLayout());
		parentPane = new JScrollPane();

		parentPannel.add(parentPane, BorderLayout.CENTER);
		// Zeichenflaeche in Scroll-Pannel
		parentPane.setViewportView(panel);
		add(parentPannel);

		// In der Klasse Zeichnung wird das Panel auf das Applet gesetzt
		Zeichnung.panel = panel;
		Zeichnung.pApplet = this;
		Zeichnung.applet = true;
		setzeScrollbar(true);
		initHTML();
	}

	public static void setzeScrollbar(boolean scrollbar) {
		if (scrollbar) {
			parentPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			parentPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		} else {
			parentPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			parentPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
		panel.setzeScrollbar(scrollbar);
	}

}

abstract class BilddateiAbstrakt {
	BufferedImage bild;

	public BufferedImage leseBild() {
		return bild;
	}
}
