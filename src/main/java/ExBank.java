import java.util.Scanner;

class ExBank  {

    public static void main(String arg[])    {
        Scanner KB=new Scanner(System.in);

        //create new acc
        System.out.print("How many clients will register ? ");
        int n=KB.nextInt();
        Bank  C[]=new Bank[n];
        for(int i=0;i<C.length;i++)
        {
            C[i]=new Bank();
            C[i].openAccount();
        }

        //start cicle
        int ch;
        do
        {
            System.out.println("Main menu\n        1.Display all\n        2.Search by account\n        3.Deposit\n        4.Withdrawal\n        5.Exit");
            System.out.println("Your choice :");
            ch=KB.nextInt();
            switch(ch)
            {
                case 1:
                    for(int i=0;i<C.length;i++)
                    {
                        C[i].showAccount();
                    }
                    break;

                case 2:
                    System.out.print("Enter account login you want to search : ");
                    String acn=KB.next();
                    boolean found=false;
                    for(int i=0;i<C.length;i++)
                    {
                        found=C[i].search(acn);
                        if(found)
                        {
                            break;
                        }
                    }
                    if(!found)
                    {
                        System.out.println("Search failed. Account not exist.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account login for search : ");
                    acn=KB.next();
                    found=false;
                    for(int i=0;i<C.length;i++)
                    {
                        found=C[i].search(acn);
                        if(found)
                        {
                            C[i].deposit();
                            break;
                        }
                    }
                    if(!found)
                    {
                        System.out.println("Search failed.Account not exist..");
                    }
                    break;

                case 4:
                    System.out.print("Enter account login : ");
                    acn=KB.next();
                    found=false;
                    for(int i=0;i<C.length;i++)
                    {
                        found=C[i].search(acn);
                        if(found)
                        {
                            C[i].withdrawal();
                            break;
                        }
                    }
                    if(!found)
                    {
                        System.out.println("Search failed.Account not exist.");
                    }
                    break;

                case 5:
                    System.out.println("Exit");
                    break;
            }
        }
        while(ch!=5);
    }
}