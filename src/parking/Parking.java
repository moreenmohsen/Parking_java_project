package parking;

import java.util.Scanner;

public class Parking {
    String BLACK="\u001B[30m";
    String RED="\u001B[31m";
    String GREEN="\u001B[32m";
    String YELLOW="\u001B[33m";
    String BLUE="\u001B[34m";
    String PURPLE="\u001B[35m";
    String CYAN="\u001B[36m";
    String WHITE="\u001B[37m";
    
    
    

    //vehicle subclasses objects
     static Bus b1=new Bus();
     static  Truck t1=new Truck();
     static Car c1=new Car();
     static Motorcycle m1=new Motorcycle();
    
    // Our 100 meter parking 
    static int[]arr =new int[101]; 
    
    // Coming vehicle Method
    public void adding(int id, String type){//non static method
        int h=0;
        if(type.equalsIgnoreCase(b1.getType())){
            h=b1.getLength();//h=b1.getheight  now height is public 
        }
        else if(type.equalsIgnoreCase(t1.getType())){
            h=t1.getLength();
        }
        else if(type.equalsIgnoreCase(c1.getType())){
            h=c1.getLength();
        }
        else if(type.equalsIgnoreCase(m1.getType())){
            h=m1.getLength();
        }
        
        int flag=0; // for test
        int min=0; //start idx
        int max=0; //end idx
        for(int i=1;i<=100;i++){
            if(arr[i]==0){ // if i found a free space
                for(int j=i;j<i+h;j++ ){
                    if(arr[j]!=0){
                        i=j;
                        break; // if i found a non free place break
                    }
                    else if(j==(i+h-1)){
                        arr[i]=id;//first idx
                        min=i;
                        max=j;
                        arr[j]=id;//last idx
                        flag=1;
                    }              
                }
            }
            if(flag==1){
               break;
            }    
        }
        for(int i=min;i<=max;i++){
            arr[i]=id;
        }
       
        for(int i=1;i<=100;i++){
            if(arr[i]==0)
                System.out.print(GREEN+"\u25A0");
            else
                System.out.print(RED+"\u25A0");
        }
        System.out.println(BLACK);
    } 
    
    // Release vehicle Method
    public void leaving(int id,String type ){
        int h=0;
        if(type.equalsIgnoreCase(b1.getType())){
            h=b1.getLength();//h=b1.getheight  now height is public 
        }
        else if(type.equalsIgnoreCase(t1.getType())){
            h=t1.getLength();
        }
        else if(type.equalsIgnoreCase(c1.getType())){
            h=c1.getLength();
        }
        else if(type.equalsIgnoreCase(m1.getType())){
            h=m1.getLength();
        }
        
        for(int i=1;i<=100;i++){
            if(arr[i]==id){
                for(int j=i; j<i+h; j++){
                    arr[j]=0;
                }
            }
        }
        for(int i=1;i<=100;i++){
            if(arr[i]==0)
                System.out.print(GREEN+"\u25A0");
            else
                System.out.print(RED+"\u25A0");
        }
        System.out.println(BLACK);
    }
    
