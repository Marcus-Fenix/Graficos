import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class PantallaCargandoMain{
  PantallaInicio cargando;
  public PantallaCargandoMain() {
    inicioPantalla();
	cargando.velocidadDeCarga();
  }
  private void inicioPantalla() {
    ImageIcon myImage = new ImageIcon("C:\\Users\\MARCUS\\Documents\\programacion II\\Graficos\\src\\img\\Inicio.gif");
    cargando= new PantallaInicio(myImage);
    cargando.setLocation(450,130);
    cargando.setProgresoMax(100);
    cargando.setVisible(true);
  }
  public static void main(String[] args)
  {
    new PantallaCargandoMain();
  }
}
