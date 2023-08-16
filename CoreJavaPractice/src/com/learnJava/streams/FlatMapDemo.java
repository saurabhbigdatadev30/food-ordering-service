package com.learnJava.streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://mkyong.com/java8/java-8-flatmap-example/
public class FlatMapDemo 
{
	
    
    public static void main(String[] args)
    {
    	FlatMapDemo obj = new FlatMapDemo();
    	
    	List<Order> orders = obj.findAll();
    	System.out.println("*** Orders *** ");
    	System.out.println(orders);
    	
    	
    	Predicate<Order> orderFiltered = ((order) -> 
    	{
    		return order.getId().equals(1);
    	});
    	
    	
    	
    	getOrderLineItems(orders, orderFiltered);

    	
    	
    	Stream<List<LineItem>> listOf_LineItemsStreamForOrders = orders   // List<Orders>
    												.stream()   // Stream<Orders>
    												  // returns the LineItems  Order wise ,  (for each order)
    												.map(order -> order.getLineItems());  // Stream<List<LineItems>
    	
    	System.out.println("List of Line Items for  all the Orders (Map Output) ");
    	System.out.println(listOf_LineItemsStreamForOrders.collect(Collectors.toList()));
    	
    	
    	/*
    	 We are getting list of [LineItems]  Order wise (for each order)
    	 
    	[
          orderID = 1     	     [
              	                    LineItem [id=1, item=apple, qty=1, price=1.20, total=1.20], 
   			        				LineItem [id=2, item=orange, qty=2, price=0.50, total=1.00]
   			        				], 
   		
   		orderID = 2 	     
  			      [
  			       LineItem [id=3, item=monitor BenQ, qty=5, price=99.00, total=495.00], 
  				   LineItem [id=4, item=monitor LG, qty=10, price=120.00, total=1200.00]
  				   ], 
  				   
  		
  		orderID = 3 		 
  				[
  				      LineItem [id=5, item=One Plus 8T, qty=3, price=499.00, total=1497.00], 
  				      LineItem [id=6, item=One Plus 9T, qty=4, price=799.00, total=1557.00]
  				 ]
]
    	 
    	 */
    	
     
    	
    	// Understanding FlatMap 
    	
    	
    	Stream<LineItem> listOf_LineItemsStreamForOrdersFlattenX =  orders.
        								   stream().
        								   // returns the LineItems for all the Orders
        								   map(order -> order.getLineItems()).
        								   flatMap(List::stream);
    	
		 // OR
		  Stream<LineItem> listOf_LineItemsStreamForOrdersFlatten =    orders
				  							  .stream()
				  							  // returns the LineItems for all the Orders
				  							 .flatMap(order ->order.getLineItems() .stream());
		 
    	
    	System.out.println("*** Flat Map Output *** ");
    	System.out.println(listOf_LineItemsStreamForOrdersFlatten.collect(Collectors.toList()));
        
    	
    	/*
    	 Getting the entire list of OrderItems flatten, not order wise
    	 [
 			[LineItem [id=1, item=apple, qty=1, price=1.20, total=1.20], 
 			LineItem [id=2, item=orange, qty=2, price=0.50, total=1.00]], 
 			[LineItem [id=3, item=monitor BenQ, qty=5, price=99.00, total=495.00], 
 			LineItem [id=4, item=monitor LG, qty=10, price=120.00, total=1200.00]], 
 			[LineItem [id=5, item=One Plus 8T, qty=3, price=499.00, total=1497.00],
 			LineItem [id=6, item=One Plus 9T, qty=4, price=799.00, total=1557.00]]
    	 */
    	
    	
    
    	// ************************************************************************************************************
    	
    	// TO DO :- Sum the LineItems' total amount for All Orders
        BigDecimal sumOfLineItems = orders.stream()
                .flatMap(order -> order.getLineItems().stream())    //  Stream<LineItem>
                .map(line -> line.getTotal())                       //  Stream<BigDecimal> => [ 1.20 , 1.00 , 495.00 , 1200.00 , 1497.00 , 1557.00 ]
                .reduce(BigDecimal.ZERO, BigDecimal::add);          //  reduce to sum all

        
        
        
        // sum the order's total amount
        BigDecimal sumOfOrder = orders.stream()
                .map(order -> order.getTotal())                     //  Stream<BigDecimal>
                .reduce(BigDecimal.ZERO, BigDecimal::add);          //  reduce to sum all

        
        
        System.out.println(sumOfLineItems);                         // 4752
        System.out.println(sumOfOrder);                             // 3194.20

        if (!sumOfOrder.equals(sumOfLineItems)) {
            System.out.println("The sumOfOrder is not equals to sumOfLineItems!");
        }

    	
	}

