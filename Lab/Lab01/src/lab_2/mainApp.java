package lab_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mainApp extends Application {
    private static final double TRACK_WIDTH = 600; // 赛道宽度
    private static final double TRACK_HEIGHT = 250; // 窗口高度
    private boolean isDrifting = false; // 是否处于漂移状态

    @Override
    public void start(Stage primaryStage) {
        // 加载小车图片
        Image[] carImage = new Image[3];
        carImage[0] = new Image("file:src/lab_2/pic/11.png");
        carImage[1] = new Image("file:src/lab_2/pic/22.png");
        carImage[2] = new Image("file:src/lab_2/pic/33.png");

        // 创建小车
        car car = new car(50, carImage);
        // 启动按钮
        Button startButton = new Button("启动！！！");
        // 漂移按钮
        Button driftButton = new Button("漂移");
        // 创建速度控制滑块
        Label speedLabel = new Label("Speed: ");
        Slider speedSlider = new Slider(0, 100, 49); 
        speedSlider.setShowTickLabels(true);                //可以显示数值
        speedSlider.valueProperty().addListener((obs, Val1, Val2) -> {
            car.setSpeed(Val1.doubleValue());                 // 设置小车速度
        });

        HBox controlBox = new HBox(50, speedLabel, speedSlider); // 为速度设置水平box
        controlBox.setSpacing(10);

        // 小车移动动画（用的Timeline）
        Timeline moveTimeline = new Timeline(
            new KeyFrame(Duration.millis(50), e -> {
                car.move(car.getSpeed() * 0.05); // 根据速度移动
                if (car.getPosition() >= TRACK_WIDTH|| car.getPosition() <= -TRACK_WIDTH) { // 如果小车到达赛道末尾
                    car.setPosition(0); // 重置到起点
                }
            })
        );
        moveTimeline.setCycleCount(Timeline.INDEFINITE); //一直飙

        // 创建图片切换动画
        Timeline imageSwitchTimeline = new Timeline(
            new KeyFrame(Duration.seconds(0.5), e -> car.switchImage()) // 每 0.5 秒切换一次图片
        );
        imageSwitchTimeline.setCycleCount(Timeline.INDEFINITE);

        // 创建漂移动画
        Timeline driftTimeline = new Timeline(
            new KeyFrame(Duration.millis(100), e -> {
                car.getCarImage().setTranslateY(car.getCarImage().getTranslateY() == 60 ? -60 : 60);
            })
        );
        driftTimeline.setCycleCount(Timeline.INDEFINITE);

         
        // 漂移按钮事件（询问了gpt）
        driftButton.setOnAction(e -> {
            if (isDrifting) {
                // 如果正在漂移，则停止漂移并重置位置
                driftTimeline.stop();          
                car.getCarImage().setTranslateY(0); // 重置到初始位置
                isDrifting = false;
                driftButton.setText("漂移");
            } else {
                // 如果未漂移，则开始漂移
                moveTimeline.play();
                driftTimeline.play();
                imageSwitchTimeline.play();
                isDrifting = true;
                driftButton.setText("停止漂移");
            }
        });

        // 启动按钮事件
        startButton.setOnAction(event -> {
            moveTimeline.play();         // 启动小车移动
            imageSwitchTimeline.play();  // 启动图片切换
        });

        // 创建赛道布局
        StackPane track = new StackPane(car.getCarImage());
        track.setPrefHeight(400); // 设置赛道高度

        // 使用 BorderPane 布局
        BorderPane root = new BorderPane();
        root.setTop(controlBox);  // 将速度控制放在顶部
        HBox bottomBox = new HBox(20, startButton, driftButton); // 底部包含启动按钮和漂移按钮
        root.setBottom(bottomBox);
        root.setCenter(track);    // 将赛道放在中央

        // 设置场景
        Scene scene = new Scene(root, TRACK_WIDTH, TRACK_HEIGHT);
        primaryStage.setTitle("潘廷峰飙车现场");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
