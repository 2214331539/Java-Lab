package lab_1;
//作为三角形处理的文件

//父类 用来存放颜色和名字
class GeometricObject {
    private String color;
    private Boolean isFilled;

    public GeometricObject() {
        this.color = "Unknown";
        this.isFilled = false;
    }
    public GeometricObject(String color, Boolean isFilled) {
        this.color = color;
        this.isFilled = false;
    }
    public String getColor() {
        return color;
    }
    public String getName() {
        return isFilled? "Filled ":"Unfilled ";
    }
    public Boolean isFilled() {  
        return isFilled;
    }
}

//子类 用来存放三角形的三个边 并作为main函数入口
class Triangle extends GeometricObject {
    private double side1;
    private double side2;
    private double side3;
    
    //接受三边长
    public Triangle(double side1, double side2, double side3)throws IllegalSideException {
        super("black", false);
        if(side1 <= 0 || side2 <= 0 || side3 <= 0||side1+side2<=side3||side1+side3<=side2||side2+side3<=side1){
            throw new IllegalSideException("Illegal side!",side1,side2,side3);
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    //接受完整参数
    public Triangle(double side1, double side2, double side3, String color, Boolean isFilled)throws IllegalSideException {
        if(side1 <= 0 || side2 <= 0 || side3 <= 0||side1+side2<=side3||side1+side3<=side2||side2+side3<=side1){
            throw new IllegalSideException("Illegal side!",side1,side2,side3);
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    //默认三角形
    public Triangle()throws IllegalSideException {
        this(3,4,5,"black",false);
    }

    public double[] getter(){
        return new double[]{this.side1, this.side2, this.side3};
    }  
    public String getArea(){
        return "the Area is:" + 0.5 * Math.sqrt(this.side1 * this.side2 * this.side3);
    }
    public String getPerimeter(){
        return "the Perimeter is:"+this.side1 + this.side2 + this.side3;
    }
    public String toString(){
        return "Triangle: side1 = " + side1 + ", side2 = " + side2 + ", side3 = " + side3;
    }
}