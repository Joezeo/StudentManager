package com.joezeo.scene;

import com.joezeo.core.Request;
import com.joezeo.message.RequestMessage;

import java.util.Scanner;

/**
 * 给客户端提供控制台界面
 */
public class Scene {
    /**
     * 欢迎界面：
     * 选择登陆或者注册
     * @return true：登陆界面    false：注册界面
     */
    public static boolean welcomeScene(){
        String flag = null;

        while(true){
            System.out.println("学生管理系统：教师端");
            System.out.println("\t\t\tver:1.0");
            System.out.println("1.登陆");
            System.out.println("2.注册");
            System.out.println("请输入你的操作：");
            Scanner scanner = new Scanner(System.in);
            flag = scanner.nextLine();

            //清屏
            for(int i=0; i<10; i++){
                System.out.println();
            }

            if(flag.equals("1")){
                return true;
            } else if (flag.equals("2")){
                return false;
            } else {
                System.out.println("error：输入的信息错误，请重新输入~");
            }
        }
    }

    public static RequestMessage login(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }

        RequestMessage msg = new RequestMessage();
        Scanner scanner = new Scanner(System.in);

        msg.setOprationName("login");
        msg.setCharacter("teacher");

        System.out.println("学生管理系统（教师端）登陆");
        System.out.println("教师号：");
        msg.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("密码：");
        msg.setPwd(scanner.nextLine());

        return msg;
    }

    public static RequestMessage register(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }

        RequestMessage msg = new RequestMessage();
        Scanner scanner = new Scanner(System.in);

        msg.setOprationName("register");
        msg.setCharacter("teacher");

        System.out.println("学生管理系统（教师端）注册");
        System.out.println("教师号：");
        msg.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("密码：");
        msg.setPwd(scanner.nextLine());

        return msg;
    }
}
