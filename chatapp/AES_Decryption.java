/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.user.chatapp;



public class AES_Decryption {
 //Box which can we replace in it if we prod by 09
static String [][]prod09={{"00","09","12","1B","24","2D","36","3F","48","41","5A","53","6C","65","7E","77"},
                          {"90","99","82","8B","B4","BD","A6","AF","D8","D1","CA","C3","FC","F5","EE","E7"},
                          {"3B","32","29","20","1F","16","0D","04","73","7A","61","68","57","5E","45","4C"},
                          {"AB","A2","B9","B0","8F","86","9D","94","E3","EA","F1","F8","C7","CE","D5","DC"},
                          {"76","7F","64","6D","52","5B","40","49","3E","37","2C","25","1A","13","08","01"},
                          {"E6","EF","F4","FD","C2","CB","D0","D9","AE","A7","BC","B5","8A","83","98","91"},
                          {"4D","44","5F","56","69","60","7B","72","05","0C","17","1E","21","28","33","3A"},
                          {"DD","D4","CF","C6","F9","F0","EB","E2","95","9C","87","8E","B1","B8","A3","AA"},
                          {"EC","E5","FE","F7","C8","C1","DA","D3","A4","AD","B6","BF","80","89","92","9B"},
                          {"7C","75","6E","67","58","51","4A","43","34","3D","26","2F","10","19","02","0B"},
                          {"D7","DE","C5","CC","F3","FA","E1","E8","9F","96","8D","84","BB","B2","A9","A0"},
                          {"47","4E","55","5C","63","6A","71","78","0F","06","1D","14","2B","22","39","30"},
                          {"9A","93","88","81","BE","B7","AC","A5","D2","DB","C0","C9","F6","FF","E4","ED"},
                          {"0A","03","18","11","2E","27","3C","35","42","4B","50","59","66","6F","74","7D"},
                          {"A1","A8","B3","BA","85","8C","97","9E","E9","E0","FB","F2","CD","C4","DF","D6"},
                          {"31","38","23","2A","15","1C","07","0E","79","70","6B","62","5D","54","4F","46"}};

    //Box which can we replace in it if we prod by 0B
static String [][]prod0B={{"00","0B","16","1D","2C","27","3A","31","58","53","4E","45","74","7F","62","69"},
                          {"B0","BB","A6","AD","9C","97","8A","81","E8","E3","FE","F5","C4","CF","D2","D9"},
                          {"7B","70","6D","66","57","5C","41","4A","23","28","35","3E","0F","04","19","12"},
                          {"CB","C0","DD","D6","E7","EC","F1","FA","93","98","85","8E","BF","B4","A9","A2"},
                          {"F6","FD","E0","EB","DA","D1","CC","C7","AE","A5","B8","B3","82","89","94","9F"},
                          {"46","4D","50","5B","6A","61","7C","77","1E","15","08","03","32","39","24","2F"},
                          {"8D","86","9B","90","A1","AA","B7","BC","D5","DE","C3","C8","F9","F2","EF","E4"},
                          {"3D","36","2B","20","11","1A","07","0C","65","6E","73","78","49","42","5F","54"},
                          {"F7","FC","E1","EA","DB","D0","CD","C6","AF","A4","B9","B2","83","88","95","9E"},
                          {"47","4C","51","5A","6B","60","7D","76","1F","14","09","02","33","38","25","2E"},
                          {"8C","87","9A","91","A0","AB","B6","BD","D4","DF","C2","C9","F8","F3","EE","E5"},
                          {"3C","37","2A","21","10","1B","06","0D","64","6F","72","79","48","43","5E","55"},
                          {"01","0A","17","1C","2D","26","3B","30","59","52","4F","44","75","7E","63","68"},
                          {"B1","BA","A7","AC","9D","96","8B","80","E9","E2","FF","F4","C5","CE","D3","D8"},
                          {"7A","71","6C","67","56","5D","40","4B","22","29","34","3F","0E","05","18","13"},
                          {"CA","C1","DC","D7","E6","ED","F0","FB","92","99","84","8F","BE","B5","A8","A3"}};

