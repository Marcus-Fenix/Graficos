import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import java.awt.event.*;


//import javax.swing.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejemplo 
{
	public static void main( String args[] )
	{
		MarcoBotones aplicacion = new MarcoBotones();
		aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		aplicacion.pack();
		aplicacion.setVisible(true);
	}

}

class MarcoBotones extends JFrame
{
	
	public MarcoBotones()
	{
		setTitle("Reversi");
		LaminaGemas lamina = new LaminaGemas();
		add(lamina);
	}
}

class LaminaGemas extends JPanel
{
	
	private JPanel panelBotones;
	private JButton botones[][];
	private String gemas[] = {"december.gif","february.gif"};
	private Boolean jugada;
	//variables para guardas las coordenadas de la posicion a cambiar
	private int cx=0,cy=0;
	
	public LaminaGemas()
	{

		setLayout(new BorderLayout());
		
		jugada=false;
		
		Random generador = new Random();
		

		// configurar panel y establecer su esquema
		//panelBotones = new JPanel(); 
		//panelBotones.setLayout(new BorderLayout());
		
		JButton botonNewGame = new JButton("Juego Nuevo");
		//botonNewGame.setSize(100, 100);
		botonNewGame.setEnabled(true);
		add(botonNewGame,BorderLayout.NORTH);
		

		JButton botonScore = new JButton("Aqui va el Score");
		botonScore.setEnabled(false);
		add(botonScore,BorderLayout.SOUTH);     


		//Crear el panel para manejar el tablero de Gemas
		panelBotones = new JPanel();
		
		// crear arreglo botones
		botones = new JButton[ 10 ][ 10 ];
		panelBotones.setLayout( new GridLayout( botones.length , botones[0].length) );

		// crear y agregar botones       
		Icon gema ;

		for ( int y = 0; y < botones.length; y++ )
			for ( int x = 0; x < botones[0].length; x++ ) 
			{
				//Crear el boton con la gema de manera aleatoria

				//seleccionar una gena
				gema = createImageIcon(gemas[generador.nextInt(gemas.length)]);
					
				/*if((x==4 || x==5) && (y==4 || y==5))
				{
					//agregar la gema al boton
					botones[ y ][ x ] = new JButton(gema);									
				}
				else
					botones[ y ][ x ] = new JButton();*/
				
				botones[ y ][ x ] = new JButton(gema);
				
				//tomar las dimenciones de la imagen de la gema
				Dimension tamanoGema = new  Dimension(gema.getIconHeight(),gema.getIconWidth());
				// hacer el boton del tamaño de la imagen de la gema
				botones[ y ][ x ].setPreferredSize(tamanoGema);
				//agregar al boton el evento del click del mouse 
				agregarAccion(botones[ y ][ x ],x,y);
				//agregar el boton al panel para ser visualizado en pantalla
				panelBotones.add( botones[ y ][ x ] );
			}

		//agregar el panel de la matriz de botones 
		//al panel principal
		add(panelBotones,BorderLayout.CENTER);
	}
	
	
	void agregarAccion(final JButton boton, final int x, final int y)
	{
		boton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evento)
				{
					//realizar aqui las validaciones del juego
					if (jugada)
					{
						JButton botonAux = new JButton(boton.getIcon());
						boton.setIcon(botones[cy][cx].getIcon());
						botones[cy][cx].setIcon(botonAux.getIcon());
						jugada=false;
					}
					else
					{
						//guarda las coordenadas
						cx=x;
						cy=y;						
						jugada=true;
					}
						
				}
			});
	}

	//Funcion que busca una imagen segun su nombre
	//en la ruta actual y crea un objeto de imagen
	protected ImageIcon createImageIcon(String nombreGema) 
	{

		java.net.URL imgURL = getClass().getResource("img/" +nombreGema);

		//si encuentra en la carpeta la imagen indicada

		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			//Cuando no encuentra la imagen           
			return new ImageIcon(getClass().getResource("img/december.gif"));
			
		}
	}
	}