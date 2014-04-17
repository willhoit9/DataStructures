
package classes;


public class Customer {
    
    private int customerID;
    private int arrivalTime;

    public Customer() {
    }


    public Customer(int customerID, int arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    
    
}
