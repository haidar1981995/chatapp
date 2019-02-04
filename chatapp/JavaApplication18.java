
package com.example.user.chatapp;


import static java.awt.font.TextAttribute.FONT;
import java.sql.Struct;
import java.util.*;




public class JavaApplication18 {
    

 
  static String [][]mixCol={{"02","03","01","01"},
                             {"01","02","03","01"},
                             {"01","01","02","03"},
                             {"03","01","01","02"}};

     
  
  //this function converts input String with 16 byte
  // to array 4*4
   static void toState(String s1,String [][]state){
       char [][]c=new char[4][4];
       String s=s1;
       int index=0;
   for(int i=0;i<4;i++)
       for(int j=0;j<4;j++){
           c[j][i]=s.charAt(index);
           index++;  
       }
   //convert every byte in the the array to its coordinate hexadicimal
   for(int i=0;i<4;i++)
       for(int j=0;j<4;j++)
       state[i][j]=Mathimatical.AsciiToHex((int)c[i][j]);
      }
   
 
   
   //convert array 4*4 to String with 16 byte
   static String stateToArray(String [][]state){
   String res="";
   for(int i=0;i<4;i++)
       for(int j=0;j<4;j++)
           res+=state[j][i];
   
   return res;
   }
   
   static ArrayList stateToArray1(String [][]state){
       ArrayList li=new ArrayList();
   for(int i=0;i<4;i++)
       for(int j=0;j<4;j++)
          li.add(Mathimatical.st2Hex(state[j][i]));
           
   
   return li;
   }
   
   /* this function compute product any hexadicimla number with 01 ,01 or 03
   * where we can use this function in mixColumns phase in AES
   * */
   static String prod(String b ,String a){    
         switch(b){
           case "01": return a;
           case "02":  a=Mathimatical.hexToBinary(a);//convert hexadicimal number to binary
                       if(a.charAt(0)=='0'){  //check if the 8th bit =0
                           a=a.substring(1);
                            a=a.concat("0");
                           a=Mathimatical.binaryTohex(a);}
                          else {
                            a=a.substring(1);
                            a=a.concat("0");
                            a=Mathimatical.XOR(a,Mathimatical.hexToBinary("1B")); //compute XOR
                                                                             // between a and 1B
                            a=Mathimatical.binaryTohex(a);}
                            return a;
           case "03":   String d=Mathimatical.hexToBinary(prod("02",a));
                        String e=Mathimatical.hexToBinary(a);
                        return Mathimatical.binaryTohex(Mathimatical.XOR(d, e));}
   return "a";}
   
   
   //this function do mixcolumns step
   static String[][] MixColoumns(String [][]b,String [][]a){     
       String [][]res=new String [4][4]; 
       String temp;
     for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                     temp =prod(b[i][0], a[0][j]) ; //call the function prod to
                                                  // compute product b[i][0] with 01,02 or 03
                   for (int k = 1; k < 4; k++)
                    {  
                        String t=Mathimatical.hexToBinary(prod(b[i][k],a[k][j]));
                            
                           temp=Mathimatical.XOR(Mathimatical.hexToBinary(temp),t );
                           temp=Mathimatical.binaryTohex(temp);
                        
                    }
                   
                    res[i][j] = temp;
                        }}
   return res;}
    
    //this function do the step add round key to state
   static String[][] addRoundKey(String [][] stat,String [][]k,int start){
       String [][]temp=new String [4][4];
       temp=Mathimatical.XORBetween2Array(stat,k,start);//do XOR between state and the partial key
                                                        // his number =start
  return temp;
   
   }
   
//this function do shiftRows step to the array w due to index of row
 static void shiftRows(String [][]w){
       String []ar=new String[4];
       for(int i=1;i<4;i++){
           for(int j=0;j<4;j++){
               ar[j]=w[i][j];}
               keyExpansation .shiftTOLeft(ar, i); //shift the iTh row in ar i time to the left
           for(int j=0;j<4;j++)
               w[i][j]=ar[j];
       }

 }
 
//this function do subByte phase to array w
 static void subByte(String [][]w){
 for(int i=0;i<4;i++)
 for(int j=0;j<4;j++)
 {
 int i0=Mathimatical.charToint(w[i][j].charAt(0));//obtain the first digit from
                                                  // the hexadicimal number w[i][j]
 int i1=Mathimatical.charToint(w[i][j].charAt(1));//obtain the second digit from
                                                  // the hexadicimal number w[i][j]
 w[i][j]=AES_Sub_Box.s_Box[i0][i1];//find the element in S_Box with row i0 and columns i1
 } 
 }
 
//this function do AES to the message with 16 byte and the encryption key
 static String do_AES(String message,String key){
       String [][]roundKey=new String[4][44];
       keyExpansation.StringToKey1(key,roundKey);

for(int i=4;i<44;i++)
           keyExpansation.computWord(roundKey,i); 
       if(roundKey[0][0]!=null){
        String [][]states=new String [4][4];
        toState(message, states);//call function tostate
         states=addRoundKey(states, roundKey,0);//call function addroundKey

         for(int i=1;i<10;i++){
             subByte(states);    //subByte step
             shiftRows(states);   //shift step
             states=MixColoumns(mixCol,states);  // Mixcolumns step
             states=addRoundKey(states, roundKey, i);  // addRoundStep
           }
         subByte(states);
         shiftRows(states);
         states=addRoundKey(states, roundKey, 10);
         

 return  stateToArray(states);
      }
       else
           return "error";
 }
 
 
 

 // this function do AES to all Text
static String AES_Encryption(String message,String key){

    //fill the last block with ' ' to 16 byte if its 0<length<16
         int insertedBits=0;
      if(message.length()%16!=0){
          int x=message.length()/16;
          for(int i=message.length();i<(x+1)*16;i++){
              message+=' ';
              insertedBits++;}}

    int block=1;
      String ciepher="";
      message+="$";
      String temp=message.charAt(0)+"";
    for(int i=1;i<message.length();i++){
        if(temp.length()%16==0){
        ciepher+=do_AES(temp, key);
        temp="";
        temp+=message.charAt(i);
        block++;
        }
    else
            temp+=message.charAt(i);
    }
    return ciepher;
}

}