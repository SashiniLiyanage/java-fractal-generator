public class Mandelbrot extends Fractal{

    //constructor overloading
    public Mandelbrot(){
    }

    public Mandelbrot(String iterations){
        Fractal.iteration=Integer.parseInt(iterations.trim()); //cast strings into integer
    }
    
    public Mandelbrot(String rl, String ru, String il, String iu,String iterations){
        Fractal.iteration=Integer.parseInt(iterations.trim()); //cast strings into integer
        roi_lx=Double.parseDouble(rl);
        roi_ux=Double.parseDouble(ru);
        roi_ly=Double.parseDouble(il);
        roi_uy=Double.parseDouble(iu);
    }
    public Mandelbrot(String rl, String ru, String il, String iu){
        roi_lx=Double.parseDouble(rl);
        roi_ux=Double.parseDouble(ru);
        roi_ly=Double.parseDouble(il);
        roi_uy=Double.parseDouble(iu);
    }

    //method overriding    
    public void renderSet(){
        for(int x=0;x<800;x++){
            for(int y=0; y<800;y++){
                //get the color corrosponding to the iteration
                int color=getColor(0,0,(x/(double)WIDTH)*(roi_ux-roi_lx)+roi_lx,(y/(double)HEIGHT)*(roi_ly-roi_uy)+roi_uy);
                image.setRGB(x,y,color); //set the color as rgb
            }
        }
        createFrame("Mandelbrot Set");
    }



	
}