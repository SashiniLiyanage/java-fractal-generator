/**************************
  E/17/190 CO225 - PROJECT1
 **************************/


import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.nio.Buffer;
import java.awt.image.BufferedImage;

public class Fractal extends JComponent{

    public static final int WIDTH= 800;
    public static final int HEIGHT=800;
    public static int iteration;
    public double cr,ci,roi_lx,roi_ux,roi_ly,roi_uy; //reigeon of interest lower and uper x,y values

    public BufferedImage image;
    

    public Fractal(){
        //default parameters
        iteration=1000; 
        this.cr=-0.4f;
        this.ci=0.6f;
        this.roi_lx=-1;
        this.roi_ux=1;
        this.roi_ly=-1;
        this.roi_uy=1;
        //create the image 
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB); 
    }

    //create the frame for the image
    public void createFrame(String name){
        JFrame frame = new JFrame(name);
        JLabel label=new JLabel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        label.setIcon(new ImageIcon(image));
        frame.getContentPane().add(label,BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        
    }

    @Override
    public void addNotify(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    //overriding in child classes
    public void renderSet(){
    }

    
    //get the color acording to the iteration
    public int getColor(double r0, double i0, double cr, double ci){
        double zr=r0;
        double zi=i0;
        int i=0;
        for(i=0;i<iteration;i++){
            double nx = zr*zr - zi*zi +cr;      //calculations of real part of the complex number
            double ny= 2*zr*zi +ci;             //calculations of imaginary part of the complex number
            zr=nx;
            zi=ny;
            if(zr*zr + zi*zi >4) break;         //stop the loop when |Z|>2 (|Z|^2 > 4)
        }
        if(i==iteration) return 0x00000000;                 //set black color for stable complex nuumbers within given range
        int a = (int) (255 * ((double) i) / (250));         //color selection 
        return ( (0) | (12*a<<16) | (a<<8) | ((a*3)<<0) );  //get the rgb value
    }


    @Override
    public void paint(Graphics g){
        g.drawImage(image,0,0,null);
    }
    
    
    //usage for invalid inputs
    public static void usage(){
        System.out.println("ERROR:invalid usage");
        System.out.println("usage:");
        System.out.println("\tjava Fractal Mandelbrot");
        System.out.println("\tjava Fractal Mandelbrot <iterations>");
        System.out.println("\tjava Fractal Mandelbrot <real region of interest> <complex region of interest> <iterations> ");
        System.out.println("\tjava Fractal Julia");
        System.out.println("\tjava Fractal Julia <iterations>");
        System.out.println("\tjava Fractal Julia <real part of complex> <imaginary part of complex> <iterations> ");
    }


    public static void main(String[] args) {

        Fractal set;
        //possible inputs
        if(args.length==1 && args[0].equals("Mandelbrot")){
            set= new Mandelbrot();
            set.renderSet();
        }else if(args.length==2 && args[0].equals("Mandelbrot")){
            set= new Mandelbrot(args[1]);
            set.renderSet();
        }else if(args.length==1 && args[0].equals("Julia")){
            set= new Julia();
            set.renderSet();
        }else if(args.length==2 && args[0].equals("Julia")){
            set= new Julia(args[1]);
            set.renderSet();
        }else if(args.length==4 && args[0].equals("Julia")){
            set= new Julia(args[1],args[2],args[3]);
            set.renderSet();
        }else if(args.length==5 && args[0].equals("Mandelbrot")){
            set= new Mandelbrot(args[1],args[2],args[3],args[4]);
            set.renderSet();
        }else if(args.length==6 && args[0].equals("Mandelbrot")){
            set= new Mandelbrot(args[1],args[2],args[3],args[4],args[5]);
            set.renderSet();
        }else{
            usage();
    
        }
        
    }
}