    //Box which can we replace in it if we prod by 0D
static String [][]prod0D={{"00","0D","1A","17","34","39","2E","23","68","65","72","7F","5C","51","46","4B"},
                          {"D0","DD","CA","C7","E4","E9","FE","F3","B8","B5","A2","AF","8C","81","96","9B"},
                          {"BB","B6","A1","AC","8F","82","95","98","D3","DE","C9","C4","E7","EA","FD","F0"},
                          {"6B","66","71","7C","5F","52","45","48","03","0E","19","14","37","3A","2D","20"},
                          {"6D","60","77","7A","59","54","43","4E","05","08","1F","12","31","3C","2B","26"},
                          {"BD","B0","A7","AA","89","84","93","9E","D5","D8","CF","C2","E1","EC","FB","F6"},
                          {"D6","DB","CC","C1","E2","EF","F8","F5","BE","B3","A4","A9","8A","87","90","9D"},
                          {"06","0B","1C","11","32","3F","28","25","6E","63","74","79","5A","57","40","4D"},
                          {"DA","D7","C0","CD","EE","E3","F4","F9","B2","BF","A8","A5","86","8B","9C","91"},
                          {"0A","07","10","1D","3E","33","24","29","62","6F","78","75","56","5B","4C","41"},
                          {"61","6C","7B","76","55","58","4F","42","09","04","13","1E","3D","30","27","2A"},
                          {"B1","BC","AB","A6","85","88","9F","92","D9","D4","C3","CE","ED","E0","F7","FA"},
                          {"B7","BA","AD","A0","83","8E","99","94","DF","D2","C5","C8","EB","E6","F1","FC"},
                          {"67","6A","7D","70","53","5E","49","44","0F","02","15","18","3B","36","21","2C"},
                          {"0C","01","16","1B","38","35","22","2F","64","69","7E","73","50","5D","4A","47"},
                          {"DC","D1","C6","CB","E8","E5","F2","FF","B4","B9","AE","A3","80","8D","9A","97"}};

    //Box which can we replace in it if we prod by 0E
static String [][]prod0E={{"00","0E","1C","12","38","36","24","2A","70","7E","6C","62","48","46","54","5A"},
                          {"E0","EE","FC","F2","D8","D6","C4","CA","90","9E","8C","82","A8","A6","B4","BA"},
                          {"DB","D5","C7","C9","E3","ED","FF","F1","AB","A5","B7","B9","93","9D","8F","81"},
                          {"3B","35","27","29","03","0D","1F","11","4B","45","57","59","73","7D","6F","61"},
                          {"AD","A3","B1","BF","95","9B","89","87","DD","D3","C1","CF","E5","EB","F9","F7"},
                          {"4D","43","51","5F","75","7B","69","67","3D","33","21","2F","05","0B","19","17"},
                          {"76","78","6A","64","4E","40","52","5C","06","08","1A","14","3E","30","22","2C"},
                          {"96","98","8A","84","AE","A0","B2","BC","E6","E8","FA","F4","DE","D0","C2","CC"},
                          {"41","4F","5D","53","79","77","65","6B","31","3F","2D","23","09","07","15","1B"},
                          {"A1","AF","BD","B3","99","97","85","8B","D1","DF","CD","C3","E9","E7","F5","FB"},
                          {"9A","94","86","88","A2","AC","BE","B0","EA","E4","F6","F8","D2","DC","CE","C0"},
                          {"7A","74","66","68","42","4C","5E","50","0A","04","16","18","32","3C","2E","20"},
                          {"EC","E2","F0","FE","D4","DA","C8","C6","9C","92","80","8E","A4","AA","B8","B6"},
                          {"0C","02","10","1E","34","3A","28","26","7C","72","60","6E","44","4A","58","56"},
                          {"37","39","2B","25","0F","01","13","1D","47","49","5B","55","7F","71","63","6D"},
                          {"D7","D9","CB","C5","EF","E1","F3","FD","A7","A9","BB","B5","9F","91","83","8D"}};
    //this array use in inverse mix columns phase
   static String [][] mixColDec={{"0E","0B","0D","09"},
                                 {"09","0E","0B","0D"},
                                 {"0D","09","0E","0B"},
                                 {"0B","0D","09","0E"}};


    //convert array 4*4 to string
    static String stateToString (String [][]w){
    String s="";
    for(int i=0;i<4;i++)
        for(int j=0;j<4;j++)
         s+=w[j][i];
    return s;
    }

    //convert ciepher text to Array 4*4
   static void toStateAr(String s1,String [][]state){
       String s=s1;
       int index=0;
       for(int i=0;i<4;i++)
           for(int j=0;j<4;j++){
                char ch1=s.charAt(index);
                char ch2=s.charAt(index+1);
                state[j][i]=ch1+""+ch2;
                index+=2;
           }
      }

