// IllegalSideException class for illeagel exception
package lab01_1;
class IllegalSideException extends Exception {
    private double side1;
    private double side2;
    private double side3;

    public IllegalSideException(){
        super("Illegal Sides.");
    }
    public IllegalSideException(String message){
        super(message);
    }
    public IllegalSideException(String message, double side1, double side2, double side3){
        super("Illegal Sides.");
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    @Override
    public String getMessage(){
        return super.getMessage() + "Side1: " + side1 + ", Side2: " + side2 + ", Side3: " + side3;
    }
}