
import com.sun.javafx.scene.control.skin.ButtonSkin;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jdk.nashorn.internal.parser.TokenType;


/**
 *
 * @author javi_
 */


public class TresEnRaya implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    int tipoTablero = 1;//tipoTablero = 1 -> 3x3    tipoTablero = 2 -> 5x5  tipoTablero = 3 -> 10x10 
    
    JPanel panelTitulo = new JPanel();
    JPanel panelBoton = new JPanel();
    JPanel panelInferior = new JPanel();
    JPanel panelInferior1 = new JPanel();
    JPanel panelInferior2 = new JPanel();
    JPanel panelRestart = new JPanel();
    JLabel campoTextoInferior1 = new JLabel();
    JLabel campoTextoInferior2 = new JLabel();
    JLabel campoTexto = new JLabel();
    JButton[] botonesTablero1 = new JButton[9];
    JButton[] botonesTablero2 = new JButton[25];
    JButton[] botonesTablero3 = new JButton[100];
    JButton botonRestart = new JButton();
    
    JMenuBar barra;
    JMenu menu;
    JMenu subMenuTablero;
    JMenuItem tablero1,tablero2,tablero3,newGame,restart,exit;
    
    Boolean turnoJugador1; //jugador1= true. Jugador2 = false
    Boolean todasOcupadas;
    Boolean comprobar;
    Boolean alguienGana;
    
    int contador;
    
    Sonido sonidoPosicionVacía;
    Sonido sonidoPosicionOcupada;
    Sonido sonidoVictoria;
    Sonido sonidoNuevaPantalla;
    Sonido sonidoExit;
    
    int puntosX;
    int puntosO;
    
    
    public TresEnRaya() {
        newGame(1);
    }
    
    private void newGame(int tipoTablero){
        this.tipoTablero = tipoTablero;
        
        alguienGana=false;

        panelBoton.removeAll();
        frame.remove(panelBoton);

        sonidoPosicionVacía = new Sonido();
        sonidoPosicionOcupada = new Sonido();
        sonidoVictoria = new Sonido();
        sonidoNuevaPantalla = new Sonido();
        sonidoExit = new Sonido();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground((new Color(50,50,50)));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        campoTexto.setBackground(new Color(25,25,25));
        campoTexto.setForeground(new Color(25,255,25));
        campoTexto.setFont(new Font("Ink Free" , Font.PLAIN,75));
        campoTexto.setHorizontalAlignment(JLabel.CENTER);
        campoTexto.setText("Tres en Raya");
        campoTexto.setOpaque(true);

        campoTextoInferior1.setBackground(new Color(25,25,25));
        campoTextoInferior1.setForeground(new Color(25,255,25));
        campoTextoInferior1.setFont(new Font("Ink Free" , Font.PLAIN,20));
        campoTextoInferior1.setHorizontalAlignment(JLabel.CENTER);
        campoTextoInferior1.setText("Puntos X = " + String.valueOf(puntosX));
        campoTextoInferior1.setOpaque(true);

        campoTextoInferior2.setBackground(new Color(25,25,25));
        campoTextoInferior2.setForeground(new Color(25,255,25));
        campoTextoInferior2.setFont(new Font("Ink Free" , Font.PLAIN,20));
        campoTextoInferior2.setHorizontalAlignment(JLabel.CENTER);
        campoTextoInferior2.setText("Puntos O = " + String.valueOf(puntosO));
        campoTextoInferior2.setOpaque(true);

        panelTitulo.setLayout(new BorderLayout());
        panelTitulo.setBounds(0, 0, 800, 100);

        

        panelInferior.setLayout(new GridLayout(3, 3));
        panelInferior.setBackground(new Color(150,150,150));

        panelInferior1.setLayout(new BorderLayout());
        panelInferior1.setBackground(new Color(50,50,50));

        panelInferior2.setLayout(new BorderLayout());
        panelInferior2.setBackground(new Color(50,50,50));


        barra=new JMenuBar();
        frame.setJMenuBar(barra);
        menu=new JMenu("MENU");
        barra.add(menu);

        subMenuTablero=new JMenu("Tablero");
        subMenuTablero.addActionListener(this);

        tablero1=new JMenuItem("Tablero 3x3");
        tablero1.addActionListener(this);
        subMenuTablero.add(tablero1);

        tablero2=new JMenuItem("Tablero 5x5");
        tablero2.addActionListener(this);
        subMenuTablero.add(tablero2);

        tablero3=new JMenuItem("Tablero 10x10");
        tablero3.addActionListener(this);
        subMenuTablero.add(tablero3);

        menu.add(subMenuTablero);

        newGame=new JMenuItem("New Game");
        newGame.addActionListener(this);
        menu.add(newGame);

        restart=new JMenuItem("Restart");
        restart.addActionListener(this);
        menu.add(restart);

        exit=new JMenuItem("Exit");
        exit.addActionListener(this);
        menu.add(exit);
            
        if (tipoTablero==1){
            panelBoton.setLayout(new GridLayout(3, 3));
            panelBoton.setBackground(new Color(150,150,150));
            //Botones
            for (int i = 0; i < 9; i++) {
                botonesTablero1[i] = new JButton();
                panelBoton.add(botonesTablero1[i]);
                botonesTablero1[i].setFont(new Font("MV Boli", Font.BOLD, 120));
                botonesTablero1[i].setFocusable(false);
                botonesTablero1[i].addActionListener(this);
            }

    //        panelRestart.add(botonRestart);
    //        botonRestart.setFont(new Font("MV Boli",Font.BOLD, 80));

        }
        if (tipoTablero==2){
            panelBoton.setLayout(new GridLayout(5, 5));
            panelBoton.setBackground(new Color(150,150,150));
            //Botones
            for (int i = 0; i < 25; i++) {
                botonesTablero2[i] = new JButton();
                panelBoton.add(botonesTablero2[i]);
                botonesTablero2[i].setFont(new Font("MV Boli", Font.BOLD, 60));
                botonesTablero2[i].setFocusable(false);
                botonesTablero2[i].addActionListener(this);
            }

    //        panelRestart.add(botonRestart);
    //        botonRestart.setFont(new Font("MV Boli",Font.BOLD, 80));

        }
        if (tipoTablero==3){
            panelBoton.setLayout(new GridLayout(10, 10));
            panelBoton.setBackground(new Color(150,150,150));
            //Botones
            for (int i = 0; i < 100; i++) {
                botonesTablero3[i] = new JButton();
                panelBoton.add(botonesTablero3[i]);
                botonesTablero3[i].setFont(new Font("MV Boli", Font.BOLD, 30));
                botonesTablero3[i].setFocusable(false);
                botonesTablero3[i].addActionListener(this);
            }

    //        panelRestart.add(botonRestart);
    //        botonRestart.setFont(new Font("MV Boli",Font.BOLD, 80));

        }
        
        panelTitulo.add(campoTexto);
        panelInferior1.add(campoTextoInferior1);
        panelInferior2.add(campoTextoInferior2);
        panelInferior.add(panelInferior1,BorderLayout.NORTH);
        panelInferior.add(panelInferior2,BorderLayout.SOUTH);
        frame.add(panelTitulo,BorderLayout.NORTH);
        frame.add(panelBoton);
        frame.add(panelInferior,BorderLayout.SOUTH);
        //frame.add(panelRestart,BorderLayout.SOUTH);

//        barraMenu.add(menu);
//        frame.add(barraMenu);

        primerTurno();
        //sonidoNuevaPantalla.ReproducirSonidoNuevaPantalla();
    }
    
    public void actionPerformed(ActionEvent e){
        
        if (tipoTablero==1){
            for(int i=0;i<9;i++){
                if (e.getSource()==botonesTablero1[i]){
                    //comprobarSiNoHayPosicionesVacias();
                    if(turnoJugador1){
                        if(botonesTablero1[i].getText()==""){
                            sonidoPosicionVacía.ReproducirSonidoPosicionVacia();
                            botonesTablero1[i].setForeground(new Color(255,0,0));
                            botonesTablero1[i].setText("X");
                            turnoJugador1=false;
                            campoTexto.setText("Turno de O");
                            comprobar(1);
                            if(alguienGana == false)
                                comprobarSiTodasLasCasillasEstanOcupadas();
                                if(todasOcupadas){
                                    newGame(1);
                                }
                        }else{
                            sonidoPosicionOcupada.ReproducirSonidoPosicionOcupada();
                        }
                    }
                    else {
                        if(botonesTablero1[i].getText()==""){
                            sonidoPosicionVacía.ReproducirSonidoPosicionVacia();
                            botonesTablero1[i].setForeground(new Color(0,0,255));
                            botonesTablero1[i].setText("O");
                            turnoJugador1=true;
                            campoTexto.setText("Turno de X");
                            comprobar(1);
                            comprobarSiTodasLasCasillasEstanOcupadas();
                            if(todasOcupadas){
                                newGame(1);
                            }
                        }else{
                            sonidoPosicionOcupada.ReproducirSonidoPosicionOcupada();
                        }
                    }
                }
            }
            if(e.getSource()==newGame){
//            panelBoton.removeAll();
//            frame.remove(panelBoton);
            //sonidoNuevaPantalla.ReproducirSonidoNuevaPantalla();
            newGame(1);
        }
        }
        
        if (tipoTablero==2){
            for(int i=0;i<25;i++){
                if (e.getSource()==botonesTablero2[i]){
                    //comprobarSiNoHayPosicionesVacias();
                    if(turnoJugador1){
                        if(botonesTablero2[i].getText()==""){
                            sonidoPosicionVacía.ReproducirSonidoPosicionVacia();
                            botonesTablero2[i].setForeground(new Color(255,0,0));
                            botonesTablero2[i].setText("X");
                            turnoJugador1=false;
                            campoTexto.setText("Turno de O");
                            comprobar(2);
                            if(alguienGana == false)
                                comprobarSiTodasLasCasillasEstanOcupadas();
                                if(todasOcupadas){
                                    newGame(2);
                                }
                        }else{
                            sonidoPosicionOcupada.ReproducirSonidoPosicionOcupada();
                        }
                    }
                    else {
                        if(botonesTablero2[i].getText()==""){
                            sonidoPosicionVacía.ReproducirSonidoPosicionVacia();
                            botonesTablero2[i].setForeground(new Color(0,0,255));
                            botonesTablero2[i].setText("O");
                            turnoJugador1=true;
                            campoTexto.setText("Turno de X");
                            comprobar(2);
                            comprobarSiTodasLasCasillasEstanOcupadas();
                            if(todasOcupadas){
                                newGame(2);
                            }
                        }else{
                            sonidoPosicionOcupada.ReproducirSonidoPosicionOcupada();
                        }
                    }
                }
            }
            if(e.getSource()==newGame){
//            panelBoton.removeAll();
//            frame.remove(panelBoton);
            //sonidoNuevaPantalla.ReproducirSonidoNuevaPantalla();
            newGame(2);
        }
        }
        if (tipoTablero==3){
            for(int i=0;i<100;i++){
                if (e.getSource()==botonesTablero3[i]){
                    //comprobarSiNoHayPosicionesVacias();
                    if(turnoJugador1){
                        if(botonesTablero3[i].getText()==""){
                            sonidoPosicionVacía.ReproducirSonidoPosicionVacia();
                            botonesTablero3[i].setForeground(new Color(255,0,0));
                            botonesTablero3[i].setText("X");
                            turnoJugador1=false;
                            campoTexto.setText("Turno de O");
                            comprobar(3);
                            if(alguienGana == false)
                                comprobarSiTodasLasCasillasEstanOcupadas();
                                if(todasOcupadas){
                                    newGame(3);
                                }
                        }else{
                            sonidoPosicionOcupada.ReproducirSonidoPosicionOcupada();
                        }
                    }
                    else {
                        if(botonesTablero3[i].getText()==""){
                            sonidoPosicionVacía.ReproducirSonidoPosicionVacia();
                            botonesTablero3[i].setForeground(new Color(0,0,255));
                            botonesTablero3[i].setText("O");
                            turnoJugador1=true;
                            campoTexto.setText("Turno de X");
                            comprobar(3);
                            comprobarSiTodasLasCasillasEstanOcupadas();
                            if(todasOcupadas){
                                newGame(3);
                            }
                        }else{
                            sonidoPosicionOcupada.ReproducirSonidoPosicionOcupada();
                        }
                    }
                }
            }
            if(e.getSource()==newGame){
//            panelBoton.removeAll();
//            frame.remove(panelBoton);
            //sonidoNuevaPantalla.ReproducirSonidoNuevaPantalla();
            newGame(3);
        }
        }
        
//        if(e.getSource()==newGame){
////            panelBoton.removeAll();
////            frame.remove(panelBoton);
//            //sonidoNuevaPantalla.ReproducirSonidoNuevaPantalla();
//            newGame(1);
//        }
        if(e.getSource()==exit){
            sonidoExit.ReproducirSonidoExit();
            tiempoEspera3();
            System.exit(0);
        }
        if(e.getSource()==restart){
//            panelBoton.removeAll();
//            frame.remove(panelBoton);
            puntosO = 0;
            puntosX = 0;
            campoTextoInferior2.setText("Puntos O = " + String.valueOf(puntosO));
            campoTextoInferior1.setText("Puntos X = " + String.valueOf(puntosX));
            if(tipoTablero==1)
                newGame(1);
            if(tipoTablero==2)
                newGame(2);
            if(tipoTablero==3)
                newGame(3);
        }
        if(e.getSource()==tablero1){
            puntosO = 0;
            puntosX = 0;
            newGame(1);
        }
        if(e.getSource()==tablero2){
            puntosO = 0;
            puntosX = 0;
            newGame(2);
        }
        if(e.getSource()==tablero3){
            puntosO = 0;
            puntosX = 0;
            newGame(3);
        }
    }
    
    public void primerTurno(){
        
        //Al principio sale el título "Tres en Raya"
        //y un poco despues sale el título del turno
        tiempoEspera1();
        
        if(random.nextInt(2)==0){
            turnoJugador1 = true;
            campoTexto.setText("Turno de X");
        }
        else{
            turnoJugador1 = false;
            campoTexto.setText("Turno de O");
        }
        tiempoEspera2();
        sonidoNuevaPantalla.ReproducirSonidoNuevaPantalla();

    }    
    
    public Boolean comprobarSiTodasLasCasillasEstanOcupadas(){
        contador = 0;
        todasOcupadas = false;
        if(tipoTablero == 1){
            for (JButton b: botonesTablero1) {
                if (b.getText()!=""){
                    contador++;
                } if(contador==botonesTablero1.length){
                    todasOcupadas=true;
                    return todasOcupadas;
                }
            }
        }
        if(tipoTablero == 2){
            for (JButton b: botonesTablero2) {
                if (b.getText()!=""){
                    contador++;
                } if(contador==botonesTablero2.length){
                    todasOcupadas=true;
                    return todasOcupadas;
                }
            }
            todasOcupadas = false;
            return todasOcupadas;
        }
        if(tipoTablero == 3){
            for (JButton b: botonesTablero3) {
                if (b.getText()!=""){
                    contador++;
                } if(contador==botonesTablero3.length){
                    todasOcupadas=true;
                    return todasOcupadas;
                }
            }
        }
        todasOcupadas = false;
        return todasOcupadas;
    }
    public void comprobar(int tipoTablero){
        
        if(tipoTablero==1){
        //condiciones para ganar de X
            if(
                (botonesTablero1[0].getText()=="X") &&
                (botonesTablero1[1].getText()=="X") &&
                (botonesTablero1[2].getText()=="X")
                ){
                xGanaTablero1(0, 1, 2);
            }
            if(
                (botonesTablero1[3].getText()=="X") &&
                (botonesTablero1[4].getText()=="X") &&
                (botonesTablero1[5].getText()=="X")
                ){
                xGanaTablero1(3, 4, 5);
            }  
            if(
                (botonesTablero1[6].getText()=="X") &&
                (botonesTablero1[7].getText()=="X") &&
                (botonesTablero1[8].getText()=="X")
                ){
                xGanaTablero1(6, 7, 8);
            }  
            if(
                (botonesTablero1[0].getText()=="X") &&
                (botonesTablero1[3].getText()=="X") &&
                (botonesTablero1[6].getText()=="X")
                ){
                xGanaTablero1(0, 3, 6);
            }  
            if(
                (botonesTablero1[1].getText()=="X") &&
                (botonesTablero1[4].getText()=="X") &&
                (botonesTablero1[7].getText()=="X")
                ){
                xGanaTablero1(1, 4, 7);
            }  
            if(
                (botonesTablero1[2].getText()=="X") &&
                (botonesTablero1[5].getText()=="X") &&
                (botonesTablero1[8].getText()=="X")
                ){
                xGanaTablero1(2, 5, 8);
            }  
            if(
                (botonesTablero1[0].getText()=="X") &&
                (botonesTablero1[4].getText()=="X") &&
                (botonesTablero1[8].getText()=="X")
                ){
                xGanaTablero1(0, 4, 8);
            }  
            if(
                (botonesTablero1[2].getText()=="X") &&
                (botonesTablero1[4].getText()=="X") &&
                (botonesTablero1[6].getText()=="X")
                ){
                xGanaTablero1(2, 4, 6);
            }  
            //condiciones para ganar de O
            if(
                (botonesTablero1[0].getText()=="O") &&
                (botonesTablero1[1].getText()=="O") &&
                (botonesTablero1[2].getText()=="O")
                ){
                oGanaTablero1(0, 1, 2);
            }
            if(
                (botonesTablero1[3].getText()=="O") &&
                (botonesTablero1[4].getText()=="O") &&
                (botonesTablero1[5].getText()=="O")
                ){
                oGanaTablero1(3, 4, 5);
            }  
            if(
                (botonesTablero1[6].getText()=="O") &&
                (botonesTablero1[7].getText()=="O") &&
                (botonesTablero1[8].getText()=="O")
                ){
                oGanaTablero1(6, 7, 8);
            }  
            if(
                (botonesTablero1[0].getText()=="O") &&
                (botonesTablero1[3].getText()=="O") &&
                (botonesTablero1[6].getText()=="O")
                ){
                oGanaTablero1(0, 3, 6);
            }  
            if(
                (botonesTablero1[1].getText()=="O") &&
                (botonesTablero1[4].getText()=="O") &&
                (botonesTablero1[7].getText()=="O")
                ){
                oGanaTablero1(1, 4, 7);
            }  
            if(
                (botonesTablero1[2].getText()=="O") &&
                (botonesTablero1[5].getText()=="O") &&
                (botonesTablero1[8].getText()=="O")
                ){
                oGanaTablero1(2, 5, 8);
            }  
            if(
                (botonesTablero1[0].getText()=="O") &&
                (botonesTablero1[4].getText()=="O") &&
                (botonesTablero1[8].getText()=="O")
                ){
                oGanaTablero1(0, 4, 8);
            }  
            if(
                (botonesTablero1[2].getText()=="O") &&
                (botonesTablero1[4].getText()=="O") &&
                (botonesTablero1[6].getText()=="O")
                ){
                oGanaTablero1(2, 4, 6);
            }
        }
        if(tipoTablero==2){
            //Condiciones para ganar de X (tablero 2)
        
            if(
                (botonesTablero2[0].getText()=="X") &&
                (botonesTablero2[1].getText()=="X") &&
                (botonesTablero2[2].getText()=="X") &&
                (botonesTablero2[3].getText()=="X") &&
                (botonesTablero2[4].getText()=="X")
                ){
                xGanaTablero2(0, 1, 2, 3, 4);
            }
            if(
                (botonesTablero2[5].getText()=="X") &&
                (botonesTablero2[6].getText()=="X") &&
                (botonesTablero2[7].getText()=="X") &&
                (botonesTablero2[8].getText()=="X") &&
                (botonesTablero2[9].getText()=="X")
                ){
                xGanaTablero2(5, 6, 7, 8, 9);
            }
            if(
                (botonesTablero2[10].getText()=="X") &&
                (botonesTablero2[11].getText()=="X") &&
                (botonesTablero2[12].getText()=="X") &&
                (botonesTablero2[13].getText()=="X") &&
                (botonesTablero2[14].getText()=="X")
                ){
                xGanaTablero2(10, 11, 12, 13, 14);
            }
            if(
                (botonesTablero2[15].getText()=="X") &&
                (botonesTablero2[16].getText()=="X") &&
                (botonesTablero2[17].getText()=="X") &&
                (botonesTablero2[18].getText()=="X") &&
                (botonesTablero2[19].getText()=="X")
                ){
                xGanaTablero2(15, 16, 17, 18, 19);
            }
            if(
                (botonesTablero2[20].getText()=="X") &&
                (botonesTablero2[21].getText()=="X") &&
                (botonesTablero2[22].getText()=="X") &&
                (botonesTablero2[23].getText()=="X") &&
                (botonesTablero2[24].getText()=="X")
                ){
                xGanaTablero2(20, 21, 22, 23, 24);
            }
            if(
                (botonesTablero2[0].getText()=="X") &&
                (botonesTablero2[5].getText()=="X") &&
                (botonesTablero2[10].getText()=="X") &&
                (botonesTablero2[15].getText()=="X") &&
                (botonesTablero2[20].getText()=="X")
                ){
                xGanaTablero2(0, 5, 10, 15, 20);
            }
            if(
                (botonesTablero2[1].getText()=="X") &&
                (botonesTablero2[6].getText()=="X") &&
                (botonesTablero2[11].getText()=="X") &&
                (botonesTablero2[16].getText()=="X") &&
                (botonesTablero2[21].getText()=="X")
                ){
                xGanaTablero2(1, 6, 11, 16, 21);
            }
            if(
                (botonesTablero2[2].getText()=="X") &&
                (botonesTablero2[7].getText()=="X") &&
                (botonesTablero2[12].getText()=="X") &&
                (botonesTablero2[17].getText()=="X") &&
                (botonesTablero2[22].getText()=="X")
                ){
                xGanaTablero2(2, 7, 12, 17, 22);
            }
            if(
                (botonesTablero2[3].getText()=="X") &&
                (botonesTablero2[8].getText()=="X") &&
                (botonesTablero2[13].getText()=="X") &&
                (botonesTablero2[18].getText()=="X") &&
                (botonesTablero2[23].getText()=="X")
                ){
                xGanaTablero2(3, 8, 13, 18, 23);
            }
            if(
                (botonesTablero2[4].getText()=="X") &&
                (botonesTablero2[9].getText()=="X") &&
                (botonesTablero2[14].getText()=="X") &&
                (botonesTablero2[19].getText()=="X") &&
                (botonesTablero2[24].getText()=="X")
                ){
                xGanaTablero2(4, 9, 14, 19, 24);
            }
            if(
                (botonesTablero2[0].getText()=="X") &&
                (botonesTablero2[6].getText()=="X") &&
                (botonesTablero2[12].getText()=="X") &&
                (botonesTablero2[18].getText()=="X") &&
                (botonesTablero2[24].getText()=="X")
                ){
                xGanaTablero2(0, 6, 12, 18, 24);
            }
            if(
                (botonesTablero2[4].getText()=="X") &&
                (botonesTablero2[8].getText()=="X") &&
                (botonesTablero2[12].getText()=="X") &&
                (botonesTablero2[16].getText()=="X") &&
                (botonesTablero2[20].getText()=="X")
                ){
                xGanaTablero2(4, 8, 12, 16, 20);
            }           
            //Condiciones para ganar de O (Tablero 2)
        
            if(
                (botonesTablero2[0].getText()=="O") &&
                (botonesTablero2[1].getText()=="O") &&
                (botonesTablero2[2].getText()=="O") &&
                (botonesTablero2[3].getText()=="O") &&
                (botonesTablero2[4].getText()=="O")
                ){
                oGanaTablero2(0, 1, 2, 3, 4);
            }
            if(
                (botonesTablero2[5].getText()=="O") &&
                (botonesTablero2[6].getText()=="O") &&
                (botonesTablero2[7].getText()=="O") &&
                (botonesTablero2[8].getText()=="O") &&
                (botonesTablero2[9].getText()=="O")
                ){
                oGanaTablero2(5, 6, 7, 8, 9);
            }
            if(
                (botonesTablero2[10].getText()=="O") &&
                (botonesTablero2[11].getText()=="O") &&
                (botonesTablero2[12].getText()=="O") &&
                (botonesTablero2[13].getText()=="O") &&
                (botonesTablero2[14].getText()=="O")
                ){
                oGanaTablero2(10, 11, 12, 13, 14);
            }
            if(
                (botonesTablero2[15].getText()=="O") &&
                (botonesTablero2[16].getText()=="O") &&
                (botonesTablero2[17].getText()=="O") &&
                (botonesTablero2[18].getText()=="O") &&
                (botonesTablero2[19].getText()=="O")
                ){
                oGanaTablero2(15, 16, 17, 18, 19);
            }
            if(
                (botonesTablero2[20].getText()=="O") &&
                (botonesTablero2[21].getText()=="O") &&
                (botonesTablero2[22].getText()=="O") &&
                (botonesTablero2[23].getText()=="O") &&
                (botonesTablero2[24].getText()=="O")
                ){
                oGanaTablero2(20, 21, 22, 23, 24);
            }
            if(
                (botonesTablero2[0].getText()=="O") &&
                (botonesTablero2[5].getText()=="O") &&
                (botonesTablero2[10].getText()=="O") &&
                (botonesTablero2[15].getText()=="O") &&
                (botonesTablero2[20].getText()=="O")
                ){
                oGanaTablero2(0, 5, 10, 15, 20);
            }
            if(
                (botonesTablero2[1].getText()=="O") &&
                (botonesTablero2[6].getText()=="O") &&
                (botonesTablero2[11].getText()=="O") &&
                (botonesTablero2[16].getText()=="O") &&
                (botonesTablero2[21].getText()=="O")
                ){
                oGanaTablero2(1, 6, 11, 16, 21);
            }
            if(
                (botonesTablero2[2].getText()=="O") &&
                (botonesTablero2[7].getText()=="O") &&
                (botonesTablero2[12].getText()=="O") &&
                (botonesTablero2[17].getText()=="O") &&
                (botonesTablero2[22].getText()=="O")
                ){
                oGanaTablero2(2, 7, 12, 17, 22);
            }
            if(
                (botonesTablero2[3].getText()=="O") &&
                (botonesTablero2[8].getText()=="O") &&
                (botonesTablero2[13].getText()=="O") &&
                (botonesTablero2[18].getText()=="O") &&
                (botonesTablero2[23].getText()=="O")
                ){
                oGanaTablero2(3, 8, 13, 18, 23);
            }
            if(
                (botonesTablero2[4].getText()=="O") &&
                (botonesTablero2[9].getText()=="O") &&
                (botonesTablero2[14].getText()=="O") &&
                (botonesTablero2[19].getText()=="O") &&
                (botonesTablero2[24].getText()=="O")
                ){
                oGanaTablero2(4, 9, 14, 19, 24);
            }
            if(
                (botonesTablero2[0].getText()=="O") &&
                (botonesTablero2[6].getText()=="O") &&
                (botonesTablero2[12].getText()=="O") &&
                (botonesTablero2[18].getText()=="O") &&
                (botonesTablero2[24].getText()=="O")
                ){
                oGanaTablero2(0, 6, 12, 18, 24);
            }
            if(
                (botonesTablero2[4].getText()=="O") &&
                (botonesTablero2[8].getText()=="O") &&
                (botonesTablero2[12].getText()=="O") &&
                (botonesTablero2[16].getText()=="O") &&
                (botonesTablero2[20].getText()=="O")
                ){
                oGanaTablero2(4, 8, 12, 16, 20);
            }           
        }
        if(tipoTablero==3){
            //Condiciones para ganar de X (tablero 3)
        
            if(
                (botonesTablero3[0].getText()=="X") &&
                (botonesTablero3[1].getText()=="X") &&
                (botonesTablero3[2].getText()=="X") &&
                (botonesTablero3[3].getText()=="X") &&
                (botonesTablero3[4].getText()=="X") &&
                (botonesTablero3[5].getText()=="X") &&
                (botonesTablero3[6].getText()=="X") &&
                (botonesTablero3[7].getText()=="X") &&
                (botonesTablero3[8].getText()=="X") &&
                (botonesTablero3[9].getText()=="X")
                ){
                xGanaTablero3(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            }
            if(
                (botonesTablero3[10].getText()=="X") &&
                (botonesTablero3[11].getText()=="X") &&
                (botonesTablero3[12].getText()=="X") &&
                (botonesTablero3[13].getText()=="X") &&
                (botonesTablero3[14].getText()=="X") &&
                (botonesTablero3[15].getText()=="X") &&
                (botonesTablero3[16].getText()=="X") &&
                (botonesTablero3[17].getText()=="X") &&
                (botonesTablero3[18].getText()=="X") &&
                (botonesTablero3[19].getText()=="X")
                ){
                xGanaTablero3(10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
            }
            if(
                (botonesTablero3[20].getText()=="X") &&
                (botonesTablero3[21].getText()=="X") &&
                (botonesTablero3[22].getText()=="X") &&
                (botonesTablero3[23].getText()=="X") &&
                (botonesTablero3[24].getText()=="X") &&
                (botonesTablero3[25].getText()=="X") &&
                (botonesTablero3[26].getText()=="X") &&
                (botonesTablero3[27].getText()=="X") &&
                (botonesTablero3[28].getText()=="X") &&
                (botonesTablero3[29].getText()=="X")
                ){
                xGanaTablero3(20, 21, 22, 23, 24, 25, 26, 27, 28, 29);
            }
            if(
                (botonesTablero3[30].getText()=="X") &&
                (botonesTablero3[31].getText()=="X") &&
                (botonesTablero3[32].getText()=="X") &&
                (botonesTablero3[33].getText()=="X") &&
                (botonesTablero3[34].getText()=="X") &&
                (botonesTablero3[35].getText()=="X") &&
                (botonesTablero3[36].getText()=="X") &&
                (botonesTablero3[37].getText()=="X") &&
                (botonesTablero3[38].getText()=="X") &&
                (botonesTablero3[39].getText()=="X")
                ){
                xGanaTablero3(30, 31, 32, 33, 34, 35, 36, 37, 38, 39);
            }
            if(
                (botonesTablero3[40].getText()=="X") &&
                (botonesTablero3[41].getText()=="X") &&
                (botonesTablero3[42].getText()=="X") &&
                (botonesTablero3[43].getText()=="X") &&
                (botonesTablero3[44].getText()=="X") &&
                (botonesTablero3[45].getText()=="X") &&
                (botonesTablero3[46].getText()=="X") &&
                (botonesTablero3[47].getText()=="X") &&
                (botonesTablero3[48].getText()=="X") &&
                (botonesTablero3[49].getText()=="X")
                ){
                xGanaTablero3(40, 41, 42, 43, 44, 45, 46, 47, 48, 49);
            }
            if(
                (botonesTablero3[50].getText()=="X") &&
                (botonesTablero3[51].getText()=="X") &&
                (botonesTablero3[52].getText()=="X") &&
                (botonesTablero3[53].getText()=="X") &&
                (botonesTablero3[54].getText()=="X") &&
                (botonesTablero3[55].getText()=="X") &&
                (botonesTablero3[56].getText()=="X") &&
                (botonesTablero3[57].getText()=="X") &&
                (botonesTablero3[58].getText()=="X") &&
                (botonesTablero3[59].getText()=="X")
                ){
                xGanaTablero3(50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
            }
            if(
                (botonesTablero3[60].getText()=="X") &&
                (botonesTablero3[61].getText()=="X") &&
                (botonesTablero3[62].getText()=="X") &&
                (botonesTablero3[63].getText()=="X") &&
                (botonesTablero3[64].getText()=="X") &&
                (botonesTablero3[65].getText()=="X") &&
                (botonesTablero3[66].getText()=="X") &&
                (botonesTablero3[67].getText()=="X") &&
                (botonesTablero3[68].getText()=="X") &&
                (botonesTablero3[69].getText()=="X")
                ){
                xGanaTablero3(60, 61, 62, 63, 64, 65, 66, 67, 68, 69);
            }
            if(
                (botonesTablero3[70].getText()=="X") &&
                (botonesTablero3[71].getText()=="X") &&
                (botonesTablero3[72].getText()=="X") &&
                (botonesTablero3[73].getText()=="X") &&
                (botonesTablero3[74].getText()=="X") &&
                (botonesTablero3[75].getText()=="X") &&
                (botonesTablero3[76].getText()=="X") &&
                (botonesTablero3[77].getText()=="X") &&
                (botonesTablero3[78].getText()=="X") &&
                (botonesTablero3[79].getText()=="X")
                ){
                xGanaTablero3(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            }
            if(
                (botonesTablero3[80].getText()=="X") &&
                (botonesTablero3[81].getText()=="X") &&
                (botonesTablero3[82].getText()=="X") &&
                (botonesTablero3[83].getText()=="X") &&
                (botonesTablero3[84].getText()=="X") &&
                (botonesTablero3[85].getText()=="X") &&
                (botonesTablero3[86].getText()=="X") &&
                (botonesTablero3[87].getText()=="X") &&
                (botonesTablero3[88].getText()=="X") &&
                (botonesTablero3[89].getText()=="X")
                ){
                xGanaTablero3(80, 81, 82, 83, 84, 85, 86, 87, 88, 89);
            }
            if(
                (botonesTablero3[90].getText()=="X") &&
                (botonesTablero3[91].getText()=="X") &&
                (botonesTablero3[92].getText()=="X") &&
                (botonesTablero3[93].getText()=="X") &&
                (botonesTablero3[94].getText()=="X") &&
                (botonesTablero3[95].getText()=="X") &&
                (botonesTablero3[96].getText()=="X") &&
                (botonesTablero3[97].getText()=="X") &&
                (botonesTablero3[98].getText()=="X") &&
                (botonesTablero3[99].getText()=="X")
                ){
                xGanaTablero3(90, 91, 92, 93, 94, 95, 96, 97, 98, 99);
            }
            if(
                (botonesTablero3[0].getText()=="X") &&
                (botonesTablero3[11].getText()=="X") &&
                (botonesTablero3[22].getText()=="X") &&
                (botonesTablero3[32].getText()=="X") &&
                (botonesTablero3[44].getText()=="X") &&
                (botonesTablero3[55].getText()=="X") &&
                (botonesTablero3[66].getText()=="X") &&
                (botonesTablero3[77].getText()=="X") &&
                (botonesTablero3[88].getText()=="X") &&
                (botonesTablero3[99].getText()=="X")
                ){
                xGanaTablero3(0, 11, 22, 33, 44, 55, 66, 77, 88, 99);
            }
            if(
                (botonesTablero3[9].getText()=="X") &&
                (botonesTablero3[18].getText()=="X") &&
                (botonesTablero3[27].getText()=="X") &&
                (botonesTablero3[36].getText()=="X") &&
                (botonesTablero3[45].getText()=="X") &&
                (botonesTablero3[54].getText()=="X") &&
                (botonesTablero3[63].getText()=="X") &&
                (botonesTablero3[72].getText()=="X") &&
                (botonesTablero3[81].getText()=="X") &&
                (botonesTablero3[90].getText()=="X")
                ){
                xGanaTablero3(9, 18, 27, 36, 45, 54, 63, 72, 81, 90);
            }
            if(
                (botonesTablero3[0].getText()=="X") &&
                (botonesTablero3[10].getText()=="X") &&
                (botonesTablero3[20].getText()=="X") &&
                (botonesTablero3[30].getText()=="X") &&
                (botonesTablero3[40].getText()=="X") &&
                (botonesTablero3[50].getText()=="X") &&
                (botonesTablero3[60].getText()=="X") &&
                (botonesTablero3[70].getText()=="X") &&
                (botonesTablero3[80].getText()=="X") &&
                (botonesTablero3[90].getText()=="X")
                ){
                xGanaTablero3(0, 10, 20, 30, 40, 50, 60, 70, 80, 90);
            }
            if(
                (botonesTablero3[1].getText()=="X") &&
                (botonesTablero3[11].getText()=="X") &&
                (botonesTablero3[21].getText()=="X") &&
                (botonesTablero3[31].getText()=="X") &&
                (botonesTablero3[41].getText()=="X") &&
                (botonesTablero3[51].getText()=="X") &&
                (botonesTablero3[61].getText()=="X") &&
                (botonesTablero3[71].getText()=="X") &&
                (botonesTablero3[81].getText()=="X") &&
                (botonesTablero3[91].getText()=="X")
                ){
                xGanaTablero3(1, 11, 21, 31, 41, 51, 61, 71, 81, 91);
            }
            if(
                (botonesTablero3[2].getText()=="X") &&
                (botonesTablero3[12].getText()=="X") &&
                (botonesTablero3[22].getText()=="X") &&
                (botonesTablero3[32].getText()=="X") &&
                (botonesTablero3[42].getText()=="X") &&
                (botonesTablero3[52].getText()=="X") &&
                (botonesTablero3[62].getText()=="X") &&
                (botonesTablero3[72].getText()=="X") &&
                (botonesTablero3[82].getText()=="X") &&
                (botonesTablero3[92].getText()=="X")
                ){
                xGanaTablero3(2, 12, 22, 32, 42, 52, 62, 72, 82, 92);
            }
            if(
                (botonesTablero3[3].getText()=="X") &&
                (botonesTablero3[13].getText()=="X") &&
                (botonesTablero3[23].getText()=="X") &&
                (botonesTablero3[33].getText()=="X") &&
                (botonesTablero3[43].getText()=="X") &&
                (botonesTablero3[53].getText()=="X") &&
                (botonesTablero3[63].getText()=="X") &&
                (botonesTablero3[73].getText()=="X") &&
                (botonesTablero3[83].getText()=="X") &&
                (botonesTablero3[93].getText()=="X")
                ){
                xGanaTablero3(3, 13, 23, 33, 43, 53, 63, 73, 83, 93);
            }
            if(
                (botonesTablero3[4].getText()=="X") &&
                (botonesTablero3[14].getText()=="X") &&
                (botonesTablero3[24].getText()=="X") &&
                (botonesTablero3[34].getText()=="X") &&
                (botonesTablero3[44].getText()=="X") &&
                (botonesTablero3[54].getText()=="X") &&
                (botonesTablero3[64].getText()=="X") &&
                (botonesTablero3[74].getText()=="X") &&
                (botonesTablero3[84].getText()=="X") &&
                (botonesTablero3[94].getText()=="X")
                ){
                xGanaTablero3(4, 14, 24, 34, 44, 54, 64, 74, 84, 94);
            }
            if(
                (botonesTablero3[5].getText()=="X") &&
                (botonesTablero3[15].getText()=="X") &&
                (botonesTablero3[25].getText()=="X") &&
                (botonesTablero3[35].getText()=="X") &&
                (botonesTablero3[45].getText()=="X") &&
                (botonesTablero3[55].getText()=="X") &&
                (botonesTablero3[65].getText()=="X") &&
                (botonesTablero3[75].getText()=="X") &&
                (botonesTablero3[85].getText()=="X") &&
                (botonesTablero3[95].getText()=="X")
                ){
                xGanaTablero3(5, 15, 25, 35, 45, 55, 65, 75, 85, 95);
            }
            if(
                (botonesTablero3[6].getText()=="X") &&
                (botonesTablero3[16].getText()=="X") &&
                (botonesTablero3[26].getText()=="X") &&
                (botonesTablero3[36].getText()=="X") &&
                (botonesTablero3[46].getText()=="X") &&
                (botonesTablero3[56].getText()=="X") &&
                (botonesTablero3[66].getText()=="X") &&
                (botonesTablero3[76].getText()=="X") &&
                (botonesTablero3[86].getText()=="X") &&
                (botonesTablero3[96].getText()=="X")
                ){
                xGanaTablero3(6, 16, 26, 36, 46, 56, 66, 76, 86, 96);
            }
            if(
                (botonesTablero3[7].getText()=="X") &&
                (botonesTablero3[17].getText()=="X") &&
                (botonesTablero3[27].getText()=="X") &&
                (botonesTablero3[37].getText()=="X") &&
                (botonesTablero3[47].getText()=="X") &&
                (botonesTablero3[57].getText()=="X") &&
                (botonesTablero3[67].getText()=="X") &&
                (botonesTablero3[77].getText()=="X") &&
                (botonesTablero3[87].getText()=="X") &&
                (botonesTablero3[97].getText()=="X")
                ){
                xGanaTablero3(7, 17, 27, 37, 47, 57, 67, 77, 87, 97);
            }
            if(
                (botonesTablero3[8].getText()=="X") &&
                (botonesTablero3[18].getText()=="X") &&
                (botonesTablero3[28].getText()=="X") &&
                (botonesTablero3[38].getText()=="X") &&
                (botonesTablero3[48].getText()=="X") &&
                (botonesTablero3[58].getText()=="X") &&
                (botonesTablero3[68].getText()=="X") &&
                (botonesTablero3[78].getText()=="X") &&
                (botonesTablero3[88].getText()=="X") &&
                (botonesTablero3[98].getText()=="X")
                ){
                xGanaTablero3(8, 18, 28, 38, 48, 58, 68, 78, 88, 98);
            }
            if(
                (botonesTablero3[9].getText()=="X") &&
                (botonesTablero3[19].getText()=="X") &&
                (botonesTablero3[29].getText()=="X") &&
                (botonesTablero3[39].getText()=="X") &&
                (botonesTablero3[49].getText()=="X") &&
                (botonesTablero3[59].getText()=="X") &&
                (botonesTablero3[69].getText()=="X") &&
                (botonesTablero3[79].getText()=="X") &&
                (botonesTablero3[89].getText()=="X") &&
                (botonesTablero3[99].getText()=="X")
                ){
                xGanaTablero3(9, 19, 29, 39, 49, 59, 69, 79, 89, 99);
            }
            //Condiciones para ganar de O (tablero 3)
        
            if(
                (botonesTablero3[0].getText()=="O") &&
                (botonesTablero3[1].getText()=="O") &&
                (botonesTablero3[2].getText()=="O") &&
                (botonesTablero3[3].getText()=="O") &&
                (botonesTablero3[4].getText()=="O") &&
                (botonesTablero3[5].getText()=="O") &&
                (botonesTablero3[6].getText()=="O") &&
                (botonesTablero3[7].getText()=="O") &&
                (botonesTablero3[8].getText()=="O") &&
                (botonesTablero3[9].getText()=="O")
                ){
                oGanaTablero3(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            }
            if(
                (botonesTablero3[10].getText()=="O") &&
                (botonesTablero3[11].getText()=="O") &&
                (botonesTablero3[12].getText()=="O") &&
                (botonesTablero3[13].getText()=="O") &&
                (botonesTablero3[14].getText()=="O") &&
                (botonesTablero3[15].getText()=="O") &&
                (botonesTablero3[16].getText()=="O") &&
                (botonesTablero3[17].getText()=="O") &&
                (botonesTablero3[18].getText()=="O") &&
                (botonesTablero3[19].getText()=="O")
                ){
                oGanaTablero3(10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
            }
            if(
                (botonesTablero3[20].getText()=="O") &&
                (botonesTablero3[21].getText()=="O") &&
                (botonesTablero3[22].getText()=="O") &&
                (botonesTablero3[23].getText()=="O") &&
                (botonesTablero3[24].getText()=="O") &&
                (botonesTablero3[25].getText()=="O") &&
                (botonesTablero3[26].getText()=="O") &&
                (botonesTablero3[27].getText()=="O") &&
                (botonesTablero3[28].getText()=="O") &&
                (botonesTablero3[29].getText()=="O")
                ){
                oGanaTablero3(20, 21, 22, 23, 24, 25, 26, 27, 28, 29);
            }
            if(
                (botonesTablero3[30].getText()=="O") &&
                (botonesTablero3[31].getText()=="O") &&
                (botonesTablero3[32].getText()=="O") &&
                (botonesTablero3[33].getText()=="O") &&
                (botonesTablero3[34].getText()=="O") &&
                (botonesTablero3[35].getText()=="O") &&
                (botonesTablero3[36].getText()=="O") &&
                (botonesTablero3[37].getText()=="O") &&
                (botonesTablero3[38].getText()=="O") &&
                (botonesTablero3[39].getText()=="O")
                ){
                oGanaTablero3(30, 31, 32, 33, 34, 35, 36, 37, 38, 39);
            }
            if(
                (botonesTablero3[40].getText()=="O") &&
                (botonesTablero3[41].getText()=="O") &&
                (botonesTablero3[42].getText()=="O") &&
                (botonesTablero3[43].getText()=="O") &&
                (botonesTablero3[44].getText()=="O") &&
                (botonesTablero3[45].getText()=="O") &&
                (botonesTablero3[46].getText()=="O") &&
                (botonesTablero3[47].getText()=="O") &&
                (botonesTablero3[48].getText()=="O") &&
                (botonesTablero3[49].getText()=="O")
                ){
                oGanaTablero3(40, 41, 42, 43, 44, 45, 46, 47, 48, 49);
            }
            if(
                (botonesTablero3[50].getText()=="O") &&
                (botonesTablero3[51].getText()=="O") &&
                (botonesTablero3[52].getText()=="O") &&
                (botonesTablero3[53].getText()=="O") &&
                (botonesTablero3[54].getText()=="O") &&
                (botonesTablero3[55].getText()=="O") &&
                (botonesTablero3[56].getText()=="O") &&
                (botonesTablero3[57].getText()=="O") &&
                (botonesTablero3[58].getText()=="O") &&
                (botonesTablero3[59].getText()=="O")
                ){
                oGanaTablero3(50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
            }
            if(
                (botonesTablero3[60].getText()=="O") &&
                (botonesTablero3[61].getText()=="O") &&
                (botonesTablero3[62].getText()=="O") &&
                (botonesTablero3[63].getText()=="O") &&
                (botonesTablero3[64].getText()=="O") &&
                (botonesTablero3[65].getText()=="O") &&
                (botonesTablero3[66].getText()=="O") &&
                (botonesTablero3[67].getText()=="O") &&
                (botonesTablero3[68].getText()=="O") &&
                (botonesTablero3[69].getText()=="O")
                ){
                oGanaTablero3(60, 61, 62, 63, 64, 65, 66, 67, 68, 69);
            }
            if(
                (botonesTablero3[70].getText()=="O") &&
                (botonesTablero3[71].getText()=="O") &&
                (botonesTablero3[72].getText()=="O") &&
                (botonesTablero3[73].getText()=="O") &&
                (botonesTablero3[74].getText()=="O") &&
                (botonesTablero3[75].getText()=="O") &&
                (botonesTablero3[76].getText()=="O") &&
                (botonesTablero3[77].getText()=="O") &&
                (botonesTablero3[78].getText()=="O") &&
                (botonesTablero3[79].getText()=="O")
                ){
                oGanaTablero3(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            }
            if(
                (botonesTablero3[80].getText()=="O") &&
                (botonesTablero3[81].getText()=="O") &&
                (botonesTablero3[82].getText()=="O") &&
                (botonesTablero3[83].getText()=="O") &&
                (botonesTablero3[84].getText()=="O") &&
                (botonesTablero3[85].getText()=="O") &&
                (botonesTablero3[86].getText()=="O") &&
                (botonesTablero3[87].getText()=="O") &&
                (botonesTablero3[88].getText()=="O") &&
                (botonesTablero3[89].getText()=="O")
                ){
                oGanaTablero3(80, 81, 82, 83, 84, 85, 86, 87, 88, 89);
            }
            if(
                (botonesTablero3[90].getText()=="O") &&
                (botonesTablero3[91].getText()=="O") &&
                (botonesTablero3[92].getText()=="O") &&
                (botonesTablero3[93].getText()=="O") &&
                (botonesTablero3[94].getText()=="O") &&
                (botonesTablero3[95].getText()=="O") &&
                (botonesTablero3[96].getText()=="O") &&
                (botonesTablero3[97].getText()=="O") &&
                (botonesTablero3[98].getText()=="O") &&
                (botonesTablero3[99].getText()=="O")
                ){
                oGanaTablero3(90, 91, 92, 93, 94, 95, 96, 97, 98, 99);
            }
            if(
                (botonesTablero3[0].getText()=="O") &&
                (botonesTablero3[11].getText()=="O") &&
                (botonesTablero3[22].getText()=="O") &&
                (botonesTablero3[32].getText()=="O") &&
                (botonesTablero3[44].getText()=="O") &&
                (botonesTablero3[55].getText()=="O") &&
                (botonesTablero3[66].getText()=="O") &&
                (botonesTablero3[77].getText()=="O") &&
                (botonesTablero3[88].getText()=="O") &&
                (botonesTablero3[99].getText()=="O")
                ){
                oGanaTablero3(0, 11, 22, 33, 44, 55, 66, 77, 88, 99);
            }
            if(
                (botonesTablero3[9].getText()=="O") &&
                (botonesTablero3[18].getText()=="O") &&
                (botonesTablero3[27].getText()=="O") &&
                (botonesTablero3[36].getText()=="O") &&
                (botonesTablero3[45].getText()=="O") &&
                (botonesTablero3[54].getText()=="O") &&
                (botonesTablero3[63].getText()=="O") &&
                (botonesTablero3[72].getText()=="O") &&
                (botonesTablero3[81].getText()=="O") &&
                (botonesTablero3[90].getText()=="O")
                ){
                oGanaTablero3(9, 18, 27, 36, 45, 54, 63, 72, 81, 90);
            }
            if(
                (botonesTablero3[0].getText()=="O") &&
                (botonesTablero3[10].getText()=="O") &&
                (botonesTablero3[20].getText()=="O") &&
                (botonesTablero3[30].getText()=="O") &&
                (botonesTablero3[40].getText()=="O") &&
                (botonesTablero3[50].getText()=="O") &&
                (botonesTablero3[60].getText()=="O") &&
                (botonesTablero3[70].getText()=="O") &&
                (botonesTablero3[80].getText()=="O") &&
                (botonesTablero3[90].getText()=="O")
                ){
                oGanaTablero3(0, 10, 20, 30, 40, 50, 60, 70, 80, 90);
            }
            if(
                (botonesTablero3[1].getText()=="O") &&
                (botonesTablero3[11].getText()=="O") &&
                (botonesTablero3[21].getText()=="O") &&
                (botonesTablero3[31].getText()=="O") &&
                (botonesTablero3[41].getText()=="O") &&
                (botonesTablero3[51].getText()=="O") &&
                (botonesTablero3[61].getText()=="O") &&
                (botonesTablero3[71].getText()=="O") &&
                (botonesTablero3[81].getText()=="O") &&
                (botonesTablero3[91].getText()=="O")
                ){
                oGanaTablero3(1, 11, 21, 31, 41, 51, 61, 71, 81, 91);
            }
            if(
                (botonesTablero3[2].getText()=="O") &&
                (botonesTablero3[12].getText()=="O") &&
                (botonesTablero3[22].getText()=="O") &&
                (botonesTablero3[32].getText()=="O") &&
                (botonesTablero3[42].getText()=="O") &&
                (botonesTablero3[52].getText()=="O") &&
                (botonesTablero3[62].getText()=="O") &&
                (botonesTablero3[72].getText()=="O") &&
                (botonesTablero3[82].getText()=="O") &&
                (botonesTablero3[92].getText()=="O")
                ){
                oGanaTablero3(2, 12, 22, 32, 42, 52, 62, 72, 82, 92);
            }
            if(
                (botonesTablero3[3].getText()=="O") &&
                (botonesTablero3[13].getText()=="O") &&
                (botonesTablero3[23].getText()=="O") &&
                (botonesTablero3[33].getText()=="O") &&
                (botonesTablero3[43].getText()=="O") &&
                (botonesTablero3[53].getText()=="O") &&
                (botonesTablero3[63].getText()=="O") &&
                (botonesTablero3[73].getText()=="O") &&
                (botonesTablero3[83].getText()=="O") &&
                (botonesTablero3[93].getText()=="O")
                ){
                oGanaTablero3(3, 13, 23, 33, 43, 53, 63, 73, 83, 93);
            }
            if(
                (botonesTablero3[4].getText()=="O") &&
                (botonesTablero3[14].getText()=="O") &&
                (botonesTablero3[24].getText()=="O") &&
                (botonesTablero3[34].getText()=="O") &&
                (botonesTablero3[44].getText()=="O") &&
                (botonesTablero3[54].getText()=="O") &&
                (botonesTablero3[64].getText()=="O") &&
                (botonesTablero3[74].getText()=="O") &&
                (botonesTablero3[84].getText()=="O") &&
                (botonesTablero3[94].getText()=="O")
                ){
                oGanaTablero3(4, 14, 24, 34, 44, 54, 64, 74, 84, 94);
            }
            if(
                (botonesTablero3[5].getText()=="O") &&
                (botonesTablero3[15].getText()=="O") &&
                (botonesTablero3[25].getText()=="O") &&
                (botonesTablero3[35].getText()=="O") &&
                (botonesTablero3[45].getText()=="O") &&
                (botonesTablero3[55].getText()=="O") &&
                (botonesTablero3[65].getText()=="O") &&
                (botonesTablero3[75].getText()=="O") &&
                (botonesTablero3[85].getText()=="O") &&
                (botonesTablero3[95].getText()=="O")
                ){
                oGanaTablero3(5, 15, 25, 35, 45, 55, 65, 75, 85, 95);
            }
            if(
                (botonesTablero3[6].getText()=="O") &&
                (botonesTablero3[16].getText()=="O") &&
                (botonesTablero3[26].getText()=="O") &&
                (botonesTablero3[36].getText()=="O") &&
                (botonesTablero3[46].getText()=="O") &&
                (botonesTablero3[56].getText()=="O") &&
                (botonesTablero3[66].getText()=="O") &&
                (botonesTablero3[76].getText()=="O") &&
                (botonesTablero3[86].getText()=="O") &&
                (botonesTablero3[96].getText()=="O")
                ){
                oGanaTablero3(6, 16, 26, 36, 46, 56, 66, 76, 86, 96);
            }
            if(
                (botonesTablero3[7].getText()=="O") &&
                (botonesTablero3[17].getText()=="O") &&
                (botonesTablero3[27].getText()=="O") &&
                (botonesTablero3[37].getText()=="O") &&
                (botonesTablero3[47].getText()=="O") &&
                (botonesTablero3[57].getText()=="O") &&
                (botonesTablero3[67].getText()=="O") &&
                (botonesTablero3[77].getText()=="O") &&
                (botonesTablero3[87].getText()=="O") &&
                (botonesTablero3[97].getText()=="O")
                ){
                oGanaTablero3(7, 17, 27, 37, 47, 57, 67, 77, 87, 97);
            }
            if(
                (botonesTablero3[8].getText()=="O") &&
                (botonesTablero3[18].getText()=="O") &&
                (botonesTablero3[28].getText()=="O") &&
                (botonesTablero3[38].getText()=="O") &&
                (botonesTablero3[48].getText()=="O") &&
                (botonesTablero3[58].getText()=="O") &&
                (botonesTablero3[68].getText()=="O") &&
                (botonesTablero3[78].getText()=="O") &&
                (botonesTablero3[88].getText()=="O") &&
                (botonesTablero3[98].getText()=="O")
                ){
                oGanaTablero3(8, 18, 28, 38, 48, 58, 68, 78, 88, 98);
            }
            if(
                (botonesTablero3[9].getText()=="O") &&
                (botonesTablero3[19].getText()=="O") &&
                (botonesTablero3[29].getText()=="O") &&
                (botonesTablero3[39].getText()=="O") &&
                (botonesTablero3[49].getText()=="O") &&
                (botonesTablero3[59].getText()=="O") &&
                (botonesTablero3[69].getText()=="O") &&
                (botonesTablero3[79].getText()=="O") &&
                (botonesTablero3[89].getText()=="O") &&
                (botonesTablero3[99].getText()=="O")
                ){
                oGanaTablero3(9, 19, 29, 39, 49, 59, 69, 79, 89, 99);
            }
                       
        }
    }
            
            
    
    
    public void xGanaTablero1(int a,int b,int c){
        botonesTablero1[a].setBackground(Color.GREEN);
        botonesTablero1[b].setBackground(Color.GREEN);
        botonesTablero1[c].setBackground(Color.GREEN);
        
        for(int i=0;i<9;i++){
            botonesTablero1[i].setEnabled(false);
        }
        campoTexto.setText("X GANA");
        sonidoVictoria.ReproducirSonidoVictoria();
        puntosX += 1;
        campoTextoInferior1.setText("Puntos X = " + String.valueOf(puntosX));
        alguienGana = true;
        
    }
    public void xGanaTablero2(int a,int b,int c,int d,int e){
        botonesTablero2[a].setBackground(Color.GREEN);
        botonesTablero2[b].setBackground(Color.GREEN);
        botonesTablero2[c].setBackground(Color.GREEN);
        botonesTablero2[d].setBackground(Color.GREEN);
        botonesTablero2[e].setBackground(Color.GREEN);
        
        for(int i=0;i<25;i++){
            botonesTablero2[i].setEnabled(false);
        }
        campoTexto.setText("X GANA");
        sonidoVictoria.ReproducirSonidoVictoria();
        puntosX += 1;
        campoTextoInferior1.setText("Puntos X = " + String.valueOf(puntosX));
        alguienGana = true;
        
    }
    public void xGanaTablero3(int a,int b,int c,int d, int e,int f, int g, int h, int i, int j){
        botonesTablero3[a].setBackground(Color.GREEN);
        botonesTablero3[b].setBackground(Color.GREEN);
        botonesTablero3[c].setBackground(Color.GREEN);
        botonesTablero3[d].setBackground(Color.GREEN);
        botonesTablero3[e].setBackground(Color.GREEN);
        botonesTablero3[f].setBackground(Color.GREEN);
        botonesTablero3[g].setBackground(Color.GREEN);
        botonesTablero3[h].setBackground(Color.GREEN);
        botonesTablero3[i].setBackground(Color.GREEN);
        botonesTablero3[j].setBackground(Color.GREEN);
        
        for(int iter=0;iter<100;iter++){
            botonesTablero3[iter].setEnabled(false);
        }
        campoTexto.setText("X GANA");
        sonidoVictoria.ReproducirSonidoVictoria();
        puntosX += 1;
        campoTextoInferior1.setText("Puntos X = " + String.valueOf(puntosX));
        alguienGana = true;
        
    }
    public void oGanaTablero1(int a,int b,int c){
        botonesTablero1[a].setBackground(Color.GREEN);
        botonesTablero1[b].setBackground(Color.GREEN);
        botonesTablero1[c].setBackground(Color.GREEN);
        
        for(int i=0;i<9;i++){
            botonesTablero1[i].setEnabled(false);
        }
        campoTexto.setText("O GANA");
        sonidoVictoria.ReproducirSonidoVictoria();
        puntosO += 1;
        campoTextoInferior2.setText("Puntos O = " + String.valueOf(puntosO));
        //frame.setVisible(false);
        //newGame(1);
        alguienGana = true;
    }
    public void oGanaTablero2(int a,int b,int c,int d,int e){
        botonesTablero2[a].setBackground(Color.GREEN);
        botonesTablero2[b].setBackground(Color.GREEN);
        botonesTablero2[c].setBackground(Color.GREEN);
        botonesTablero2[d].setBackground(Color.GREEN);
        botonesTablero2[e].setBackground(Color.GREEN);
        
        for(int i=0;i<25;i++){
            botonesTablero2[i].setEnabled(false);
        }
        campoTexto.setText("O GANA");
        sonidoVictoria.ReproducirSonidoVictoria();
        puntosO += 1;
        campoTextoInferior2.setText("Puntos O = " + String.valueOf(puntosO));
        //frame.setVisible(false);
        //newGame(1);
        alguienGana = true;
    }
    public void oGanaTablero3(int a,int b,int c,int d, int e,int f, int g, int h, int i, int j){
        botonesTablero3[a].setBackground(Color.GREEN);
        botonesTablero3[b].setBackground(Color.GREEN);
        botonesTablero3[c].setBackground(Color.GREEN);
        botonesTablero3[d].setBackground(Color.GREEN);
        botonesTablero3[e].setBackground(Color.GREEN);
        botonesTablero3[f].setBackground(Color.GREEN);
        botonesTablero3[g].setBackground(Color.GREEN);
        botonesTablero3[h].setBackground(Color.GREEN);
        botonesTablero3[i].setBackground(Color.GREEN);
        botonesTablero3[j].setBackground(Color.GREEN);
        
        for(int iter=0;iter<100;iter++){
            botonesTablero3[iter].setEnabled(false);
        }
        campoTexto.setText("O GANA");
        sonidoVictoria.ReproducirSonidoVictoria();
        puntosO += 1;
        campoTextoInferior2.setText("Puntos O = " + String.valueOf(puntosO));
        //frame.setVisible(false);
        //newGame(1);
        alguienGana = true;
    }
    
    public void tiempoEspera1(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TresEnRaya.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tiempoEspera2(){
        try {
            Thread.sleep(60);
        } catch (InterruptedException ex) {
            Logger.getLogger(TresEnRaya.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tiempoEspera3(){
        try {
            Thread.sleep(850);
        } catch (InterruptedException ex) {
            Logger.getLogger(TresEnRaya.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