    //this function shift the word iter time to right
       static  void  shiftTORight(String []word,int iter){
             for(int k=0;k<iter;k++){
                 String temp=word[3];
                 for(int i=2;i>=0;i--)
                     word[i+1]=word[i];
                 word[0]=temp;
             }
 }

    //compute inverse shift rows to arrary w
     static void shiftRowsInv(String [][]w){
             String []ar=new String[4];
             for(int i=1;i<4;i++){
                 for(int j=0;j<4;j++){
                     ar[j]=w[i][j];}
                 shiftTORight(ar, i);
                 for(int j=0;j<4;j++)
                 w[i][j]=ar[j];
             }
     }
 
 
//this function compute inverse sub Byte
  static void subByteInv(String [][]w){
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++) {
                int i0=Mathimatical.charToint(w[i][j].charAt(0));//get first digit form the hexadicimal number
                int i1=Mathimatical.charToint(w[i][j].charAt(1));//get second digit form the hexadicimal number
                /*get hexadicimal number from S_Box_Dec
                 which its row =i0 and column=i1
                  */
                w[i][j]=AES_sub_Box_Decryption.s_Box_Decryption[i0][i1];

             }
         }
  


    //compute inverse product between hexadicimal number and 09,0B,0D or 0E
  static String prodInv(String a,String b){
      char x=a.charAt(1);
      switch(x){     
          case '9':return replaceValue(b,prod09);
          case 'B':return replaceValue(b,prod0B);
          case 'D':return replaceValue(b,prod0D);
          case 'E':return replaceValue(b,prod0E);
      }
  
  return "ERROR REPLACE";
  }

    //replace w with its equlalivat in table
    static String replaceValue(String w,String [][]table){
        int i0=Mathimatical.charToint(w.charAt(0));
        int i1=Mathimatical.charToint(w.charAt(1));
        return table[i0][i1];

    }

   //compute mix columns inverse
    static String[][] MixColoumnsInv(String [][]b,String [][]a){   
       String [][]res=new String [4][4]; 
       String temp;

        for (int i = 0; i < 4; i++)
            {  
            
                for (int j = 0; j < 4; j++)
                {    
                     temp =prodInv(b[i][0], a[0][j]) ;//cal prodInv function
                    
                   for (int k = 1; k < 4; k++)
                    {  
                        String t=Mathimatical.hexToBinary(prodInv(b[i][k],a[k][j]));
                           
                           temp=Mathimatical.XOR(Mathimatical.hexToBinary(temp),t );
                           temp=Mathimatical.binaryTohex(temp);
                            
                        
                    }
                   
                    res[i][j] = temp;
                        }
            }
        return res;
   
   
    }

    /*this function do AES Decryption to message with 16 hexadicimal number
    and keyE is Key of Decryption
     */
  static String do_AES_Decryption(String message,String keyE){
      String key=keyE;
      String [][]states=new String [4][4];
      String [][]roundKey=new String[4][44];
      keyExpansation.StringToKey1(key,roundKey);//convert key to array 4*4

      if(roundKey[0][0]!=null){
          for(int i=4;i<44;i++)
               keyExpansation.computWord(roundKey,i); //compute partial keys by computing words 4-> 43
          toStateAr(message,states);
          //add round key [10] to state
          states=JavaApplication18.addRoundKey(states, roundKey, 10);
         for(int i=9;i>=1;i--){//repeat 9 times
             shiftRowsInv(states);
            subByteInv(states);
            states=JavaApplication18.addRoundKey(states, roundKey, i);
           states=MixColoumnsInv(mixColDec,states);
         }
            shiftRowsInv(states);
            subByteInv(states);
            states=JavaApplication18.addRoundKey(states, roundKey, 0);//add round key[0]
       }
       String [][]res=new String[4][4];
       for(int i=0;i<4;i++)
           for(int j=0;j<4;j++)
               states[i][j]=(char)Mathimatical.hexTOAscii(states[i][j])+"";//convert final block to Ascii
       return stateToString(states);}

        
  
  
  //do AES Decryption for all plain text
  static String AES_DecryptionS(String message,String key){
  String plain="";
  message+='$';
  String temp=message.charAt(0)+"";
  for(int i=1;i<message.length();i++)
      if(temp.length()%32==0){
      plain+=do_AES_Decryption(temp, key);
      temp="";
      temp+=message.charAt(i);
      
      }
 else
          temp+=message.charAt(i);
      
      return plain;
  }
  }

    
    

