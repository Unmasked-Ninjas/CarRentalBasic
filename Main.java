package CarRental;

import java.util.LinkedList;
import java.util.Scanner;

class Car{
	private String brand;
	private String carId;
	private String model;
	private boolean isAvilable;
	private double price;
	
	public Car(String brand, String carId, String model, boolean isavilable, double price) {
		this.brand = brand;
		this.carId = carId;
		this.model = model;
		this.isAvilable = isavilable;
		this.price = price;
	}
	
	public String getbrand() {
		return brand;
	}
	
	public String getcarId() {
		return carId;
	}
	
	public String getmodel() {
		return model;
		
	}
	public boolean getisAvilable() {
		return isAvilable;
	}
	public void rent() {
		isAvilable = false;
	}
	public void returned() {
		isAvilable = true;
	}
	
	
	public double getprice() {
		return price;
	}
	
	public int calculatedprice(int totdays) {
		return (int) (totdays*price);
	}

	@Override
	public String toString() {
		return "Brand=" + brand + ", carId=" + carId + ", Model=" + model + ", isAvilable=" + isAvilable
				+ ", price=" + price + "";
	}
	
	
}

class Customer{
	private String fullname;
	private String CustomerID;
	
	public Customer(String fullname,String CustomerID) {
		this.fullname = fullname;
		this.CustomerID = CustomerID;
	}
	
	public String getfullname() {
		return fullname;
	}
	
	public String getCustomerID() {
		return CustomerID;
	}
	
}

class rental{
	private Car car;
	private Customer customer;
	private int days;
	
	
	public rental(Car car, Customer customer, int days) {
		this.car = car;
		this.customer =customer;
		this.days = days;
	}
	
	public Car getcar() {
		return car;
	}
	public Customer getcustomer() {
		return customer;
	}
	public int getdays() {
		return days;
	}
	
}

class rentalSystem{
	private LinkedList<Car> cars;
	private LinkedList<Customer> customers;
	private LinkedList<rental> rentals;
	
	public rentalSystem() {
		this.cars = new LinkedList<>();
		this.customers = new LinkedList<>();
		this.rentals = new LinkedList<>();
	}
	public void addCar(Car car) {
//		System.out.println(car);
		cars.add(car);
	}
//	public void printCars() {
//		for(Car car : cars) {
//			System.out.println(car.toString());
//		}
//	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void rentcar(Car cars, Customer customer, int days) {
		
		if(cars.getisAvilable()) {
			cars.rent();
			rentals.add(new rental(cars,customer,days));
		}
		else {
			System.out.println("Not available for rent");
		}
		
	}
	
	public void returnCar(Car car) {
		car.returned();
		rental rentalReturned = null;
		for(rental i: rentals) {
			if(i.getcar().equals(car)) {
				rentalReturned = i;
				break;
			}
		if(rentalReturned != null) {
			rentals.remove(rentalReturned);
		}
			
		}
	}
	
	public void menu() {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Welcome to puntu rental systems\n");
			System.out.println("1. Rent a car");
			System.out.println("2. Return a car");
			System.out.println("3 Exit \n");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice == 1) {
			System.out.println("Enter your FUllNAME");
			String Cname = sc.nextLine();
			
			if(Cname.length()!=0) {
			System.out.println("\nList of cars");
			System.out.println("Available cars:\n");
			for(Car car : cars) {
				
				if(car.getisAvilable()) {
					
					System.out.println(car.getcarId()+ " - "+ car.getbrand()+" "+car.getmodel()+"\n");
				}
			}
		}
		
		else {
			System.out.println("Enter a valid name please");
		}
		
		System.out.println("Enter the CarId that you want to Rent");
		String rentId = sc.nextLine();
		
		System.out.println("Enter the no of days that you want to Rent");
		int Rdays = sc.nextInt();
		
		Customer newcustomer = new Customer(Cname,"CUS"+(customers.size()+1));
		addCustomer(newcustomer);
		
		Car SelectedCar = null;
		for(Car car:cars) {
			if(car.getcarId().equals(rentId) && car.getisAvilable()) {
				SelectedCar = car;
				break;
			}
		}
		if(SelectedCar!=null) {
			int totalPrice = SelectedCar.calculatedprice(Rdays);
			System.out.println("\n== Rental Bill ==");
			System.out.println("Customer ID: "+newcustomer.getfullname());
			System.out.println("Customer Name: "+newcustomer.getCustomerID());
			System.out.println("Car: "+SelectedCar.getbrand()+" "+SelectedCar.getmodel());
			System.out.println("Rental Days: "+ Rdays);
			System.out.println("Total Paying amount $"+totalPrice);
		}
		System.out.println("Confirm Rental (Y/N)\n");
		Scanner sc1 = new Scanner(System.in);
		String Uinp = sc1.nextLine();
		sc.nextLine();
		System.out.println(Uinp);
		if(Uinp.equalsIgnoreCase("Y")) {
			rentcar(SelectedCar,newcustomer,Rdays);
			System.out.println(newcustomer.getfullname());
			System.out.println("Car rented carefully!!! Happy driving");
//			menu();
		}
		else {
			System.out.println("Rental Cancled\n");
//			menu();
		}
		
	}
		else if(choice == 2) {
			Scanner s2 = new Scanner(System.in);
			System.out.println("Enter the Car ID that you want to return");
			String returnId = s2.next();
			Car returnCar = null;
			for(Car car:cars) {
				if(car.getcarId().equals(returnId) && !car.getisAvilable()) {
					returnCar = car;
					break;
				}
				
			}
			if(returnCar!= null) {
				Customer customer = null;
				for(rental r: rentals) {
					if(r.getcar() == returnCar) {
						customer = r.getcustomer();
						
						break;
					}
				}
				if(customer!=null) {
					returnCar(returnCar);
					System.out.println("Successfully returned by "+customer.getfullname());
				}
				else {
                    System.out.println("Car was not rented or rental information is missing.");
			
				}
			}
			 else {
            System.out.println("Invalid car ID or car is not rented.");
			}
			}
		
		else if(choice == 3) {
			break;
		}
		else {
			System.out.println("invalid input");
		}
		}
		
	}
	
	}


public class Main {

	public static void main(String[] args) {
		rentalSystem rs= new rentalSystem();
		Car car1 = new Car("Ferrari","C-981","spyder",true,1400);
		Car car2 = new Car("Lambo", "C-907","hurrican",true,1000);
		Car car3 = new Car("Buggati","C-900","Chiron",true,5000);
		rs.addCar(car1);
		rs.addCar(car2);
		rs.addCar(car3);
		rs.menu();
	
		
	}

}
