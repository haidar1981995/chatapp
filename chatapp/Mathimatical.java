/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.user.chatapp;

public class Mathimatical {
    
    
    
    //convert char to hexadicimal number
    static int toHex(char c){
        switch(c){
               case '0':return 0;
               case '1':return 1;  
               case '2':return 2;
               case '3':return 3;       
               case '4':return 4;
               case '5':return 5;
               case '6':return 6;
               case '7':return 7;
               case '8':return 8;
               case '9':return 9;
               case 'A':return 10;
               case 'B':return 11;
               case 'C':return 12;
               case 'D':return 13;
               case 'E':return 14;
               case 'F':return 15;
        }
        
   return 1;}
    


    //convert string to hexadicimal number
    static int st2Hex(String str){
    int a=toHex(str.charAt(0))*16+toHex(str.charAt(1));
         
   return a;
    
    }

    //convert hexadicimal to binary
     static  String hexToBinary(String hex){
       String bin="";
       for(int i=0;i<hex.length();i++){
           switch(hex.charAt(i)){
               case '0':bin+="0000";break;
               case '1':bin+="0001";break;    
               case '2':bin+="0010";break;
               case '3':bin+="0011";break;           
               case '4':bin+="0100";break; 
               case '5':bin+="0101";break;
               case '6':bin+="0110";break;
               case '7':bin+="0111";break;
               case '8':bin+="1000";break;
               case '9':bin+="1001";break;
               case 'A':bin+="1010";break;
               case 'B':bin+="1011";break;
               case 'C':bin+="1100";break;
               case 'D':bin+="1101";break;
               case 'E':bin+="1110";break;
               case 'F':bin+="1111";break;    
           }

       }
       return bin;
     }
     
     
     //convert binary to hexadicimal
    static String binaryTohex(String s){
           s+='$';
          String temp="";
          temp+=s.charAt(0);
          String fin="";
          for(int i=1;i<s.length();i++){
              if(temp.length()%4==0){
                    switch(temp){
                          case "0000":fin+="0";break;
                          case "0001":fin+="1";break;
                          case "0010":fin+="2";break;
                          case "0011":fin+="3";break;
                          case "0100":fin+="4";break;
                          case "0101":fin+="5";break;
                          case "0110":fin+="6";break;
                          case "0111":fin+="7";break;
                          case "1000":fin+="8";break;
                          case "1001":fin+="9";break;
                          case "1010":fin+="A";break;
                          case "1011":fin+="B";break;
                          case "1100":fin+="C";break;
                          case "1101":fin+="D";break;
                          case "1110":fin+="E";break;
                          case "1111":fin+="F";break;
                    }
                    temp="";
                    temp+=s.charAt(i);
              }
            else
                temp+=s.charAt(i);
          }
           return fin;
  
    }
      
      //apply xor operation between two string
      static String XOR(String a,String b){
           String result="";
          for(int i=0;i<a.length();i++) {
              if (a.charAt(i) == b.charAt(i))
                  result += '0';
              else
                  result += '1';
          }
          return result;
      }
      
      
      
      //apply xor between two words
       static void XORBetween2Word(String []w1,String []w2,String []res){
                  for(int i=0;i<4;i++){
                      res[i]=XOR(hexToBinary(w1[i]),hexToBinary(w2[i]));
                  }
                  for(int i=0;i<4;i++){
                      res[i]=binaryTohex(res[i]);
                  }
       
       }
       
       
       //apply xor between two array
        static String[][] XORBetween2Array(String [][]w1,String [][]w2,int start){
                   String [][]res=new String[4][4];
                  for(int i=0;i<4;i++) {
                      for (int j = 0, k = 4 * start; j < 4 && k < 4 * start + 4; j++, k++) {
                          res[i][j] = XOR(hexToBinary(w1[i][j]), hexToBinary(w2[i][k]));
                      }
                  }
                 for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++) {
                        res[i][j] = binaryTohex(res[i][j]);
                    }
                 }
                 return res;
        }
        
    static int charToint(char a){
         switch(a){
             case'0':return 0;
             case'1':return 1;
             case'2':return 2;
             case'3':return 3;
             case'4':return 4;
             case'5':return 5;
             case'6':return 6;
             case'7':return 7;
             case'8':return 8;
             case'9':return 9 ;
             case'A':return 10;
             case'B':return 11;
             case'C':return 12;
             case'D':return 13;
             case'E':return 14;
             case'F':return 15;
        }
      return 0;
    }
         
       
               //convert number to char
         static char intToChar(int a){
             switch(a){
                 case 0:return '0';
                 case 1:return '1';
                 case 2:return '2';
                 case 3:return '3';
                 case 4:return '4';
                 case 5:return '5';
                 case 6:return '6';
                 case 7:return '7';
                 case 8:return '8';
                 case 9:return '9' ;
                 case 10:return 'A';
                 case 11:return 'B';
                 case 12:return 'C';
                 case 13:return 'D';
                 case 14:return 'E';
                 case 15:return 'F';
             }
            return 'K';
         }
         

         //convert number to hexadicimal string
         static String AsciiToHex(int x){
             int a=x;
             String res="";
             while(a!=0){
                 res=intToChar(a%16)+res;
                 a=a/16;
             }
         
             if(res.length()==0) res+="00";
                 else
                   if(res.length()==1) res='0'+res;
                           return res;
         
         }
         

          //convert hexadicimal string to ASCII
         static int hexTOAscii(String pl){
                 int res=0;
                 String st=pl;
                 res+=charToint(st.charAt(0))*16+charToint(st.charAt(1));
         
                  return res;
         
         }

    
}