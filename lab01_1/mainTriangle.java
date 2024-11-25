package lab01_1;
//主函数入口
public class mainTriangle{
    public static void main(String[] args) {
        try{
            Triangle t = new Triangle(1.0, 1.5, 1.0,"yellow",true);
            System.out.println(t.toString());
            System.out.println(t.getArea());
            System.out.println(t.getPerimeter());
            System.out.println(t.getColor());
            System.out.println(t.isFilled());
            for (double side : t.getter()) {
                System.out.println("Side: " + side);
            }
            Triangle test1 = new Triangle(-1.0, 0.0,-1.0);
            
        }catch(IllegalSideException e){
            System.out.println(e.getMessage());

        }
        try{Triangle test2 = new Triangle(1.0, 2.0, 5.0); }
        catch(IllegalSideException e){
            System.out.println(e.getMessage());
        }
    }
}