	private static void getOrderLineItems(List<Order> orders, Predicate<Order> orderFiltered) {
		Stream<List<LineItem>> listOf_LineItemsStreamForOrderId_1 = orders
    										 .stream()
    										 .filter(orderFiltered)
    										 .map(order -> order.getLineItems());
    	System.out.println("List of Line Items for OrderId = 1 ");
    	System.out.println(listOf_LineItemsStreamForOrderId_1.collect(Collectors.toList()));
	}
	
	
	
    // create dummy records
    private  List<Order> findAll() 
    {

    	// Order[1] contains item1 , item2
        LineItem item1 = new LineItem(1, "apple",  1, new BigDecimal("1.20"), new BigDecimal("1.20"));
        LineItem item2 = new LineItem(2, "orange", 2, new BigDecimal(".50"), new BigDecimal("1.00"));
        Order order1 = new Order(1, "A0000001", Arrays.asList(item1, item2), new BigDecimal("2.20"));

        
     // Order[2] contains item3 , item4
        LineItem item3 = new LineItem(3, "monitor BenQ", 5, new BigDecimal("99.00"), new BigDecimal("495.00"));
        LineItem item4 = new LineItem(4, "monitor LG", 10, new BigDecimal("120.00"), new BigDecimal("1200.00"));
        Order order2 = new Order(2, "A0000002", Arrays.asList(item3, item4), new BigDecimal("1695.00"));

     // Order[4] contains item4 , item5
        LineItem item5 = new LineItem(5, "One Plus 8T", 3, new BigDecimal("499.00"), new BigDecimal("1497.00"));
        LineItem item6 = new LineItem(6, "One Plus 9T", 4, new BigDecimal("799.00"), new BigDecimal("1557.00"));
        Order order3 = new Order(3, "A0000003", Arrays.asList(item5,item6), new BigDecimal("1497.00"));

        return Arrays.asList(order1, order2, order3);

    }
	
}
	
	
	  class Order 
	 {

		    private Integer id;
		    private String invoice;
		    private List<LineItem> lineItems;
		    private BigDecimal total;
		    
			public Order(Integer id, String invoice, List<LineItem> lineItems, BigDecimal total) {
				super();
				this.id = id;
				this.invoice = invoice;
				this.lineItems = lineItems;
				this.total = total;
			}
			
			
			public Integer getId() 
			{
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public String getInvoice() {
				return invoice;
			}
			public void setInvoice(String invoice) {
				this.invoice = invoice;
			}
			public List<LineItem> getLineItems() {
				return lineItems;
			}
			public void setLineItems(List<LineItem> lineItems) {
				this.lineItems = lineItems;
			}
			public BigDecimal getTotal() {
				return total;
			}
			public void setTotal(BigDecimal total) {
				this.total = total;
			}


			@Override
			public String toString() {
				return "Order [id=" + id + ", invoice=" + invoice + ", lineItems=" + lineItems + ", total=" + total
						+ "]";
			}

		    
		    
		}
	 
	 
	
	class LineItem 
	 {
         
		    private Integer id;
		    private String item;
		    private Integer qty;
		    private BigDecimal price;
		    private BigDecimal total;
		    
			@Override
			public String toString() {
				return "LineItem [id=" + id + ", item=" + item + ", qty=" + qty + ", price=" + price + ", total="
						+ total + "]";
			}
			public LineItem(Integer id, String item, Integer qty, BigDecimal price, BigDecimal total) {
				super();
				this.id = id;
				this.item = item;
				this.qty = qty;
				this.price = price;
				this.total = total;
			}
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public String getItem() {
				return item;
			}
			public void setItem(String item) {
				this.item = item;
			}
			public Integer getQty() {
				return qty;
			}
			public void setQty(Integer qty) {
				this.qty = qty;
			}
			public BigDecimal getPrice() {
				return price;
			}
			public void setPrice(BigDecimal price) {
				this.price = price;
			}
			public BigDecimal getTotal() {
				return total;
			}
			public void setTotal(BigDecimal total) {
				this.total = total;
			}

		    // getter, setters, constructor
		    
		    
		}


