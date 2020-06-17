import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
public class llm{


    public static void main (String [] args) throws IOException   {



        ArrayList<Record> r = new ArrayList<Record>();
        int totalOrder = 0;
//read from the text file and convert to arraylist object
        try {
            File myObj = new File("orderRecord.txt");
            Scanner myReader = new Scanner(myObj);
            int count = 0;                    
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] a = data.split(",");      //FIRST line will be the number of the orders
                if (count == 0) {
                    count++;
                    totalOrder = Integer.parseInt(a[1]);

                } else {
                    Record temp=new Record(a);
                    r.add(temp);
                }

            }
            myReader.close();


        } catch (Exception e) {

            System.out.print("There is error on reading the orderRecord.txt");
            e.printStackTrace();

        }


   //based on the cli input to make condition, such as take order, list order or create order
        if(args.length==0){
            System.out.println("you dont have any input ");
        }
    if(args.length>0) {
        switch (args[0]) {
            case "list_orders":         //call listOrder helper function to print the order
                  listOrder(r);
                break;
            case "take_order":
                if (args.length < 2) {                        //user should input id to process the order
                    System.out.println("You need to input id");
                    break;
                } else {                  //isOrderExist function will check if the order id exist or not, and check isdne or not
                         if(Integer.parseInt(args[1])<1){
			 System.out.println("The id should be greater than 1");
			 	break;
			 }
                         isOrderExist(r, Integer.parseInt(args[1]));
                }
                break;
            case "create_order":
                if (args.length < 3) {
                    System.out.print("you want to create order but miss some input e.g(to,from)");
                    break;
                } else {

			//create new order

                    String[] k = ((++totalOrder) + "," + args[1] + "," + args[2] + ",false").split(",");
                    System.out.println(totalOrder);
                    Record temp = new Record(k);
                    r.add(temp);
                }
                break;
            default:
		System.out.print("it seems your choise does not exist");
                break;
        }
			//update the order by overwriting the information from arraylist to the txt file
        File fout = new File("orderRecord.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write("totalOrder,"+r.size());
        bw.newLine();
        for (Record x: r   ) {
            bw.write(x.strRecord());
            bw.newLine();
        }

        bw.close();
    }

    }
    //helper function
    //if the order list is empty or all of the order is taken, it will show a empty message
    public static void listOrder(ArrayList<Record> r){
       	boolean isEmpty=true;
	   for(Record i :r){       
            if(i.returnIsDone()==false){
                i.print();
		isEmpty=false;
            }
        }
	if(isEmpty==true){
	System.out.println("it seems the order list is empty or all of the orders have been taken");
	}

    }
    //helper function to check order exist or not, and check if the order  has been taken or not
    public static void isOrderExist(ArrayList<Record> r, int id){
        if(r.size()<=(id-1)) {
            System.out.println("order does not exist");
        } else if (r.get(id-1).returnIsDone()==true&&r.get(id-1).getId()==id){
            System.out.println("order already taken");
        }else if (r.get(id-1).getId()==id&&r.get(id-1).returnIsDone()==false){
            r.get(id-1).takeOrder();
        }else if(id<1){
		System.out.println("The order id should be greater than 1");
	}


    }
}



//class of record 
class Record {
    private   int id;
    private   String location;
    private  String dst;
    private  boolean isDone;

    public Record() {
        // System.out.print("debug");
        //default constructor i used for debugging
    }




    public Record(String[] var1) {
//        System.out.print("debug");
        this.id = Integer.parseInt(var1[0]);
        this.location = var1[1];
        this.dst = var1[2];
        if (var1[3].equals("true")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }

    }
		// if calls this function,that mean the order will be taken
    public void takeOrder() {
        this.isDone = true;
    }
		//print the order information
    public void print() {
        System.out.println(this.id + "," + this.location + "," + this.dst);
    }
		//convert the record to string and write to txt
    public String strRecord() {
        return this.id + "," + this.location + "," + this.dst + "," + this.isDone;
    } //check the order is taken or not
    public boolean returnIsDone(){
        return isDone;
    }  //return the id of order
    public int getId(){
        return id;
    }
}
