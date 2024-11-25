package lab_2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class car {
    private double speed;           // 小车速度
    private ImageView carImage;     // 帅照
    private double position;        // 位置
    private Image[] images;         // 存储动态帅照
    private int Index;  

    public car(double speed, Image[] images) {
        this.speed = speed;
        this.position = 350; // 小车从右向左
        this.images = images;
        this.Index = 0;

        // 初始化小车图片
        this.carImage = new ImageView(images[0]);
        this.carImage.setFitWidth(300);         // 调整图片宽度
        this.carImage.setPreserveRatio(true);   // 保持比例
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public ImageView getCarImage() {
        return carImage;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void move(double distance) {
        position -= distance;
        carImage.setTranslateX(position); // 更新小车位置
    }

    public void switchImage() {
        Index = (Index + 1) % images.length;
        carImage.setImage(images[Index]); // 切换图片
    }
}
