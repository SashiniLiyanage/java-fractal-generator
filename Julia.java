public class Julia extends Fractal{

    //constructor overloading
    public Julia(){

    }
    public Julia(String iterations){
        Fractal.iteration=Integer.parseInt(iterations.trim()); //cast strings into integer 
    }
    public Julia(String cx,String cy,String iterations){
        Fractal.iteration=Integer.parseInt(iterations.trim()); //cast strings into integer
        cr=Double.parseDouble(cx);
        ci=Double.parseDouble(cy);
    }

    //method overriding
    public void renderSet(){
        for(int x=0;x<WIDTH;x++){
            for(int y=0; y<HEIGHT;y++){
                //get the color corrosponding to the iteration
                int color=getColor((x*2/(double)WIDTH)-1,1-(y*2/(double)HEIGHT),cr,ci);
                image.setRGB(x,y,color);    //set the color as rgb
            }
        }
        createFrame("Julia Set");
    }




}