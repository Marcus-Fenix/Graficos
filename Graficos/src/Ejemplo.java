import java.awt.BorderLayout;
import java.lang.Thread;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import java.awt.event.*;


//import javax.swing.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ejemplo 
{
	public static void main( String args[] )
	{
		new PantallaCargandoMain();
		MarcoBotones aplicacion = new MarcoBotones();
		aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		aplicacion.pack();
		aplicacion.setLocation(450, 100);
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
	private JPanel panelScore;
	private JButton botones[][];
	private String gemas[] = {"december.gif","february.gif","january.gif","september.gif"};
	private String gemas2[] = {"negro.gif"};
	private Boolean jugada;
	//variables para guardas las coordenadas de la posicion a cambiar
	private int cx=0,cy=0,hola = 0,y2=0, minutos = 0, segundos = 0, score = 0, puntos = 0;
	


	@SuppressWarnings("unused")
	public LaminaGemas()
	{
		
		class hilo extends Thread
		{

			public hilo() {
				// TODO Auto-generated constructor stub
			}
			public void run()
			{	
				try
				{
					for(minutos = 0; minutos > -1; minutos--)
					{
						for(segundos= 59; segundos > -1; segundos--)
						{
							JButton botonScore = new JButton();
							botonScore.setEnabled(true);
							add(botonScore,BorderLayout.SOUTH);
							botonScore.setText("Score: "+ score + "        Tiempo: " +minutos + " : " + segundos);
							Thread.sleep(1000);
						}
					}
				
				}catch(Exception e){}
			}
		}

		hilo h = new hilo();

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
		
		
		h.start();
		

		//Crear el panel para manejar el tablero de Gemas
		panelBotones = new JPanel();
		
		// crear arreglo botones
		botones = new JButton[ 10 ][ 10 ];
		panelBotones.setLayout( new GridLayout( botones.length , botones[0].length) );

		// crear y agregar botones       
		Icon gema;
		Icon gemas3;
		
		
		for ( int y = 0; y < botones.length; y++ )
			for ( int x = 0; x < botones[0].length; x++ ) 
			{
				//Crear el boton con la gema de manera aleatoria

				//seleccionar una gena
				hola = generador.nextInt(gemas.length);
				gema = createImageIcon(gemas[hola]);
					
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
	
	
	void agregarAccion(final JButton boton,final int x, final int y){
		boton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					puntos = 0;
					jugada = true;
					if(jugada)
					{
						cy = y;
						cx = x;
						y2= y;
						for(int y1 = y; y1 > -1; y1-- )
						{
							if(botones[y1][cx].getIcon().toString().equalsIgnoreCase(boton.getIcon().toString()))
							{
								puntos += 10;
							}
							else
							{
								break;
							}
						}
						
						cy = y;
						cx = x;
						
						for(int y2 = y; y2 < 10; y2++ )
						{
							if(botones[y2][cx].getIcon().toString().equalsIgnoreCase(boton.getIcon().toString()))
							{
								puntos += 10;
							}
							else
							{
								break;
							}
						}
						
						cy = y;
						cx = x;
						
						for(int x1 = x; x1 < 10; x1++ )
						{
							if(botones[cy][x1].getIcon().toString().equalsIgnoreCase(boton.getIcon().toString()))
							{
								puntos += 10;
							}
							else
							{
								break;
							}
						}
						
						cy = y;
						cx = x;
						
						for(int x2 = x; x2 > -1; x2-- )
						{
							if(botones[cy][x2].getIcon().toString().equalsIgnoreCase(boton.getIcon().toString()))
							{
								puntos += 10;
							}
							else
							{
								break;
							}
						}
						puntos -= 30;
						if(puntos >= 30)
						{
							score += puntos;
						}
						System.out.println(puntos);
						jugada = false;
						
					}
					
					//realizar aqui las validaciones del juego
					/*if (jugada)
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
					}*/
						
				}

			});
	}

	//Funcion que busca una imagen segun su nombre
	//en la ruta actual y crea un objeto de imagen
	protected ImageIcon createImageIcon(String nombreGema) 
	{

		java.net.URL img1URL = getClass().getResource("img/" +nombreGema);

		//si encuentra en la carpeta la imagen indicada

		if (img1URL != null) {
			return new ImageIcon(img1URL);
		} else {
			//Cuando no encuentra la imagen           
			return new ImageIcon(getClass().getResource("img/december.gif"));
			
		}
	}
	
	}