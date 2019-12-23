package com.wubin.testdemo;

import com.wubin.testdemo.viewModel.UserPo;

/**
 * @author luyanjun
 * @description
 */
public class JavaMain {


    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();
        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("------------------");
                break;
            }
        }
    }

    static class ThreadDemo implements Runnable {

        private boolean flag = false;

        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //在子线程中修改了flag的值
            flag = true;
            System.out.println("isFlag -> " + flag);
        }

        public boolean isFlag() {
            //这行代码为啥对程序运行有影响
//            System.out.println("nothing");
            synchronized (UserPo.class){
            }
            return flag;
        }
    }

}
