package com.zhl.register.command;

public class RemoteControlTest {
    public static void main(String[] args) {
        // 调用者
        RemoteControl remote = new RemoteControl();
        // 现在创建了一个电灯对象，此对象也就是请求的接收者
        Light light = new Light("Living room");
        // 在这里创建了两个命令，然后将接收者传给他
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff=new LightOffCommand(light);
        // 把命令传给调用者
        remote.setCommand(0,lightOn,lightOff);
        // 调用者调用命令
        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);
    }
}
