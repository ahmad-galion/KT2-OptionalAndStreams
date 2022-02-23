
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static java.util.Optional.of;

class Customer {
    private String id;
    private String name;
    private String mobile;
    private int salary;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }



    public Customer(String id, String name, String mobile, int salary) {
        System.out.println("Customer Created Fully " +Thread.currentThread());
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.salary = salary;

    }

    public Customer() {
        System.out.println("Customer Created Empty " +Thread.currentThread());
    }
    public String printCustomer() {
        System.out.println ("ID:"+id+" name:"+name+" mobile:"+mobile+" salary:"+salary);
        return ("ID:"+id+" name:"+name+" mobile:"+mobile);
    }
    public  boolean isValid() {
        return null!=id;
    }
    public  boolean isNotValid() {
        return !isValid();
    }
    public  void execute()  {
        System.out.println("execute called for method customer "+ id+" " +Thread.currentThread());

    }
    public void update() {
        this.id = "Anonymize";
        this.name = "Anonymize";
        this.mobile = "Anonymize";
        this.salary = 0;
    }


}
public class MainClass {



    public static void main(String[] args) throws Exception {

            optional();
            streams();

    }


    private static void optional() throws Exception {
        //define Optional Empty
        Optional<Customer>  customerEmpty = Optional.empty();

        //define Optional of
        Optional<Customer> customer= of(new Customer("test","test","test",0));

        //check if present call print function
        customer.ifPresent(Customer::printCustomer);

        //check if present call print function
        customerEmpty.ifPresentOrElse(Customer::printCustomer,()-> System.out.println("customer not found"));

        //if Exist in optional get it or else create new Customer
        Customer newCustomer= customer.orElse(new Customer());
        newCustomer.printCustomer();

        //if optionalEmpty throw Exception
       // customerEmpty.orElseThrow(() -> new Exception("customer not found "));
    }

    private static void streams() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("1","ahmad","0524044665",8500));
        customers.add(new Customer("2","jone","123456789",15000));
        customers.add(new Customer("3","ahmad","22366566",1500));
        customers.add(new Customer());

        //Foreach Loop in streams
        customers.stream().forEach(Customer::printCustomer);
        //Filter Function for Valid Customer
        customers.stream().filter(Customer::isValid).forEach(Customer::execute);
        //Filter Function for non-Valid Customer
        customers.stream().filter(Customer::isNotValid).forEach(Customer::update);



        //how to get average salary for customers with name ahmad
        // old way
        int sum=0;
        int numOfCustomers=0;
        for(Customer c:customers) {
            if("ahmad".equals(c.getName())) {
                sum+=c.getSalary();
                numOfCustomers+=1;
            }
        }
         System.out.println("average old way:"+sum/numOfCustomers);

        //modern way
        OptionalDouble ahmadSalary= customers.stream().filter(s -> "ahmad".equals(s.getName())).map(Customer::getSalary).mapToInt(Integer::valueOf).average();
        System.out.println("average modern way:"+ahmadSalary.getAsDouble());

    }


}
