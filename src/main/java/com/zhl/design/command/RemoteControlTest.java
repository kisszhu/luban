package com.zhl.design.command;

public class RemoteControlTest {

    public static void main(String[] args) {
        // 控制者
        SimpleRemoteControl remote = new SimpleRemoteControl();
        // 命令的接收者
        Light light = new Light();
        // 调用命令
        LightOnCommand lightOn = new LightOnCommand(light);
        // 设置命令
        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }

}