    // Parking Status Method
    public void status(){
        int empty = 0, busy = 0;
        boolean flag = false;
        // print shape
        for(int i=1; i<=100; i++){
            if(arr[i]!=0){
                System.out.print(RED+"\u25A0");
            }
            else{
                System.out.print(GREEN+"\u25A0");
                //System.out.print(GREEN+"-");
               // System.out.print(GREEN+"-");
            }
        }
        System.out.println();
        
        //print from to
        for(int i=1; i<=100; i++){
            if(arr[i]==0){
                for(int j=i; j<=100; j++){
                    if(j==100){
                        //System.out.println((i)+" "+(j));
                        flag=true;
                        empty++;
                        break;
                    }
                    if(arr[j]!=0){
                        i=j-1;
                        //System.out.println((i)+" "+(j));
                        break;
                    }
                    empty++;
                }
                System.out.println(GREEN+"There are "+empty+" meters free  ");
                empty=0;
            }
            else{
                for(int j=i; j<=100; j++){
                    if(j==100){
                        flag=true;
                        busy++;
                        break;
                    }
                    if(arr[j]==0){
                        i=j-1;
                        //System.out.println((i)+" "+(j));
                        break;
                    }
                    busy++;
                }
                System.out.println(RED+"There are "+busy+" meters busy  ");
                busy=0;
            }
            if(flag==true)
                break;
        }
    }
    public static void main(String[] args) {
        int x=0;
        while(x!=4){
        Scanner input = new Scanner(System.in);
        //CHOSSE FROM THE 3 CASES
        Parking color=new Parking(); //obj for colors
        System.out.println("please enter "+color.YELLOW+1+color.BLACK+" if you want to "+color.YELLOW+"add "+color.BLACK+" a vehicle \n"
                        +"   or enter "+color.YELLOW+2+color.BLACK+" if you want to "+color.YELLOW+"release"+color.BLACK+" a vehicle \n"
                        +"   or enter "+color.YELLOW+3+color.BLACK+" if you want to "+color.YELLOW+"show parking status. \n "+color.BLACK
                       +"    or enter "+color.YELLOW+4+color.BLACK+" if you want to "+color.RED+"Exit. \n "+color.BLACK);
        //INPUT YOUR ANS
        int numberOfMenu=input.nextInt();
        x = numberOfMenu;
        switch (numberOfMenu) {
            case 1:
                {
                    System.out.println(color.GREEN+"Please enter the type of your vehicle:"+color.BLACK);
                    String type=input.next();
                    System.out.println(color.GREEN+"and please enter the id of your " + type + " :"+color.BLACK);
                    int id=input.nextInt();
                    //to call non static method from static method
                    Parking calling=new Parking();
                    calling.adding(id, type);
                    break;
                }
            case 2:
                {
                    System.out.println(color.RED+"Please enter the type of your vehicle:"+color.BLACK);
                    String type=input.next();
                    System.out.println(color.RED+"and please enter the id of your " + type + " :"+color.BLACK);
                    int id=input.nextInt();
                    System.out.println(color.RED+"Please enter the number of hours that your vechile spent:"+color.BLACK);
                    double numberofhour=input.nextDouble();
                     if(type.equalsIgnoreCase(b1.getType())){
                             System.out.println( "the cost wii be "+color.RED+b1.cost(numberofhour)+color.BLACK+" $");
                        }
                       else if(type.equalsIgnoreCase(t1.getType())){
                        System.out.println(  "the cost wii be "+color.RED+t1.cost(numberofhour)+color.BLACK+" $");
                      }
                     else if(type.equalsIgnoreCase(c1.getType())){
                      System.out.println(  "the cost wii be "+color.RED+c1.cost(numberofhour)+color.BLACK+" $");
                       }
                         else if(type.equalsIgnoreCase(m1.getType())){
                        System.out.println(  "the cost wii be "+color.RED+m1.cost(numberofhour)+color.BLACK+" $");
                       }
                    
                    
                    //to call non static method from static method
                    Parking calling = new Parking();
                    calling.leaving(id, type);
                    
                    break;
                }
            case 3:
                {
                    Parking calling = new Parking();
                    calling.status();
                    break;
                }
            case 4:
                {
                    System.out.println("GoodBYE"+color.BLACK);
                    System.exit(0);
                }
            default:
                System.out.println(color.RED+"Not a valid choice"+color.BLACK);
                break;
        }
        }
    }
}

class Vehicle{
    //atributes
   
    private int length;
    private double price;
    private int id;  
    //constructors
    public Vehicle() {}   
    public Vehicle(int length, int id) {
        this.length = length;
        this.id = id;
    }
    //methods
    //setters
    public void setLength(int length) {
        this.length = length;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(double price){
        this.price = price;
    }
    //getters
    public int getLength() {
        return length;
    }
    public int getId() {
        return id;
    } 
    public double getPrice() {
        return price;
    }
    public double cost(double numberofhours){
        return price*numberofhours; 
    }
}

//Truck class inherits from Vehicle class
class Truck extends Vehicle{
    //attributes
    private final String type;
    //constructors
    public Truck(){
        super.setLength(7);
        super.setPrice(15);
        this.type= "Truck";
    }
    //methods
    public String getType(){
        return type;
    }
}

//Bus class inherits from Vehicle class
class Bus extends Vehicle{
    //attributes
    private final String type;
    //constructors
    public Bus(){
        super.setLength(10);
        super.setPrice(15);
        this.type= "Bus";
    }
    //methods
    public String getType(){
        return type;
    }
}

//Car class inherits from Vehicle class
class Car extends Vehicle{
    //attributes
    private final String type;
    //constructors
    public Car(){
        super.setLength(5);
        super.setPrice(10);
        this.type= "Car";
    }
    //methods
    public String getType(){
        return type;
    }
}

//motorcycle class inherits from Vehicle class
class Motorcycle extends Vehicle{
    //attributes
    private final String type;
    //constructors
    public Motorcycle(){
        super.setLength(2);
        super.setPrice(5);
        this.type= "Motorcycle";
    }
    //methods
    public String getType(){
        return type;
    }
}