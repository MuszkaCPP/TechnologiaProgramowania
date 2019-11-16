/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.creational.builder;

import java.util.Iterator;

import eu.jpereira.trainings.designpatterns.creational.builder.json.JSONReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;
import eu.jpereira.trainings.designpatterns.creational.builder.xml.XMLReportBody;

/**
 * @author jpereira
 * 
 */
public class ReportAssembler {
	
	private SaleEntry saleEntry;
	/**
	 * @param reportDate
	 */
	public void setSaleEntry(SaleEntry saleEntry) {
		this.saleEntry = saleEntry;
	}

	
	public Report getReport(String type) {
		Report report = new Report();

		if (type.equals("JSON")) {
			report.setReportBody(new JSONReportBody(saleEntry));
		} else if (type.equals("XML")) {
			report.setReportBody(new XMLReportBody(saleEntry));
		} else if (type.equals("HTML")) {
			report.setReportBody(new HTMLReportBody(saleEntry));
		}

		return report;
	}
	
	/**
	 * @param type
	 * @return
	 */
	/*public Report getReport(String type) {
		Report report = new Report();

		// Algorithms to build the body objects are different
		if (type.equals("JSON")) {

			JSONReportBody reportBody = new JSONReportBody();
			//Add customer info
			reportBody.putContent("sale:{customer:{");
			reportBody.putContent("name:\"");
			reportBody.putContent(saleEntry.getCustomer().getName());
			reportBody.putContent("\",phone:\"");
			reportBody.putContent(saleEntry.getCustomer().getPhone());
			reportBody.putContent("\"}");
			//add array of items
			reportBody.putContent(",items:[");
			reportBody.setSoldItemList(saleEntry.getSoldItems().iterator());
			while ( reportBody.getSoldItemList().hasNext() ) {
				SoldItem item = reportBody.getSoldItemList().next();
				reportBody.putContent("{name:\"");
				reportBody.putContent(item.getName());
				reportBody.putContent("\",quantity:");
				reportBody.putContent(String.valueOf(item.getQuantity()));
				reportBody.putContent(",price:");
				reportBody.putContent(String.valueOf(item.getUnitPrice()));
				reportBody.putContent("}");
				if (reportBody.getSoldItemList().hasNext() ) {
					reportBody.putContent(",");
				}
				
			}
			reportBody.putContent("]}");
			report.setReportBody(reportBody);

		} else if (type.equals("XML")) {
			XMLReportBody reportBody = new XMLReportBody();
			reportBody.putContent("<sale><customer><name>");
			reportBody.putContent(this.saleEntry.getCustomer().getName());
			reportBody.putContent("</name><phone>");
			reportBody.putContent(this.saleEntry.getCustomer().getPhone());
			reportBody.putContent("</phone></customer>");
			
			reportBody.putContent("<items>");
			
			reportBody.setSoldItemList(saleEntry.getSoldItems().iterator());
			while ( reportBody.getSoldItemList().hasNext() ) {
				SoldItem soldEntry= reportBody.getSoldItemList().next();
				reportBody.putContent("<item><name>");
				reportBody.putContent(soldEntry.getName());
				reportBody.putContent("</name><quantity>");
				reportBody.putContent(soldEntry.getQuantity());
				reportBody.putContent("</quantity><price>");
				reportBody.putContent(soldEntry.getUnitPrice());
				reportBody.putContent("</price></item>");
			}
			reportBody.putContent("</items></sale>");
			report.setReportBody(reportBody);
		} else if (type.equals("HTML")) {
			
			HTMLReportBody reportBody = new HTMLReportBody();
			reportBody.putContent("<span class=\"customerName\">");
			reportBody.putContent(this.saleEntry.getCustomer().getName());
			reportBody.putContent("</span><span class=\"customerPhone\">");
			reportBody.putContent(this.saleEntry.getCustomer().getPhone());
			reportBody.putContent("</span>");
			
			reportBody.putContent("<items>");
			
			reportBody.setSoldItemList(saleEntry.getSoldItems().iterator());
			while (  reportBody.getSoldItemList().hasNext() ) {
				SoldItem soldEntry=  reportBody.getSoldItemList().next();
				reportBody.putContent("<item><name>");
				reportBody.putContent(soldEntry.getName());
				reportBody.putContent("</name><quantity>");
				reportBody.putContent(soldEntry.getQuantity());
				reportBody.putContent("</quantity><price>");
				reportBody.putContent(soldEntry.getUnitPrice());
				reportBody.putContent("</price></item>");
			}
			reportBody.putContent("</items>");
			report.setReportBody(reportBody);
		}

		return report;
	}*/

}
