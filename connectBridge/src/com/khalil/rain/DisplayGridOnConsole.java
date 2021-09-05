package com.khalil.rain;

public class DisplayGridOnConsole {

    static void Display(char[] arr,int width,int hight){
        for(int i=0;i<hight;i++){
            for(int j=0;j<width;j++){
                switch (arr[j+i*width]){
                    case 'B':System.out.print("\033[1;91m"+" B");break;
                    case 'R':System.out.print("\033[1;90m"+" R");break;
                    case 'G':System.out.print("\033[1;92m"+" G");break;
                    case 'E':System.out.print("\033[1;94m"+" E");break;
                }
            }
            System.out.println();

        }
        System.out.println();
    }
}
