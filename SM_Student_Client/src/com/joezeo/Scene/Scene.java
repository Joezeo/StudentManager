package com.joezeo.Scene;

import com.joezeo.message.RequestMessage;

import java.util.Scanner;

public class Scene {
    /**
     * 欢迎界面：
     * 选择登陆或者注册
     * @return true：登陆界面    false：注册界面
     */
    public static boolean welcomeScene(){
        String flag = null;

        while(true){
            System.out.println("学生管理系统：学生端");
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

    /**
     * 登陆界面
     * @return 请求信息
     */
    public static RequestMessage login(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }

        RequestMessage msg = new RequestMessage();
        Scanner scanner = new Scanner(System.in);

        msg.setOprationName("login");
        msg.setCharacter("student");

        System.out.println("学生管理系统（学生端）登陆");
        System.out.println("学号：");
        msg.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("密码：");
        msg.setPwd(scanner.nextLine());

        return msg;
    }

    /**
     * 注册界面
     * @return 请求信息
     */
    public static RequestMessage register(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }

        RequestMessage msg = new RequestMessage();
        Scanner scanner = new Scanner(System.in);

        msg.setOprationName("register");
        msg.setCharacter("student");

        System.out.println("学生管理系统（学生端）注册");
        System.out.println("学号：");
        msg.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("密码：");
        msg.setPwd(scanner.nextLine());
        System.out.println("姓名：");
        msg.setName(scanner.nextLine());

        return msg;
    }

    /**
     * 查询学生成绩界面
     * @return 请求信息
     */
    public static RequestMessage inquiry(){
        RequestMessage msg = new RequestMessage();
        Scanner scanner = new Scanner(System.in);

        msg.setCharacter("student");
        msg.setOprationName("inquiry");

        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }
        System.out.println("学生管理系统：学生端");
        System.out.println("--查询学生成绩");
        System.out.println("请输入学生学号：");
        msg.setId(Integer.parseInt(scanner.nextLine()));

        return msg;
    }

    /**
     * 操作界面
     * @return 1：查询学生成绩
     */
    public static int oprationScene(){
        String flag = null;
        while(true) {
            System.out.println("学生管理系统：学生端 操作界面");
            System.out.println("1.查询学生成绩");
            Scanner scanner = new Scanner(System.in);
            flag =scanner.nextLine();
            if(flag.equals("1")){
                return Integer.parseInt(flag);
            } else {
                //清屏
                for(int i=0; i<10; i++){
                    System.out.println();
                }
            }
        }
    }

    /**
     * 登陆成功界面
     */
    public static void loginSuccessScene(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }
        System.out.println("学生管理系统：学生端");
        System.out.println();
        System.out.println("登陆成功~ 欢迎回来");
        System.out.println("请按任意键继续");
        new Scanner(System.in).next();
    }

    /**
     * 注册成功界面
     */
    public static void registerSuccessScene(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }
        System.out.println("学生管理系统：学生端");
        System.out.println();
        System.out.println("注册成功，已自动登陆~ 欢迎");
        System.out.println("请按任意键继续");
        new Scanner(System.in).next();
    }

    /**
     * 查询学生成绩结果界面
     * @param result 由ResponseMessage获取的查询结果
     */
    public static void inquiryResultScene(String result){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }
        System.out.println("学生管理系统：学生端");
        System.out.println();
        System.out.println(result);
        System.out.println();
        System.out.println("请按任意键继续");
        new Scanner(System.in).next();
    }

    /**
     * 登陆失败界面
     */
    public static void loginFailedScene(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }
        System.out.println("学生管理系统：学生端");
        System.out.println();
        System.out.println("登陆失败 @~@");
        System.out.println("请按任意键返回欢迎界面");
        new Scanner(System.in).next();
    }

    /**
     * 注册失败界面
     */
    public static void registerFailedScene(){
        //清屏
        for(int i=0; i<10; i++){
            System.out.println();
        }
        System.out.println("学生管理系统：学生端");
        System.out.println();
        System.out.println("注册失败 @~@");
        System.out.println("请按任意键返回欢迎界面");
        new Scanner(System.in).next();
    }
}
