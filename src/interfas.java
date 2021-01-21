import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class interfas extends JFrame implements ActionListener{

    JFrame marco=new JFrame();
    JButton boton = new JButton("CALCULAR");
    JLabel Cantidad = new JLabel("¿Cuantos números evaluara?");
    JLabel Numeros = new JLabel("Digite los numeros separados por ',':");
    JLabel Error = new JLabel();
    JLabel Propietario = new JLabel("Juan Sebastian Aparicio Wallis (20201020131)");
    JLabel uni = new JLabel("UNIVERSIDAD DISTRITAL FRANCISCO JOSE DE CALDAS");

    JTextField Num = new JTextField();
    JTextField Cant = new JTextField();
    JTextArea proceso1 = new JTextArea();
    JTextArea proceso2 = new JTextArea();
    JTextArea proceso3 = new JTextArea();
    JScrollPane scroll1 = new JScrollPane (proceso1);
    JScrollPane scroll2 = new JScrollPane (proceso2);
    JScrollPane scroll3 = new JScrollPane (proceso3);
    String mensaje1="";
    String mensaje2="";
    String mensaje3="";
    int y[];
    int x;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==boton) {
            limpiar();
            int x;
            x=obtener();
            if(x!=0) {
                calcular(x-1);
                escribir();
            }
        }
    }

    interfas(){

        marco.getContentPane().setBackground(new Color(100, 100, 141));  //Background
        marco.setTitle("MCD");
        marco.setSize(490,670);
        marco.setVisible(true);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(null);

        marco.add(boton);
        boton.setBounds(300,20,100,30);
        boton.setBackground(new Color(76, 109, 219));
        boton.addActionListener(this);
        marco.add(Cantidad);
        Cantidad.setBounds(30,30,180,25);
        Cantidad.setForeground(new Color(200, 72, 255));  //Primer texto
        marco.add(Numeros);
        Numeros.setBounds(30,70,230,25);
        Numeros.setForeground(new Color(200, 72, 255));  //Segundo texto
        marco.add(Cant);
        Cant.setBounds(210,30,30,25);
        Cant.setBackground(new Color(56, 139, 140));   //Primera caja text
        marco.add(Num);
        Num.setBounds(250,70,190,25);
        Num.setBackground(new Color(56, 139, 140));
        marco.add(Error);
        Error.setBounds(250,30,250,25);
        Error.setForeground(new Color(30, 14, 14));
        marco.add(Propietario);
        Propietario.setBounds(30,100,300,25);
        Propietario.setBackground(Color.black);
        uni.setBounds(60,130,400,25);
        marco.add(uni);
        uni.setBackground(Color.black);

        scroll1.setBounds(130,170,200,200);
        proceso1.setBackground(new Color(20, 150, 175));
        marco.add(scroll1);
        scroll2.setBounds(130,380,200,200);
        proceso2.setBackground(new Color(255, 30, 134));
        marco.add(scroll2);
        scroll3.setBounds(240,170,200,410);
        //proceso3.setBackground(new Color(134, 255, 244));
        //marco.add(scroll3);



    }

    int obtener () {

        Error.setText("");
        String aux;
        String a1[];
        aux=Cant.getText();
        x=leerdatos(aux);
        y = new int [x];
        aux=Num.getText();
        a1=aux.split(",");
        if (a1.length!=y.length) {
            Error.setText("Cantidad de numeros incorrecta");
            return 0;
        }
        for(int i=0;i<x;i++){
            aux=a1[i];
            y[i]=leerdatos(aux);
        }
        Arrays.sort(y);
        return x;
    }

    boolean verificacion(String x) {
        int j;
        try {
            j=Integer.parseInt(x);
            if(j<=0) {
                return false;
            }
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    int leerdatos(String x) {
        int y=0;
        if (verificacion(x) == false) {
            Error.setText("Hay un caracter invalido");
        } else {
            y = Integer.parseInt(x);
        }
        return y;
    }

    int mcd(int a, int b) {
        if(b == 0) return a;
        mensaje1=mensaje1+(a+"="+b+"*"+a/b+"+"+a%b)+"\n";
        mensaje2=mensaje2+(b+"="+a%b+"*"+a/b+"-"+a)+"\n";
        return mcd(b, a%b);
    }

    int calcular(int j) {
        int k=y[j];
        for(int i=j; i>0; i--) {
            k=mcd(k,y[i-1]);
            mensaje1=mensaje1+"\n";
            mensaje2=mensaje2+"\n";
        }
        Error.setText("MCD:"+ k);
        return 0;
    }

    void escribir() {
        proceso1.setText(mensaje1);
        proceso2.setText(mensaje2);
    }

    void limpiar() {
        mensaje1=mensaje2=mensaje3="";
    }

    public static void main(String[] args) {
        interfas obj=new interfas();
    }
}
