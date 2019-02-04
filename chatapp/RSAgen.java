package com.example.user.chatapp;

/**
 * Created by user on 12/05/2017.
 */
public class RSAgen {

    public long  n;
    public long e;

//the constructor of class RSA

    public  RSAgen(){
        this.e=ChatFragment.ea;
        this.n=ChatFragment.na;

    }

    /*method to compute a^k mod n with asimple repitition
      way and use mod every repitition for reduce memory needed for it more and more

     */

    public static long exp_mod(long a,long k,long n)
    {
        long d=1;
        long aa=a;

        while(k>0)
        {
            if(k%2==1)
            {
                d=(d*aa)%n;
            }
            k=(k-(k%2))/2;
            aa=(aa*aa) %n;

        }

        return d;
    }





     /*compute d using e and n*/





    /*method for compute prime factors to a long number and put it in an array*/



/*take a string we wante to encrypt it and e&&n as an input */
   /*output an array with along numbers which is the encrypted characters in RSA algorithm */

    public static String rsaEncrypt(String s,long e,long n){
        int i=0;
        String [] res=new String[s.length()];
        /*the index*/
        String result="";
        int m;
        /*avariable to store the order of character in ascii*/
        char a;
        /*avariable to store  character with index (i) in the string*/

        while(i<s.length())
        {
            a = s.charAt(i);

            m=(int)a;

             /*compute the encrypted character with term (m^e mod n)*/
            res[i]=java.lang.Long.toString(exp_mod(m, e, n))+' ';
          result+=res[i];
            i++;
        }

        return  result;
    }
}