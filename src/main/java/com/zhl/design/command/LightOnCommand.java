package com.zhl.design.command;

public class LightOnCommand implements Command {
    Light light;

    /**
     * 构造器被传入了某个电灯（比方说：客厅的电灯），以便让这个命令控制
     * 然后记录在实例变量中。一旦调用execute()，就由这个电灯对象成为接收者，
     * 负责接收请求。
     * @param light
     */
    public LightOnCommand(Light light){
        this.light=light;
    }

    public void execute() {
        light.on();
    }
}
