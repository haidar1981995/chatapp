/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.user.chatapp;





public class keyExpansation {


//this function convert Encrption hey with length 16 byte to array 4*4
        static  void StringToKey1(String s,String [][]key){
   char [][]c=new char[4][4];

       if(s.length()==32){
           int index=0;
           for(int i=0;i<4;i++)
           for(int j=0;j<4;j++){
                key[j][i]=s.charAt(index)+""+s.charAt(index+1);
                index+=2;
                   }
   
       
       }
   else
           if(s.length()>32){
           String str="";
           for(int i=0;i<32;i++)
               str+=s.charAt(i);
               StringToKey1(str, key);
           }
        }
      
      
    //comput constant of  replication Rcon(i/4)
    static String [] Rcn(int i){
     String []res=new String [4];
     
            switch(i){
                 case 1: res[0]="01" ;break;
                 case 2: res[0]="02" ;break;
                 case 3: res[0]="04" ;break;
                 case 4: res[0]="08" ;break;
                 case 5: res[0]="10" ;break;
                 case 6: res[0]="20" ;break;
                 case 7: res[0]="40" ;break;
                 case 8: res[0]="80" ;break;
                 case 9: res[0]="1B" ;break;
                 case 10: res[0]="36" ;break;
                  }
        //fill the next three cell with 00
      for(int j=1;j<4;j++)
          res[j]="00";
 return res;
 }
 

       static  void  shiftTOLeft(String []word,int iter){
     for(int k=0;k<iter;k++){
   String temp=word[0];
   for(int i=1;i<word.length;i++)
       word[i-1]=word[i];
  
 word[word.length-1]=temp;
 }
 }
       //this function compute the ith word
       static void computWord(String [][]w,int i){
             String []arr=new String[4];
             String []wi1=new String [4];
             String []wi4=new String[4];
            for(int j=0;j<4;j++){
               wi1[j]=w[j][i-1];
               wi4[j]=w[j][i-4];
             }
    
            if(i%4==0){ //compute subword(Rotword(wi-1))XOR Rcon(i/4)
            String []t=new String[4];
            shiftTOLeft(wi1, 1);//rotWord
            wi1=subword(wi1);
            Mathimatical.XORBetween2Word(wi1, Rcn(i/4), t);
            Mathimatical.XORBetween2Word(t, wi4, arr);
            for(int j=0;j<4;j++)
               w[j][i]=arr[j];
            }
          else
               {  //compute Wi-1 XOR Wi-4
                Mathimatical.XORBetween2Word(wi1, wi4, arr);
                for(int j=0;j<4;j++)
                   w[j][i]=arr[j];
 
               }
       }

       static String[] subword(String[] w){
             String []res=new String [4];
             for(int i=0;i<4;i++){
                 int i0=Mathimatical.charToint(w[i].charAt(0));
                 int i1=Mathimatical.charToint(w[i].charAt(1));
                 res[i]=AES_Sub_Box.s_Box[i0][i1];
             }
 
          return res;
        }
}
