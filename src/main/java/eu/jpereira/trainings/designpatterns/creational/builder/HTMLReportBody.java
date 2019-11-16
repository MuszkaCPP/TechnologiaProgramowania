package eu.jpereira.trainings.designpatterns.creational.builder;

import java.util.Iterator;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

public class HTMLReportBody implements ReportBody {

	private StringBuffer delegate = new StringBuffer();
	private Iterator<SoldItem> it;
	
	public HTMLReportBody(SaleEntry saleEntry) {
		generateReportBody(saleEntry);
	}
	
	@Override
	public Object getAsString() {
		return this.delegate .toString();
	}
	@Override
	public void putContent(Object content) {
		this.delegate.append(content);
		
	}
	@Override
	public void generateReportBody(SaleEntry saleEntry) {
		putContent("<span class=\"customerName\">");
		putContent(saleEntry.getCustomer().getName());
		putContent("</span><span class=\"customerPhone\">");
		putContent(saleEntry.getCustomer().getPhone());
		putContent("</span>");
		putContent("<items>");
		
		it = saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem soldEntry= it.next();
			putContent("<item><name>");
			putContent(soldEntry.getName());
			putContent("</name><quantity>");
			putContent(soldEntry.getQuantity());
			putContent("</quantity><price>");
			putContent(soldEntry.getUnitPrice());
			putContent("</price></item>");
		}
		putContent("</items>");
	}

}
