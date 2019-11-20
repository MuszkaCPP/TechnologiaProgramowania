package eu.jpereira.trainings.designpatterns.structural.facade;


import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.BookDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerNotificationService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.OrderingService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.WharehouseService;

public class DefaultBookstoreFacade implements BookstoreFacade{
	protected CustomerDBService _customerService;
	protected BookDBService _bookService;
	protected OrderingService _orderingService;
	protected CustomerNotificationService _customerNotificationService;
	protected WharehouseService _warehouseService;
	
	public void setCustomerService(CustomerDBService customerService)
	{
		_customerService = customerService;
	};
	public void setBookDBService(BookDBService bookService)
	{
		_bookService = bookService;
	};
	public void setOrderingService(OrderingService orderingService)
	{
		_orderingService = orderingService;
	};
	public void setCustomerNotificationService(CustomerNotificationService customerNotificationService)
	{
		_customerNotificationService = customerNotificationService;
	};
	public void setWharehouseService(WharehouseService warehouseService)
	{
		_warehouseService = warehouseService;
	};
	
	@Override
	public void placeOrder(String customerId, String isbn) {
		Customer _customer = _customerService.findCustomerById(customerId);
		Book _book = _bookService.findBookByISBN(isbn);
		Order _order = _orderingService.createOrder(_customer, _bookService.findBookByISBN(isbn));
		
		DispatchReceipt _dispatchReceipt = _warehouseService.dispatch(_order);
		_customerNotificationService.notifyClient(_dispatchReceipt);
		
	}
}
